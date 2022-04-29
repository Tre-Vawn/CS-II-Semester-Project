package com.iffi.comparators;

import java.util.Comparator;

import com.iffi.Account;

/**
 * This class compares two accounts by their total value and sorts them in
 * descending order.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class CompareByTotalValue implements Comparator<Account> {

	public final int compare(Account a1, Account a2) {
		if (a1.getTotalValue() > a2.getTotalValue()) {
			return -1;
		} else if (a1.getTotalValue() < a2.getTotalValue()) {
			return 1;
		} else {
			return 0;
		}
	}
}
