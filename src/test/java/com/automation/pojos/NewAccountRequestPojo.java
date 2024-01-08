package com.automation.pojos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewAccountRequestPojo {
    String accountName;
    String accountTypeCode;
    double openingDeposit;
    String ownerTypeCode;
}
