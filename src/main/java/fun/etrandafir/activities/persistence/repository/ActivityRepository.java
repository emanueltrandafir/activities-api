package fun.etrandafir.activities.persistence.repository;

import fun.etrandafir.activities.persistence.model.Activity;
import fun.etrandafir.activities.persistence.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {

    @Query("SELECT a.type as type, COUNT(a.type) as numberOfActivities, SUM(a.durationInMinutes) as totalDurationInMinutes "
            + " FROM Activity AS a GROUP BY a.type")
    List<ActivitiesOverview> groupByType();


    interface ActivitiesOverview {
        ActivityType getType();
        int getNumberOfActivities();
        int getTotalDurationInMinutes();
    }
}
