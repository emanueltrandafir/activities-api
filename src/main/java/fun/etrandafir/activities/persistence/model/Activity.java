package fun.etrandafir.activities.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private Long id;
    private String name;
    private Integer durationInMinutes;
}
