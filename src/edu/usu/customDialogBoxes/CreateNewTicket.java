package edu.usu.customDialogBoxes;

import edu.usu.weaponsStorage.Person;
import edu.usu.weaponsStorage.Weapon;
import edu.usu.weaponsStorage.WeaponStorage;
import edu.usu.weaponsStorage.WeaponTicket;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CreateNewTicket implements ActionListener {
	private Person Person;
	private Weapon Weapon;
	private JLabel firstNameLabel;
	private JTextField firstNameField;
	private JLabel newPersonLabel;
	private JButton newPerson;
	private JButton existingPerson;
	private static AutoComboBox autoComboBox = new AutoComboBox();
	private WeaponStorage weaponStorage;
	private static Vector<String> personArray = new Vector<String>();
	private static String[] people;
	private JLabel personNameLabel;
	private JLabel weaponMakeLabel;
	private JLabel weaponModelLabel;
	private JLabel weaponTypeLabel;
	private JLabel weaponSerialNumberLabel;
	private JLabel lastNameLabel;
	private JLabel DOBLabel;
	private JTextField weaponMakeTextField;
	private JTextField weaponModelTextField;
	private JTextField weaponTypeTextField;
	private JTextField weaponSerialNumberTextField;
	private JTextField lastNameTextField;
	private JTextField firstNameTextField;
	private JTextField DOBTextField;
	private JButton create;
	private JLabel ticketNumberLabel;
	private JTextField ticketNumberTextField;
	private JLabel aNumberLabel;
	private JTextField aNumberTextField;
	private JLabel addressLabel;
	private JTextField addressTextField;
	private JLabel phoneNumberLabel;
	private JTextField phoneNumberTextField;
	private JLabel noteLabel;
	private JTextField noteTextField;
	private Date date = new Date();
	private DateFormat format;
	private DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
	private DateFormat dateFormatMonth = new SimpleDateFormat("mm");
	private JFrame Frame;
	private String notes;
	
	public CreateNewTicket() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JPanel panel = new JPanel();
				JPanel contentPane = new JPanel();
				contentPane.add(panel);
				Frame = new JFrame();
				Frame.setContentPane(contentPane);
				Frame.setSize(1200, 700);
				Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				contentPane.setLayout(null);

				newPersonLabel = new JLabel();
				newPersonLabel.setText("Is this a new Person?");
				newPersonLabel.setFont(new Font("Arial", Font.BOLD, 25));
				newPersonLabel.setBounds(5, 0, 300, 50);
				newPersonLabel.setVisible(true);
				contentPane.add(newPersonLabel);

				newPerson = new JButton("Yes");
				newPerson.setFont(new Font("Arial", Font.BOLD, 15));
				newPerson.setBounds(300, 0, 100, 50);
				newPerson.addActionListener(buttonListener);
				newPerson.setForeground(Color.RED);
				newPerson.setVisible(true);
				contentPane.add(newPerson);

				existingPerson = new JButton("No");
				existingPerson.setFont(new Font("Arial", Font.BOLD, 15));
				existingPerson.setBounds(300, 0, 100, 50);
				existingPerson.addActionListener(buttonListener);
				existingPerson.setForeground(Color.RED);
				existingPerson.setVisible(false);
				contentPane.add(existingPerson);

				ticketNumberLabel = new JLabel();
				ticketNumberLabel.setText("Ticket Number?");
				ticketNumberLabel.setFont(new Font("Arial", Font.BOLD, 15));
				ticketNumberLabel.setBounds(0, 50, 150, 50);
				ticketNumberLabel.setVisible(true);
				contentPane.add(ticketNumberLabel);

				ticketNumberTextField = new JTextField();
				ticketNumberTextField.setBounds(150, 50, 200, 50);
				ticketNumberTextField.setFont(new Font("Arial", Font.BOLD, 15));
				ticketNumberTextField.setVisible(true);
				contentPane.add(ticketNumberTextField);

				// Existing People
				personNameLabel = new JLabel();
				personNameLabel.setText("Name and DOB: ");
				personNameLabel.setBounds(0, 100, 150, 50);
				personNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
				personNameLabel.setVisible(true);
				contentPane.add(personNameLabel);

				autoComboBox.setSelectedIndex(-1);
				autoComboBox.setBounds(150, 100, 500, 50);
				autoComboBox.setFont(new Font("Arial", Font.PLAIN, 30));
				autoComboBox.setVisible(true);
				contentPane.add(autoComboBox);

				weaponTypeLabel = new JLabel("Weapon Type: ");
				weaponTypeLabel.setBounds(0, 150, 150, 50);
				weaponTypeLabel.setFont(new Font("Arial", Font.BOLD, 15));
				weaponTypeLabel.setVisible(true);
				contentPane.add(weaponTypeLabel);

				weaponMakeTextField = new JTextField();
				weaponMakeTextField.setBounds(150, 150, 200, 50);
				weaponMakeTextField.setFont(new Font("Arial", Font.BOLD, 15));
				weaponMakeTextField.setVisible(true);
				contentPane.add(weaponMakeTextField);

				weaponModelLabel = new JLabel("Weapon Model: ");
				weaponModelLabel.setBounds(0, 200, 150, 50);
				weaponModelLabel.setFont(new Font("Arial", Font.BOLD, 15));
				weaponModelLabel.setVisible(true);
				contentPane.add(weaponModelLabel);

				weaponModelTextField = new JTextField();
				weaponModelTextField.setBounds(150, 200, 200, 50);
				weaponModelTextField.setFont(new Font("Arial", Font.BOLD, 15));
				weaponModelTextField.setVisible(true);
				contentPane.add(weaponModelTextField);

				weaponMakeLabel = new JLabel("Weapon Make: ");
				weaponMakeLabel.setBounds(0, 250, 150, 50);
				weaponMakeLabel.setFont(new Font("Arial", Font.BOLD, 15));
				weaponMakeLabel.setVisible(true);
				contentPane.add(weaponMakeLabel);

				weaponTypeTextField = new JTextField();
				weaponTypeTextField.setBounds(150, 250, 200, 50);
				weaponTypeTextField.setFont(new Font("Arial", Font.BOLD, 15));
				weaponTypeTextField.setVisible(true);
				contentPane.add(weaponTypeTextField);

				weaponSerialNumberLabel = new JLabel("Weapon SN#: ");
				weaponSerialNumberLabel.setBounds(0, 300, 150, 50);
				weaponSerialNumberLabel.setFont(new Font("Arial", Font.BOLD, 15));
				weaponSerialNumberLabel.setVisible(true);
				contentPane.add(weaponSerialNumberLabel);

				weaponSerialNumberTextField = new JTextField();
				weaponSerialNumberTextField.setBounds(150, 300, 200, 50);
				weaponSerialNumberTextField.setFont(new Font("Arial", Font.BOLD, 15));
				weaponSerialNumberTextField.setVisible(true);
				contentPane.add(weaponSerialNumberTextField);

				// New People
				firstNameLabel = new JLabel("First Name");
				firstNameLabel.setBounds(0, 100, 150, 50);
				firstNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
				firstNameLabel.setVisible(false);
				contentPane.add(firstNameLabel);

				lastNameLabel = new JLabel("Last Name: ");
				lastNameLabel.setBounds(350, 100, 100, 50);
				lastNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
				lastNameLabel.setVisible(false);
				contentPane.add(lastNameLabel);

				DOBLabel = new JLabel("DOB: ");
				DOBLabel.setBounds(700, 100, 50, 50);
				DOBLabel.setFont(new Font("Arial", Font.BOLD, 15));
				DOBLabel.setVisible(false);
				contentPane.add(DOBLabel);

				aNumberLabel = new JLabel("A#: ");
				aNumberLabel.setBounds(350, 50, 50, 50);
				aNumberLabel.setFont(new Font("Arial", Font.BOLD, 15));
				aNumberLabel.setVisible(false);
				contentPane.add(aNumberLabel);

				aNumberTextField = new JTextField();
				aNumberTextField.setBounds(400, 50, 100, 50);
				aNumberTextField.setFont(new Font("Arial", Font.BOLD, 15));
				aNumberTextField.setVisible(false);
				contentPane.add(aNumberTextField);

				addressLabel = new JLabel("Address: ");
				addressLabel.setBounds(500, 50, 100, 50);
				addressLabel.setFont(new Font("Arial", Font.BOLD, 15));
				addressLabel.setVisible(false);
				contentPane.add(addressLabel);

				addressTextField = new JTextField();
				addressTextField.setBounds(600, 50, 450, 50);
				addressTextField.setFont(new Font("Arial", Font.BOLD, 15));
				addressTextField.setVisible(false);
				contentPane.add(addressTextField);

				firstNameTextField = new JTextField();
				firstNameTextField.setBounds(150, 100, 200, 50);
				firstNameTextField.setFont(new Font("Arial", Font.BOLD, 15));
				firstNameTextField.setVisible(false);
				contentPane.add(firstNameTextField);

				lastNameTextField = new JTextField();
				lastNameTextField.setBounds(450, 100, 200, 50);
				lastNameTextField.setFont(new Font("Arial", Font.BOLD, 15));
				lastNameTextField.setVisible(false);
				contentPane.add(lastNameTextField);

				DOBTextField = new JTextField();
				DOBTextField.setBounds(750, 100, 200, 50);
				DOBTextField.setFont(new Font("Arial", Font.BOLD, 15));
				DOBTextField.setVisible(false);
				contentPane.add(DOBTextField);

				phoneNumberLabel = new JLabel("Phone: ");
				phoneNumberLabel.setBounds(950, 100, 75, 50);
				phoneNumberLabel.setFont(new Font("Arial", Font.BOLD, 15));
				phoneNumberLabel.setVisible(false);
				contentPane.add(phoneNumberLabel);

				phoneNumberTextField = new JTextField();
				phoneNumberTextField.setBounds(1050, 100, 200, 50);
				phoneNumberTextField.setFont(new Font("Arial", Font.BOLD, 15));
				phoneNumberTextField.setVisible(false);
				contentPane.add(phoneNumberTextField);
				
				noteLabel = new JLabel("Notes: ");
				noteLabel.setBounds(0, 350, 75, 50);
				noteLabel.setFont(new Font("Arial", Font.BOLD, 15));
				noteLabel.setVisible(true);
				contentPane.add(noteLabel);

				noteTextField = new JTextField();
				noteTextField.setBounds(150, 350, 500, 50);
				noteTextField.setFont(new Font("Arial", Font.BOLD, 15));
				noteTextField.setVisible(true);
				contentPane.add(noteTextField);

				// Populating functions
				populateExistingPeople();

				create = new JButton("Create");
				create.setFont(new Font("Arial", Font.BOLD, 30));
				create.setBounds(300, 450, 200, 50);
				create.addActionListener(buttonListener);
				create.setForeground(Color.ORANGE);
				create.setVisible(true);
				contentPane.add(create);

				Frame.setVisible(true);
			}

			ActionListener buttonListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (e.getSource() == newPerson) {
						createNewPerson(true);
						createExistingPerson(false);

					} else if (e.getSource() == existingPerson) {
						createNewPerson(false);
						createExistingPerson(true);
					} else if (e.getSource() == create) {
						boolean birthdayFormatCorrect = false;
						boolean phoneFormatCorrect = false;
						int ticketNumber = Integer.parseInt(ticketNumberTextField.getText());
						String firstName = "unkown", lastName = "unkown", DOB = "unkown", aNumber = "unkown",
								address = "unkown", phone = "unkown";
						if (autoComboBox.isVisible() == true) {
							String personKey = autoComboBox.getName();
						} else {
							lastName = lastNameTextField.getText();
							firstName = firstNameTextField.getText();
							aNumber = aNumberTextField.getText();
							address = addressTextField.getText();
							notes = noteTextField.getText();
							if (DOBTextField.getText().length() == 8 || DOBTextField.getText() == null) {
								DOB = DOBTextField.getText();
								birthdayFormatCorrect = true;
							} else {
								birthdayFormatCorrect = false;
							}
							if (phoneNumberTextField.getText().length() == 11
									|| phoneNumberTextField.getText() == null) {
								phoneFormatCorrect = true;
								phone = phoneNumberTextField.getText();
							} else {
								phoneFormatCorrect = false;

							}
						}
						String weaponMake = weaponMakeTextField.getText();
						String weaponModel = weaponModelTextField.getText();
						String weaponType = weaponTypeTextField.getText();
						String weaponSerialNumber = weaponSerialNumberTextField.getText();

						if (autoComboBox.isVisible() == true) {
							Person person = WeaponStorage.getPersonFromKey(((String) autoComboBox.getSelectedItem())
									.replaceAll("\\s", "").replace("null", ""));
							Weapon weapon = new Weapon(weaponMake, weaponModel, weaponType, weaponSerialNumber);
							WeaponTicket weaponTicket = new WeaponTicket(ticketNumber, person, weapon,
									createSemesterCreated());
							WeaponStorage.addNewWeaponTicketAndPersonToVectors(weaponTicket, person, false);

						} else if (birthdayFormatCorrect == true && phoneFormatCorrect == true) {
							Person person = new Person(firstName, lastName, DOB, aNumber, address, phone);
							Weapon weapon = new Weapon(weaponMake, weaponModel, weaponType, weaponSerialNumber);
							WeaponTicket weaponTicket = new WeaponTicket(ticketNumber, person, weapon,
									createSemesterCreated());
							WeaponStorage.addNewWeaponTicketAndPersonToVectors(weaponTicket, person, true);
						} else if (phoneFormatCorrect == false) {
							JOptionPane.showMessageDialog(null, "Incorrect Phone Number format i.e. 18013723336");
						} else {
							JOptionPane.showMessageDialog(null, "Incorrect birthday format i.e.12141988");
						}

						WeaponStorage.sortPersonVector();
						Frame.dispose();
					}
				}
			};
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public String createSemesterCreated() {

		String monthString, semesterCreatedTemp;
		int month, year;
		LocalDateTime now = LocalDateTime.now();
		year = now.getYear();
		month = now.getMonthValue();

		if (month <= 4) {
			monthString = "Spring";
		} else if (month <= 7) {
			monthString = "Summer";
		} else {
			monthString = "Fall";
		}

		semesterCreatedTemp = (monthString + " " + year);

		return semesterCreatedTemp;
	}


	public void createExistingPerson(boolean show) {
		existingPerson.setVisible(!show);
		lastNameLabel.setVisible(!show);
		firstNameLabel.setVisible(!show);
		DOBLabel.setVisible(!show);
		lastNameTextField.setVisible(!show);
		firstNameTextField.setVisible(!show);
		DOBTextField.setVisible(!show);
		aNumberLabel.setVisible(!show);
		addressLabel.setVisible(!show);
		aNumberTextField.setVisible(!show);
		addressTextField.setVisible(!show);
		phoneNumberLabel.setVisible(!show);
		phoneNumberTextField.setVisible(!show);
	}

	public void createNewPerson(boolean show) {
		autoComboBox.setVisible(!show);
		newPerson.setVisible(!show);
		personNameLabel.setVisible(!show);
	}

	public static void populateExistingPeople() {
		autoComboBox.removeAllItems();
		personArray.clear();
		int i = 0;
		int size = WeaponStorage.getNumberOfPeople();
		while (i < size) {
			personArray.add(WeaponStorage.getPersonFromPeople(i).getFirstName() + " "
					+ WeaponStorage.getPersonFromPeople(i).getLastName() + " "
					+ WeaponStorage.getPersonFromPeople(i).getDateOfBirth());
			i++;
		}
		autoComboBox.setKeyWord(personArray.toArray(new String[personArray.size()]));
	}
}
