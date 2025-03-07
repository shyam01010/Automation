package requestBuilder;

import base.BaseTest;
import endPoints.EndPoints;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Product_Response extends BaseTest {

    public  static Response response;
    public void get_ProductResponseDetails() {

        getRequest();
        long startTime = System.currentTimeMillis();
         response = request.get(EndPoints.Get_Product);
        long endTime = System.currentTimeMillis();

        if (response.getStatusCode() == EndPoints.statusCode) {
            System.out.println("Response Status Code: 200");
        } else {
            System.out.println("Error Status Code: " + response.getStatusCode());
            return;
        }

        System.out.println("Response time: " + (endTime - startTime) + "ms");

        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JSONArray productsArray = jsonResponse.getJSONArray("products");
        List<JSONObject> allProducts = new ArrayList<>();

        for (int i = 0; i < productsArray.length(); i++) {
            allProducts.add(productsArray.getJSONObject(i));
        }

        System.out.println("\nAvailable Products:");
        for (JSONObject product : allProducts) {
            System.out.println("ID: " + product.getInt("id") + ", Category: " + product.optString("category", "N/A") + ", Price: " + product.optDouble("price", 0.0));
        }

        // Extract and filter products
        List<JSONObject> groceries = allProducts.stream()
                .filter(p -> p.has("category") && p.getString("category").equals("groceries") && p.has("price") && p.getDouble("price") <= 5)
                .limit(3)
                .collect(Collectors.toList());

        List<JSONObject> beautyProducts = allProducts.stream()
                .filter(p -> p.has("category") && p.getString("category").equals("beauty") && p.has("price") && p.getDouble("price") >= 5 && p.getDouble("price") <= 14)
                .limit(3)
                .collect(Collectors.toList());

        List<JSONObject> selectedProducts = new ArrayList<>();
        selectedProducts.addAll(groceries);
        selectedProducts.addAll(beautyProducts);

        if (selectedProducts.isEmpty()) {
            System.err.println("No products matched the filtering criteria.");
            return;
        }

        double totalPrice = selectedProducts.stream().mapToDouble(p -> p.getDouble("price")).sum();
        double averagePrice = selectedProducts.isEmpty() ? 0 : totalPrice / selectedProducts.size();
        double avgGroceriesPrice = groceries.isEmpty() ? 0 : groceries.stream().mapToDouble(p -> p.getDouble("price")).average().orElse(0);
        double avgBeautyPrice = beautyProducts.isEmpty() ? 0 : beautyProducts.stream().mapToDouble(p -> p.getDouble("price")).average().orElse(0);

        System.out.println("\nSelected Products:");
        for (JSONObject product : selectedProducts) {
            System.out.println("ID: " + product.getInt("id") + ", Title: " + product.getString("title") +
                    ", Category: " + product.getString("category") + ", Price: $" + product.getDouble("price"));
        }

        System.out.println("\nTotal price: $" + totalPrice);
        System.out.println("Overall average price: $" + averagePrice);
        System.out.println("Average groceries price: $" + avgGroceriesPrice);
        System.out.println("Average beauty price: $" + avgBeautyPrice);

    }
    }
