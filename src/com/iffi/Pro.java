package com.iffi;


/**
 * A class representing the different attributes that a pro account has.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class Pro extends Account {

	protected Pro(String accountCode, Person owner, Person manager, Person beneficiary) {
		super(accountCode, owner, manager, beneficiary);
	}

	protected Pro(Integer accountId, String accountCode, Person owner, Person manager, Person beneficiary) {
		super(accountId, accountCode, owner, manager, beneficiary);
	}

	protected final double getFees() {
		double fees = 0.0;
		for (Asset a : this.getAssetList()) {
			if (a.getClass() == Property.class) {
				fees += 100.0;
			} else if (a.getClass() == Cryptocurrency.class) {
				fees += 10.0;
			}
		}
		return fees * .75;
	}

	@Override
	protected void addAsset(Asset a) {
		this.getAssetList().add(a);
	}
}