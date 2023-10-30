package com.crowncastle.tests;

import com.crowncastle.pages.Checkers;
import com.crowncastle.utilities.ConfigurationReader;
import com.crowncastle.utilities.Driver;
import com.crowncastle.utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Logger;
import static org.testng.Assert.*;

public class MoveOrangePieces extends TestBase {

    @Test
    public void moveElement() {

        Logger logger = Logger.getAnonymousLogger();
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofMillis(1000));
        Checkers checkers = new Checkers();

// 1.	Navigate to https://www.gamesforthebrain.com/game/checkers/
//      This step is in the @BeforeMethod
//      Driver.get().get(ConfigurationReader.get("url"));
        logger.info("landing the url: "+ ConfigurationReader.get("url"));
        
// 2.	Confirm that the site is up:

        String expectedTitle = "Checkers";
        String actualTitle = checkers.title.getText();
        System.out.println("actualTitle = " + actualTitle);

        assertEquals(actualTitle, expectedTitle, "the site is NOT up and running");

// 3.	Make five legal moves as orange:

        WebElement piece1 = checkers.yellowPieces.get(0);
        WebElement piece2 = checkers.yellowPieces.get(1);
        WebElement piece3 = checkers.yellowPieces.get(2);
//        WebElement piece4 = checkers.yellowPieces.get(3);

        WebElement graySpace1 = checkers.graySpaces.get(0);
        WebElement graySpace2 = checkers.graySpaces.get(1);
//        WebElement graySpace3 = checkers.graySpaces.get(2);
        WebElement graySpace4 = checkers.graySpaces.get(3);
//        WebElement graySpace5 = checkers.graySpaces.get(4);
        WebElement graySpace6 = checkers.graySpaces.get(5);
//        WebElement graySpace7 = checkers.graySpaces.get(6);
        WebElement graySpace8 = checkers.graySpaces.get(7);


        String expectedSelectMessage = "Select an orange piece to move.";
        String actualSelectMessage = checkers.selectMessage.getText();

        assertEquals(actualSelectMessage, expectedSelectMessage, "Select an orange (...) message is not correct");

        Actions actions = new Actions(Driver.get());
        actions
                .click(piece1)                         //1st move
                .moveToElement(graySpace6)
                .click(graySpace6)
                .build()
                .perform();

// b)	Use “Make a move” as confirmation that you can take the next step

        wait.until(ExpectedConditions.visibilityOf(checkers.makeAMoveMessage));
        String expectedMakeAMoveMessage = "Make a move.";
        String actualMakeMoveMessage = checkers.makeAMoveMessage.getText();

        assertEquals(actualMakeMoveMessage, expectedMakeAMoveMessage, "Make a move message is not correct");

                actions
                        .pause(Duration.ofSeconds(3))          //2nd move
                        .click(graySpace6)
                        .moveToElement(graySpace2)
                        .click(graySpace2)

// a)	Include taking a blue piece
                        .pause(Duration.ofSeconds(3))          //3rd move  TAKE a BLUE
                        .click(piece2)
                        .moveToElement(graySpace1)
                        .click(graySpace1)

                        .pause(Duration.ofSeconds(3))          //4th move
                        .click(piece3)
                        .moveToElement(graySpace8)
                        .click(graySpace8)

                        .pause(Duration.ofSeconds(3))          //5th move
                        .click(graySpace8)
                        .moveToElement(graySpace4)
                        .click(graySpace4)

//                .release()
                .build()
                .perform();

//c)	Restart the game after five moves

        checkers.restart.click();

//d)	Confirm that the restarting had been successful

        assertTrue(checkers.selectMessage.isDisplayed(), "Restart link is not working");

        logger.info("TEST PASSED SUCCESSFULLY");
    }

}
