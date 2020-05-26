package com.bestbuy.crudtest;

import com.bestbuy.steps.ProductsSteps;
import com.bestbuy.testbase.TestBase;

import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.equalTo;


/**
 * Created by Ankita
 */
@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCRUDTest extends TestBase {


    static String name = "Apple new mobile"+ TestUtils.getRandomValue();
    static String type = "Hard Good"+ TestUtils.getRandomValue();
    static String upc = "123abc"+ TestUtils.getRandomValue();
    static double price = 90.99;
    static String description = "This is a placeholder request for creating a new product.";
    static String model = "NP12345"+ TestUtils.getRandomValue();


    @Steps
    ProductsSteps productsSteps;

    @Title("This test will get the  all product information by ID")
    @Test
    public void test001() {
        productsSteps.getAllProductById().statusCode(200);

    }

    @Title("Creating a new product")
    @Test
    public void test002() {
        productsSteps.createNewProduct(name, type, upc, price, description, model);


    }

    @Title("This test will get the product information by ID")
    @Test

    public void test003() {
        productsSteps.getProductById().statusCode(200);

    }

    @Title("This test will update the product information and verify the updated information")
    @Test

    public void test004() {
        name = name + "_Changed";
        price = 89.99;
        upc = upc + "_added";
        productsSteps.updateProduct(name, type, upc, price, description, model).statusCode(200);
        productsSteps.getProductById().body("name", equalTo(name));

    }

    @Title("This test will delete the product information and verify the product is deleted ")
    @Test

    public void test005() {
        productsSteps.deleteProduct().statusCode(200);
        productsSteps.getProductById().statusCode(404);
    }

}
