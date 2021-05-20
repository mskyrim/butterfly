package com.butterfly.version;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:build/test-results/version.html",
                            "json:build/test-results/version.json"},
        features = {"classpath:features/version.feature"},
        glue = {"version.stepdef"}
)
public class VersionTestRunner {
}
