package com.iffi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Prints the data that is provided as a specified list to the specified XML
 * file.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class XMLFileDataPrinter {

	/**
	 * Prints the person data that is provided as a list of people to the
	 * appropriate XML file.
	 * 
	 * @param people
	 *
	 */
	public static final void personDataToXML(List<Person> people) {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("person", Person.class);
		xstream.alias("email", String.class);
		FileWriter myWriter;
		try {
			myWriter = new FileWriter("data/Persons.xml");
			myWriter.write("<persons>\n");
			for (Person p : people) {
				String xml = xstream.toXML(p);
				myWriter.write(xml);
				myWriter.write("\n");
			}
			myWriter.write("</persons>\n");
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * Prints the assets data that is provided as a list of assets to the
	 * appropriate XML file.
	 * 
	 * @param assets
	 *
	 */
	public static final void assetDataToXML(List<Asset> assets) {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("property", Property.class);
		xstream.alias("cryptocurrency", Cryptocurrency.class);
		xstream.alias("stock", Stock.class);
		try {
			FileWriter myWriter = new FileWriter("data/Assets.xml");
			myWriter.write("<assets>\n");
			for (Asset a : assets) {
				if (a.getClass() == Property.class) {
					xstream.omitField(Property.class, "purchasedPrice");
					String xml = xstream.toXML((Property) a);
					myWriter.write(xml);
					myWriter.write("\n");
				}
				if (a.getClass() == Cryptocurrency.class) {
					xstream.omitField(Cryptocurrency.class, "purchasedExchangeRate");
					xstream.omitField(Cryptocurrency.class, "numCoins");
					String xml = xstream.toXML((Cryptocurrency) a);
					myWriter.write(xml);
					myWriter.write("\n");
				}
				if (a.getClass() == Stock.class) {
					xstream.omitField(Stock.class, "purchasedSharePrice");
					xstream.omitField(Stock.class, "numShares");
					xstream.omitField(Stock.class, "dividendTotal");
					String xml = xstream.toXML((Stock) a);
					myWriter.write(xml);
					myWriter.write("\n");
				}
			}
			myWriter.write("</assets>\n");
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
}