package com.iffi.comparators;

import java.util.Comparator;
import com.iffi.Account;

public class CompareByOwner implements Comparator<Account> {
	@Override
	public int compare(Account a1, Account a2) {
		return (a1.getOwnerCode().getName().getLastName()).compareTo(a2.getOwnerCode().getName().getLastName());
	}
}
