package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {

    private static RequestSpecification reqSpec = RestAssured.given();
    private static Response response;
    private static String endpoint;

    public static void setEndpoint(String endpoint) {
        RestAssuredUtils.endpoint = endpoint;
    }

    public static void setPathParam(String key, Object value) {
        reqSpec = reqSpec.pathParam(key, value);
    }

    public static void setQueryParam(String key, Object value) {
        reqSpec = reqSpec.queryParam(key, value);
    }

    public static void setHeader(String key, Object value) {
        reqSpec = reqSpec.header(key, value);
    }

    public static void get() {
        response = reqSpec.when().log().all().get(endpoint);
        response.then().log().all();
    }

    public static void post() {
        response = reqSpec.when().log().all().post(endpoint);
        response.then().log().all();
    }

    public static Response getResponse() {
        return response;
    }

    public static int getStatusCode() {
        return response.getStatusCode();
    }

    public static void setAuthorizationToken() {
        RestAssuredUtils.setEndpoint("/auth");
        String username = ConfigFileReaderUtils.getProperty("api.username");
        String password = ConfigFileReaderUtils.getProperty("api.password");
        RestAssuredUtils.setQueryParam("username", username);
        RestAssuredUtils.setQueryParam("password", password);
        RestAssuredUtils.post();
    }

    public static String getAuthorizationToken() {
        System.out.println("Bearer " + response.jsonPath().getString("authToken"));
        return "Bearer " + response.jsonPath().getString("authToken");
    }

}
