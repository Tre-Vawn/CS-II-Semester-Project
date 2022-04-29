package com.iffi;

import java.time.LocalDate;

/**
 * A class representing the different attributes that an asset has.
 *
 * @author tre-vawnrainey, ethanwood
 *
 */
public abstract class Asset {
	protected final String assetCode;
	protected final String label;

	protected LocalDate purchasedDate;

	protected Integer assetId;

	protected Asset(String assetCode, String label) {
		this.assetCode = assetCode;
		this.label = label;
	}

	protected Asset(Integer assetId, String assetCode, String label) {
		this.assetId = assetId;
		this.assetCode = assetCode;
		this.label = label;
	}

	protected final String getAssetCode() {
		return this.assetCode;
	}

	protected final String getLabel() {
		return this.label;
	}

	protected final LocalDate getPurchasedDate() {
		return this.purchasedDate;
	}

	protected final Integer getAssetId() {
		return this.assetId;
	}

	/**
	 * Gets the gain of an asset by subtracting the amount it was purchased for from
	 * its current value.
	 * 
	 * @return
	 */
	protected final double getGain() {
		return this.getValue() - this.getPurchasedPrice();
	}

	/**
	 * An abstract method to get the current value in various ways for different
	 * types of assets.
	 * 
	 * @return
	 */
	protected abstract double getValue();

	/**
	 * An abstract method to get the purchased price in various ways for different
	 * types assets.
	 * 
	 * @return
	 */
	protected abstract double getPurchasedPrice();

	/**
	 * An abstract method to get the return percentage in various ways for different
	 * types assets.
	 * 
	 * @return
	 */
	protected abstract double returnPercent();

	public abstract String toString();
}