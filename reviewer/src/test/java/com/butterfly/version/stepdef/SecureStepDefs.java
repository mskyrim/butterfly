package com.butterfly.version.stepdef;

import com.butterfly.ButterflyApplication;
import com.butterfly.SpringIntegrationTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.TestPropertySource;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


@SpringBootTest(classes = ButterflyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:secure-endpoint-test.properties")
public class SecureStepDefs {
    @LocalServerPort
    private int serverPort;

    private String urlEndpoint;
    private ResponseEntity<String> responseEntity;
    private static final String VERSION_ENDPOINT = "/version";
    protected static TestRestTemplate testRestTemplate = new TestRestTemplate();

    protected String getHttpsServerUrl(){
        return "https://localhost:" + serverPort;
    }

    @Given("App is up and running and the endpoint is secure and ready")
    public void appIsUpAndRunningAndTheEndpointIsReady() {
        this.urlEndpoint = this.getHttpsServerUrl() + VERSION_ENDPOINT;

    }

    @BeforeAll
    public static void beforeAll() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build());
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        ((HttpComponentsClientHttpRequestFactory) testRestTemplate.getRestTemplate().getRequestFactory()).setHttpClient(httpClient);
    }

}
