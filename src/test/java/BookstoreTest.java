import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BookstoreTest {
    @Test
    public void getBookStore(){
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1/";
        Response response = RestAssured
                .given()
                .header("accept", "application/json")
                .when()
                .get("Books")
                .then()
                .log().all()
                .extract().response();

    }
}
