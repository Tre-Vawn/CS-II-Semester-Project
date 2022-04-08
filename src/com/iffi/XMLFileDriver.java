package com.iffi;

import java.util.List;

/**
 * Main data driver for loading in the data from a specified CSV file and
 * producing an output to the specified XML file.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class XMLFileDriver {

	public static final void main(String[] args) {
		List<Person> people = CSVFileLoader.loadPersonCSVFile();
		List<Asset> assets = CSVFileLoader.loadAssetCSVFile();

		XMLFileDataPrinter.personDataToXML(people);
		XMLFileDataPrinter.assetDataToXML(assets);
		return;
	}
}