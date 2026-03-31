package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailShowDTO {
    private String customerId;
    private String customerName;
    private String orderId;
    private String itemCode;
    private int quantity;
    private double discount;
    private String date;


}