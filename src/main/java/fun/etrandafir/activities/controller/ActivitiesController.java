package fun.etrandafir.activities.controller;

import fun.etrandafir.activities.persistence.model.Activity;
import fun.etrandafir.activities.persistence.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("activities/api")
public class ActivitiesController {

    private final ActivityRepository repository;

    @GetMapping
    public List<Activity> findAll() {
        return repository.findAll();
    }
}
