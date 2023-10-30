package com.crowncastle.testAPI;

import com.crowncastle.utilities.ConfigurationReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static io.restassured.RestAssured.*;

public class DealThreeCards {

    //NOTE this part was not clear for me. I don't have detailed idea about the card game.
    //I assume that there are 2 players and I will deal three cards to each player; total 6 cards

    public static final Logger logger = Logger.getLogger(GETANewDeck.class.getName());

    @BeforeMethod
    public static void setUp() {
        baseURI = ConfigurationReader.get("baseURL");
    }

//  5.	Deal three cards to each of two players
// 6.	Check whether either has blackjack
// 7.	If either has, write out which one does


    /*
    /    user story-3: Deal three cards
            user sends a GET request to Draw the cards
            user should be able to verify that response status code is 200
            user should be able to verify that success in the body is true
            user should be able to verify that shuffled in the body is true
            user should be able to see the remaining cards are 48
            user should be able to check which player has balckjack card

     */

    @Test(description = "DEAL 3 CARDS TO EACH OF TWO PLAYERS")
    public void drawDeck() {
        // Initially I create first call and get the deck_id and stored in a String
        JsonPath json =
                given()
                        .queryParam("jokers_enabled","true")
                        .when()
                        .get("/api/deck/new/").jsonPath();
        String deckID = json.getString("deck_id");

//        given()
//                .queryParam("jokers_enabled","true")
//                .queryParam("count", 6)  // draw 6 cards
//                .pathParam("deck_id",deckID)
//                .when()
////                .get("deck/{id}/draw/")
//                .get("/api/deck/{deck_id}/draw/")
//                .prettyPeek()
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body("success", equalTo(true))
//                .body("remaining", equalTo(48));

        Response response =  given()
                .queryParam("jokers_enabled","true")
                .queryParam("count", 6)  // draw 6 cards
                .pathParam("deck_id",deckID)
                .when()
//                .get("deck/{id}/draw/")
                .get("/api/deck/{deck_id}/draw/")
                .prettyPeek();
        assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = response.jsonPath();
        assertEquals(response.getBody().path("remaining").toString(), String.valueOf(48));
        assertEquals(String.valueOf(true), response.getBody().path("success").toString());

//        6.	Check whether either has blackjack
//        7.	If either has, write out which one does

        List<String> cardCodes = new ArrayList<>();
        cardCodes = response.path("cards.code");
        System.out.println("cardCodes = " + cardCodes);
        String player = "";
        for (int i = 0; i < cardCodes.size(); i++) {
            if (cardCodes.get(i).equalsIgnoreCase("AS")){
                if (i % 2 == 0){                   // if the first player got the card index zero; 0%2=0 and this is the first player
                    player = "first player";
                }else {
                    player = "second player";
                }

            }
        }
        System.out.println(player + " has received AS (Blackjack card)");
    }

    @AfterMethod
    public static void teardown() {
        reset();
    }



}
