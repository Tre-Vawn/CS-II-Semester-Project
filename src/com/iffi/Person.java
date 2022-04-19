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
	private final String personCode;
	private final Name name;
	private final Address address;
	private final ArrayList<String> emails;

	private Integer personId;

	protected Person(String personCode, Name name, Address address) {
		this.personCode = personCode;
		this.name = name;
		this.address = address;
		this.emails = new ArrayList<String>();
	}

	protected Person(Integer personId, String personCode, Name name, Address address) {
		this.personId = personId;
		this.personCode = personCode;
		this.name = name;
		this.address = address;
		this.emails = new ArrayList<String>();
	}

	protected final String getPersonCode() {
		return this.personCode;
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
	protected static final Person findPerson(List<Person> people, String personCode) {
		for (Person p : people) {
			if (p.getPersonCode().equals(personCode)) {
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
		int count = 0;
		System.out.print("[");
		for (String email : this.emails) {
			if(count == this.emails.size() - 1) {
				System.out.print(email);
			} else {
				System.out.print(email + ", ");
			}
			count++;
		}
		System.out.println("]");
		return;
	}

	public final String toString() {
		return this.personCode + " " + this.name.toString() + " " + this.address.toString();
	}
}