package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utilities.ConfigurationReader;
import utilities.Driver;

public class LoginStepDefinitions
{
    private final LoginPage loginPage;

    public LoginStepDefinitions(LoginPage loginPage)
    {
        this.loginPage = loginPage;
    }

    @When("the user enters {string}")
    @When("the user enters {string} username and password")
    public void theUserEntersValidUsernameAndPassword(String credentialState)
    {
        this.loginPage.LoginWithValidCredentials(credentialState);
    }

    @Given("a user open the home page")
    public void aUserOpenTheHomePage()
    {
        Driver.getDriver().get(ConfigurationReader.getProperty("baseUrl"));
    }

    @Then("the user logged in successfully")
    public void theUserLoggedInSuccessfully()
    {
        this.loginPage.VerifySuccessfulLogin();
    }

    @Then("the user is shown {string}")
    public void theUserIsShown(String message)
    {
        this.loginPage.VerifyLoginFailure(message);
    }
}
