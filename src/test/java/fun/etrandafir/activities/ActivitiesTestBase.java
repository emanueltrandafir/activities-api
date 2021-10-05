package fun.etrandafir.activities;

import fun.etrandafir.activities.persistence.model.Activity;
import fun.etrandafir.activities.persistence.model.ActivityType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import java.util.stream.Collectors;


//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@DirtiesContext
//@AutoConfigureMessageVerifier

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ActivitiesTestBase {

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    public List<Activity> skipFirst(List<Activity> list) {
        return list.stream().skip(1).collect(Collectors.toList());
    }

    public List<Activity> testActivities() {
        return List.of(
            Activity.builder().type(ActivityType.CYCLING).durationInMinutes(30).id(1L).build(),
            Activity.builder().type(ActivityType.SPIKEBALL).durationInMinutes(60).id(2L).build(),
            Activity.builder().type(ActivityType.SPIKEBALL).durationInMinutes(90).id(3L).build());
    }
}
