package com.iffi;

/**
 * A class representing the different attributes that a put has.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class Put extends StockOption {
	protected Put(StockOption so) {
		super(so, so.strikePrice, so.shareLimit, so.premium, so.strikeDate);
	}

	protected Put(Integer assetId, StockOption so) {
		this(so);
		this.assetId = assetId;
	}
	
	private final double getLongPut() {
		return this.getPremium() * this.getShareLimit();
	}

	private final double getShortPut() {
		return (this.getStrikePrice() - this.getCurrentSharePrice() + this.getPremium()) * this.getShareLimit();
	}

	protected final double getPurchasedPrice() {
		return 0.0;
	}

	protected final double getValue() {
		if (this.getCurrentSharePrice() > this.getStrikePrice()) {
			return this.getShortPut();
		}
		return this.getLongPut();
	}

	protected final double returnPercent() {
		if (this.getGain() > 0.0) {
			return 1.0;
		} else if (this.getGain() < 0.0) {
			return -1.0;
		}
		return 0.0;
	}

	public final String toString() {
		if (this.getCurrentSharePrice() > this.getStrikePrice()) {
			return String.format("%s   %s   Option   %s   (Put)\n", this.getCode(), this.getLabel(), this.getSymbol())
					+ String.format("  Sell up to %.3f shares @ $%.2f until %s\n", this.getShareLimit(),
							this.getStrikePrice(), this.getStrikeDate())
					+ String.format("  Premium of $%.2f/share $%.2f total\n", this.getPremium(), this.getPremiumTotal())
					+ String.format("  Share Price: $%.2f\n", this.getCurrentSharePrice())
					+ String.format("  Short Put Value: %.3f shares @\n", getShareLimit())
					+ String.format("     ($%.2f - $%.2f - $%.2f = $%.2f)\n", this.getCurrentSharePrice(),
							this.getStrikePrice(), this.getPremium(), this.getValue())
					+ String.format(" %90.3f", (this.returnPercent() * 100.0)) + "%"
					+ String.format("     $%.2f\n", this.getValue());
		}
		return String.format("%s   %s   Option   %s   (Put)\n", this.getCode(), this.getLabel(), this.getSymbol())
				+ String.format("  Sell up to %.3f shares @ $%.2f until %s\n", this.getShareLimit(),
						this.getStrikePrice(), this.getStrikeDate())
				+ String.format("  Premium of $%.2f/share $%.2f total\n", this.getPremium(), this.getPremiumTotal())
				+ String.format("  Share Price: $%.2f\n", this.getCurrentSharePrice())
				+ String.format("  Long Put Value: = %.3f shares @ $%.2f = $%.2f\n", this.getShareLimit(),
						this.getPremium(), this.getValue())
				+ String.format("%91.3f", (this.returnPercent() * 100.0)) + "%"
				+ String.format("     $%.2f\n", this.getValue());
	}
}