package com.iffi;

import java.time.LocalDate;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * A class representing the different attribute that a cryptocurrency has.
 *
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class Cryptocurrency extends Asset {
	private final double currentExchangeRate;
	private final double exchangeFeeRate;

	@XStreamOmitField
	private double purchasedExchangeRate;
	@XStreamOmitField
	private double numCoins;

	protected Cryptocurrency(String code, String label, double currentExchangeRate, double exchangeFeeRate) {
		super(code, label);
		this.currentExchangeRate = currentExchangeRate;
		this.exchangeFeeRate = exchangeFeeRate;
	}

	protected Cryptocurrency(Cryptocurrency c, LocalDate purchasedDate, double purchasedExchangeRate, double numCoins) {
		this(c.getCode(), c.getLabel(), c.getCurrentExchangeRate(), c.getExchangeFeeRate());
		this.purchasedDate = purchasedDate;
		this.purchasedExchangeRate = purchasedExchangeRate;
		this.numCoins = numCoins;
	}

	protected Cryptocurrency(Integer assetId, Cryptocurrency c, LocalDate purchasedDate, double purchasedExchangeRate,
			double numCoins) {
		this(c.getCode(), c.getLabel(), c.getCurrentExchangeRate(), c.getExchangeFeeRate());
		this.assetId = assetId;
		this.purchasedDate = purchasedDate;
		this.purchasedExchangeRate = purchasedExchangeRate;
		this.numCoins = numCoins;
	}

	private final double getCurrentExchangeRate() {
		return this.currentExchangeRate;
	}

	private final double getExchangeFeeRate() {
		return this.exchangeFeeRate;
	}

	private final double getPurchasedExchangeRate() {
		return this.purchasedExchangeRate;
	}

	private final double getNumCoins() {
		return this.numCoins;
	}

	protected final double getPurchasedPrice() {
		return this.getNumCoins() * this.getPurchasedExchangeRate();
	}

	protected final double getValue() {
		return this.getNumCoins() * this.getCurrentExchangeRate() * (1.0 - (this.getExchangeFeeRate() / 100.0));
	}

	protected final double returnPercent() {
		return this.getGain() / this.getPurchasedPrice();
	}

	public final String toString() {
		return String.format("%s   %s   (Cryptocurrency)\n", this.getCode(), this.getLabel())
				+ String.format("  Cost Basis: %.3f coins @ $%.2f on %s\n", this.getNumCoins(),
						this.getPurchasedExchangeRate(), this.getPurchasedDate())
				+ String.format("  Value Basis: %.3f coins @ $%.2f less %.2f", this.getNumCoins(),
						this.getCurrentExchangeRate(), this.getExchangeFeeRate())
				+ "%" + String.format(" %41.3f", (this.returnPercent() * 100.0)) + "%"
				+ String.format("     $%.2f\n", this.getValue());
	}
}