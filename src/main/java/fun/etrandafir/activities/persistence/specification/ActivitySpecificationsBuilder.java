package fun.etrandafir.activities.persistence.specification;

import fun.etrandafir.activities.persistence.model.Activity;
import org.springframework.data.jpa.domain.Specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivitySpecificationsBuilder {

    private Specification spec;

    public ActivitySpecificationsBuilder with(String key, String operation, Object value) {
        var newSpec = new ActivitySpecification(key, operation, value);
        if(spec == null) {
            spec = newSpec;
        } else {
            spec = spec.and(newSpec);
        }
        return this;
    }

    public Specification<Activity> build() {
        return spec;
    }

    public static Specification<Activity> fromString(String queryString) {
        if(queryString==null) {
            return null;
        }
        ActivitySpecificationsBuilder builder = new ActivitySpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(queryString + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return builder.build();
    }
}