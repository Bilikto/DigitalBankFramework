package com.automation.pojos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AccountResponsePojo {

	private int id;
	private String name;
	private int accountNumber;
	private double currentBalance;
	private double openingBalance;
	private double interestRate;
	private double paymentAmount;
	private int paymentTerm;
	private AccountType accountType;
	private OwnershipType ownershipType;
	private AccountStanding accountStanding;
	private String dateOpened;
	private String dateClosed;
	private String paymentDue;

}