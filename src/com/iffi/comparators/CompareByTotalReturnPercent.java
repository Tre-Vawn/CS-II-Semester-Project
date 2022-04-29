package com.iffi.comparators;

import java.util.Comparator;

import com.iffi.Account;

/**
 * This class compares two accounts by their total return percent and sorts them
 * in descending order.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class CompareByTotalReturnPercent implements Comparator<Account> {

	public final int compare(Account a1, Account a2) {
		if (a1.getTotalReturnPercent() > a2.getTotalReturnPercent()) {
			return -1;
		} else if (a1.getTotalReturnPercent() < a2.getTotalReturnPercent()) {
			return 1;
		} else {
			return 0;
		}
	}
}
