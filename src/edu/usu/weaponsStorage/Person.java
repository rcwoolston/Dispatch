package edu.usu.weaponsStorage;

public class Person {

	private String firstName;
	private String lastName;
	private String aNumber;
	private String address;
	private String phoneNumber;
	private String dateOfBirth;
	private String personKey;

	public Person() {
		this.firstName = null;
		this.lastName = null;
		this.aNumber = null;
		this.address = null;
		this.phoneNumber = null;
		this.dateOfBirth = null;
	}

	public Person(String firstName, String lastName, String dateOfBirth, String aNumber, String address,
			String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.aNumber = aNumber;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
	}
	
	public void setPersonKey(String personKey){
		this.personKey = personKey;
	}
	
	public String getPersonKey(){
		return this.personKey;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	public String getaddress() {
		return this.address;
	}

	public void setphoneNumber(String phoneNumber) {
		if (phoneNumber != null) {
			this.phoneNumber = phoneNumber;
		} else {
			this.phoneNumber = "UKNOWN";
		}
	}

	public String getphoneNumber() {
		return this.phoneNumber;
	}

	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}

	public String getaNumber() {
		return this.aNumber;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	public String printMe(){
		return ("First Name: "+this.getFirstName() + "\nLast Name: " + this.getLastName() + "\nA-Number: " + this.getaNumber() + "\nAddress: " + this.getaddress() + "\nDate of Birth: " + this.getDateOfBirth());
	}
}
