package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem {

    private String itemCode;
    private String itemName;
    private int quantity;
    private double unitPrice;
    private double discount;
    private double total;
}
