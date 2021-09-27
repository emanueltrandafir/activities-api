package fun.etrandafir.activities.persistence.repository;

import fun.etrandafir.activities.persistence.model.Activity;
import fun.etrandafir.activities.persistence.model.ActivityType;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {
    List<Activity> findByType(ActivityType type);
}
