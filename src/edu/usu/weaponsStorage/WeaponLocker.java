package edu.usu.weaponsStorage;

public class WeaponLocker {

	private String weaponLockerNumber;
	private WeaponTicket weaponTicket;

	public WeaponLocker() {
		this.weaponLockerNumber = null;
	}

	public WeaponLocker(String weaponLockerNumber) {
		this.weaponLockerNumber = weaponLockerNumber;
	}

	public void setWeaponLockerNumber(String weaponLockerNumber) {
		this.weaponLockerNumber = weaponLockerNumber;
	}

	public String getWeaponLockerNumber() {
		return this.weaponLockerNumber;
	}

	public void setWeaponTicket(WeaponTicket weaponTicket) {
		this.weaponTicket = weaponTicket;
	}

	public WeaponTicket getWeaponTicket() {
		return this.weaponTicket;
	}
	
	public void clearLocker(){
		this.weaponTicket = null;
	}
}
