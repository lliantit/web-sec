package chnu.edu.websec.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document




public class Car extends Auditable {
    private String id;
    private String name;
    private String year;
    private String description;
    private String color;

    public Car(String description, String name) {
        this.description = description;
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Car car)) return false;

        return getId().equals(car.getId());
    }



    @Override
    public int hashCode() {
        return getId().hashCode();
    }


}
