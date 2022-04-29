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
	private final Person ownerCode;
	private final Person managerCode;
	private final Person beneficiaryCode;

	private List<Asset> assetList;

	private Integer accountId;

	protected Account(String accountCode, Person ownerCode, Person managerCode, Person beneficiaryCode) {
		this.accountCode = accountCode;
		this.ownerCode = ownerCode;
		this.managerCode = managerCode;
		this.beneficiaryCode = beneficiaryCode;
		this.assetList = new ArrayList<Asset>();
	}

	protected Account(Integer accountId, String accountCode, Person ownerCode, Person managerCode,
			Person beneficiaryCode) {
		this.accountCode = accountCode;
		this.ownerCode = ownerCode;
		this.managerCode = managerCode;
		this.beneficiaryCode = beneficiaryCode;
		this.accountId = accountId;
		this.assetList = new ArrayList<Asset>();
	}

	protected final String getAccountCode() {
		return this.accountCode;
	}

	public final Person getOwnerCode() {
		return this.ownerCode;
	}

	public final Person getManagerCode() {
		return this.managerCode;
	}

	protected final Person getBeneficiaryCode() {
		return this.beneficiaryCode;
	}

	protected final List<Asset> getAssetList() {
		return this.assetList;
	}
	
	protected final Integer getAccountId() {
		return this.accountId;
	}

	/**
	 * Takes in an asset and adds that asset to a list of assets.
	 * 
	 * @param a
	 */
	protected abstract void addAsset(Asset a);

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
	public final double getTotalReturnPercent() {
		double totalGain = getTotalReturn();
		double count = 0.0;
		for (Asset a : this.getAssetList()) {
			count += a.getPurchasedPrice();
		}
		if (count == 0.0) {
			if (this.getTotalValue() > 0.0) {
				return 100.0;
			} else {
				return -100.0;
			}
		}
		return (totalGain / count) * 100.0;
	}

	/**
	 * A method to get the total value for an account.
	 * 
	 * @return
	 */
	public final double getTotalValue() {
		double totalValue = 0.0;
		for (Asset a : this.getAssetList()) {
			totalValue += a.getValue();
		}
		return totalValue;
	}

	/**
	 * A method to get the total cost basis for an account.
	 * 
	 * @return
	 */
	protected final double getTotalCostBasis() {
		double totalCostBasis = 0.0;
		for (Asset a : this.getAssetList()) {
			totalCostBasis += a.getPurchasedPrice();
		}
		return totalCostBasis;
	}

	/**
	 * Abstract method to get fees in various ways for different types of accounts
	 * and assets.
	 * 
	 * @return
	 */
	protected abstract double getFees();

	public final String toString() {
		if (this.getBeneficiaryCode() != null) {
			return this.accountCode + " " + this.ownerCode.getPersonCode() + " " + this.managerCode.getPersonCode()
					+ " " + this.beneficiaryCode.getPersonCode();
		}
		return this.accountCode + " " + this.ownerCode.getPersonCode() + " " + this.managerCode.getPersonCode() + " "
				+ "----";
	}
}