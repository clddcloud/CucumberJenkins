package steps;

import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

public class DashboardSteps extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        //WebElement pimOption= driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();
        click(dashboardPage.pimOption);
    }

    @When("user clicks on employee list option")
    public void user_clicks_on_employee_list_option() {
       // WebElement empListOption= driver.findElement(By.id("menu_pim_viewEmployeeList"));
        click(dashboardPage.empListOption);

    }
    @When("user clicks on Add employee option")
    public void user_clicks_on_add_employee_option() {
        //WebElement addEmpOption= driver.findElement(By.id("menu_pim_addEmployee"));
        //addEmpOption.click();
        click(dashboardPage.addEmpOption);
    }
}
