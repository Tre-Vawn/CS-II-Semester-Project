package com.iffi;

/**
 * A class representing the different parts of an address that a person lives
 * at.
 *
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class Address {
	private final String street;
	private final String city;
	private final String state;
	private final String zip;
	private final String country;

	private Integer addressId;

	protected Address(String street, String city, String state, String zip, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	protected Address(Integer addressId, String street, String city, String state, String zip, String country) {
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	@SuppressWarnings("unused")
	private final String getStreet() {
		return this.street;
	}

	@SuppressWarnings("unused")
	private final String getCity() {
		return this.city;
	}

	@SuppressWarnings("unused")
	private final String getState() {
		return this.state;
	}

	@SuppressWarnings("unused")
	private final String getZip() {
		return this.zip;
	}

	@SuppressWarnings("unused")
	private final String getCountry() {
		return this.country;
	}

	@SuppressWarnings("unused")
	private final Integer getAddressId() {
		return this.addressId;
	}

	public final String toString() {
		return this.street + ", " + this.city + ", " + this.state + ", " + this.zip + ", " + this.country;
	}
}