package chnu.edu.websec.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString



public class Car {
    private String id;
    private String name;
    private String year;
    private String description;
    private String color;

}
