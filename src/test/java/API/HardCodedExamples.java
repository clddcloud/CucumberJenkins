package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

// order of executing test cases
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    //base URI = base URL
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String employee_id;

    @Test
    public void createEmployee() {

    // prepare the request
    RequestSpecification request = given().
            header("Content-Type", "application/json").
            header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzEzMTc5NTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTczMTM2MTE1OCwidXNlcklkIjoiNjg3NyJ9.V8mmJhTOxwez-oN7P9u4sPmpk-2OjDDnLrkJVwZBV2w").
            body("{\n" +
                    "  \"emp_firstname\": \"Asana\",\n" +
                    "  \"emp_lastname\": \"Lawrence\",\n" +
                    "  \"emp_middle_name\": \"ms\",\n" +
                    "  \"emp_gender\": \"F\",\n" +
                    "  \"emp_birthday\": \"1993-11-06\",\n" +
                    "  \"emp_status\": \"permanent\",\n" +
                    "  \"emp_job_title\": \"QA manager\"\n" +
                    "}");
    // response will be return when we send the request
    Response response = request.when().post("/createEmployee.php");
    //to print the response
    response.prettyPrint();
    response.then().assertThat().statusCode(201);
    //hamcrest matchers
    response.then().assertThat().body("Employee.emp_firstname",equalTo("Asana"));
    //retrieve the employee id after creating the employee and store it in a global variable
        // jsonPath() is a method which returns the value against the key specified
    employee_id = response.jsonPath().getString("Employee.employee_id");
    System.out.println("the employee id is " +employee_id);
    }

    @Test
    public void getCreatedEmployee(){
            RequestSpecification request = given().
                    header("Content-Type", "application/json").
                    header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzEzMTc5NTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTczMTM2MTE1OCwidXNlcklkIjoiNjg3NyJ9.V8mmJhTOxwez-oN7P9u4sPmpk-2OjDDnLrkJVwZBV2w").
                    queryParam("employee_id", employee_id);

            Response response = request.when().get("/getOneEmployee.php");
            response.then().assertThat().statusCode(200);
            String tempEmpId=response.jsonPath().getString("employee.employee_id");
            Assert.assertEquals(tempEmpId, employee_id);
        }

    @Test
    public void updateEmployee() {
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzEzMTc5NTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTczMTM2MTE1OCwidXNlcklkIjoiNjg3NyJ9.V8mmJhTOxwez-oN7P9u4sPmpk-2OjDDnLrkJVwZBV2w").
                body("{\n" +
                        "        \"employee_id\": \"" + employee_id + "\",\n" +
                        "        \"emp_firstname\": \"Asana\",\n" +
                        "        \"emp_middle_name\": \"ms\",\n" +
                        "        \"emp_lastname\": \"Lawrence\",\n" +
                        "        \"emp_birthday\": \"1993-11-06\",\n" +
                        "        \"emp_gender\": \"F\",\n" +
                        "        \"emp_job_title\": \"QA manager\",\n" +
                        "        \"emp_status\": \"temp\"\n" +
                        "    }");
        Response response = request.when().put("updateEmployee.php");
        response.then().assertThat().statusCode(200);

    }

}
