package com.iffi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts data that is read in from three different CSV files and creates the
 * specified objects and adds those objects to the specified array list.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class CSVFileLoader {

	/**
	 * Loads in data from a persons CSV file and splits that data up into the
	 * appropriate tokens to create a new Person and add that Person to a returned
	 * array list of people.
	 * 
	 * @return
	 */
	public static final ArrayList<Person> loadPersonCSVFile() {
		ArrayList<Person> people = new ArrayList<Person>();
		File f = new File("data/Persons.csv");
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			String s = null;
			int numPeople = Integer.parseInt(br.readLine());
			for (int i = 0; i < numPeople; i++) {
				s = br.readLine();
				List<String> emailList = new ArrayList<>();
				String tokens[] = s.split(",");
				Person p1 = new Person(tokens[0], new Name(tokens[1], tokens[2]),
						new Address(tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]));
				for (int j = 8; j < tokens.length; j++) {
					p1.addEmail(tokens[j]);
				}
				people.add(p1);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return people;
	}

	/**
	 * Loads in data from an assets CSV file and splits that data up into the
	 * appropriate tokens to create a new Asset and add that Asset to a returned
	 * array list of assets.
	 * 
	 * @return
	 */
	public static final ArrayList<Asset> loadAssetCSVFile() {
		ArrayList<Asset> assets = new ArrayList<Asset>();
		File f = new File("data/Assets.csv");
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			String line = null;
			int numAssets = Integer.parseInt(br.readLine());
			for (int i = 0; i < numAssets; i++) {
				line = br.readLine();
				String tokens[] = line.split(",");
				if (tokens[1].equals("P")) {
					Property p = new Property(tokens[0], tokens[2], Double.parseDouble(tokens[3]));
					assets.add(p);
				} else if (tokens[1].equals("C")) {
					Cryptocurrency c = new Cryptocurrency(tokens[0], tokens[2], Double.parseDouble(tokens[3]),
							Double.parseDouble(tokens[4]));
					assets.add(c);
				} else if (tokens[1].equals("S")) {
					Stock s = new Stock(tokens[0], tokens[2], tokens[3], Double.parseDouble(tokens[4]));
					assets.add(s);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return assets;
	}

	/**
	 * Takes in a list of assets loaded from an assets CSV file and a list of people
	 * loaded from a persons CSV file and uses that data to fill a returned array
	 * list of accounts.
	 *
	 * @param assets
	 * @param people
	 * @return
	 */
	public static final ArrayList<Account> loadAccountCSVFile(List<Asset> assets, List<Person> people) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		File f = new File("data/Accounts.csv");
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			String line = null;
			int numAccounts = Integer.parseInt(br.readLine());
			for (int i = 0; i < numAccounts; i++) {
				line = br.readLine();
				String tokens[] = line.split(",");
				List<Asset> assetsPerPerson = new ArrayList<>();
				int count = 5;
				boolean found = false;
				while (count <= tokens.length - 1) {
					found = false;
					for (Asset a : assets) {
						if (count <= tokens.length - 1) {
							found = false;
							if (tokens[count].equals(a.getCode())) {
								if (a.getClass() == Property.class && !found) {
									found = true;
									Property p = new Property((Property) a, LocalDate.parse(tokens[count + 1]),
											Double.parseDouble(tokens[count + 2]));
									assetsPerPerson.add(p);
									count += 3;

								} else if (a.getClass() == Cryptocurrency.class && !found) {
									found = true;
									Cryptocurrency c = new Cryptocurrency((Cryptocurrency) a,
											LocalDate.parse(tokens[count + 1]), Double.parseDouble(tokens[count + 2]),
											Double.parseDouble(tokens[count + 3]));
									assetsPerPerson.add(c);
									count += 4;

								} else if (tokens[count + 1].equals("S") && !found) {
									found = true;
									Stock s = new Stock((Stock) a, LocalDate.parse(tokens[count + 2]),
											Double.parseDouble(tokens[count + 3]),
											Double.parseDouble(tokens[count + 4]),
											Double.parseDouble(tokens[count + 5]));
									assetsPerPerson.add(s);
									count += 6;
								} else if (tokens[count + 1].equals("P") && !found) {
									found = true;
									StockOption s = new StockOption((Stock) a, Double.parseDouble(tokens[count + 3]),
											Double.parseDouble(tokens[count + 4]),
											Double.parseDouble(tokens[count + 5]), LocalDate.parse(tokens[count + 6]));
									Put p = new Put(s);
									assetsPerPerson.add(p);
									count += 7;
								} else if (tokens[count + 1].equals("C") && !found) {
									found = true;
									StockOption s = new StockOption((Stock) a, Double.parseDouble(tokens[count + 3]),
											Double.parseDouble(tokens[count + 4]),
											Double.parseDouble(tokens[count + 5]), LocalDate.parse(tokens[count + 6]));
									Call c = new Call(s);
									assetsPerPerson.add(c);
									count += 7;
								}
							}
						}
					}
				}

				if (tokens[1].equals("P")) {
					Pro proAccount = new Pro(tokens[0], Person.findPerson(people, tokens[2]),
							Person.findPerson(people, tokens[3]), Person.findPerson(people, tokens[4]));
					for (Asset a1 : assetsPerPerson) {
						proAccount.addAsset(a1);
					}
					accounts.add(proAccount);

				} else if (tokens[1].equals("N")) {
					Noob noobAccount = new Noob(tokens[0], Person.findPerson(people, tokens[2]),
							Person.findPerson(people, tokens[3]), Person.findPerson(people, tokens[4]));
					for (Asset a1 : assetsPerPerson) {
						noobAccount.addAsset(a1);
					}
					accounts.add(noobAccount);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accounts;
	}
}