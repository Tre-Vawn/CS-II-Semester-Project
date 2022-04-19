package com.iffi;

import java.time.LocalDate;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * A class representing the different attribute that a property has.
 *
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class Property extends Asset {
	private final double currentAppraisedValue;

	@XStreamOmitField
	private double purchasedPrice;

	protected Property(String assetCode, String label, double currentAppraisedValue) {
		super(assetCode, label);
		this.currentAppraisedValue = currentAppraisedValue;
	}

	protected Property(Property p, LocalDate purchasedDate, double purchasedPrice) {
		this(p.getAssetCode(), p.getLabel(), p.getCurrentAppraisedValue());
		this.purchasedDate = purchasedDate;
		this.purchasedPrice = purchasedPrice;
	}
	
	protected Property(Integer assetId, Property p, LocalDate purchasedDate, double purchasedPrice) {
		this(p.getAssetCode(), p.getLabel(), p.getCurrentAppraisedValue());
		this.assetId = assetId;
		this.purchasedDate = purchasedDate;
		this.purchasedPrice = purchasedPrice;
	}
	
	private final double getCurrentAppraisedValue() {
		return this.currentAppraisedValue;
	}

	protected final double getPurchasedPrice() {
		return this.purchasedPrice;
	}

	protected final double getValue() {
		return this.getCurrentAppraisedValue();
	}

	protected final double returnPercent() {
		return this.getGain() / this.getPurchasedPrice();
	}

	public final String toString() {
		return String.format("%s   %s   (Property)\n", this.getAssetCode(), this.getLabel())
				+ String.format("  Cost Basis: purchased @ $%.2f on %s\n", this.getPurchasedPrice(),
						this.getPurchasedDate())
				+ String.format("  Value Basis: appraised @ $%.2f\n", this.getValue())
				+ String.format(" %90.3f", (this.returnPercent() * 100.0)) + "%"
				+ String.format("     $%.2f\n", this.getValue());
	}
}