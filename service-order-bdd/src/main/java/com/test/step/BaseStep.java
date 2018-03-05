package com.test.step;

import com.test.common.CucumberRequest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public abstract class BaseStep {
    private static final String BASE_URI = "http://localhost:8763";
    private static ThreadLocal<CucumberRequest> cucumberRequest = new ThreadLocal<>();
    private static ThreadLocal<ValidatableResponse> response = new ThreadLocal<>();

    private void setResponse(ValidatableResponse validatableResponse) {
        response.set(validatableResponse);
    }

    private CucumberRequest getRequest() {
        return cucumberRequest.get();
    }

    ValidatableResponse getResponse() {
        return response.get();
    }

    void newRequest(CucumberRequest request) {
        cucumberRequest.remove();
        response.remove();
        cucumberRequest.set(request);
    }

    void sendRequest() {
        Response response = given()
                .baseUri(BASE_URI)
                .pathParams(getRequest().getPathParams())
                .queryParams(getRequest().getQueryParams())
                .when()
                .get(getRequest().getPath())
                .then()
                .extract()
                .response();

        setResponse(response.then());
    }

}