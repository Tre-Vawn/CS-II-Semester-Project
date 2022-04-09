package com.iffi;

import java.util.List;

/**
 * A class that prints the account report to the standard output.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class AccountReportPrinter {

	/**
	 * This function prints the account report given a list of accounts.
	 * 
	 * @param accounts
	 */
	public static final void printAccountReport(List<Account> accounts) {
		System.out.println("Account Summary Report By Owner");
		System.out.println(
				"========================================================================================================================");
		System.out.println("Account\t\tOwner\t\t\tManager\t\t\tFees\t\tReturn\t\tReturn%\t      Value");
		double totalReportFees = 0.0;
		double totalReportReturn = 0.0;
		double totalReportValue = 0.0;
		for (Account a : accounts) {
			totalReportFees += a.getFees();
			totalReportReturn += a.getTotalReturn();
			totalReportValue += a.getTotalValue();
			System.out.println(
					String.format("%-16s%-24s%-23s$%-15.2f$%-16.2f%7.3f", a.getAccountCode(), a.getOwner().getName(),
							a.getManager().getName(), a.getFees(), a.getTotalReturn(), a.getTotalReturnPercent()) + "%"
							+ String.format("     $%-5.2f", a.getTotalValue()));
		}

		System.out.println(
				"                                                     -------------------------------------------------------------------");
		System.out.println("                                                     Totals    $"
				+ String.format("%-15.2f", totalReportFees) + "$" + String.format("%-29.2f", totalReportReturn) + "$"
				+ String.format("%.2f\n", totalReportValue));
		System.out.println("\nAccount Details");
		System.out.println(
				"========================================================================================================================");
		for (Account a : accounts) {
			System.out.println("======================================");
			if (a.getClass() == Pro.class) {
				System.out.format("||    Pro Account  %s          ||\n", a.getAccountCode());
			} else if (a.getClass() == Noob.class) {
				System.out.format("||    Noob Account %s         ||\n", a.getAccountCode());
			}
			System.out.println("======================================");
			System.out.println("+---------+");
			System.out.println("|  Owner  |");
			System.out.println("+---------+");
			System.out.println(a.getOwner().getName());
			if(a.getOwner().getEmails() != null) {
				a.getOwner().printEmails();
			}
			System.out.println(a.getOwner().getAddress());
			System.out.println("+-----------+");
			System.out.println("|  Manager  |");
			System.out.println("+-----------+");
			System.out.println(a.getManager().getName());
			if(a.getManager().getEmails() != null) {
				a.getManager().printEmails();
			}
			System.out.println(a.getManager().getAddress());
			System.out.println("+---------------+");
			System.out.println("|  Beneficiary  |");
			System.out.println("+---------------+");
			if (a.getBeneficiary() != null) {
				System.out.println(a.getBeneficiary().getName());
				a.getBeneficiary().printEmails();
				System.out.println(a.getBeneficiary().getAddress());
			}
			System.out.println("+----------+");
			System.out.println("|  Assets  |");
			System.out.println("+----------+");
			for (Asset a1 : a.getAssetList()) {
				System.out.println(a1.toString());
			}
			System.out.println("+----------+");
			System.out.println("|  Totals  |");
			System.out.println("+----------+");
			double totalValue = 0.0;
			double totalCostBasis = 0.0;
			double totalFees = 0.0;
			for (Asset a1 : a.getAssetList()) {
				totalValue += a1.getValue();
				totalCostBasis += a1.getPurchasedPrice();
			}
			System.out.printf("Total Value:         $%10.2f\n", totalValue);
			System.out.printf("Cost Basis:          $%10.2f\n", totalCostBasis);
			System.out.printf("Total Account Fees:  $%10.2f\n", a.getFees());
			System.out.printf("Total Return:        $%10.2f\n", totalValue - totalCostBasis);
			System.out.println(String.format("Total Return ") + "%"
					+ String.format(":       %10.2f", (100.0 * (totalValue - totalCostBasis) / totalCostBasis))
					+ "%\n");
		}
		return;
	}
}
