package com.bestbuy.testsuite;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.pojo.StoresPojo;
import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
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
public class StoreTestWithTags extends TestBase {

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

    @WithTags({
            @WithTag("Features:POSITIVE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Creating a new Store")
    @Test
    public void test001() {
        storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours);

    }

    @WithTags({
            @WithTag("Features:SANITY"),
            @WithTag("Features:REGRESSION")
    })

    @Title("Getting a created Store")
    @Test
    public void test002() {
        storesSteps.getStoreById().statusCode(200);
    }

    @WithTags({
            @WithTag("Features:SMOKE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Getting the all store")
    @Test
    public void test003() {
        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then();
    }

    @WithTags({
            @WithTag("Features:POSITIVE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Updating Store information with  name:{0}, type:{1}. address:{2}, address2:{3}, city:{4}, state:{5}, zip:{6}, lat:{7}, lng:{8}, hours:{9}")
    @Test
    public void test004() {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(storesPojo)
                .patch(EndPoints.UPDATE_STORE_BY_ID + "/" + 7)
                .then();
    }

    @WithTags({
            @WithTag("Features: NEGATIVE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Deleting the store with store ID")
    @Test
    public void test005() {
        SerenityRest.rest()
                .given()
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID + "/" + 7)
                .then();


    }



}
