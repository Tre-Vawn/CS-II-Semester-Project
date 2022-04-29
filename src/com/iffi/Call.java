package com.iffi;

/**
 * A class representing the different attributes that a call has.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class Call extends StockOption {

	protected Call(StockOption so) {
		super(so, so.strikePrice, so.shareLimit, so.premium, so.strikeDate);
	}

	protected Call(Integer assetId, StockOption so) {
		this(so);
		this.assetId = assetId;
	}

	private final double getLongCall() {
		return 0.0;
	}

	private final double getShortCall() {
		return (this.getCurrentSharePrice() - this.getStrikePrice()) * this.getShareLimit();
	}

	protected final double getPurchasedPrice() {
		return this.getPremium() * this.getShareLimit();
	}

	protected final double getValue() {
		if (this.getCurrentSharePrice() > this.getStrikePrice()) {
			return this.getShortCall();
		}
		return this.getLongCall();
	}

	protected final double returnPercent() {
		return this.getGain() / this.getPurchasedPrice();
	}

	public final String toString() {
		if (this.getCurrentSharePrice() > this.getStrikePrice()) {
			return String.format("%s   %s   Option   %s   (Call)\n", this.getAssetCode(), this.getLabel(), this.getSymbol())
					+ String.format("  Buy up to %.3f shares @ $%.2f until %s\n", this.getShareLimit(),
							this.getStrikePrice(), this.getStrikeDate())
					+ String.format("  Premium of $%.2f/share $%.2f total\n", this.getPremium(), this.getPremiumTotal())
					+ String.format("  Share Price: $%.2f\n", this.getCurrentSharePrice())
					+ String.format("  Short Call Value: %.3f shares @\n", getShareLimit())
					+ String.format("     ($%.2f - $%.2f - $%.2f = $%.2f)\n", this.getCurrentSharePrice(),
							this.getStrikePrice(), this.getPremium(), (this.getValue() - this.getPremiumTotal()))
					+ String.format(" %90.3f", (this.returnPercent() * 100.0)) + "%"
					+ String.format("     $%.2f\n", this.getValue());
		}
		return String.format("%s   %s   Option   %s   (Call)\n", this.getAssetCode(), this.getLabel(), this.getSymbol())
				+ String.format("  Buy up to %.3f shares @ $%.2f until %s\n", this.getShareLimit(),
						this.getStrikePrice(), this.getStrikeDate())
				+ String.format("  Premium of $%.2f/share $%.2f total\n", this.getPremium(), this.getPremiumTotal())
				+ String.format("  Share Price: $%.2f\n", this.getCurrentSharePrice())

				+ String.format("  Long Call (not executed) total loss: $%.2f\n", this.getPremiumTotal())
				+ String.format("%91.3f", (this.returnPercent() * 100.0)) + "%"
				+ String.format("     $%.2f\n", this.getValue());
	}
}