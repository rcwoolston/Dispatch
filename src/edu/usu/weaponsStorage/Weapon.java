package edu.usu.weaponsStorage;

public class Weapon {

	private String weaponType;
	private String weaponMake;
	private String weaponModel;
	private String weaponSerialNumber;
	//private String semesterCreated;

	public Weapon() {
		this.weaponType = null;
		this.weaponMake = null;
		this.weaponModel = null;
		this.weaponSerialNumber = null;
		//this.semesterCreated = null;
	}

	public Weapon(String weaponType, String weaponMake, String weaponModel, String weaponSerialNumber){//,
			//String semesterCreated) {
		this.weaponType = weaponType;
		this.weaponMake = weaponMake;
		this.weaponModel = weaponModel;
		this.weaponSerialNumber = weaponSerialNumber;
		//this.semesterCreated = semesterCreated;
	}

	public void setweaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public String getweaponType() {
		return this.weaponType;
	}

	public void setweaponMake(String weaponMake) {
		this.weaponMake = weaponMake;
	}

	public String getweaponMake() {
		return this.weaponMake;
	}

	public void setweaponModel(String weaponModel) {
		this.weaponModel = weaponModel;
	}

	public String getweaponModel() {
		return this.weaponModel;
	}

	public void setweaponSerialNumber(String weaponSerialNumber) {
		this.weaponSerialNumber = weaponSerialNumber;
	}

	public String getweaponSerialNumber() {
		return this.weaponSerialNumber;
	}

//	public void setsemesterCreated(String semesterCreated) {
//		this.semesterCreated = semesterCreated;
//	}
//
//	public String getsemesterCreated() {
//		return this.semesterCreated;
//	}
	
	public String print(){
		return ("\nWeapon Type: " + this.weaponType + "\nWeapon Make: " + this.weaponMake + "\nWeapons Model: " + this.weaponModel + "\nWeapon Serial Number:" + this.weaponSerialNumber);// + "\nSemester Created: " + this.semesterCreated);
	}
}

