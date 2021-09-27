package fun.etrandafir.activities.persistence.specification;

import fun.etrandafir.activities.persistence.model.Activity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivitySpecificationsBuilder {

    private final List<SearchCriteria> params = new ArrayList<SearchCriteria>();

    public ActivitySpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Activity> build() {
        if (params.size() == 0) {
            return null;
        }

        List<ActivitySpecification> specs = params.stream().map(ActivitySpecification::new).collect(Collectors.toList());
        
        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                ? Specification.where(result)
                  .or(specs.get(i))
                : Specification.where(result)
                  .and(specs.get(i));
        }       
        return result;
    }
}