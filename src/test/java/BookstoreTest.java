import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
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
    @Test
    public void postBookStore(){
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = "https://bookstore.toolsqa.com/Account/v1/";
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", "meg1")
                .put("password", "Aa123123@");

        Response response = given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("User")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body " + responseBody);

        Assert.assertEquals(statusCode, 201, "Status code is not 201");
        Assert.assertTrue(responseBody.contains("userID"), "ResponseBody does not contain userId");
    }
}
