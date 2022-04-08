package com.iffi;

import java.time.LocalDate;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * A class representing the different attributes that a stock has.
 *
 * @author tre-vawnrainey, ethanwood
 *
 */
public class Stock extends Asset {
	protected final String symbol;
	protected final double currentSharePrice;

	@XStreamOmitField
	private double purchasedSharePrice;
	@XStreamOmitField
	private double numShares;
	@XStreamOmitField
	private double dividendTotal;

	protected Stock(String code, String label, String symbol, double currentSharePrice) {
		super(code, label);
		this.symbol = symbol;
		this.currentSharePrice = currentSharePrice;
	}
	protected Stock(Asset a, String symbol, double currentSharePrice) {
		super(a.code, a.label);
		this.symbol = symbol;
		this.currentSharePrice = currentSharePrice;
	}
	protected Stock(Asset a, LocalDate purchasedDate, double purchasedSharePrice, double numShares,
			double dividendTotal) {
		this(a.assetId, a.code, a.label, ((Stock) a).getSymbol(), ((Stock) a).getCurrentSharePrice());
		this.purchasedDate = purchasedDate;
		this.purchasedSharePrice = purchasedSharePrice;
		this.numShares = numShares;
		this.dividendTotal = dividendTotal;
	}

	protected Stock(Integer assetId, String code, String label, String symbol, double currentSharePrice) {
		super(assetId, code, label);
		this.symbol = symbol;
		this.currentSharePrice = currentSharePrice;
	}
	
	protected Stock(Integer assetId, Stock s, LocalDate purchasedDate, double purchasedSharePrice, double numShares, double dividendTotal) {
		this(s.assetId, s.getCode(), s.getLabel(), s.getSymbol(), s.getCurrentSharePrice());
		this.purchasedDate = purchasedDate;
		this.purchasedSharePrice = purchasedSharePrice;
		this.numShares = numShares;
		this.dividendTotal = dividendTotal;
	}

	protected final String getSymbol() {
		return this.symbol;
	}

	protected final double getCurrentSharePrice() {
		return this.currentSharePrice;
	}

	private final double getPurchasedSharePrice() {
		return this.purchasedSharePrice;
	}

	private final double getNumShares() {
		return this.numShares;
	}

	private final double getDividendTotal() {
		return this.dividendTotal;
	}

	protected double getPurchasedPrice() {
		return this.getNumShares() * this.getPurchasedSharePrice();
	}

	protected double getValue() {
		return (this.getCurrentSharePrice() * this.getNumShares()) + this.getDividendTotal();
	}

	protected double returnPercent() {
		return this.getGain() / this.getPurchasedPrice();
	}

	public String toString() {
		return String.format("%s   %s   %s  (Stock)\n", this.getCode(), this.getLabel(), this.getSymbol())
				+ String.format("  Cost Basis: %.3f shares @ $%.2f on %s\n", this.getNumShares(),
						this.getPurchasedSharePrice(), this.getPurchasedDate())
				+ String.format("  Value Basis: %.3f shares @ $%.2f\n", this.getNumShares(),
						this.getCurrentSharePrice())
				+ String.format(" %90.3f", (this.returnPercent() * 100.0)) + "%"
				+ String.format("     $%.2f\n", this.getValue());
	}
}