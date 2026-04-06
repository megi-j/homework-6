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

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "message");

        String author = response.jsonPath().getString("books[0].author");
        String publisher = response.jsonPath().getString("books[0].publisher");

        Assert.assertNotNull(author, "Author is null");
        Assert.assertNotNull(publisher, "Publisher is null");

        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);

    }
}
