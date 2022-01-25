package com.butterfly;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

//@CucumberContextConfiguration
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(classes = ButterflyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {

    @LocalServerPort
    private int serverPort;

    protected static TestRestTemplate testRestTemplate = new TestRestTemplate();

    protected String getHttpServerUrl(){
        return "http://localhost:" + serverPort;
    }
    protected String getHttpsServerUrl(){
        return "https://localhost:" + serverPort;
    }
}
