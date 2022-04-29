package com.iffi;

/**
 * A class representing the different attributes that a name has.
 *
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class Name {
	private final String lastName;
	private final String firstName;

	protected Name(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}

	protected String getLastName() {
		return this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String toString() {
		return this.firstName + ", " + this.lastName;
	}
}