package com.automation.pojos;

import lombok.Data;

@Data
public class AccountType {
	private double interestRate;
	private double overdraftFee;
	private String code;
	private String name;
	private double overdraftLimit;
	private int id;
	private double minDeposit;
	private String category;
}