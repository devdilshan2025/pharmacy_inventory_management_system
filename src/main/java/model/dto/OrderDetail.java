package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail {

    private String orderID;
    private String itemCode;
    private Integer orderQty;
    private Double discount;


}
