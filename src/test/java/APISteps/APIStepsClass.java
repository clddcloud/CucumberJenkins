package APISteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIStepsClass {

    // String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    RequestSpecification request;
    Response response;
    public static String employee_id;
    public static String token;

    @Given("a JWT bearer token is generated")
    public void a_jwt_bearer_token_is_generated() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body("{\n" +
                        "  \"email\": \"kaylinadmin321@kaylinworld.com\",\n" +
                        "  \"password\": \"Test@123\"\n" +
                        "}");

        response = request.when().post(APIConstants.GENERATE_TOKEN);

        token = "Bearer "+ response.jsonPath().getString("token");
    }

    @Given("a request is prepared for creating the employee")
    public void a_request_is_prepared_for_creating_the_employee() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create the employee")
    public void a_post_call_is_made_to_create_the_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE);
    }

    @Then("the status code for this request is {int}")
    public void the_status_code_for_this_request_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }

    @Then("the employee id {string} is stored and values are validated")
    public void the_employee_id_is_stored_and_values_are_validated(String empPath) {
        //hamcrest matchers

        //retrieve the employee id after creating the employee and store it in global variable

        //jsonPath() is the method which returns the value in string against the key specified
        employee_id = response.jsonPath().getString(empPath);
        System.out.println("The employee id is:::::: " + employee_id);
        response.then().assertThat().
                body("Employee.emp_firstname",equalTo("asana"));
    }

    @Given("a request is prepared to get the created the employee")
    public void a_request_is_prepared_to_get_the_created_the_employee() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                queryParam("employee_id",employee_id);
    }
    @When("a GET call is made to get the employee")
    public void a_get_call_is_made_to_get_the_employee() {
       response=request.when().get(APIConstants.GET_ONE_EMPLOYEE);
    }
    @Then("the status for GET call is {int}")
    public void the_status_for_get_call_is(Integer statusCode) {
       response.then().assertThat().statusCode(statusCode);
    }
    @Then("the employee has same employee id {string} as global employee_id")
    public void the_employee_has_same_employee_id_as_global_employee_id(String empID) {
     String tempEmpID=response.jsonPath().getString(empID);
     //to compare both emo id's
     Assert.assertEquals(employee_id, tempEmpID);

    }
    @Then("the data coming from the GET call should equal to the data used in POST call")
    public void the_data_coming_from_the_get_call_should_equal_to_the_data_used_in_post_call(io.cucumber.datatable.DataTable dataTable) {
       // it is coming from feature file
        List<Map<String,String>> expectedData=dataTable.asMaps();
        // it is coming from the response body which we need whole object
        Map<String,String> actualData = response.jsonPath().get("employee");

        //compare values
        for (Map<String,String> employeeData: expectedData){
            // to get all the unique keys
            Set<String> keys = employeeData.keySet();
            //but we need one key at a time to compare with value
            for (String key:keys){
                // to return the value against the key
                String expectedValue=employeeData.get(key);
                String actualValue=actualData.get(key);
                Assert.assertEquals(expectedValue,actualValue);
            }
        }
    }


    @Given("a request is prepared for creating the employee using json payload")
    public void aRequestIsPreparedForCreatingTheEmployeeUsingJsonPayload() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                body(APIPayloadConstants.createEmployeeJsonPayload());
    }

    @Given("a request is prepared for creating the employee using data {string}, {string}, {string}, {string}, {string}, {string},{string}")
    public void aRequestIsPreparedForCreatingTheEmployeeUsingData(String firstname, String lastname, String middlename,
                                                                  String gender, String birthday, String status, String jobTitle) {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(firstname,lastname,middlename,gender,birthday,status,jobTitle));
    }
    @Given("a request is prepared for updating the employee using data employee_id, {string}, {string}, {string}, {string}, {string}, {string},{string}")
    public void a_request_is_prepared_for_updating_the_employee_using_data_employee_id(String firstname, String lastname, String middlename,
                                                                                       String gender, String birthday, String status, String jobTitle) {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                body(APIPayloadConstants.updateEmployeeJsonPayloadDynamic(employee_id, firstname,lastname,middlename,gender,birthday,status,jobTitle));
    }

    @When("a PUT call is made to update the employee")
    public void a_put_call_is_made_to_update_the_employee() {
        response = request.when().put(APIConstants.UPDATE_EMPLOYEE);
    }
    @Then("the status for PUT call is {int}")
    public void the_status_for_put_call_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @And("the data coming from the GET call should equal to the data used in PUT call")
    public void theDataComingFromTheGETCallShouldEqualToTheDataUsedInPUTCall(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>> expectedData=dataTable.asMaps();
        Map<String,String> actualData = response.jsonPath().get("employee");
        for (Map<String,String> employeeData: expectedData){
            Set<String> keys = employeeData.keySet();
            for (String key:keys){
                String expectedValue=employeeData.get(key);
                String actualValue=actualData.get(key);
                Assert.assertEquals(expectedValue,actualValue);
            }
        }
    }
}