package com.butterfly.version.stepdef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DummyStepDefs {

    @When("i call dummy")
    public void i_call_dummy() {
        Assert.assertTrue(true);
    }

    @Then("the response dummy")
    public void the_response_dummy() {
        Assert.assertTrue(true);
    }
}
