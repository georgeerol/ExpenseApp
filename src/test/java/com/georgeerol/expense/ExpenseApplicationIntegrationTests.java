package com.georgeerol.expense;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;


import static io.restassured.RestAssured.given;
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

}
