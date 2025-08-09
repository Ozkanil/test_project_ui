package pages;

import Contexts.RegistrationContext;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.TestData;

import static org.junit.Assert.assertTrue;

public class LoginPage
{
    RegistrationContext registrationContext;

    public LoginPage(RegistrationContext registrationContext)
    {
        PageFactory.initElements(Driver.getDriver(),this);
        this.registrationContext = registrationContext;
    }

    @FindBy(name = "username")
    private WebElement usernameField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(css = "input[value='Log In']")
    private WebElement loginButton;
    @FindBy(id = "showOverview")
    private WebElement AccountOverviewText;
    @FindBy(id = "rightPanel")
    private WebElement LoginErrorText;

    public void LoginWithValidCredentials(String credentialState)
    {
        if (credentialState.equals("new") || credentialState.equals("existing"))
        {
            usernameField.sendKeys(credentialState.equals("new") ? registrationContext.getUserName() : TestData.ValidUsername);
            passwordField.sendKeys(credentialState.equals("new") ? registrationContext.getPassword() : TestData.ValidPassword);
        }
        else if (credentialState.equals("invalid"))
        {
            usernameField.sendKeys(TestData.InvalidUsername);
            passwordField.sendKeys(TestData.InvalidPassword);
        }
        else if (credentialState.equals("white spaces with"))
        {
            usernameField.sendKeys(TestData.ValidUsername, Keys.SPACE);
            passwordField.sendKeys(TestData.ValidPassword, Keys.SPACE);
        }
        else if (credentialState.equals("wrong username") || credentialState.equals("wrong password"))
        {
            usernameField.sendKeys(credentialState.equals("wrong username") ? TestData.InvalidUsername : TestData.ValidUsername);
            passwordField.sendKeys(credentialState.equals("wrong username") ? TestData.ValidPassword : TestData.InvalidPassword);
        }
        else if (credentialState.equals("saved"))
        {
            usernameField.sendKeys(registrationContext.getUserName());
            passwordField.sendKeys(registrationContext.getPassword());
        }

        loginButton.click();
    }

    public void  VerifySuccessfulLogin()
    {
        String accountOverviewText = AccountOverviewText.getText();

        assertTrue(accountOverviewText.contains("Accounts Overview"));
    }

    public void  VerifyLoginFailure(String message)
    {
        String accountOverviewText = LoginErrorText.getText();

        assertTrue(accountOverviewText.contains(message));
    }
}
