
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.sun.glass.events.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;


import static com.codeborne.selenide.Selenide.*;

public class DemoqaTest {



    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        open("http://jira.hillel.it:8080/login.jsp");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserName("Igor_Medvediev");
        loginPage.enterPassword("2018j4qa");
        loginPage.clickSubmitButton();



    }

       @Test(priority = 1)

        public void usingRobotDragAndDrop() throws AWTException       //Robot+Actions
        {       Robot robot =new Robot();

            robot.delay(2500);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_G);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.delay(3500);
            $(By.xpath("//*[@id=\"subnav-title\"]/span[@title =\"Kanban board\"]")).should(Condition.visible);

//            open("http://jira.hillel.it:8080/secure/RapidBoard.jspa?rapidView=302&projectKey=QAAUT6&selectedIssue=QAAUT6-160&quickFilter=439");
//            Actions actions = new Actions(WebDriverRunner.getWebDriver());
//            actions.dragAndDropBy($(By.xpath("//*[@class =\"ghx-key\"]/a[@title=\"QAAUT6-160\"]")), 100, 200).perform();
            $(By.xpath("//a[contains(text(), \"Only My Issues\")]")).click();
            $(By.xpath("//*[@class =\"ghx-key\"]/a[@title=\"QAAUT6-160\"]")).dragAndDropTo($(By.xpath("//li[@data-column-id='860']")));


        }
        @Test(priority = 2)
        public void usingAlertJSscroll(){           //JS+Alert+Scroll
            JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
            //$(By.xpath("//a[contains(text(), \"Only My Issues\")]")).click();
            open("http://jira.hillel.it:8080/secure/RapidBoard.jspa?rapidView=302&projectKey=QAAUT6&selectedIssue=QAAUT6-160&quickFilter=439");
            js.executeScript("arguments[0].click();",$(By.xpath("//a[contains(text(), \"Only My Issues\")]")));
            js.executeScript("arguments[0].scrollIntoView();",$(By.xpath("//*[@class =\"ghx-key\"]/a[@title=\"QAAUT6-129\"]")));
            String domainName = js.executeScript("return document.domain;").toString();
            System.out.println("Domain name is "+domainName);
            js.executeScript("alert('Congratulation!!! Test Passed');");
            switchTo().alert().accept();
        }
        @Test(priority = 3)
        public void usingWindowFrame(){     //Window+Frame
           String MainWindow= WebDriverRunner.getWebDriver().getWindowHandle();
            $(By.id("jira-header-feedback-link")).click();
            switchTo().frame($(By.id("atlwdg-frame")));
            $(By.xpath("//*[@class=\"external-link\"][contains(text(), 'support request')]")).click();
            switchTo().window(MainWindow);
            switchTo().defaultContent();
            switchTo().frame($(By.id("atlwdg-frame")));
            $(By.xpath("//*[@id=\"jic-collector-form\"]//a[@class =\"cancel\"]")).click();
            switchTo().defaultContent();
            Assert.assertFalse($(By.id("atlwdg-frame")).isDisplayed(),"frame is displayed ");
            $(By.id("logo")).click();
            Assert.assertTrue($(By.xpath("//div[@class=\"aui-page-header-main\"]/h1")).isDisplayed());
        }


    @AfterMethod
    public void tearDown()
    {
        WebDriverRunner.getWebDriver().quit();
    }

}
