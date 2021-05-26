package com.butterfly;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = ButterflyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {

    @LocalServerPort
    private int serverPort;

    protected TestRestTemplate testRestTemplate = new TestRestTemplate();

    protected String getServerUrl(){
        return "http://localhost:" + serverPort;
    }
}