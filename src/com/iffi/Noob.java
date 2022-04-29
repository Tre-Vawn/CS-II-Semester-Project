package com.iffi;

/**
 * A class representing the different attributes that a noob account has.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class Noob extends Account {

	protected Noob(String accountCode, Person owner, Person manager, Person beneficiary) {
		super(accountCode, owner, manager, beneficiary);
	}

	protected Noob(Integer accountId, String accountCode, Person owner, Person manager, Person beneficiary) {
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
		return fees;
	}

	@Override
	protected void addAsset(Asset a) {
		getAssetList().add(a);
	}
}