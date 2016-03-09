package edu.usu.bible;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import edu.usu.customDialogBoxes.CreateNewTicket;
import edu.usu.customDialogBoxes.EditPerson;
import edu.usu.customDialogBoxes.ModifyLocker;
import edu.usu.customDialogBoxes.OldCardReport;
import edu.usu.weaponsStorage.LockerButton;
import edu.usu.weaponsStorage.WeaponLocker;
import edu.usu.weaponsStorage.WeaponStorage;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.*;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Bible implements MouseListener, ActionListener {

	private JFrame mainFrame;
	private Vector<WeaponLocker> lockerVector = new Vector<WeaponLocker>();
	private Container pane;
	private JButton searchFiles;
	private JTextField fileSearchValueField;
	private String searchValue;
	private Vector<String> buildingArray = new Vector<String>();
	private Vector<String> buildingArrayFilePath = new Vector<String>();
	private File folder;
	public int fileCount = 0;
	private String matchedPath;
	boolean found = false;
	private int matchedIndex;
	private JButton fileChoser, openFile, update;
	private JFileChooser jfc = new JFileChooser();
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private String line = null, readOutLine = null;
	private JLabel addressLabel, addressField, mainAlarmPanelLocationLabel, sprinklerSystemLabel, fireHydrantLabel,
			elevatorShutOffLabel, contactOneLabel, contactTwoLabel, contactThreeLabel, additionInformationLabel;
	private JLabel bestRouteLabel, emAccessLabel, roofAccessLabel, enunciatorPanel;
	private JTextArea bestRouteField, emAccessField, roofAccessField, enunciatorField, mainAlarmPanelLocationField,
			sprinklerSystemField, fireHydrantField, elevatorShutOffField, contactOneField, contactTwoField,
			contactThreeField, additionInformationField;
	private String matchedFile;
	private boolean isBold = true;
	private int positionStart, positionEnd, length;
	private static final String NOT_SET = "Not Set";
	private String[] fileData;
	private AutoComboBox autoComboBox = new AutoComboBox();
	private String[] buildings;
	private JButton weaponsStorageButton, onCallFacilities;
	private WeaponStorage weaponStorage = new WeaponStorage();
	private static LockerButton[][] lockerButtonLayout;
	private static JLabel[][] ticketLabelLayout;
	private JPanel boardPanel;
	private LockerButton tmp;
	private JLabel tempTicketLabel;
	private String temp;
	private JButton goToBible;
	private JButton setWeaponStorageFileLocation;
	private JButton oldCardReport;
	private JButton createNewTicket;
	private JButton deleteTicket;
	private JButton editPerson;
	//private CreateNewTicket createNewTicketTemp;

	public Bible() {
		mainFrame = new JFrame("Bible");

		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);

		pane = mainFrame.getContentPane();
		pane.setLayout(null);

		// Search Files Button
		searchFiles = new JButton("Search Files");
		searchFiles.setFont(new Font("Arial", Font.BOLD, 30));
		searchFiles.setBounds(0, 0, 300, 100);
		searchFiles.addActionListener(buttonListener);
		pane.add(searchFiles);

		// Dropdown box
		autoComboBox.setBounds(300, 0, 200, 100);
		autoComboBox.setFont(new Font("Arial", Font.PLAIN, 30));
		pane.add(autoComboBox);

		// Populate file list
		folder = new File("C:/Users/Richard/Desktop/Dispach Bible/Fire Dept Bldg Info");
		//folder = new File("C:/Users/Dispatch1-user/Desktop/Dispach Bible/Dispach Bible/Fire Dept Bldg Info");
//		JFileChooser chooser = new JFileChooser();
//		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		chooser.showDialog(null,"Please select the bible documents folder");
//		chooser.setVisible(true);
//		folder =  chooser.getSelectedFile();
		listFilesForFolder(folder);

		// Choose file directory for Bible
		fileChoser = new JButton("Choose Directory for Bible documents");
		fileChoser.setFont(new Font("Arial", Font.BOLD, 15));
		fileChoser.setBounds(500, 0, 350, 100);
		fileChoser.addActionListener(buttonListener);
		pane.add(fileChoser);

		// Open document
		openFile = new JButton("Open building document");
		openFile.setFont(new Font("Arial", Font.BOLD, 15));
		openFile.setBounds(850, 0, 300, 100);
		openFile.addActionListener(buttonListener);
		pane.add(openFile);

		// Manually update
		update = new JButton("Refresh");
		update.setFont(new Font("Arial", Font.BOLD, 15));
		update.setBounds(1150, 0, 300, 100);
		update.addActionListener(buttonListener);
		pane.add(update);

		// Weapons Storage
		weaponsStorageButton = new JButton("Weapons Storage");
		weaponsStorageButton.setFont(new Font("Arial", Font.BOLD, 15));
		weaponsStorageButton.setBounds(1450, 0, 200, 100);
		weaponsStorageButton.addActionListener(buttonListener);
		weaponsStorageButton.setForeground(Color.BLUE);
		pane.add(weaponsStorageButton);

		// On Call
		onCallFacilities = new JButton("On Call");
		onCallFacilities.setFont(new Font("Arial", Font.BOLD, 15));
		onCallFacilities.setBounds(1650, 0, 200, 100);
		onCallFacilities.addActionListener(buttonListener);
		onCallFacilities.setForeground(Color.GREEN);
		pane.add(onCallFacilities);

		// Labels and their corresponding values
		// Address
		addressLabel = new JLabel("ADDRESS: ", SwingConstants.RIGHT);
		addressLabel.setFont(new Font(addressLabel.getFont().getName(), Font.BOLD, 20));
		addressLabel.setBounds(25, 100, 150, 100);
		addressLabel.setForeground(Color.red);
		pane.add(addressLabel);

		addressField = new JLabel(NOT_SET, SwingConstants.LEFT);
		addressField.setFont(new Font(addressField.getFont().getName(), Font.PLAIN, 40));
		addressField.setBounds(175, 100, 300, 100);
		pane.add(addressField);

		// Best Route
		bestRouteLabel = new JLabel("BEST ROUTE: ", SwingConstants.RIGHT);
		bestRouteLabel.setFont(new Font(bestRouteLabel.getFont().getName(), Font.BOLD, 20));
		bestRouteLabel.setBounds(475, 100, 150, 100);
		bestRouteLabel.setForeground(Color.red);
		pane.add(bestRouteLabel);

		bestRouteField = new JTextArea();
		bestRouteField.setFont(new Font(bestRouteField.getFont().getName(), Font.PLAIN, 20));
		bestRouteField.setBounds(625, 100, 1200, 100);
		bestRouteField.setLineWrap(true);
		bestRouteField.setBackground(null);
		pane.add(bestRouteField);

		// Emergency Access
		emAccessLabel = new JLabel("EMERGENCY VEHICLE ACCESS: ");
		emAccessLabel.setFont(new Font(emAccessLabel.getFont().getName(), Font.BOLD, 20));
		emAccessLabel.setBounds(0, 200, 325, 100);
		emAccessLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emAccessLabel.setVerticalAlignment(SwingConstants.TOP);
		emAccessLabel.setForeground(Color.red);
		pane.add(emAccessLabel);

		emAccessField = new JTextArea();
		emAccessField.setFont(new Font(emAccessField.getFont().getName(), Font.PLAIN, 20));
		emAccessField.setBounds(325, 200, 1200, 100);
		emAccessField.setLineWrap(true);
		emAccessField.setBackground(null);
		pane.add(emAccessField);

		// Roof Access
		roofAccessLabel = new JLabel("Roof Access or Attic Access: ");
		roofAccessLabel.setFont(new Font(roofAccessLabel.getFont().getName(), Font.BOLD, 20));
		roofAccessLabel.setBounds(0, 300, 325, 50);
		roofAccessLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		roofAccessLabel.setVerticalAlignment(SwingConstants.CENTER);
		roofAccessLabel.setForeground(Color.red);
		pane.add(roofAccessLabel);

		roofAccessField = new JTextArea();
		roofAccessField.setFont(new Font(emAccessField.getFont().getName(), Font.PLAIN, 20));
		roofAccessField.setBounds(325, 300, 1200, 50);
		roofAccessField.setLineWrap(true);
		roofAccessField.setBackground(null);
		pane.add(roofAccessField);

		// Enunciator Panel:
		enunciatorPanel = new JLabel("Enunciator Panel: ");
		enunciatorPanel.setFont(new Font(enunciatorPanel.getFont().getName(), Font.BOLD, 20));
		enunciatorPanel.setBounds(0, 350, 325, 50);
		enunciatorPanel.setHorizontalAlignment(SwingConstants.RIGHT);
		enunciatorPanel.setVerticalAlignment(SwingConstants.CENTER);
		enunciatorPanel.setForeground(Color.red);
		pane.add(enunciatorPanel);

		enunciatorField = new JTextArea();
		enunciatorField.setFont(new Font(enunciatorField.getFont().getName(), Font.PLAIN, 20));
		enunciatorField.setBounds(325, 350, 1200, 50);
		enunciatorField.setLineWrap(true);
		enunciatorField.setBackground(null);
		pane.add(enunciatorField);

		// Main Alarm Panel Location
		mainAlarmPanelLocationLabel = new JLabel("Main alarm panel: ");
		mainAlarmPanelLocationLabel.setFont(new Font(mainAlarmPanelLocationLabel.getFont().getName(), Font.BOLD, 20));
		mainAlarmPanelLocationLabel.setBounds(0, 400, 325, 50);
		mainAlarmPanelLocationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		mainAlarmPanelLocationLabel.setVerticalAlignment(SwingConstants.CENTER);
		mainAlarmPanelLocationLabel.setForeground(Color.red);
		pane.add(mainAlarmPanelLocationLabel);

		mainAlarmPanelLocationField = new JTextArea();
		mainAlarmPanelLocationField.setFont(new Font(mainAlarmPanelLocationField.getFont().getName(), Font.PLAIN, 20));
		mainAlarmPanelLocationField.setBounds(325, 400, 1200, 50);
		mainAlarmPanelLocationField.setLineWrap(true);
		mainAlarmPanelLocationField.setBackground(null);
		pane.add(mainAlarmPanelLocationField);

		// Sprinkler System
		sprinklerSystemLabel = new JLabel("Sprinkler System: ");
		sprinklerSystemLabel.setFont(new Font(sprinklerSystemLabel.getFont().getName(), Font.BOLD, 20));
		sprinklerSystemLabel.setBounds(0, 450, 325, 50);
		sprinklerSystemLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sprinklerSystemLabel.setVerticalAlignment(SwingConstants.CENTER);
		sprinklerSystemLabel.setForeground(Color.red);
		pane.add(sprinklerSystemLabel);

		sprinklerSystemField = new JTextArea();
		sprinklerSystemField.setFont(new Font(sprinklerSystemField.getFont().getName(), Font.PLAIN, 20));
		sprinklerSystemField.setBounds(325, 450, 1200, 50);
		sprinklerSystemField.setLineWrap(true);
		sprinklerSystemField.setBackground(null);
		pane.add(sprinklerSystemField);

		// Fire Hydrant Locations
		fireHydrantLabel = new JLabel("Fire Hydrant: ");
		fireHydrantLabel.setFont(new Font(fireHydrantLabel.getFont().getName(), Font.BOLD, 20));
		fireHydrantLabel.setBounds(0, 500, 325, 150);
		fireHydrantLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fireHydrantLabel.setVerticalAlignment(SwingConstants.CENTER);
		fireHydrantLabel.setForeground(Color.red);
		pane.add(fireHydrantLabel);

		fireHydrantField = new JTextArea();
		fireHydrantField.setFont(new Font(fireHydrantField.getFont().getName(), Font.PLAIN, 20));
		fireHydrantField.setBounds(325, 500, 1200, 150);
		fireHydrantField.setLineWrap(true);
		fireHydrantField.setBackground(null);
		pane.add(fireHydrantField);

		// Elevator Shut off
		elevatorShutOffLabel = new JLabel("Elevator Shut off: ");
		elevatorShutOffLabel.setFont(new Font(elevatorShutOffLabel.getFont().getName(), Font.BOLD, 20));
		elevatorShutOffLabel.setBounds(0, 650, 325, 50);
		elevatorShutOffLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		elevatorShutOffLabel.setVerticalAlignment(SwingConstants.CENTER);
		elevatorShutOffLabel.setForeground(Color.red);
		pane.add(elevatorShutOffLabel);

		elevatorShutOffField = new JTextArea();
		elevatorShutOffField.setFont(new Font(elevatorShutOffField.getFont().getName(), Font.PLAIN, 20));
		elevatorShutOffField.setBounds(325, 650, 1200, 50);
		elevatorShutOffField.setLineWrap(true);
		elevatorShutOffField.setBackground(null);
		pane.add(elevatorShutOffField);

		// Contact One
		contactOneLabel = new JLabel("Contact One: ");
		contactOneLabel.setFont(new Font(contactOneLabel.getFont().getName(), Font.BOLD, 20));
		contactOneLabel.setBounds(0, 700, 325, 50);
		contactOneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contactOneLabel.setVerticalAlignment(SwingConstants.CENTER);
		contactOneLabel.setForeground(Color.red);
		pane.add(contactOneLabel);

		contactOneField = new JTextArea();
		contactOneField.setFont(new Font(contactOneField.getFont().getName(), Font.PLAIN, 20));
		contactOneField.setBounds(325, 700, 1200, 50);
		contactOneField.setLineWrap(true);
		contactOneField.setBackground(null);
		pane.add(contactOneField);

		// Contact two
		contactTwoLabel = new JLabel("Contact Two: ");
		contactTwoLabel.setFont(new Font(contactTwoLabel.getFont().getName(), Font.BOLD, 20));
		contactTwoLabel.setBounds(0, 750, 325, 50);
		contactTwoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contactTwoLabel.setVerticalAlignment(SwingConstants.CENTER);
		contactTwoLabel.setForeground(Color.red);
		pane.add(contactTwoLabel);

		contactTwoField = new JTextArea();
		contactTwoField.setFont(new Font(contactTwoField.getFont().getName(), Font.PLAIN, 20));
		contactTwoField.setBounds(325, 750, 1200, 50);
		contactTwoField.setLineWrap(true);
		contactTwoField.setBackground(null);
		pane.add(contactTwoField);

		// Contact three
		contactThreeLabel = new JLabel("Contact Three: ");
		contactThreeLabel.setFont(new Font(contactThreeLabel.getFont().getName(), Font.BOLD, 20));
		contactThreeLabel.setBounds(0, 800, 325, 50);
		contactThreeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contactThreeLabel.setVerticalAlignment(SwingConstants.CENTER);
		contactThreeLabel.setForeground(Color.red);
		pane.add(contactThreeLabel);

		contactThreeField = new JTextArea();
		contactThreeField.setFont(new Font(contactThreeField.getFont().getName(), Font.PLAIN, 20));
		contactThreeField.setBounds(325, 800, 1200, 50);
		contactThreeField.setLineWrap(true);
		contactThreeField.setBackground(null);
		pane.add(contactThreeField);

		// Additional Information
		additionInformationLabel = new JLabel("Additional Information: ");
		additionInformationLabel.setFont(new Font(additionInformationLabel.getFont().getName(), Font.BOLD, 20));
		additionInformationLabel.setBounds(0, 850, 325, 50);
		additionInformationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		additionInformationLabel.setVerticalAlignment(SwingConstants.CENTER);
		additionInformationLabel.setForeground(Color.red);
		pane.add(additionInformationLabel);

		additionInformationField = new JTextArea();
		additionInformationField.setFont(new Font(additionInformationField.getFont().getName(), Font.PLAIN, 20));
		additionInformationField.setBounds(325, 850, 1200, 50);
		additionInformationField.setLineWrap(true);
		additionInformationField.setBackground(null);
		pane.add(additionInformationField);

		// Navigate back to bible
		goToBible = new JButton("Go Back to Bible");
		goToBible.setFont(new Font("Arial", Font.BOLD, 15));
		goToBible.setBounds(0, 900, 200, 100);
		goToBible.addActionListener(buttonListener);
		goToBible.setForeground(Color.RED);
		goToBible.setVisible(false);
		pane.add(goToBible, BorderLayout.PAGE_END);

		// Set location of Excel file for weapon storage
		setWeaponStorageFileLocation = new JButton("Set weapons storage file location");
		setWeaponStorageFileLocation.setFont(new Font("Arial", Font.BOLD, 15));
		setWeaponStorageFileLocation.setBounds(200, 900, 400, 100);
		setWeaponStorageFileLocation.addActionListener(buttonListener);
		setWeaponStorageFileLocation.setVisible(false);
		pane.add(setWeaponStorageFileLocation, BorderLayout.PAGE_END);

		createNewTicket = new JButton("Create New Ticket");
		createNewTicket.setFont(new Font("Arial", Font.BOLD, 15));
		createNewTicket.setBounds(600, 900, 300, 100);
		createNewTicket.addActionListener(buttonListener);
		createNewTicket.setVisible(false);
		pane.add(createNewTicket, BorderLayout.PAGE_END);

		editPerson = new JButton("Edit Person");
		editPerson.setFont(new Font("Arial", Font.BOLD, 15));
		editPerson.setBounds(900, 900, 300, 100);
		editPerson.addActionListener(buttonListener);
		editPerson.setVisible(false);
		pane.add(editPerson, BorderLayout.PAGE_END);
		
		oldCardReport = new JButton("Old Cards");
		oldCardReport.setFont(new Font("Arial", Font.BOLD, 15));
		oldCardReport.setBounds(1200, 900, 300, 100);
		oldCardReport.addActionListener(buttonListener);
		oldCardReport.setVisible(false);
		pane.add(oldCardReport, BorderLayout.PAGE_END);

		// Lockers
		boardPanel = new JPanel();
		boardPanel.setLayout(null);

		createLockers();

		// Set to visible
		mainFrame.setVisible(true);
	}

	ActionListener buttonListener = new ActionListener() {

		// we have to define this method in order for an Action Listener to work
		public void actionPerformed(ActionEvent e) { // 'e' is the Action Event

			if (e.getSource() == searchFiles) {
				searchValue = (String) autoComboBox.getSelectedItem();

				found = false;

				for (int search = 0; search <= buildings.length && !found; search++) {
					if (searchValue.compareTo(buildings[search].toString()) == 0) {
						matchedFile = buildings[search].toString();
						matchedPath = buildingArrayFilePath.get(search);
						found = true;
						matchedIndex = search;
					}
				}

				File file = null;
				WordExtractor extractor = null;

				try {
					file = new File(matchedPath);
					FileInputStream fis = new FileInputStream(file.getAbsolutePath());
					HWPFDocument document = new HWPFDocument(fis);
					extractor = new WordExtractor(document);
					fileData = extractor.getParagraphText();
				} catch (FileNotFoundException ex) {
					// System.out.println("File not found");
					JOptionPane.showMessageDialog(null, "File not found");
				} catch (IOException ex) {
					// System.out.println("IO Exception");
					JOptionPane.showMessageDialog(null, "IO Exception");
				}

				found = false;

				for (int i = 0; i < fileData.length && !found; i++) {
					line = fileData[i].toString().toLowerCase();
					System.out.println(fileData[i].toString());
					if (line.indexOf(("\r\n").toLowerCase()) != 0) {
						if (line.indexOf(("Approximate Address:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Approximate Address:");
							positionStart = positionStart + ("Approximate Address:").length() + 1;
							addressField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Address:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Address:");
							positionStart = positionStart + ("Address:").length() + 1;
							addressField.setText(line.substring(positionStart).trim());
						}else if (line.indexOf(("Best Route:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Best Route:");
							positionStart = positionStart + ("Best Route:").length() + 1;
							bestRouteField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Emergency Vehicle Access:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Emergency Vehicle Access:");
							positionStart = positionStart + ("Emergency Vehicle Access:").length() + 1;
							emAccessField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Roof Access:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Roof Access:");
							positionStart = positionStart + ("Roof Access:").length() + 1;
							roofAccessField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Attic Access:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Attic Access:");
							positionStart = positionStart + ("Attic Access:").length() + 1;
							roofAccessField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Enunciator Panel:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Enunciator Panel:");
							positionStart = positionStart + ("Enunciator Panel:").length() + 1;
							enunciatorField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Main Alarm Panel Location:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Main Alarm Panel Location:");
							positionStart = positionStart + ("Main Alarm Panel Location:").length() + 1;
							mainAlarmPanelLocationField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Sprinkler System:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Sprinkler System:");
							positionStart = positionStart + ("Sprinkler System:").length() + 1;
							sprinklerSystemField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Fire Hydrant Location:").toLowerCase().trim()) == 0) {
							String tempHydrant=null;
							while(fileData[(i+1)].toString().toLowerCase().indexOf(("\r\n").toLowerCase()) != 0){
								i++;
								line = fileData[i].toString().toLowerCase();
								positionStart = line.toString().toLowerCase().lastIndexOf("1.");
								if(tempHydrant == null){
									positionStart = positionStart + ("1.").length() + 1;
									tempHydrant = line.substring(positionStart).trim();
								}else{
									positionStart = positionStart + ("1.").length() + 3;
									tempHydrant += ("\n"+line.substring(positionStart).trim());
								}
							}
							fireHydrantField.setText(tempHydrant);
						} else if (line.indexOf(("Elevator Shutoff:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Elevator Shutoff:");
							positionStart = positionStart + ("Elevator Shutoff:").length() + 1;
							elevatorShutOffField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Contact 1:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Contact 1:");
							positionStart = positionStart + ("Contact 1:").length() + 1;
							contactOneField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Contact 2:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Contact 2:");
							positionStart = positionStart + ("Contact 2:").length() + 1;
							contactTwoField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Contact 3:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Contact 3:");
							positionStart = positionStart + ("Contact 3:").length() + 1;
							contactThreeField.setText(line.substring(positionStart).trim());
						} else if (line.indexOf(("Additional Information:").toLowerCase().trim()) == 0) {
							positionStart = line.lastIndexOf("Additional Information:");
							positionStart = positionStart + ("Additional Information:").length() + 1;
							additionInformationField.setText(line.substring(positionStart).trim());
						}
					}
				}
				found = false;
			}

			if (e.getSource() == fileChoser) {
				jfc.showDialog(null, "Please Select the File");
				jfc.setVisible(true);
				folder = jfc.getSelectedFile();

				if (folder != null) {
					listFilesForFolder(folder);
				}
			}

			if (e.getSource() == openFile) {
				searchValue = (String) autoComboBox.getSelectedItem();

				found = false;

				for (int search = 0; search <= buildingArray.size() && !found; search++) {
					if (searchValue.compareTo(buildingArray.get(search)) == 0) {
						matchedFile = buildingArray.get(search);
						matchedPath = buildingArrayFilePath.get(search);
						found = true;
						matchedIndex = search;
					}
				}

				File file = null;
				WordExtractor extractor = null;

				try {
					file = new File(matchedPath);
					FileInputStream fis = new FileInputStream(file.getAbsolutePath());
					HWPFDocument document = new HWPFDocument(fis);
					extractor = new WordExtractor(document);
					fileData = extractor.getParagraphText();
				} catch (FileNotFoundException ex) {
					// System.out.println("File not found");
					JOptionPane.showMessageDialog(null, "File not found");
				} catch (IOException ex) {
					// System.out.println("IO Exception");
					JOptionPane.showMessageDialog(null, "IO Exception");
				}
				try {
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().open(file);
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please save files as .docx");
				}
			}

			if (e.getSource() == update) {
				listFilesForFolder(folder);
			}

			if (e.getSource() == weaponsStorageButton) {
				hideBible(false);
				hideWeaponsStorage(true);
				pane.update(pane.getGraphics());
			}

			if (e.getSource() == goToBible) {
				hideWeaponsStorage(false);
				hideBible(true);
				pane.update(pane.getGraphics());
			}

			if (e.getSource() == setWeaponStorageFileLocation) {
				jfc.showDialog(null, "Please Select the File");
				jfc.setVisible(true);
				folder = jfc.getSelectedFile();

				if (folder != null) {
					listFilesForFolder(folder);
				}
			}
			if (e.getSource() == oldCardReport) {
				new OldCardReport();
			}
			if (e.getSource() == createNewTicket) {
				new CreateNewTicket();
			}
			if (e.getSource() == deleteTicket) {
				jfc.showDialog(null, "Please Select the File");
				jfc.setVisible(true);
				folder = jfc.getSelectedFile();

				WeaponStorage.chooseFile(folder.toString());
			}
			if (e.getSource() == editPerson) {
				new EditPerson();
			}

		}
	};

	public void mouseClicked(MouseEvent e) {
		tmp = (LockerButton) e.getSource();
		if (SwingUtilities.isRightMouseButton(e)) {
			new ModifyLocker(tmp, tmp.getWeaponLocker());
			// resetLockers(tmp);

		} else if (tmp.getWeaponLocker().getWeaponTicket().getTicketNumber() != -1) {
			JOptionPane.showMessageDialog(null, tmp.getLockerInformation());
		}
		// Right click on locker and open options
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void clearLockers() {
		boardPanel.removeAll();
	}

	public void createLockers() {

		lockerVector = weaponStorage.lockers();

		int numOfWeapons = lockerVector.size();
		int width = (numOfWeapons / 15 + 1) * 2;
		int height = 15;
		int position = 0;

		this.lockerButtonLayout = new LockerButton[width][height];
		this.ticketLabelLayout = new JLabel[width][height];

		for (int w = 0; w < width && (position < numOfWeapons); w = w + 2) {
			for (int h = 0; h < height && (position < numOfWeapons); h++) {
				tmp = new LockerButton(lockerVector.elementAt(position));
				tmp.setBounds(w * 100, h * 50, 100, 50);
				tmp.setText(tmp.getLockerName());
				tmp.addMouseListener(this);
				temp = Integer.toString(lockerVector.elementAt(position).getWeaponTicket().getTicketNumber());
				tempTicketLabel = new JLabel();
				tempTicketLabel.setFont(new Font("Arial", Font.BOLD, 20));
				if (temp.equals("-1")) {
					tempTicketLabel.setText("EMPTY");
				} else {
					tempTicketLabel.setText(temp);
				}
				tempTicketLabel.setBounds((w + 1) * 100, (h) * 50, 100, 50);
				ticketLabelLayout[w][h] = tempTicketLabel;
				tmp.setH(h);
				tmp.setW(w);
				lockerButtonLayout[w][h] = tmp;
				boardPanel.add(ticketLabelLayout[w][h]);
				boardPanel.add(lockerButtonLayout[w][h]);
				position++;
			}
		}
		boardPanel.setBounds(0, 0, (100) * width, (50 * 2) * height);
		boardPanel.setVisible(false);
		pane.add(boardPanel, BorderLayout.CENTER);
	}

	public static void resetLockers(LockerButton lockerButton) {
		int w = lockerButton.getW();
		int h = lockerButton.getH();

		ticketLabelLayout[(w)][h].setText("EMPTY");
	}

	public static void updateLockerText(LockerButton lockerButton, String text) {
		int w = lockerButton.getW();
		int h = lockerButton.getH();

		ticketLabelLayout[(w)][h].setText(text);
	}

	public void hideBible(boolean show) {
		searchFiles.setVisible(show);
		autoComboBox.setVisible(show);
		fileChoser.setVisible(show);
		openFile.setVisible(show);
		update.setVisible(show);
		weaponsStorageButton.setVisible(show);
		onCallFacilities.setVisible(show);
		addressLabel.setVisible(show);
		addressField.setVisible(show);
		bestRouteLabel.setVisible(show);
		bestRouteField.setVisible(show);
		emAccessLabel.setVisible(show);
		emAccessField.setVisible(show);
		roofAccessLabel.setVisible(show);
		roofAccessField.setVisible(show);
		enunciatorPanel.setVisible(show);
		enunciatorField.setVisible(show);
		mainAlarmPanelLocationLabel.setVisible(show);
		mainAlarmPanelLocationField.setVisible(show);
		sprinklerSystemLabel.setVisible(show);
		sprinklerSystemField.setVisible(show);
		fireHydrantField.setVisible(show);
		fireHydrantLabel.setVisible(show);
		elevatorShutOffField.setVisible(show);
		elevatorShutOffLabel.setVisible(show);
		contactOneLabel.setVisible(show);
		contactOneField.setVisible(show);
		contactTwoField.setVisible(show);
		contactTwoLabel.setVisible(show);
		contactThreeLabel.setVisible(show);
		contactThreeField.setVisible(show);
		additionInformationField.setVisible(show);
		additionInformationLabel.setVisible(show);
		pane.update(pane.getGraphics());
	}

	public void hideWeaponsStorage(boolean show) {
		boardPanel.setVisible(show);
		goToBible.setVisible(show);
		setWeaponStorageFileLocation.setVisible(show);
		createNewTicket.setVisible(show);
		editPerson.setVisible(show);
		oldCardReport.setVisible(show);
		pane.update(pane.getGraphics());
	}

	public void listFilesForFolder(File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				// System.out.println(fileEntry.getName());
				buildingArray.add(fileEntry.getName().substring(0, (fileEntry.getName().length() - 4)));
				buildingArrayFilePath.add(fileEntry.getPath());
				fileCount++;

			}
		}
		buildings = buildingArray.toArray(new String[buildingArray.size()]);
		autoComboBox.setKeyWord(buildings);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Bible();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
