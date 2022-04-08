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

	@SuppressWarnings("unused")
	private String getLastName() {
		return this.lastName;
	}

	@SuppressWarnings("unused")
	private String getFirstName() {
		return this.firstName;
	}

	public String toString() {
		return this.lastName + ", " + this.firstName;
	}
}