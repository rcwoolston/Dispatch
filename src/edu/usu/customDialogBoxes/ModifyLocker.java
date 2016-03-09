package edu.usu.customDialogBoxes;

import edu.usu.bible.Bible;
import edu.usu.weaponsStorage.LockerButton;
import java.awt.Container;
import edu.usu.weaponsStorage.Person;
import edu.usu.weaponsStorage.Weapon;
import edu.usu.weaponsStorage.WeaponLocker;
import edu.usu.weaponsStorage.WeaponStorage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class ModifyLocker implements ActionListener {

	private JButton empty;
	private JButton add;
	private LockerButton lockerButton;
	private WeaponLocker weaponLocker;
	private String tempPersonKey;
	private JButton exit;
	private Container contentPane;
	private static AutoComboBox autoComboBoxTickets;
	private static Vector<String> ticketNumberVector;
	private static String[] ticketStringArray;
	private JFrame Frame;

	public ModifyLocker(LockerButton lockerButton, WeaponLocker weaponLocker) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frame = new JFrame();
				contentPane = Frame.getContentPane();
				Frame.setSize(300, 400);
				Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				contentPane.setLayout(null);

				ticketNumberVector = new Vector<String>();

				empty = new JButton("Empty Locker");
				empty.setBounds(50, 50, 200, 50);
				empty.addActionListener(buttonListener);
				empty.setVisible(true);
				empty.setFont(new Font(empty.getFont().getName(), Font.BOLD, 25));
				contentPane.add(empty);

				add = new JButton("Add");
				add.setBounds(50, 250, 200, 50);
				add.addActionListener(buttonListener);
				add.setVisible(true);
				add.setFont(new Font(add.getFont().getName(), Font.BOLD, 25));
				contentPane.add(add);
				
				autoComboBoxTickets = new AutoComboBox();
				autoComboBoxTickets.setBounds(50, 150, 200, 50);
				autoComboBoxTickets.setVisible(true);
				autoComboBoxTickets.setSelectedIndex(-1);
				autoComboBoxTickets.setFont(new Font(autoComboBoxTickets.getFont().getName(), Font.BOLD, 30));
				contentPane.add(autoComboBoxTickets);

				populateTicketsToChooseFrom();

				Frame.setVisible(true);
			}
			
		});

		this.lockerButton = lockerButton;
		this.weaponLocker = weaponLocker;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void populateTicketsToChooseFrom() {
		int i = 0;
		while (i < WeaponStorage.getNumberOfTickets()) {
			ticketNumberVector.add(Integer.toString(WeaponStorage.getWeaponTicketNumberForElement(i)));
			i++;
		}

		ticketStringArray = ticketNumberVector.toArray(new String[ticketNumberVector.size()]);
		autoComboBoxTickets.setKeyWord(ticketStringArray);
	}

	ActionListener buttonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == empty) {

				tempPersonKey = weaponLocker.getWeaponTicket().getPerson().getPersonKey();
				try {
					WeaponStorage.emptyLockerFromLockerVector(tempPersonKey, lockerButton);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Frame.dispose();
				
			} else if (e.getSource() == add) {
				WeaponStorage.addTicketToLockerInExcel(lockerButton, (String) autoComboBoxTickets.getSelectedItem());
				Bible.updateLockerText(lockerButton, (String) autoComboBoxTickets.getSelectedItem());
				Frame.dispose();
			}
		}

	};
	
	
}
