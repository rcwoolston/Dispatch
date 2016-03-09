package edu.usu.weaponsStorage;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LockerButton extends JButton {

	private WeaponLocker weaponLocker;
	private String lockerName;
	private int h;
	private int w;


	public LockerButton() {
		weaponLocker = new WeaponLocker();
	}

	public LockerButton(WeaponLocker weaponLocker) {
		this.weaponLocker = weaponLocker;
		this.lockerName = weaponLocker.getWeaponLockerNumber().toString();
	}
	
	public String getLockerName(){
		return this.lockerName;
	}
	
	public void emptyLocker(){
		weaponLocker = new WeaponLocker();
	}

	public WeaponLocker getWeaponLocker() {
		return this.weaponLocker;
	}

	public void setWeaponLocker(WeaponLocker weaponLocker) {
		this.weaponLocker = weaponLocker;
		this.lockerName = weaponLocker.getWeaponLockerNumber().toString();
	}

	public boolean clicked() {
		// TODO Auto-generated method stub
		System.out.println("Yes");
		return false;
	}

	public String getLockerInformation() {
		return ("Locker Name: " + this.lockerName + "\nTicket Number: " + this.weaponLocker.getWeaponTicket().getTicketNumber() + "\n"+this.weaponLocker.getWeaponTicket().print());
	}
	
	public void setH(int h){
		this.h= h;
	}
	
	public int getH(){
		return this.h;
	}
	
	public void setW(int w){
		this.w= w;
	}
	
	public int getW(){
		return this.w;
	}
}
