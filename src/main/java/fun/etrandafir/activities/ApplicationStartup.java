package fun.etrandafir.activities;

import fun.etrandafir.activities.persistence.model.Activity;
import fun.etrandafir.activities.persistence.model.ActivityType;
import fun.etrandafir.activities.persistence.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name="db.mockData", havingValue="true")
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final ActivityRepository repo;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        repo.saveAll(List.of(
                Activity.builder().type(ActivityType.CYCLING).name("aaa").durationInMinutes(180).build(),
                Activity.builder().type(ActivityType.SPIKEBALL).name("bbb").durationInMinutes(180).build(),
                Activity.builder().type(ActivityType.BOULDERING).name("ccc").durationInMinutes(120).build(),
                Activity.builder().type(ActivityType.ROCK_CLIMBING).name("ddd").durationInMinutes(90).build(),
                Activity.builder().type(ActivityType.ROCK_CLIMBING).name("eee").durationInMinutes(90).build(),
                Activity.builder().type(ActivityType.RUNNING).name("111").durationInMinutes(90).build(),
                Activity.builder().type(ActivityType.RUNNING).name("222").durationInMinutes(80).build(),
                Activity.builder().type(ActivityType.RUNNING).name("333").durationInMinutes(30).build(),
                Activity.builder().type(ActivityType.BOULDERING).name("fff").durationInMinutes(200).build()
        ));
    }
}