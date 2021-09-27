package fun.etrandafir.activities.persistence.specification;

import fun.etrandafir.activities.persistence.model.Activity;
import fun.etrandafir.activities.persistence.model.ActivityType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class ActivitySpecification implements Specification<Activity> {

    private final String key;
    private final String operation;
    private final Object value;

    @Override
    public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        switch (operation) {
            case ">": return greaterThanOrEqualTo(root, builder);
            case "<":                 return lessThanOrEqualTo(root, builder);
            case ":":
                return equalIgnoreCase(root, builder);
            default:
                throw new IllegalArgumentException(operation + " is not a supported operation type!");
        }
    }

    private Predicate equalIgnoreCase(Root<Activity> root, CriteriaBuilder builder) {
        var valueType = root.get(key).getJavaType();
        if (root.get(key).getJavaType() == String.class) {
            return builder.like(builder.lower(root.<String>get(key)), "%" + value.toString().toLowerCase() + "%");
        } else if(valueType == ActivityType.class) {
            return builder.equal(root.<ActivityType>get(key), value);
        } else {
            return builder.equal(root.get(key), value);
        }
    }

    private Predicate lessThanOrEqualTo(Root<Activity> root, CriteriaBuilder builder) {
        return builder.lessThanOrEqualTo(root.<String>get(key), value.toString());
    }

    private Predicate greaterThanOrEqualTo(Root<Activity> root, CriteriaBuilder builder) {
        return builder.greaterThanOrEqualTo(root.<String>get(key), value.toString());
    }
}