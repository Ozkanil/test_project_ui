package utilities;

import Contexts.RegistrationContext;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper
{
    public static void ExtractCredentialsAndSaveToContext(WebElement element, RegistrationContext registrationContext)
    {
        String panelText = element.getText();

        Pattern pattern = Pattern.compile("Username:\\s*(\\S+)\\s*Password:\\s*(\\S+)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(panelText);

        if (matcher.find()) {
            String username = matcher.group(1);
            String password = matcher.group(2);

            // Save to the Context
            registrationContext.setUserName(username);
            registrationContext.setPassword(password);
        }
        else
        {
            throw new AssertionError("Username and Password not found in element text.");
        }
    }
}
