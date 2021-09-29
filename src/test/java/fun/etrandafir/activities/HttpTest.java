package fun.etrandafir.activities;

import fun.etrandafir.activities.controller.ActivitiesController;
import fun.etrandafir.activities.persistence.model.Activity;
import fun.etrandafir.activities.persistence.repository.ActivityRepository;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wiremock.com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

public class HttpTest extends ActivitiesTestBase {
    @Autowired
    private ActivitiesController controller;

    @Autowired
    private ActivityRepository repository;

    private Type listOfActivitiesType = new TypeToken<List<Activity>>(){}.getType();

    @AfterEach
    void afterEach() {
        repository.deleteAll(testActivities());
    }

    @Test
    void findAll() {
        var savedActivities = repository.saveAll(testActivities());
        MockMvcRequestSpecification request = given();

        ResponseOptions response = given().spec(request).get("/api/activities");

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat((List<Activity>)response.getBody().as(listOfActivitiesType))
                .containsExactlyInAnyOrderElementsOf(savedActivities);
    }

    @Test
    void findLastTwo() {
        var savedActivities = repository.saveAll(testActivities());
        MockMvcRequestSpecification request = given();

        ResponseOptions response = given().spec(request)
                .queryParam("search","durationInMinutes>45")
                .get("/api/activities");

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat((List<Activity>)response.getBody().as(listOfActivitiesType))
                .containsExactlyInAnyOrderElementsOf(skipFirst(savedActivities));
    }

}
