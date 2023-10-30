package com.crowncastle.testAPI;

import com.crowncastle.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class GETANewDeck {

    public static final Logger logger = Logger.getLogger(GETANewDeck.class.getName());

    @BeforeMethod
    public static void setUp() {
        baseURI = ConfigurationReader.get("baseURL");
    }

/*
3.	Get a new deck
4.	Shuffle it
5.	Deal three cards to each of two players
6.	Check whether either has blackjack
7.	If either has, write out which one does

 */

//    baseURL=https://deckofcardsapi.com/

//    user story-1: GET a new deck of cards
//    query parameter is jokers_enabled=true
//    user sends a GET request to create a new deck
//    user should be able to verify that response status code is 200
//    user should be able to verify that success in the body is true
//    user should be able to verify that shuffled in the body is false
//    user should be able to see the remaining cards are 54


    @Test(description = "GET A NEW DECK")
    public void getANewDeck() {

        given().accept(ContentType.JSON)
                .queryParam("jokers_enabled","true")
                .when()
                .get("/api/deck/new/")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("shuffled", equalTo(false))
                .body(containsString("deck_id"))
                .body("remaining", equalTo(54));


    }







    @AfterMethod
    public static void teardown() {
        reset();
    }


}
