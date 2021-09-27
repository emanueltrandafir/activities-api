package fun.etrandafir.activities.controller;

import fun.etrandafir.activities.persistence.model.Activity;
import fun.etrandafir.activities.persistence.model.ActivityType;
import fun.etrandafir.activities.persistence.repository.ActivityRepository;
import fun.etrandafir.activities.persistence.specification.ActivitySpecificationsBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.AbstractType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("activities/api")
public class ActivitiesController {

    private final ActivityRepository repository;

    @GetMapping
    public List<Activity> find(@RequestParam(value = "search") String search) {
        ActivitySpecificationsBuilder builder = new ActivitySpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Activity> spec = builder.build();
        return repository.findAll(spec);
    }
}
