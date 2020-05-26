package com.bestbuy.crudtest;


import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.TestBase;
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
public class StoresCRUDTest extends TestBase {

    static String name = "New store";
    static String type = "Box";
    static String address = "123 Street Avenue";
    static String address2 ="Harrow";
    static String city ="London";
    static String state = "UK";
    static String zip = "UB10 8BD";
    static double lat = 44.969658;
    static double lng =  -93.449539;
    static String hours = "Mon: 10-9, Tue: 8-6, Wed: 10-9, Thurs: 8-6, Fri: 8-6, Sat: 8-6" ;

    @Steps
    StoresSteps storesSteps;

    @Title("This test will get all stores information by ID")
    @Test
    public void test001() {
        storesSteps.getAllStoresById().statusCode(200);

    }

    @Title("Creating a new store")
    @Test
    public void test002() {
        storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours);


    }

    @Title("This test will get the store information by ID")
    @Test

    public void test003() {
        storesSteps.getStoreById().statusCode(200);

    }

    @Title("This test will update the store information and verify the updated information")
    @Test

    public void test004() {
        name = name + "_Changed";
        type = "big box";
        city = city + "_added";
        storesSteps.updateStore(name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);
        storesSteps.getStoreById().body("name", equalTo(name));

    }

    @Title("This test will delete the store information and verify the product is deleted ")
    @Test

    public void test005() {
        storesSteps.deleteStore().statusCode(200);
        storesSteps.getStoreById().statusCode(404);
    }
}
