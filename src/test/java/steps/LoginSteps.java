package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;

import java.io.IOException;


public class LoginSteps extends CommonMethods {

    // call object
    //LoginPage loginPage=new LoginPage();

   // public WebDriver driver;

    /*
    @Given("user is able to access HRMS application")
    public void user_is_able_to_access_hrms_application() {
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        driver.manage().window().maximize();
    }
     */

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() throws IOException {
        // WebElement usernameField=driver.findElement(By.id("txtUsername"));
        //usernameField.sendKeys("Admin");
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        //WebElement passwordField= driver.findElement(By.id("txtPassword"));
        //passwordField.sendKeys("Hum@nhrm123");
        sendText(ConfigReader.read("password"),loginPage.passwordField);
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        // WebElement loginButton=driver.findElement(By.id("btnLogin"));
        //loginButton.click();
        click (loginPage.loginButton);
    }
    @Then("user is able to see dashboard page")
    public void user_is_able_to_see_dashboard_page() {
        // check for true or not.
        // assert to compare 2 elements as well (without True)
        Assert.assertTrue(dashboardPage.welcomeText.isDisplayed());
        System.out.println("test passed");
    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() throws IOException {
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("passwordInvalid"),loginPage.passwordField);
    }
    @Then("user can see error message")
    public void user_can_see_error_message() {
       String actualMessage=loginPage.errorMessage.getText();
       Assert.assertEquals("Invalid credentials", actualMessage);
    }


}
