package com.iffi;

/**
 * A utility class to hold our database connection.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class DatabaseInfo {
	
	/**
	 * Connection parameters that are necessary for CSE's configuration
	 */
	public static final String PARAMETERS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	public static final String USERNAME = "trainey";
	public static final String PASSWORD = "9Up7T33f";
	public static final String URL = "jdbc:mysql://cse.unl.edu/" + USERNAME + PARAMETERS;
}
