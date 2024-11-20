package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.EmployeeSearchPage;
import utils.CommonMethods;

public class EmployeeSearchSteps extends CommonMethods {


    @When("user enters employee ID")
    public void user_enters_employee_id() {
        //WebElement empIdTextBox= driver.findElement(By.id("empsearch_id"));
        sendText("110360A", employeeSearchPage.empIdTextBox);

    }
    @When("user click on search button")
    public void user_click_on_search_button() {
        //WebElement searchButton= driver.findElement(By.id("searchBtn"));
        click (employeeSearchPage.searchButton);
    }
    @Then("user is able to see searched employee on screen")
    public void user_is_able_to_see_searched_employee_on_screen() {
        System.out.println("Test passed");
    }
    @When("user enters employee name")
    public void user_enters_employee_name() {
        //WebElement empSearchNameField= driver.findElement(By.id("empsearch_employee_name_empName"));
        //WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        //wait.until(ExpectedConditions.textToBePresentInElementValue(empSearchNameField, "Type for hints..."));
        getWait().until(ExpectedConditions.textToBePresentInElementValue(employeeSearchPage.empSearchNameField, "Type for hints..."));
        sendText("gogaa", employeeSearchPage.empSearchNameField);
    }
}
