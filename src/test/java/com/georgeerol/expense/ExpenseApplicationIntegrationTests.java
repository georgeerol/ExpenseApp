package com.georgeerol.expense;

import com.georgeerol.expense.model.Category;
import com.georgeerol.expense.model.Expense;
import com.georgeerol.expense.model.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;


import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExpenseApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExpenseApplicationIntegrationTests {


    @LocalServerPort
    private int port;

    @Test
    public void whenGetAllExpenses_thenOK() {
        String api = "http://localhost:" + port + "/api/expenses";
        Response response = given().port(port).get(api);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenDelete_ThenOK() {
        String api = "http://localhost:" + port + "/api/expenses/100";
        Response response = given().port(port).delete(api);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    public void whenCreateNewExpense_ThenCreate() {
        String api = "http://localhost:" + port + "/api/expenses";
        Expense expense = createRandomExpense();
        Response response = given().
                port(port).
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(expense).
                post(api);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }


    private Expense createRandomExpense() {
        Expense expense = new Expense();
        Category category = new Category();
        category.setId(new Long(1));
        category.setName("Travel");
        expense.setCategory(category);
        User user = new User();
        user.setId(new Long(1));
        expense.setUser(user);
        expense.setDescription(randomAlphabetic(10));
        expense.setExpenseDate(Instant.now());
        expense.setId(new Long(304));
        return expense;
    }

}
