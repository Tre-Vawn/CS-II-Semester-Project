package com.iffi;

import java.time.LocalDate;

/**
 * A class representing the different attributes that a stock option has.
 *
 * @author tre-vawnrainey, ethanwood
 *
 */
public class StockOption extends Stock {
	protected final double strikePrice;
	protected final double shareLimit;
	protected final double premium;
	protected final LocalDate strikeDate;

	protected StockOption(Stock s, LocalDate purchasedDate, double strikePrice, double shareLimit, double premium,
			LocalDate strikeDate) {
		super(s, s.symbol, s.currentSharePrice);
		this.purchasedDate = purchasedDate;
		this.strikePrice = strikePrice;
		this.shareLimit = shareLimit;
		this.premium = premium;
		this.strikeDate = strikeDate;
	}

	protected StockOption(Integer assetId, StockOption s) {
		this(s, s.getPurchasedDate(), s.getStrikePrice(), s.getShareLimit(), s.getPremium(), s.getStrikeDate());
		this.assetId = assetId;
	}

	protected final double getStrikePrice() {
		return this.strikePrice;
	}

	protected final double getShareLimit() {
		return this.shareLimit;
	}

	protected final double getPremium() {
		return this.premium;
	}

	protected final LocalDate getStrikeDate() {
		return this.strikeDate;
	}

	protected final double getPremiumTotal() {
		return this.premium * this.shareLimit;
	}
}