package edu.usu.weaponsStorage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeaponTicket {

	private int ticketNumber;
	private Person person;
	private Weapon weapon;
	private String semesterCreated;
//	private DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
//	private DateFormat dateFormatMonth = new SimpleDateFormat("mm");
//	private Date date = new Date();
//	private DateFormat format;
	private String notes;

	public WeaponTicket() {
		this.ticketNumber = -1;
		this.semesterCreated = null;
		this.person = null;
		this.weapon = null;
		this.notes = null;
	}

	public WeaponTicket(int ticketNumber, Person person, Weapon weapon, String semesterCreated) {
		this.ticketNumber = ticketNumber;
		this.person = person;
		this.weapon = weapon;
		this.semesterCreated = semesterCreated;
		this.notes = null;
	}
	
	public void setNotes(String notes){
		this.notes = notes;
	}
	
	public String getNotes(){
		return this.notes;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public int getTicketNumber() {
		return this.ticketNumber;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public String print() {
		return (person.printMe() + weapon.print() + "\n" + "Semester Created: " + getsemesterCreated());
	}

	public void setsemesterCreated(String semesterCreated) {
		this.semesterCreated = semesterCreated;
	}

	public String getsemesterCreated() {
		return this.semesterCreated;
	}
}
