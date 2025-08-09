package stepdefinitions;

import io.cucumber.java.After;
import utilities.Driver;

public class Hooks {

    public Hooks() {}

    @After
    public void tearDown()
    {
        Driver.closeDriver();
    }
}
