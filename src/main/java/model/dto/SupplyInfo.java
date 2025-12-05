package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplyInfo {

    private String supplierID;
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private String notes;

}
