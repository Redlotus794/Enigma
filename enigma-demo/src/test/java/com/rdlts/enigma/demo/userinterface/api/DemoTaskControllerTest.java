package com.rdlts.enigma.demo.userinterface.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdlts.enigma.ddd.core.event.DomainEventPublisher;
import com.rdlts.enigma.ddd.core.event.DomainEventRepository;
import com.rdlts.enigma.ddd.core.service.DomainServiceRegistry;
import com.rdlts.enigma.demo.EnigmaDemoApplication;
import com.rdlts.enigma.test.annotation.EnigmaWebSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Collections;

/**
 * DemoTaskController 集成测试。
 *
 * @author wangjialong
 * @since 2026/05/17 10:00
 */
@EnigmaWebSpringBootTest(classes = EnigmaDemoApplication.class)
public class DemoTaskControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    private DomainEventRepository domainEventRepository;

    @Autowired
    private DomainServiceRegistry domainServiceRegistry;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    /**
     * 验证 starter 自动装配与 CRUD 接口流程。
     *
     * @throws IOException JSON 解析异常
     */
    @Test
    void shouldLoadStarterBeansAndSupportCrudApi() throws IOException {
        Assertions.assertNotNull(domainEventPublisher);
        Assertions.assertNotNull(domainEventRepository);
        Assertions.assertNotNull(domainServiceRegistry);

        ResponseEntity<String> emptyListResponse = testRestTemplate.getForEntity(url("/api/v1/demo-tasks"), String.class);
        Assertions.assertEquals(HttpStatus.OK, emptyListResponse.getStatusCode());
        Assertions.assertEquals(0, read(emptyListResponse).path("data").size());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<java.util.Map<String, String>> createRequest = new HttpEntity<>(
                Collections.singletonMap("name", "learn enigma starter"), headers);
        ResponseEntity<String> createResponse = testRestTemplate.postForEntity(url("/api/v1/demo-tasks"), createRequest, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        JsonNode createdNode = read(createResponse).path("data");
        String id = createdNode.path("id").asText();
        Assertions.assertFalse(id.isEmpty());
        Assertions.assertEquals("learn enigma starter", createdNode.path("name").asText());

        ResponseEntity<String> getResponse = testRestTemplate.getForEntity(url("/api/v1/demo-tasks/" + id), String.class);
        Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        Assertions.assertEquals(id, read(getResponse).path("data").path("id").asText());

        ResponseEntity<String> deleteResponse = testRestTemplate.exchange(
                url("/api/v1/demo-tasks/" + id), HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        Assertions.assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        Assertions.assertEquals("SUCCESS", read(deleteResponse).path("code").asText());

        ResponseEntity<String> notFoundResponse = testRestTemplate.getForEntity(url("/api/v1/demo-tasks/" + id), String.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
        Assertions.assertEquals("NOT_FOUND", read(notFoundResponse).path("code").asText());
    }

    /**
     * 验证空名称请求被正确拦截。
     *
     * @throws IOException JSON 解析异常
     */
    @Test
    void shouldRejectBlankTaskName() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<java.util.Map<String, String>> request = new HttpEntity<>(
                Collections.singletonMap("name", "   "), headers);

        ResponseEntity<String> response = testRestTemplate.postForEntity(url("/api/v1/demo-tasks"), request, String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        JsonNode body = read(response);
        Assertions.assertEquals("BAD_REQUEST", body.path("code").asText());
        Assertions.assertTrue(body.path("message").asText().contains("must not be blank"));
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    private JsonNode read(ResponseEntity<String> responseEntity) throws IOException {
        return objectMapper.readTree(responseEntity.getBody());
    }
}


