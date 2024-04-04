package tqs.lab7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class JsonPlaceholderAPITest 
{
    @Test
    public void whenGetAllTodos_thenOK(){
        when()
            .get("https://jsonplaceholder.typicode.com/todos")
        .then()
            .statusCode(200);
    }

    @Test
    public void whenGetTodoByNum_thenTitle(){
        when()
            .get("https://jsonplaceholder.typicode.com/todos/4")
        .then()
            .statusCode(200)
            .body("title", is("et porro tempora"));
    }

    @Test
    public void whenGetAllTodos_thenId198And199(){
        when()
            .get("https://jsonplaceholder.typicode.com/todos")
        .then()
            .statusCode(200)
            .body("id", hasItems(198, 199));
    }

    @Test
    public void whenGetAllTodos_thenResponseTimeLessThan2Sec(){
        when()
            .get("https://jsonplaceholder.typicode.com/todos")
        .then()
            .statusCode(200)
            .time(lessThan(2000L));
    }
}
