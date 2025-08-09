package stepdefinitions;

import io.cucumber.java.en.And;
import pages.RegistrationPage;

public class RegistrationStepDefinitions
{
    private  final RegistrationPage registrationPage;

    public RegistrationStepDefinitions(RegistrationPage registrationPage)
    {
        this.registrationPage = registrationPage;
    }

    @And("the user makes registration")
    public void theUserMakesRegistration()
    {
        this.registrationPage.registerNewUser();
    }

    @And("the registration is successful")
    public void theRegistrationIsSuccessful()
    {
        this.registrationPage.VerifySuccessfulRegistration();
    }
}
