package model.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {

    private String OrderID;
    private LocalDate OrderDate;
    private  String customerID;
}
