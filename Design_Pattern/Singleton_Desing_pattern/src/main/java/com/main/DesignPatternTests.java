package com.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DesignPatternTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void homeResponse() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertEquals("Spring is here!", body);
	}

    @LocalServerPort
    private int port;

    private String baseUrl(String params) {
        return "http://localhost:" + port + "/user" + params;
    }

    // ✅ Case 1: Valid role and matching access path
    @Test
    void testValidUserAndValidPath() {
        String url = "/user?userName=Aditya&roleDescription=user access&accessPath=user access";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Aditya"));
    }

    // ❌ Case 2: Valid role but wrong access path
    @Test
    void testValidRoleButInvalidAccessPath() {
        String url = baseUrl("?userName=Alice&roleDescription=user access&accessPath=admin access");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertTrue(response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError());
    }

    // ❌ Case 3: Invalid role description
    @Test
    void testInvalidRoleDescription() {
        String url = baseUrl("?userName=Bob&roleDescription=superuser&accessPath=user access");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertTrue(response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError());
    }

    // ❌ Case 4: Missing parameters (Bad Request)
    @Test
    void testMissingParameters() {
        String url = baseUrl("?userName=Tom&roleDescription=user access"); // Missing accessPath
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
