package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    private  String customerID;
    private  String customerName;
    private  String customerAddress;
    private  String  phone;
    private  String email;
    private  String postalcode;


}
