package org.example;

import io.qameta.allure.Step;

import static org.example.model.MainPage.MAIN_PAGE_URL;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    public static final RequestSpecification REQUEST_SPECIFICATION =
            new RequestSpecBuilder()
                    .setBaseUri(MAIN_PAGE_URL + "/api")
                    .setBasePath("/auth")
                    .setContentType(ContentType.JSON)
                    .build();

    @Step("Создаём уникального юзера")
    public static Response createUniqueUser(UserRequest body) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(body)
                .when()
                .post("/register");
    }

    @Step("Удаляем пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .header("Authorization", accessToken)
                .when()
                .delete("/user");
    }

    @Step("Выполняем авторизацию с помощью тела запроса на авторизацию")
    public static Response signInWithSignInRequest(SignInRequest signInRequest) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(signInRequest)
                .when()
                .post("/login");
    }
}
