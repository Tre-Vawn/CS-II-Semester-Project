package com.iffi.comparators;

import java.util.Comparator;

import com.iffi.Account;

/**
 * This class compares two accounts by their manager's last name and sorts them in
 * lexiographic order.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class CompareByManager implements Comparator<Account>{
	
	public final int compare(Account a1, Account a2) {
		return (a1.getManagerCode().getName().getFirstName()).compareTo(a2.getManagerCode().getName().getFirstName());
	}
}