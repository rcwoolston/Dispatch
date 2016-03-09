package edu.usu.customDialogBoxes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import edu.usu.weaponsStorage.Person;
import edu.usu.weaponsStorage.WeaponStorage;

public class EditPerson implements ActionListener {

	private JButton edit, change;
	private static AutoComboBox autoComboBox;
	private static Vector<String> personArray = new Vector<String>();
	private JTextField firstNameText, lastNameText, DOBText, addressText, phoneNumberText, aNumberText;
	private JLabel firstNameLabel, lastNameLabel, DOBLabel, addressLabel, phoneNumberLabel, aNumberLabel;
	private JFrame Frame;

	public EditPerson() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JPanel panel = new JPanel();
				JPanel contentPane = new JPanel();
				contentPane.add(panel);
				Frame = new JFrame();
				Frame.setContentPane(contentPane);
				Frame.setSize(650, 550);
				Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				contentPane.setLayout(null);

				autoComboBox = new AutoComboBox();
				populateExistingPeople();

				autoComboBox.setSelectedIndex(-1);
				autoComboBox.setBounds(0, 0, 500, 50);
				autoComboBox.setFont(new Font("Arial", Font.PLAIN, 30));
				autoComboBox.setVisible(true);
				contentPane.add(autoComboBox);

				edit = new JButton("Edit");
				edit.setFont(new Font("Arial", Font.BOLD, 15));
				edit.setBounds(500, 0, 100, 50);
				edit.addActionListener(buttonListener);
				edit.setForeground(Color.RED);
				edit.setVisible(true);
				contentPane.add(edit);

				firstNameLabel = new JLabel("First Name: ");
				firstNameLabel.setBounds(0, 150, 150, 50);
				firstNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
				firstNameLabel.setVisible(true);
				contentPane.add(firstNameLabel);

				firstNameText = new JTextField();
				firstNameText.setBounds(150, 150, 200, 50);
				firstNameText.setFont(new Font("Arial", Font.BOLD, 15));
				firstNameText.setVisible(true);
				contentPane.add(firstNameText);

				lastNameLabel = new JLabel("Last Name: ");
				lastNameLabel.setBounds(0, 200, 150, 50);
				lastNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
				lastNameLabel.setVisible(true);
				contentPane.add(lastNameLabel);

				lastNameText = new JTextField();
				lastNameText.setBounds(150, 200, 200, 50);
				lastNameText.setFont(new Font("Arial", Font.BOLD, 15));
				lastNameText.setVisible(true);
				contentPane.add(lastNameText);

				phoneNumberLabel = new JLabel("Phone Number: ");
				phoneNumberLabel.setBounds(0, 300, 150, 50);
				phoneNumberLabel.setFont(new Font("Arial", Font.BOLD, 15));
				phoneNumberLabel.setVisible(true);
				contentPane.add(phoneNumberLabel);

				phoneNumberText = new JTextField();
				phoneNumberText.setBounds(150, 300, 200, 50);
				phoneNumberText.setFont(new Font("Arial", Font.BOLD, 15));
				phoneNumberText.setVisible(true);
				contentPane.add(phoneNumberText);

				DOBLabel = new JLabel("DOB: ");
				DOBLabel.setBounds(0, 250, 150, 50);
				DOBLabel.setFont(new Font("Arial", Font.BOLD, 15));
				DOBLabel.setVisible(true);
				contentPane.add(DOBLabel);

				DOBText = new JTextField();
				DOBText.setBounds(150, 250, 200, 50);
				DOBText.setFont(new Font("Arial", Font.BOLD, 15));
				DOBText.setVisible(true);
				contentPane.add(DOBText);

				addressLabel = new JLabel("Address: ");
				addressLabel.setBounds(0, 350, 150, 50);
				addressLabel.setFont(new Font("Arial", Font.BOLD, 15));
				addressLabel.setVisible(true);
				contentPane.add(addressLabel);

				addressText = new JTextField();
				addressText.setBounds(150, 350, 200, 50);
				addressText.setFont(new Font("Arial", Font.BOLD, 15));
				addressText.setVisible(true);
				contentPane.add(addressText);

				aNumberLabel = new JLabel("ANumber: ");
				aNumberLabel.setBounds(0, 400, 150, 50);
				aNumberLabel.setFont(new Font("Arial", Font.BOLD, 15));
				aNumberLabel.setVisible(true);
				contentPane.add(aNumberLabel);

				aNumberText = new JTextField();
				aNumberText.setBounds(150, 400, 200, 50);
				aNumberText.setFont(new Font("Arial", Font.BOLD, 15));
				aNumberText.setVisible(true);
				contentPane.add(aNumberText);

				change = new JButton("Change");
				change.setFont(new Font("Arial", Font.BOLD, 15));
				change.setBounds(0, 450, 100, 50);
				change.addActionListener(buttonListener);
				change.setForeground(Color.BLUE);
				change.setVisible(false);
				contentPane.add(change);

				Frame.setVisible(true);

			}

		});
	}

	public static void populateExistingPeople() {
		autoComboBox.removeAllItems();
		personArray.clear();
		WeaponStorage.sortPersonVector();
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

	ActionListener buttonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == edit) {

				if ((String) autoComboBox.getSelectedItem() != null) {
					Person person = WeaponStorage.getPersonFromKey(
							((String) autoComboBox.getSelectedItem()).replaceAll("\\s", "").replace("null", ""));

					firstNameText.setText(person.getFirstName());
					lastNameText.setText(person.getLastName());
					DOBText.setText(person.getDateOfBirth());
					addressText.setText(person.getaddress());
					aNumberText.setText(person.getaNumber());
					phoneNumberText.setText(person.getphoneNumber());
					change.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "No name selected");
				}
			} else if (e.getSource() == change) {
				Person person = new Person();
				boolean dobCorrect = true;
				boolean phoneNumberCorrect = true;

				String key = ((String) autoComboBox.getSelectedItem()).replaceAll("\\s", "").replace("null", "");

				person.setFirstName(firstNameText.getText());
				person.setLastName(lastNameText.getText());

				if (DOBText.getText().length() == 8 || DOBText.getText() == null) {
					person.setDateOfBirth(DOBText.getText());
				} else {
					dobCorrect = false;
				}
				person.setaNumber(aNumberText.getText());
				if (phoneNumberText.getText().length() == 11 || phoneNumberText.getText() == null) {
					person.setphoneNumber(phoneNumberText.getText());
				} else {
					phoneNumberCorrect = false;
				}
				person.setaddress(addressText.getText());

				if (phoneNumberCorrect && dobCorrect) {
					WeaponStorage.updatePersonFromKey(key, person);
					WeaponStorage.editedPersonOutToExcel(person, key);
					WeaponStorage.sortPersonVector();
					Frame.dispose();
				} else {
					if (!phoneNumberCorrect && !dobCorrect) {
						JOptionPane.showMessageDialog(null,
								"Incorrect Phone number format: i.e. 14357971939 \n Incorrect DOB format: i.e. 0101888");
					} else if (!phoneNumberCorrect) {
						JOptionPane.showMessageDialog(null, "Incorrect Phone number format: i.e. 14357971939");
					} else if (!dobCorrect) {
						JOptionPane.showMessageDialog(null, "Incorrect DOB format: i.e. 0101888");
					}
				}
			}
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
