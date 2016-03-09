package edu.usu.weaponsStorage;

public class OldCards {
	
	private WeaponTicket weaponTicket;
	private int ticketNumber;
	private String semesterCreated;

	public OldCards(){
		this.weaponTicket = null;
		this.ticketNumber = -1;
		this.semesterCreated = null;
	}
	
	public OldCards(WeaponTicket weaponTicket, int ticketNumber, String semesterCreated){
		this.weaponTicket = weaponTicket;
		this.ticketNumber = ticketNumber;
		this.semesterCreated = semesterCreated;
	}
	
	public String getSemesterCreated(){
		return this.semesterCreated;
	}
	
	public void setSemesterCreated(String semesterCreated){
		this.semesterCreated = semesterCreated;
	}
	
	public int getTicketNumber(){
		return this.ticketNumber;
	}
	
	public void setTicketNumber(int ticketNumber){
		this.ticketNumber = ticketNumber;
	}
	
	public WeaponTicket getWeaponTicket(){
		return this.weaponTicket;
	}
	
	public void setWeaponTicket(WeaponTicket weaponTicket){
		this.weaponTicket = weaponTicket;
	}
}
