package fun.etrandafir.activities.controller;

import fun.etrandafir.activities.persistence.model.Activity;
import fun.etrandafir.activities.persistence.repository.ActivityRepository;
import fun.etrandafir.activities.persistence.specification.ActivitySpecificationsBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ActivitiesController {

    private final ActivityRepository repository;

    @GetMapping("activities")
    public List<Activity> find(@RequestParam(value = "search", required = false) String search) {
        return repository.findAll(ActivitySpecificationsBuilder.fromString(search));
    }

    @GetMapping("overview")
    public List<ActivityRepository.ActivitiesOverview> group() {
        return repository.groupByType();
    }
}
