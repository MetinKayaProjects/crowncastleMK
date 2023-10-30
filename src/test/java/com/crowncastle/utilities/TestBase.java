package com.crowncastle.utilities;

import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class TestBase {

    @BeforeMethod
    public void setUp(){
        Driver.get().get(ConfigurationReader.get("url"));
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void tearDown(){
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.closeDriver();
    }

}
