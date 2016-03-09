package edu.usu.weaponsStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.store.Path;

import edu.usu.bible.*;
import java.util.Collections;

public class WeaponStorage {

	private static Vector<WeaponLocker> lockerVector = new Vector<WeaponLocker>();
	private static Vector<Person> peopleVector = new Vector<Person>();
	private static Vector<WeaponTicket> ticketVector = new Vector<WeaponTicket>();
	private static Person tempPerson = new Person();
	private WeaponLocker tempWeaponLocker = new WeaponLocker();
	private WeaponTicket tempWeaponTicket = new WeaponTicket();
	private Weapon tempWeapon = new Weapon();
	private String tempFirstName;
	private String tempLastName;
	private String tempDOB;
	private String tempLockerNumber;
	private int tempTicket;
	private int tempIndex;
	private boolean found;
	private static int tempInt;
	private static FileInputStream file;
	private static FileOutputStream fout;
	private static XSSFWorkbook workbook;
	private static String fileString;
	private String tempPersonKey;

	public WeaponStorage() {
		fileString = "C:/Users/Richard/Desktop/Dispach Bible/Weapons Storage/Weapons Storage.xlsm";
		//fileString = "C:/Users/Dispatch1-user/Desktop/Dispach Bible/Dispach Bible/Weapons Storage/Weapons Storage.xlsm";
//		JFileChooser chooser = new JFileChooser();
//		chooser.showDialog(null,"Please select the weapons storage file");
//		chooser.setVisible(true);
//		fileString =  chooser.getSelectedFile().toString();
		lockerVector.clear();
		peopleVector.clear();
		// setFilePaths();
		readInPeople();
		readInTickets();
		readInLockers();
	}

