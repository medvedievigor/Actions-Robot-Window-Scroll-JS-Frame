import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;


public class LoginPage {
    public static String jsessionCookie;
    private String userNameInputXpath = "//*[@id=\"login-form-username\"]";
        private String passwordInputXpath = "//*[@id=\"login-form-password\"]";
        private String submitButtonXpath = "//*[@id=\"login-form-submit\"]";


    public  void enterUserName(String userName){
//        $(byXpath(userNameInputXpath)).clear();
        $(byXpath(userNameInputXpath)).sendKeys(userName);

    }public  void enterPassword(String password){
//        $(byXpath(passwordInputXpath)).clear();
        $(byXpath(passwordInputXpath)).sendKeys(password);

    }public  void clickSubmitButton (){
        $(byXpath(submitButtonXpath)).click();
            }
    public boolean atRequiredPage() //проверяет, что мы на dashboardPage.
    {   Assert.assertEquals(title(), "System Dashboard - Hillel IT School JIRA");
        return true;
    }

}

