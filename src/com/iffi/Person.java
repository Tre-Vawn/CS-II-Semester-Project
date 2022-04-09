package com.iffi;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the different attributes that a person has.
 *
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class Person {
	private final String code;
	private final Name name;
	private final Address address;
	private final ArrayList<String> emails;

	private Integer personId;

	protected Person(String code, Name name, Address address) {
		this.code = code;
		this.name = name;
		this.address = address;
		this.emails = new ArrayList<String>();
	}

	protected Person(Integer personId, String code, Name name, Address address) {
		this.personId = personId;
		this.code = code;
		this.name = name;
		this.address = address;
		this.emails = new ArrayList<String>();
	}

	protected final String getCode() {
		return this.code;
	}

	protected final Name getName() {
		return this.name;
	}

	protected final Address getAddress() {
		return this.address;
	}
	
	protected final ArrayList<String> getEmails() {
		return this.emails;
	}

	@SuppressWarnings("unused")
	private final Integer getPersonId() {
		return this.personId;
	}

	/**
	 * This searches through a list of people for a person who has a code that
	 * matches the desired code.
	 * 
	 * @param people
	 * @param code
	 * @return
	 */
	protected static final Person findPerson(List<Person> people, String code) {
		for (Person p : people) {
			if (p.getCode().equals(code)) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Method that adds an email to an email list.
	 * 
	 * @param email
	 */
	protected final void addEmail(String email) {
		this.emails.add(email);
		return;
	}

	protected final void printEmails() {
		System.out.print("[");
		for (String email : this.emails) {
			System.out.print(email + " ");
		}
		System.out.println("]");
		return;
	}

	public final String toString() {
		return this.code + " " + this.name.toString() + " " + this.address.toString();
	}
}