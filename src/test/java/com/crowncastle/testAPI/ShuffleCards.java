package com.crowncastle.testAPI;

import com.crowncastle.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ShuffleCards {

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

//    user story-2: Shuffle the cards
        //    user sends a GET request to shuttle the cards
        //    user should be able to verify that response status code is 200
        //    user should be able to verify that success in the body is true
        //    user should be able to verify that shuffled in the body is true
        //    user should be able to see the remaining cards are 52


    @Test(description = "SHUFFLE THE CARDS")
    public void shuffleCards() {

        given()
//                .queryParam("jokers_enabled","true")
                .queryParam("deck_count", 1)
                .when()
                .get("/api/deck/new/shuffle/")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("remaining", equalTo(52))
                .body("shuffled", equalTo(true));

    }

    @AfterMethod
    public static void teardown() {
        reset();
    }

}
