package com.iffi;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the different attributes that an account has.
 *
 * @author tre-vawnrainey, ethanwood
 *
 */
public abstract class Account {
	private final String accountCode;
	private final Person owner;
	private final Person manager;
	private final Person beneficiary;
	private List<Asset> assetList;

	private Integer accountId;

	protected Account(String accountCode, Person owner, Person manager, Person beneficiary) {
		this.accountCode = accountCode;
		this.owner = owner;
		this.manager = manager;
		this.beneficiary = beneficiary;
		this.assetList = new ArrayList<Asset>();
	}

	protected Account(Integer accountId, String accountCode, Person owner, Person manager, Person beneficiary) {
		this.accountCode = accountCode;
		this.owner = owner;
		this.manager = manager;
		this.beneficiary = beneficiary;
		this.accountId = accountId;
	}

	protected final String getAccountCode() {
		return this.accountCode;
	}

	protected final Person getOwner() {
		return this.owner;
	}

	protected final Person getManager() {
		return this.manager;
	}

	protected final Person getBeneficiary() {
		return this.beneficiary;
	}

	protected final List<Asset> getAssetList() {
		return this.assetList;
	}

	@SuppressWarnings("unused")
	private final Integer getAccountId() {
		return this.accountId;
	}

	/**
	 * Takes in an asset and adds that asset to a list of assets.
	 * 
	 * @param a
	 */
	protected final void addAsset(Asset a) {
		this.assetList.add(a);
	}

	/**
	 * A method to get the total return for an account.
	 * 
	 * @return
	 */
	protected final double getTotalReturn() {
		double count = 0.0;
		for (Asset a : this.getAssetList()) {
			count += a.getGain();
		}
		return count;
	}

	/**
	 * A method to get the total return percent for an account.
	 * 
	 * @return
	 */
	protected final double getTotalReturnPercent() {
		double totalGain = getTotalReturn();
		double count = 0.0;
		for (Asset a : this.getAssetList()) {
			count += a.getPurchasedPrice();
		}
		return (totalGain / count) * 100.0;
	}

	/**
	 * A method to get the total value for an account.
	 * 
	 * @return
	 */
	protected final double getTotalValue() {
		double count = 0.0;
		for (Asset a : this.getAssetList()) {
			count += a.getValue();
		}
		return count;
	}

	/**
	 * Abstract method to get fees in various ways for different types of accounts
	 * and assets.
	 * 
	 * @return
	 */
	protected abstract double getFees();

	public final String toString() {
		return this.accountCode + " " + this.owner.getCode() + " " + this.manager.getCode() + " "
				+ this.beneficiary.getCode() + " " + this.assetList;
	}
}