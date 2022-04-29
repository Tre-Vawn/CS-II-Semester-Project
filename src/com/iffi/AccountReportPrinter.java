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
	public static final void printOwnerAccountReport(MyList<Account> accounts) {
		System.out.println("Account Summary Report By Owner");
		System.out.println(
				"=================================================================================================================================");
		System.out.println("Account\t\tOwner\t\t\tManager\t\t\tFees\t\tReturn\t\t\tReturn%\t      Value");
		double totalReportFees = 0.0;
		double totalReportReturn = 0.0;
		double totalReportValue = 0.0;
		for (Account a : accounts) {
			totalReportFees += a.getFees();
			totalReportReturn += a.getTotalReturn();
			totalReportValue += a.getTotalValue();
			System.out.println(
					String.format("%-16s%-24s%-23s$%-16.2f$%-17.2f%12.3f", a.getAccountCode(), a.getOwnerCode().getName(),
							a.getManagerCode().getName(), a.getFees(), a.getTotalReturn(), a.getTotalReturnPercent()) + "%"
							+ String.format("     $%-13.2f", a.getTotalValue()));
		}

		System.out.println(
				"                                                     ----------------------------------------------------------------------------");
		System.out.println("                                                     Totals    $"
				+ String.format("%-15.2f", totalReportFees) + "$" + String.format("%-36.2f", totalReportReturn) + "$"
				+ String.format("%.2f\n", totalReportValue));
		/*
		System.out.println("\nAccount Details");
		System.out.println(
				"=================================================================================================================================");
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
			System.out.println(a.getOwnerCode().getName());
			if(!(a.getOwnerCode().getEmails().isEmpty())) {
				a.getOwnerCode().printEmails();
			} else {
				System.out.println("[]");
			}
			System.out.println(a.getOwnerCode().getAddress());
			System.out.println("+-----------+");
			System.out.println("|  Manager  |");
			System.out.println("+-----------+");
			System.out.println(a.getManagerCode().getName());
			if(!(a.getManagerCode().getEmails().isEmpty())) {
				a.getManagerCode().printEmails();
			} else {
				System.out.println("[]");
			}
			System.out.println(a.getManagerCode().getAddress());
			System.out.println("+---------------+");
			System.out.println("|  Beneficiary  |");
			System.out.println("+---------------+");
			if (a.getBeneficiaryCode() != null) {
				System.out.println(a.getBeneficiaryCode().getName());
				if(!(a.getBeneficiaryCode().getEmails().isEmpty())) {
					a.getBeneficiaryCode().printEmails();					
				} else {
					System.out.println("[]");
				}
				System.out.println(a.getBeneficiaryCode().getAddress());
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
			double totalValue = a.getTotalValue();
			double totalCostBasis = a.getTotalCostBasis();
			System.out.printf("Total Value:         $%10.2f\n", totalValue);
			System.out.printf("Cost Basis:          $%10.2f\n", totalCostBasis);
			System.out.printf("Total Account Fees:  $%10.2f\n", a.getFees());
			System.out.printf("Total Return:        $%10.2f\n", totalValue - totalCostBasis);
			
			if(totalCostBasis != 0.0) {
				System.out.println(String.format("Total Return ") + "%"
						+ String.format(":       %10.2f", (100.0 * (totalValue - totalCostBasis) / totalCostBasis))
						+ "%\n");
			} else if(totalValue > 0.0) {
				System.out.println(String.format("Total Return ") + "%"
						+ String.format(":       %10.2f", 100.0)
						+ "%\n");
			}else if(totalValue < 0.0) {
				System.out.println(String.format("Total Return ") + "%"
						+ String.format(":       %10.2f", -100.0)
						+ "%\n");
			}
		}
		*/
		return;
	}
	
	public static final void printManagerAccountReport(MyList<Account> accounts) {
		System.out.println("Account Summary Report By Manager");
		System.out.println(
				"=================================================================================================================================");
		System.out.println("Account\t\tOwner\t\t\tManager\t\t\tFees\t\tReturn\t\t\tReturn%\t      Value");
		double totalReportFees = 0.0;
		double totalReportReturn = 0.0;
		double totalReportValue = 0.0;
		for (Account a : accounts) {
			totalReportFees += a.getFees();
			totalReportReturn += a.getTotalReturn();
			totalReportValue += a.getTotalValue();
			System.out.println(
					String.format("%-16s%-24s%-23s$%-16.2f$%-17.2f%12.3f", a.getAccountCode(), a.getOwnerCode().getName(),
							a.getManagerCode().getName(), a.getFees(), a.getTotalReturn(), a.getTotalReturnPercent()) + "%"
							+ String.format("     $%-13.2f", a.getTotalValue()));
		}

		System.out.println(
				"                                                     ----------------------------------------------------------------------------");
		System.out.println("                                                     Totals    $"
				+ String.format("%-15.2f", totalReportFees) + "$" + String.format("%-36.2f", totalReportReturn) + "$"
				+ String.format("%.2f\n", totalReportValue));
		return;
		}
	
	public static final void printTotalValueAccountReport(MyList<Account> accounts) {
		System.out.println("Account Summary Report By Total Value");
		System.out.println(
				"=================================================================================================================================");
		System.out.println("Account\t\tOwner\t\t\tManager\t\t\tFees\t\tReturn\t\t\tReturn%\t      Value");
		double totalReportFees = 0.0;
		double totalReportReturn = 0.0;
		double totalReportValue = 0.0;
		for (Account a : accounts) {
			totalReportFees += a.getFees();
			totalReportReturn += a.getTotalReturn();
			totalReportValue += a.getTotalValue();
			System.out.println(
					String.format("%-16s%-24s%-23s$%-16.2f$%-17.2f%12.3f", a.getAccountCode(), a.getOwnerCode().getName(),
							a.getManagerCode().getName(), a.getFees(), a.getTotalReturn(), a.getTotalReturnPercent()) + "%"
							+ String.format("     $%-13.2f", a.getTotalValue()));
		}

		System.out.println(
				"                                                     ----------------------------------------------------------------------------");
		System.out.println("                                                     Totals    $"
				+ String.format("%-15.2f", totalReportFees) + "$" + String.format("%-36.2f", totalReportReturn) + "$"
				+ String.format("%.2f\n", totalReportValue));
		return;
		}
	
	public static final void printTotalReturnPercentAccountReport(MyList<Account> accounts) {
		System.out.println("Account Summary Report By Total Return Percent");
		System.out.println(
				"=================================================================================================================================");
		System.out.println("Account\t\tOwner\t\t\tManager\t\t\tFees\t\tReturn\t\t\tReturn%\t      Value");
		double totalReportFees = 0.0;
		double totalReportReturn = 0.0;
		double totalReportValue = 0.0;
		for (Account a : accounts) {
			totalReportFees += a.getFees();
			totalReportReturn += a.getTotalReturn();
			totalReportValue += a.getTotalValue();
			System.out.println(
					String.format("%-16s%-24s%-23s$%-16.2f$%-17.2f%12.3f", a.getAccountCode(), a.getOwnerCode().getName(),
							a.getManagerCode().getName(), a.getFees(), a.getTotalReturn(), a.getTotalReturnPercent()) + "%"
							+ String.format("     $%-13.2f", a.getTotalValue()));
		}

		System.out.println(
				"                                                     ----------------------------------------------------------------------------");
		System.out.println("                                                     Totals    $"
				+ String.format("%-15.2f", totalReportFees) + "$" + String.format("%-36.2f", totalReportReturn) + "$"
				+ String.format("%.2f\n", totalReportValue));
		return;
		}
}