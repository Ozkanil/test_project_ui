package pages;

import Contexts.RegistrationContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.Helper;

import static org.junit.Assert.assertTrue;

public class CustomerLookupPage
{
    RegistrationContext registrationContext;
    RegistrationPage registrationPage;

    public CustomerLookupPage(RegistrationContext registrationContext, RegistrationPage registrationPage)
    {
        PageFactory.initElements(Driver.getDriver(),this);
        this.registrationContext = registrationContext;
        this.registrationPage = registrationPage;
    }

    @FindBy(linkText = "Forgot login info?")
    private WebElement ForgotLoginLink;
    @FindBy(xpath = "//h1[text()='Customer Lookup']")
    private WebElement CustomerLookUpText;
    @FindBy(id = "firstName")
    private WebElement FirstName;
    @FindBy(id = "lastName")
    private WebElement LastName;
    @FindBy(id="address.street")
    private WebElement Address;
    @FindBy(id="address.city")
    private WebElement City;
    @FindBy(id="address.state")
    private WebElement State;
    @FindBy(id="address.zipCode")
    private WebElement ZipCode;
    @FindBy(id="ssn")
    private WebElement SSN;
    @FindBy(id="rightPanel")
    private WebElement PanelText;
    @FindBy(xpath="//input[@type='submit' and @value='Find My Login Info']")
    private WebElement FindLoginInfoButton;
    @FindBy(id="ssn.errors")
    private WebElement SSNError;
    @FindBy(id = "firstName.errors")
    private WebElement FirstNameError;
    @FindBy(id = "lastName.errors")
    private WebElement LastNameError;
    @FindBy(id="address.street.errors")
    private WebElement AddressErrors;
    @FindBy(id="address.city.errors")
    private WebElement CityError;
    @FindBy(id="address.state.errors")
    private WebElement StateError;
    @FindBy(id="address.zipCode.errors")
    private WebElement ZipCodeErrors;

    public void FindLoginInfo(String customerData)
    {
        ForgotLoginLink.click();

        String customerLookupText = CustomerLookUpText.getText();

        assertTrue(customerLookupText.contains("Customer Lookup"));

        FirstName.sendKeys(registrationContext.getFirstName());
        LastName.sendKeys(registrationContext.getLastName());
        Address.sendKeys(registrationContext.getAddress());
        City.sendKeys(registrationContext.getCity());
        State.sendKeys(registrationContext.getState());
        ZipCode.sendKeys(registrationContext.getZip());

        if (customerData.equals("with valid data"))
        {
            SSN.sendKeys(registrationContext.getSsn());
        }

        FindLoginInfoButton.click();

        if (customerData.equals("with valid data"))
        {
            Helper.ExtractCredentialsAndSaveToContext(PanelText, registrationContext);

            registrationPage.LogoutButton.click();
        }
    }

    public void VerifyErrorMessageInCustomerLookup(String errorMessage)
    {
        String sSNError = SSNError.getText();

        assertTrue(sSNError.contains(errorMessage));
    }

    public void TryToViewLoginCredentialsWithoutProvidingAnyPersonalInfo()
    {
        ForgotLoginLink.click();
        FindLoginInfoButton.click();
    }

    public void VerifyAllErrorMessagesInCustomerLookup()
    {
        String firstNameError = FirstNameError.getText();
        String lastNameError = LastNameError.getText();
        String addressError = AddressErrors.getText();
        String cityError = CityError.getText();
        String stateError = StateError.getText();
        String zipCodeError = ZipCodeErrors.getText();
        String sSNError = SSNError.getText();

        assertTrue(firstNameError.contains("First name is required."));
        assertTrue(lastNameError.contains("Last name is required."));
        assertTrue(addressError.contains("Address is required."));
        assertTrue(cityError.contains("City is required."));
        assertTrue(stateError.contains("State is required."));
        assertTrue(zipCodeError.contains("Zip Code is required."));
        assertTrue(sSNError.contains("Social Security Number is required."));
    }
}
