package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AddEmployeePage;
import utils.CommonMethods;
import utils.ExcelReader;
import utils.dbUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    // AddEmployeePage addEmployeePage=new AddEmployeePage();
    String employeeID;
    String expectedFirstName;
    String expectedMiddleName;
    String expectedLastName;

    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {
        //WebElement firstNameLocator=driver.findElement(By.id("firstName"));
        //WebElement lastNameLocator=driver.findElement(By.id("lastName"));
        //firstNameLocator.sendKeys("John");
        //lastNameLocator.sendKeys("Jovi");
        sendText("John", addEmployeePage.firstNameLocator);
        sendText("Jovi", addEmployeePage.lastNameLocator);


    }
    @When("user clicks on Save button")
    public void user_clicks_on_save_button() {
        //WebElement saveButton= driver.findElement(By.id("btnSave"));
        //saveButton.click();
        click(addEmployeePage.saveButton);
    }

    @Then("employ added successfully")
    public void employAddedSuccessfully() {
        String query="select emp_firstname, emp_middle_name, emp_lastname from hs_hr_employees where employee_id="+employeeID;
        List <Map<String,String>> dataFromdb= dbUtils.fetch(query);
        String actualFN=dataFromdb.get(0).get("emp_firstname");
        String actualMN=dataFromdb.get(0).get("emp_middle_name");
        String actualLN=dataFromdb.get(0).get("emp_lastname");
        Assert.assertEquals(expectedFirstName,actualFN);
        Assert.assertEquals(expectedMiddleName,actualMN);
        Assert.assertEquals(expectedLastName,actualLN);
    }

    @When("user enters firstname and middlename and lastname")
    public void user_enters_firstname_and_middlename_and_lastname() {
        //WebElement firstNameLocator=driver.findElement(By.id("firstName"));
        //WebElement middleNameLocator=driver.findElement(By.id("middleName"));
        //WebElement lastNameLocator=driver.findElement(By.id("lastName"));
        // firstNameLocator.sendKeys("John");
        // middleNameLocator.sendKeys("Bon");
        // lastNameLocator.sendKeys("Jovi");
        sendText("John", addEmployeePage.firstNameLocator);
        sendText("Bon", addEmployeePage.middleNameLocator);
        sendText("Jovi", addEmployeePage.lastNameLocator);


    }
    @When("user enters {string} and {string} and {string} in the application")
    public void user_enters_and_and_in_the_application(String firstName, String middleName, String lastName) {
        //WebElement firstNameLocator=driver.findElement(By.id("firstName"));
        //WebElement middleNameLocator=driver.findElement(By.id("middleName"));
        //WebElement lastNameLocator=driver.findElement(By.id("lastName"));
        //firstNameLocator.sendKeys(firstName);
        //middleNameLocator.sendKeys(middleName);
        //lastNameLocator.sendKeys(lastName);
        sendText(firstName, addEmployeePage.firstNameLocator);
        sendText(middleName, addEmployeePage.middleNameLocator);
        sendText(lastName, addEmployeePage.lastNameLocator);
        expectedFirstName=firstName;
        expectedMiddleName=middleName;
        expectedLastName=lastName;
        // get the id of the employee from add employee page
        employeeID=addEmployeePage.employeeIDField.getAttribute("value");
    }

    @When("user adds {string}, {string} and {string}")
    public void user_adds_and(String firstName, String middleName, String lastName) {
        // WebElement firstNameLocator=driver.findElement(By.id("firstName"));
       // WebElement middleNameLocator=driver.findElement(By.id("middleName"));
        // WebElement lastNameLocator=driver.findElement(By.id("lastName"));
        //firstNameLocator.sendKeys(firstName);
        //middleNameLocator.sendKeys(middleName);
        //lastNameLocator.sendKeys(lastName);
        sendText(firstName, addEmployeePage.firstNameLocator);
        sendText(middleName, addEmployeePage.middleNameLocator);
        sendText(lastName, addEmployeePage.lastNameLocator);
    }

    @When("user adds multiple employees using data table and save them")
    public void user_adds_multiple_employees_using_data_table_and_save_them(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> employeeNames=dataTable.asMaps();
        for (Map<String, String> employee:employeeNames){
            //WebElement firstNameLocator=driver.findElement(By.id("firstName"));
           // WebElement middleNameLocator=driver.findElement(By.id("middleName"));
            //WebElement lastNameLocator=driver.findElement(By.id("lastName"));

            // firstNameLocator.sendKeys(employee.get("firstName"));
            // middleNameLocator.sendKeys(employee.get("middleName"));
            // lastNameLocator.sendKeys(employee.get("lastName"));

            sendText(employee.get("firstName"), addEmployeePage.firstNameLocator);
            sendText(employee.get("middleName"), addEmployeePage.middleNameLocator);
            sendText(employee.get("lastName"), addEmployeePage.lastNameLocator);

            //WebElement saveButton= driver.findElement(By.id("btnSave"));
            //saveButton.click();
            click(addEmployeePage.saveButton);
            //WebElement addEmpOption= driver.findElement(By.id("menu_pim_addEmployee"));
            //addEmpOption.click();
            click(addEmployeePage.addEmpOption);
        }
    }

    @When("user adds multiple employees from excel file")
    public void user_adds_multiple_employees_from_excel_file() throws IOException {
        List<Map<String, String>> employeeNames= ExcelReader.read();
        for (Map<String, String> employee:employeeNames){
           // WebElement firstNameLocator=driver.findElement(By.id("firstName"));
           // WebElement middleNameLocator=driver.findElement(By.id("middleName"));
           // WebElement lastNameLocator=driver.findElement(By.id("lastName"));

            sendText(employee.get("firstName"), addEmployeePage.firstNameLocator);
            sendText(employee.get("middleName"), addEmployeePage.middleNameLocator);
            sendText(employee.get("lastName"), addEmployeePage.lastNameLocator);

           // WebElement saveButton= driver.findElement(By.id("btnSave"));
            click(addEmployeePage.saveButton);
           // WebElement addEmpOption= driver.findElement(By.id("menu_pim_addEmployee"));
            click(addEmployeePage.addEmpOption);

        }
    }
}
