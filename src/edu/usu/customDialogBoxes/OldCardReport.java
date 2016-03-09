package edu.usu.customDialogBoxes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import edu.usu.weaponsStorage.OldCards;
import edu.usu.weaponsStorage.WeaponStorage;
import edu.usu.weaponsStorage.WeaponTicket;

public class OldCardReport implements ActionListener, MouseListener {

	private JFrame Frame;
	private Container contentPane;
	public Vector<OldCards> oldCardsVector;
	private LocalDateTime now;
	private JButton close;

	public OldCardReport() {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frame = new JFrame();
				contentPane = Frame.getContentPane();
				Frame.setSize(300, 400);
				Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				contentPane.setLayout(new GridLayout());

				close = new JButton("Close");
				close.addActionListener(buttonListener);
				
				close.setVisible(true);

				now = LocalDateTime.now();

				oldCardsVector = new Vector<OldCards>();
				oldCardsVector = findOldCards(now);

				contentPane.setLayout(new GridLayout((oldCardsVector.size() + 1), 2));

				JLabel spacer = new JLabel();

				contentPane.add(close);
				contentPane.add(spacer);

				int i = 0;
				while (i < oldCardsVector.size()) {
					JButton temp = new JButton("Delete: " + oldCardsVector.elementAt(i).getTicketNumber());
					temp.addMouseListener(mouseListener);
					contentPane.add(temp);
					JLabel tempLabel = new JLabel(
							("Semester Created: " + oldCardsVector.elementAt(i).getSemesterCreated()));
					contentPane.add(tempLabel);
					i++;
				}
				Frame.setVisible(true);
			}
		});
	}

	ActionListener buttonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (e.getSource() == close) {
				Frame.dispose();
			}
		}
	};

	MouseListener mouseListener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton tmp = (JButton) e.getSource();
			if (SwingUtilities.isRightMouseButton(e)) {
				String tempString = tmp.getText().split(" ")[1];
				WeaponTicket tempWeaponTicket = WeaponStorage.getWeaponTicketFromTicketNumber(Integer.parseInt(tempString));
				int choice = JOptionPane.showConfirmDialog(null,
						(tempWeaponTicket.print()+"\n\n"+"Are you sure you want to delete this ticket. \n This CANNOT be undone."));

				if (choice == 0) {
					WeaponStorage.deleteOldCard(Integer.parseInt(tempString));

					JButton searchTmp;
					int i = 2;
					boolean found = false;
					while (i < (contentPane.getSize().getHeight()) && !found) {
						searchTmp = (JButton) contentPane.getComponent(i);

						if (searchTmp.equals(tmp)) {
							contentPane.remove((i + 1));
							contentPane.remove(i);
							found = true;
						}
						i += 2;
					}
					contentPane.revalidate();
					contentPane.repaint();
				}
			} else {
				String tempString = tmp.getText().split(" ")[1];
				WeaponTicket tempWeaponTicket = WeaponStorage.getWeaponTicketFromTicketNumber(Integer.parseInt(tempString));
				JOptionPane.showMessageDialog(null, tempWeaponTicket.print());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	};

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	private Vector<OldCards> findOldCards(LocalDateTime now) {
		Vector<OldCards> tempOldCards = new Vector<OldCards>();
		Vector<WeaponTicket> tempTickets = new Vector<WeaponTicket>();
		String tempYear = null;
		String[] tempYearBroken;

		tempTickets = WeaponStorage.getTickets();

		int i = 0;
		while (i < tempTickets.size()) {
			tempYear = tempTickets.elementAt(i).getsemesterCreated();
			tempYearBroken = tempYear.split(" ");
			if (tempYearBroken.length == 2) {

				tempYear = tempYearBroken[1];
				if ((now.getYear()) > (Integer.parseInt(tempYear) + 5)) {
					tempOldCards.add(new OldCards(tempTickets.elementAt(i), tempTickets.elementAt(i).getTicketNumber(),
							tempYear));
				}
			}
			i++;
		}
		return tempOldCards;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
