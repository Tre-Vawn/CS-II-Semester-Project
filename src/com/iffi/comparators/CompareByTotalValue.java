package com.iffi.comparators;

import java.util.Comparator;

import com.iffi.Account;

public class CompareByTotalValue implements Comparator<Account> {

	public int compare(Account a1, Account a2) {
		if (a1.getTotalValue() > a2.getTotalValue()) {
			return -1;
		} else if (a1.getTotalValue() > a2.getTotalValue()) {
			return 1;
		} else {
			return 0;
		}
	}
}