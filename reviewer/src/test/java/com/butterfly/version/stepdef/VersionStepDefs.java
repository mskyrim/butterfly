package com.butterfly.version.stepdef;

import com.butterfly.SpringIntegrationTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;

@CucumberContextConfiguration
public class VersionStepDefs extends SpringIntegrationTest {
    private String urlEndpoint;
    private ResponseEntity<String> responseEntity;
    private static final String VERSION_ENDPOINT = "/version";
    @When("^the client calls /version$")
    public void the_client_issues_GET_version() throws Throwable{
        this.responseEntity = this.testRestTemplate.getForEntity(this.urlEndpoint, String.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        Assertions.assertEquals(statusCode, this.responseEntity.getStatusCodeValue());
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) throws Throwable {
        Assertions.assertEquals(version, this.responseEntity.getBody());
    }

    @Given("App is up and running and the endpoint is ready")
    public void appIsUpAndRunningAndTheEndpointIsReady() {
        this.urlEndpoint = this.getHttpServerUrl() + VERSION_ENDPOINT;
    }


}
