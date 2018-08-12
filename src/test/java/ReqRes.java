import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ReqRes {
    @Steps
    private ReqResDef reqResDef;

    @Before
    public void testSetup() {
        reqResDef.endpointShouldBeAvailable();
    }

    @After
    public void testTeardown() {

    }

    @Test
    public void listOfUsersVerification() {
        reqResDef.requestListOfUsers();
        reqResDef.verifyResponseAccordingToSchema("list_users_response_schema.json");
    }

    @Test
    public void singleUserRequestVerification() {
        reqResDef.requestSingleUser(2);
        reqResDef.verifyResponseAccordingToSchema("single_user_response_schema.json");
    }

    @Test
    public void missingUserRequestVerification() {
        reqResDef.requestSingleUser(112223);
        reqResDef.assertResponseCodeTo(404);
    }

    @Test
    public void createANewUser() {
        reqResDef.postCreateUser("bob", "the tester");
        reqResDef.assertResponseCodeTo(201);
    }
}
