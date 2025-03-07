package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

    public static RequestSpecification request;

    public static String url_Products = "https://dummyjson.com";


    public static RequestSpecification getRequest() {

        RestAssured.baseURI = url_Products;
        request = RestAssured.given();

        return request;
    }


}
