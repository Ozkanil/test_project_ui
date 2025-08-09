package pages;

import Contexts.RegistrationContext;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import static org.junit.Assert.assertTrue;

public class RegistrationPage
{
    RegistrationContext registrationContext;

    public RegistrationPage(RegistrationContext registrationContext)
    {
        PageFactory.initElements(Driver.getDriver(),this);
        this.registrationContext = registrationContext;
    }

    @FindBy(linkText = "Register")
    private WebElement Register;
    @FindBy(id = "customer.firstName")
    private WebElement FirstName;
    @FindBy(id = "customer.lastName")
    private WebElement LastName;
    @FindBy(id="customer.address.street")
    private WebElement Address;
    @FindBy(id="customer.address.city")
    private WebElement City;
    @FindBy(id="customer.address.state")
    private WebElement State;
    @FindBy(id="customer.address.zipCode")
    private WebElement ZipCode;
    @FindBy(id="customer.phoneNumber")
    private WebElement Phone;
    @FindBy(id="customer.username")
    private WebElement UserName;
    @FindBy(id="customer.password")
    private WebElement Password;
    @FindBy(id="repeatedPassword")
    private WebElement ConfirmPassword;
    @FindBy(css = "input[value='Register']")
    private WebElement RegisterConfirmation;
    @FindBy(id="customer.ssn")
    private WebElement SSN;
    @FindBy(id="rightPanel")
    private WebElement WelcomeText;
    @FindBy(linkText="Log Out")
    public WebElement LogoutButton;

    public void registerNewUser()
    {
        Faker faker = new Faker();

        // Generate fake data
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeAddress = faker.address().streetAddress();
        String fakeCity = faker.address().city();
        String fakeState = faker.address().state();
        String fakeZip = faker.address().zipCode();
        String fakePhone = faker.phoneNumber().cellPhone();
        String fakeUsername = faker.name().username() + faker.number().digits(3);
        String fakePassword = faker.internet().password(8, 12);
        String fakeSSN = faker.idNumber().ssnValid();

        //Save data to the context to use later
        registrationContext.setFirstName(fakeFirstName);
        registrationContext.setLastName(fakeLastName);
        registrationContext.setAddress(fakeAddress);
        registrationContext.setCity(fakeCity);
        registrationContext.setZip(fakeZip);
        registrationContext.setState(fakeState);
        registrationContext.setSsn(fakeSSN);

        registrationContext.setUserName(fakeUsername);
        registrationContext.setPassword(fakePassword);

        Register.click();
        FirstName.sendKeys(fakeFirstName);
        LastName.sendKeys(fakeLastName);
        Address.sendKeys(fakeAddress);
        City.sendKeys(fakeCity);
        State.sendKeys(fakeState);
        ZipCode.sendKeys(fakeZip);
        Phone.sendKeys(fakePhone);
        SSN.sendKeys(fakeSSN);
        UserName.sendKeys(fakeUsername);
        Password.sendKeys(fakePassword);
        ConfirmPassword.sendKeys(fakePassword);
        RegisterConfirmation.click();
    }

    public void VerifySuccessfulRegistration()
    {
        String panelText = WelcomeText.getText();

        String expectedText = "Your account was created successfully. You are now logged in.";

        assertTrue(panelText.contains(expectedText));

        assertTrue(panelText.contains(registrationContext.getUserName()));

        LogoutButton.click();
    }
}