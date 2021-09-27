package fun.etrandafir.activities.persistence.repository;

import fun.etrandafir.activities.persistence.model.Activity;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ActivityRepository {
    private final Map<Long, Activity> activities = Map.of(
            1L, new Activity(1L, "cycling", 120),
            2L, new Activity(2L, "bouldering", 90),
            3L, new Activity(3L, "running", 60),
            4L, new Activity(4L, "cycling", 120)
    );

    public List<Activity> findAll() {
        return activities.values().stream().collect(Collectors.toList());
    }
}
