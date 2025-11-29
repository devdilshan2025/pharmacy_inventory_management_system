package model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockInfo {

    private String itemId;
    private String name;
    private  String brand;
    private LocalDate expDate;
    private int quantity;
    private double price;
}
