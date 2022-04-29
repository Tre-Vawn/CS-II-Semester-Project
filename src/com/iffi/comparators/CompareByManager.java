package com.iffi.comparators;

import java.util.Comparator;

import com.iffi.Account;

public class CompareByManager implements Comparator<Account>{
	
	public int compare(Account a1, Account a2) {
		return (a1.getManagerCode().getName().getLastName()).compareTo(a2.getManagerCode().getName().getLastName());
	}
}