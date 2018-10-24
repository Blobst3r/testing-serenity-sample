package definitions;

import com.fasterxml.jackson.databind.util.JSONPObject;
import data.Configuration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ReqResDef {
    private Configuration conf = new Configuration();
    private static final String RESPONSE = "RESPONSE";

    public ReqResDef() {
        conf.readConfigFile();
    }

    public void endpointShouldBeAvailable() {
        RestAssured
                .when()
                .get(conf.getReqResUrl())
                .then()
                .assertThat().statusCode(200);
    }

    @Step("request a list of users")
    public void requestListOfUsers() {
        Serenity.getCurrentSession().put(RESPONSE, RestAssured
                .when()
                .get(conf.getReqResUrl() + "/api/users?page=2")
        );
    }

    @Step("request a single user with id {0}")
    public void requestSingleUser(int userId) {
        Serenity.getCurrentSession().put(RESPONSE, RestAssured
                .when()
                .get(conf.getReqResUrl() + String.format("/api/users/%d", userId))
        );
    }

    @Step("verify response according to schema {0}")
    public void verifyResponseAccordingToSchema(String schemaJsonPath) {
        Response response = (Response) Serenity.getCurrentSession().get(RESPONSE);
        response.then().assertThat().statusCode(200).
                and().body(matchesJsonSchemaInClasspath(schemaJsonPath));
    }

    @Step("create a new user with name '{0}' and job '{0}'")
    public void postCreateUser(String firstName, String job) {
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", firstName);
        requestParams.put("job", job);

        request.body(requestParams.toJSONString());
        Serenity.getCurrentSession().put(RESPONSE, request.post(conf.getReqResUrl() + "/api/users"));
    }

    @Step("response code of the request was {0}")
    public void assertResponseCodeTo(int expectedResponseCode) {
        Response response = (Response) Serenity.getCurrentSession().get(RESPONSE);
        response.then().statusCode(expectedResponseCode);
    }


}
