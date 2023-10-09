package api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

public class NovaPoshtaAPITest extends BaseApiTest {
    String API_key = "e529158cf238a61f2502862a4e42690c";

    @Test
    public void verifyThatSuccess() {
        Map<String, Object> reqBody = new HashMap<>();
        Map<String, Object> methodProperties = new HashMap<>();

        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Address");
        reqBody.put("calledMethod", "getSettlements");
        reqBody.put("methodProperties", methodProperties);

        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    public void verifyResultCanBeFilteredByWarehouse() {
        Map<String, Object> reqBody = new HashMap<>();
        Map<String, Object> methodProperties = new HashMap<>();
        methodProperties.put("Warehouse", "1");

        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Address");
        reqBody.put("calledMethod", "getSettlements");
        reqBody.put("methodProperties", methodProperties);
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body("data.Warehouse", everyItem(equalTo("1")));
    }

    @Test
    public void verifyResultCanBeFilteredByStringName() {
        Map<String, Object> reqBody = new HashMap<>();
        Map<String, Object> methodProperties = new HashMap<>();
        methodProperties.put("FindByString", "Чернігів");

        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Address");
        reqBody.put("calledMethod", "getSettlements");
        reqBody.put("methodProperties", methodProperties);
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body("data.Description", everyItem(containsString("Чернігів")));
    }

    @Test
    public void verifyResultCanBeFilteredByLimitSize() {
        Map<String, Object> reqBody = new HashMap<>();
        Map<String, Object> methodProperties = new HashMap<>();
        methodProperties.put("Limit", "10");

        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Address");
        reqBody.put("calledMethod", "getSettlements");
        reqBody.put("methodProperties", methodProperties);
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body("data.size()", is(10));
    }

    @Test
    public void verifyJsonSchema() {
        Map<String, Object> reqBody = new HashMap<>();
        Map<String, Object> methodProperties = new HashMap<>();

        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Address");
        reqBody.put("calledMethod", "getSettlements");
        reqBody.put("methodProperties", methodProperties);

        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(new File(System.getProperty("user.dir").concat(System.getProperty("file.separator")).concat(
                        "src/main/resources/validators/np_address_schema.json"))));
    }

    @Test
    public void verifyPresentValueInData() {
        Map<String, Object> reqBody = new HashMap<>();
        Map<String, Object> methodProperties = new HashMap<>();
        methodProperties.put("AreaRef", "dcaadf02-4b33-11e4-ab6d-005056801329");

        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Address");
        reqBody.put("calledMethod", "getSettlements");
        reqBody.put("methodProperties", methodProperties);

        List<DataAddress> dataList = given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList("data", DataAddress.class);
        dataList.forEach(item -> Assertions.assertTrue(item.getAreaDescription().contains("Полтавська область")));
    }
}
