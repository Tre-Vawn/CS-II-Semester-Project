package com.iffi.comparators;

import java.util.Comparator;

import com.iffi.Account;

public class CompareByTotalReturnPercent implements Comparator<Account>{
	
	public int compare(Account a1, Account a2) {
		if (a1.getTotalReturn() > a2.getTotalReturn()) {
			return -1;
		} else if (a1.getTotalReturn() > a2.getTotalReturn()) {
			return 1;
		} else {
			return 0;
		}
	}
}
