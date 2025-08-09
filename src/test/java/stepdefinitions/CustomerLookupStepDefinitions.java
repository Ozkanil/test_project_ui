package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CustomerLookupPage;

public class CustomerLookupStepDefinitions
{
    private final CustomerLookupPage customerLookupPage;

    public CustomerLookupStepDefinitions(CustomerLookupPage customerLookupPage)
    {
        this.customerLookupPage = customerLookupPage;
    }

    @When("the user searches forgotten user credentials {string}")
    public void theUserSearchesForgottenUserCredentials(String customerData)
    {
        this.customerLookupPage.FindLoginInfo(customerData);
    }

    @Then("the user is  shown {string}")
    public void theUserIsShown(String errorMessage)
    {
        this.customerLookupPage.VerifyErrorMessageInCustomerLookup(errorMessage);
    }

    @When("the user searches forgotten user credentials without providing any personal info")
    public void theUserSearchesForgottenUserCredentialsWithoutProvidingAnyPersonalInfo()
    {
        this.customerLookupPage.TryToViewLoginCredentialsWithoutProvidingAnyPersonalInfo();
    }

    @Then("the user is shown the proper error messages")
    public void theUserIsShownTheProperErrorMessages()
    {
        this.customerLookupPage.VerifyAllErrorMessagesInCustomerLookup();
    }
}
