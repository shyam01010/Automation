package test.products;

import org.testng.annotations.Test;
import requestBuilder.Product_Response;

public class ProductTest extends Product_Response {


    @Test
    public void get_ProductDetails() {

        get_ProductResponseDetails();

    }
}


