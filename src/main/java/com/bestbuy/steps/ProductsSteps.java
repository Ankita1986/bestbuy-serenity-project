package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.pojo.ProductsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * Created by Ankita
 */
public class ProductsSteps {

    static long productId;

    @Step("Getting the information of the all product by id:{0}")
    public ValidatableResponse getAllProductById() {
        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();


    }



    @Step("Creating a new product with name: {0}, type: {1}, upc: {2}, price: {3}, description: {4}, model: {5}")
    public void createNewProduct
            (String name, String type, String upc, double price, String description, String model) {

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setUpc(upc);
        productsPojo.setPrice(price);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);

        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(productsPojo)
                .post(EndPoints.POST_A_PRODUCTS)
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();
        productId = response.body().jsonPath().getLong("id");
    }






    @Step("Getting the information of the product created by id:{0}")
    public ValidatableResponse getProductById() {
        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID + "/" + productId)
                .then().log().all();


    }

    @Step("Updating Product information with productId: {0}, name : {1} , type : {2} , upc : {3} , price : {4} , description : {5} , model : {6} ")

    public ValidatableResponse updateProduct
            (String name, String type, String upc, double price, String description, String model) {

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setUpc(upc);
        productsPojo.setPrice(price);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);


        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(productsPojo)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID + "/"+ productId)
                .then().log().all();


    }

    @Step("Deleting the Product information with productId : {0} ")

    public ValidatableResponse deleteProduct() {
        return SerenityRest.rest()
                .given()
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID+ "/"+ productId)
                .then().log().all();


    }
}




