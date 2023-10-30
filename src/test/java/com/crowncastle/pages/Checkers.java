package com.crowncastle.pages;

import com.crowncastle.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Checkers {
    public Checkers() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[src='you1.gif']")
    public List<WebElement> yellowPieces;

    @FindBy(css = "[src='gray.gif']")
    public List<WebElement> graySpaces;

    @FindBy(css = ".page h1")
    public WebElement title;

    @FindBy(css = ".gameWrapper p#message")
    public WebElement selectMessage;

    @FindBy(xpath = "//p[text() ='Make a move.']")
    public WebElement makeAMoveMessage;

    @FindBy(xpath = "//a[text() ='Restart...']")
    public  WebElement restart;

}
