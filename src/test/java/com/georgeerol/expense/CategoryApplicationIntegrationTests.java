package com.georgeerol.expense;

import com.georgeerol.expense.model.Category;
import com.georgeerol.expense.model.Expense;
import com.georgeerol.expense.model.User;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExpenseApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryApplicationIntegrationTests {


    @LocalServerPort
    private int port;

    @Test
    void whenGetAllCategories_thenOK() {
        String api = "http://localhost:" + port + "/api/categories";
        Response response = given().port(port).get(api);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Ignore
    public void whenDelete_ThenOK() {
        String api = "http://localhost:" + port + "/api/category/2";
        Response response = given().port(port).delete(api);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    void whenCreateNewExpense_ThenCreate() {
        String api = "http://localhost:" + port + "/api/category";
        Category category = createRandomCategory();
        Response response = given().
                port(port).
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(category).
                post(api);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }


    private Category createRandomCategory() {

        Category category = new Category();
        category.setId(new Long(1));
        category.setName("Travel");
        return category;
    }

}