	public void readInLockers() {
		try {
			// Path path = (Path) Paths.get(fileString);
			FileInputStream file = new FileInputStream(new File(fileString));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			while (rowIterator.hasNext()) {

				row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				tempWeaponLocker = new WeaponLocker();

				// while (cellIterator.hasNext())
				// {
				Cell cell = cellIterator.next();

				tempLockerNumber = cell.getStringCellValue();

				cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					tempTicket = (int) cell.getNumericCellValue();
					break;
				case Cell.CELL_TYPE_STRING:
					tempTicket = -1;
					break;
				}

				if (tempTicket == -1) {
					tempWeaponLocker.setWeaponLockerNumber(tempLockerNumber);
					tempWeaponLocker.setWeaponTicket(new WeaponTicket());
				} else {
					tempWeaponLocker.setWeaponLockerNumber(tempLockerNumber);
					found = false;
					for (int i = 0; i < ticketVector.size() && !found; i++) {
						if (ticketVector.elementAt(i).getTicketNumber() == tempTicket) {

							tempWeaponLocker.setWeaponTicket(ticketVector.elementAt(i));
							found = true;
						}
					}
				}

				lockerVector.addElement(tempWeaponLocker);
			}

			file.close();

			for (int i = 0; i < lockerVector.size(); i++) {
				if (lockerVector.elementAt(i).getWeaponTicket() == null) {
					lockerVector.elementAt(i).setWeaponTicket(new WeaponTicket());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readInPeople() {
		try {
			FileInputStream file = new FileInputStream(new File(fileString));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(2);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			while (rowIterator.hasNext()) {

				row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					tempPerson = new Person();
					Cell cell = cellIterator.next();
					tempPerson.setPersonKey(cell.getStringCellValue());
					cell = cellIterator.next();
					// Check the cell type and format accordingly
					String temp = cell.getStringCellValue();
					tempPerson.setFirstName(temp);
					cell = cellIterator.next();
					tempPerson.setLastName(cell.getStringCellValue());
					cell = cellIterator.next();
					// tempPerson.setDateOfBirth(cell.getStringCellValue());

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						tempPerson.setDateOfBirth(Integer.toString((int) (cell.getNumericCellValue())));
						break;
					case Cell.CELL_TYPE_STRING:
						tempPerson.setDateOfBirth(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BLANK:
						tempPerson.setDateOfBirth(null);
						break;
					}
					cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						tempPerson.setphoneNumber(Integer.toString((int) (cell.getNumericCellValue())));
						break;
					case Cell.CELL_TYPE_STRING:
						tempPerson.setphoneNumber(cell.getStringCellValue());
						break;
					}
					// tempPerson.setphoneNumber(cell.getStringCellValue().toString());
					cell = cellIterator.next();
					tempPerson.setaddress(cell.getStringCellValue());
					cell = cellIterator.next();
					tempPerson.setaNumber(cell.getStringCellValue());

					peopleVector.add(tempPerson);
				}

			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readInTickets() {
		try {

			FileInputStream file = new FileInputStream(new File(fileString));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(1);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			while (rowIterator.hasNext()) {

				row = rowIterator.next();
				tempWeaponTicket = new WeaponTicket();
				tempWeapon = new Weapon();

				Iterator<Cell> cellIterator = row.cellIterator();

				Cell cell = cellIterator.next();

				if (cell.getNumericCellValue() != 0.0) {
					// For each row, iterate through all the columns

					tempWeaponTicket.setTicketNumber((int) cell.getNumericCellValue());

					cell = cellIterator.next();

					tempPersonKey = cell.getStringCellValue();

					cell = cellIterator.next();
					tempFirstName = cell.getStringCellValue();

					cell = cellIterator.next();
					tempLastName = cell.getStringCellValue();

					cell = cellIterator.next();
					// tempDOB = cell.getStringCellValue();

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						tempDOB = (Integer.toString((int) (cell.getNumericCellValue())));
						break;
					case Cell.CELL_TYPE_STRING:
						tempDOB = (cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BLANK:
						tempDOB = null;
						break;
					}

					tempIndex = 0;
					found = false;

					while (tempIndex < peopleVector.size() && !found) {
						if (peopleVector.elementAt(tempIndex).getPersonKey().toLowerCase()
								.equals(tempPersonKey.toLowerCase())) {
							tempPerson = peopleVector.elementAt(tempIndex);
							found = true;

						}
						tempIndex++;
					}

					if (found == false) {
						tempWeaponTicket.setPerson(
								tempPerson = new Person(tempFirstName, tempLastName, tempDOB, null, null, null));
					}

					tempWeaponTicket.setPerson(tempPerson);
					cell = cellIterator.next();
					cell = cellIterator.next();
					cell = cellIterator.next();
					cell = cellIterator.next();
					tempWeapon.setweaponType(cell.getStringCellValue());

					cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						tempWeapon.setweaponMake(Integer.toString((int) (cell.getNumericCellValue())));
						break;
					case Cell.CELL_TYPE_STRING:
						tempWeapon.setweaponMake(cell.getStringCellValue());
						break;
					}

					cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						tempWeapon.setweaponModel(Integer.toString((int) (cell.getNumericCellValue())));
						break;
					case Cell.CELL_TYPE_STRING:
						tempWeapon.setweaponModel(cell.getStringCellValue());
						break;
					}
					// tempWeapon.setweaponModel();

					cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						tempWeapon.setweaponSerialNumber(Integer.toString((int) (cell.getNumericCellValue())));
						break;
					case Cell.CELL_TYPE_STRING:
						tempWeapon.setweaponSerialNumber(cell.getStringCellValue());
						break;
					}
					cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						tempWeaponTicket.setNotes(Integer.toString((int) (cell.getNumericCellValue())));
						break;
					case Cell.CELL_TYPE_STRING:
						tempWeaponTicket.setNotes(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BLANK:
						tempWeaponTicket.setNotes(null);
						break;
					}

					cell = cellIterator.next();
					tempWeaponTicket.setsemesterCreated(cell.getStringCellValue());

					tempWeaponTicket.setWeapon(tempWeapon);

					ticketVector.add(tempWeaponTicket);
				}

			}

			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void removeTicketFromLockerInExcel(WeaponTicket weaponTicket) throws FileNotFoundException {

		boolean found = false;
		// static WeaponTicket tempWeaponTicket;
		try {
			FileInputStream file = new FileInputStream(new File(fileString));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			while (rowIterator.hasNext() && !found) {

				row = rowIterator.next();
				// tempWeapon = new Weapon();

				Iterator<Cell> cellIterator = row.cellIterator();

				Cell cell = cellIterator.next();

				cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					tempInt = (int) cell.getNumericCellValue();
					break;
				case Cell.CELL_TYPE_STRING:
					tempInt = -1;
					break;
				}
				// HERE
				if (weaponTicket != null) {
					if (tempInt == weaponTicket.getTicketNumber()) {
						cell.setCellValue("EMPTY");
						file.close();
						found = true;
						fout = new FileOutputStream(fileString);
						if (workbook.getNumberOfSheets() == 3) {
							workbook.write(fout);
						} else {
							System.out.println("Wrong number of sheets" + workbook.getNumberOfSheets());

						}
					}
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addTicketToLockerInExcel(LockerButton lockerButton, String ticketNumber) {

		try {
			addLockerToLockerVector(ticketNumber, lockerButton);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean found = false;
		// static WeaponTicket tempWeaponTicket;
		try {
			FileInputStream file = new FileInputStream(new File(fileString));
			//
			// // Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			while (rowIterator.hasNext() && !found) {

				row = rowIterator.next();
				// tempWeapon = new Weapon();

				Iterator<Cell> cellIterator = row.cellIterator();

				Cell cell = cellIterator.next();

				if (cell.getStringCellValue().toLowerCase()
						.equals(lockerButton.getLockerName().toLowerCase()) == true) {
					cell = cellIterator.next();
					cell.setCellValue(Integer.parseInt(ticketNumber));
					file.close();
					found = true;
					fout = new FileOutputStream(fileString);
					workbook.write(fout);
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Vector<WeaponTicket> getTickets() {
		return WeaponStorage.ticketVector;
	}

	public static int getNumberOfTickets() {
		return (WeaponStorage.ticketVector.size());
	}

	public static int getWeaponTicketNumberForElement(int i) {
		return WeaponStorage.ticketVector.elementAt(i).getTicketNumber();
	}

	public Vector<WeaponLocker> lockers() {
		return lockerVector;
	}

	public Vector<Person> getPeople() {
		return peopleVector;
	}

	public void setPeople(Vector<Person> peopleVector) {
		this.peopleVector = peopleVector;
	}

	public static Person getPersonFromPeople(int i) {
		return peopleVector.elementAt(i);
	}

	public static int getNumberOfPeople() {
		return peopleVector.size();
	}

	public static void emptyLockerFromLockerVector(String key, LockerButton lockerButton) throws FileNotFoundException {
		int i = 0;
		boolean found = false;
		String tempKey = "Uknown";
		while (!found && i < lockerVector.size()) {
			if (lockerVector.elementAt(i).getWeaponTicket().getPerson() != null) {
				tempKey = lockerVector.elementAt(i).getWeaponTicket().getPerson().getPersonKey();
				if (tempKey.equals(key)) {
					found = true;
					Bible.resetLockers(lockerButton);
					removeTicketFromLockerInExcel(lockerButton.getWeaponLocker().getWeaponTicket());
					lockerVector.elementAt(i).setWeaponTicket(new WeaponTicket());
				}
			}
			i++;
		}

	}
	
	public class Backup {
		
	}

	public static void addLockerToLockerVector(String key, LockerButton lockerButton) throws FileNotFoundException {

		int i = 0;
		boolean found = false;
		String tempLockerName = lockerButton.getLockerName();
		WeaponTicket tempWeaponTicket = new WeaponTicket();
		while (!found && i < ticketVector.size()) {
			if (ticketVector.elementAt(i).getTicketNumber() == Integer.parseInt(key)) {
				found = true;
				tempWeaponTicket = ticketVector.elementAt(i);
			}
			i++;
		}
		i = 0;
		found = false;
		while (!found && i < lockerVector.size()) {
			if (lockerVector.elementAt(i).getWeaponLockerNumber().equals(tempLockerName) == true) {
				found = true;
				lockerVector.elementAt(i).setWeaponTicket(tempWeaponTicket);
			}
			i++;
		}
		Bible.updateLockerText(lockerButton, key);
	}

	public static void addNewWeaponTicketAndPersonToVectors(WeaponTicket weaponTicket, Person person,
			boolean newPerson) {
		int i = 0;
		while (i < ticketVector.size()
				&& ticketVector.elementAt(i).getTicketNumber() < weaponTicket.getTicketNumber()) {
			i++;
		}
		if (i == ticketVector.size()) {
			ticketVector.addElement(weaponTicket);
		} else {
			ticketVector.add(i, weaponTicket);
		}
		writeTicketOutToExcel(weaponTicket, person, newPerson);
	}

	public static Person getPersonFromKey(String key) {
		int i = 0;
		boolean found = false;
		Person tempPerson = new Person();

		while (i < peopleVector.size() && !found) {
			if (peopleVector.elementAt(i).getPersonKey().toLowerCase().equals(key.toLowerCase()) == true) {
				found = true;
				tempPerson = peopleVector.elementAt(i);
			}
			i++;
		}

		return tempPerson;
	}

	public static void updatePersonFromKey(String key, Person person) {
		int i = 0;
		boolean found = false;

		while (i < peopleVector.size() && !found) {
			if (peopleVector.elementAt(i).getPersonKey().toLowerCase().equals(key.toLowerCase()) == true) {
				found = true;
				peopleVector.set(i, person);
			}
			i++;
		}
		System.out.println(i);
	}

	public static void writeTicketOutToExcel(WeaponTicket weaponTicket, Person person, boolean newPerson) {

		if (newPerson == true && person != null) {
			peopleVector.addElement(person);
			writePersonOutToExcel(person);
		}

		try {
			FileInputStream file = new FileInputStream(new File(fileString));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(1);

			Iterator<Row> rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
			}

			Iterator<Cell> cellIterator = row.cellIterator();

			Cell cell = cellIterator.next();

			cell.setCellValue(weaponTicket.getTicketNumber());
			cell = cellIterator.next();

			cell.setCellValue(person.getPersonKey());
			cell = cellIterator.next();
			cell = cellIterator.next(); // Skip FirstName
			cell = cellIterator.next(); // Skip LastName
			cell = cellIterator.next(); // Skip DOB
			cell = cellIterator.next(); // Skip Phone
			cell = cellIterator.next(); // Skip Address
			cell = cellIterator.next(); // Skip A number

			cell.setCellValue(weaponTicket.getWeapon().getweaponType());
			cell = cellIterator.next();

			cell.setCellValue(weaponTicket.getWeapon().getweaponMake());
			cell = cellIterator.next();

			cell.setCellValue(weaponTicket.getWeapon().getweaponModel());
			cell = cellIterator.next();

			cell.setCellValue(weaponTicket.getWeapon().getweaponSerialNumber());
			cell = cellIterator.next();

			// cell.setCellValue(weaponTicket.getWeapon()); //Weapon Notes
			cell = cellIterator.next();

			cell.setCellValue(weaponTicket.getsemesterCreated());
			cell = cellIterator.next();

			fout = new FileOutputStream(fileString);
			workbook.write(fout);

			file.close();
			fout.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void editedPersonOutToExcel(Person person, String key) {
		FileInputStream file;
		try {
			file = new FileInputStream(new File(fileString));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(2);

			boolean found = false;
			boolean next = true;

			Iterator<Row> rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			while (next && !found) {
				Iterator<Cell> cellIterator = row.cellIterator();

				Cell cell = cellIterator.next();

				if (cell.getStringCellValue().toLowerCase().equals(key.toLowerCase())) {
					found = true;
					cell = cellIterator.next();
					cell.setCellValue(person.getFirstName());

					cell = cellIterator.next();
					cell.setCellValue(person.getLastName());

					cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						cell.setCellValue(Integer.parseInt(person.getDateOfBirth()));
						break;
					case Cell.CELL_TYPE_STRING:
						cell.setCellValue(person.getDateOfBirth());
						break;
					case Cell.CELL_TYPE_BLANK:
						cell.setCellValue(person.getDateOfBirth());
						break;
					}

					cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						cell.setCellValue(Integer.parseInt(person.getphoneNumber()));
						break;
					case Cell.CELL_TYPE_STRING:
						cell.setCellValue(person.getphoneNumber());
						break;
					}

					cell = cellIterator.next();
					cell.setCellValue(person.getaddress());

					cell = cellIterator.next();
					cell.setCellValue(person.getaNumber());

					fout = new FileOutputStream(fileString);
					workbook.write(fout);
					file.close();
					fout.close();
					workbook.close();
				}
				if (rowIterator.hasNext()) {
					row = rowIterator.next();
				} else {
					next = false;
				}

			}
			if (found) {
				JOptionPane.showMessageDialog(null, "Changes made");
			} else {
				JOptionPane.showMessageDialog(null,
						"Unable to edit person. \n Tell Richard Error in editedPersonOutToExcel");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create Workbook instance holding reference to .xlsx file
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writePersonOutToExcel(Person person) {
		try {
			FileInputStream file = new FileInputStream(new File(fileString));
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(2);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			while (rowIterator.hasNext()) {

				row = rowIterator.next();
			}

			Iterator<Cell> cellIterator = row.cellIterator();

			Cell cell = cellIterator.next();

			cell = cellIterator.next();
			cell.setCellValue(person.getFirstName());

			cell = cellIterator.next();
			cell.setCellValue(person.getLastName());

			cell = cellIterator.next();
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				cell.setCellValue(Integer.parseInt(person.getDateOfBirth()));
				break;
			case Cell.CELL_TYPE_STRING:
				cell.setCellValue(person.getDateOfBirth());
				break;
			case Cell.CELL_TYPE_BLANK:
				cell.setCellValue(person.getDateOfBirth());
				break;
			}

			cell = cellIterator.next();
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				cell.setCellValue(Integer.parseInt(person.getphoneNumber()));
				break;
			case Cell.CELL_TYPE_STRING:
				cell.setCellValue(person.getphoneNumber());
				break;
			}

			cell = cellIterator.next();
			cell.setCellValue(person.getaddress());

			cell = cellIterator.next();
			cell.setCellValue(person.getaNumber());

			fout = new FileOutputStream(fileString);
			workbook.write(fout);
			file.close();
			fout.close();
			workbook.close();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void chooseFile(String newFileName) {
		fileString = newFileName;
	}

	public static void sortPersonVector() {
		Vector<Person> temp = new Vector<Person>();

		int i = 0;
		while (i < peopleVector.size()) {
			if (i == 0) {
				temp.add(peopleVector.elementAt(i));
			} else {
				int j = 0;
				boolean next = true;
				while ((next) && (peopleVector.elementAt(i).getFirstName().toLowerCase()
						.compareTo(temp.elementAt(j).getFirstName().toLowerCase()) > 0)) {
					j++;
					if (j >= temp.size()) {
						next = false;
					}
				}
				if (j == temp.size()) {
					temp.add(peopleVector.elementAt(i));
				} else if (peopleVector.elementAt(i).getFirstName().toLowerCase()
						.compareTo(temp.elementAt(j).getFirstName().toLowerCase()) <= 0) {
					if (peopleVector.elementAt(i).getFirstName().toLowerCase()
							.compareTo(temp.elementAt(j).getFirstName().toLowerCase()) < 0) {
						if ((j - 1) < 0) {
							temp.add(0, peopleVector.elementAt(i));
						} else {
							temp.add((j - 1), peopleVector.elementAt(i));
						}
					} else if (peopleVector.elementAt(i).getFirstName().toLowerCase()
							.compareTo(temp.elementAt(j).getFirstName().toLowerCase()) == 0) {
						if ((j - 1) < 0) {
							temp.add(0, peopleVector.elementAt(i));
						} else {
							temp.add((j - 1), peopleVector.elementAt(i));
						}
					}
				}
			}

			i++;
		}

		if (temp.size() == peopleVector.size()) {
			peopleVector.removeAllElements();
			peopleVector = (Vector<Person>) temp.clone();
		} else {
			JOptionPane.showMessageDialog(null,
					"Unable to Sort \n Tell Richard, it is unable to sort the people Vector");
		}
		peopleVector.size();
	}

	public static WeaponTicket getWeaponTicketFromTicketNumber(int ticketNumber) {

		WeaponTicket weaponTicket = null;

		int i = 0;
		boolean found = false;
		while (i < ticketVector.size() && !found) {
			if (ticketVector.elementAt(i).getTicketNumber() == ticketNumber) {

				weaponTicket = ticketVector.elementAt(i);
				found = true;
			}
			i++;
		}

		return weaponTicket;
	}

	public static void deleteOldCard(int ticketNumber) {
		WeaponTicket tempWeaponTicket = new WeaponTicket();

		tempWeaponTicket = getWeaponTicketFromTicketNumber(ticketNumber);

		int i = 0;
		boolean found = false;
		while (i < ticketVector.size() && !found) {
			if (ticketVector.elementAt(i).getTicketNumber() == ticketNumber) {
				ticketVector.removeElementAt(i);
				found = true;
			}
			i++;
		}
		FileInputStream file;
		try {
			file = new FileInputStream(new File(fileString));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(1);

			Iterator<Row> rowIterator = sheet.iterator();

			Row row = rowIterator.next();

			found = false;
			while (rowIterator.hasNext() && !found) {

				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					tempInt = (int) cell.getNumericCellValue();
					break;
				case Cell.CELL_TYPE_STRING:
					tempInt = -1;
					break;
				}

				if (tempInt == tempWeaponTicket.getTicketNumber()) {

					int tempRowNumber = row.getRowNum();

					Row lastRow = row;
					while (rowIterator.hasNext() && lastRow.cellIterator().next().getNumericCellValue() != 0.0) {
						lastRow = rowIterator.next();
					}

					int lastRowNumber = lastRow.getRowNum();

					sheet.removeRow(row);
					sheet.shiftRows((tempRowNumber + 1), sheet.getLastRowNum(), (-1));

					file.close();
					found = true;
					fout = new FileOutputStream(fileString);
					if (workbook.getNumberOfSheets() == 3) {
						workbook.write(fout);
					} else {
						System.out.println("Wrong number of sheets" + workbook.getNumberOfSheets());

					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
