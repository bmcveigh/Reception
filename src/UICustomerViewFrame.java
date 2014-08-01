//Brian McVeigh and Eduardo Mendoza
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;


public class UICustomerViewFrame extends JFrame
{

	private static final long serialVersionUID = 1L;


	//NORTH

	private JPanel reservationsPanel;


	//CENTER LEFT
	private String[] reservationLabels = {"First Name:", "Last Name:", "Address:", "City:", "State:", "Zip: ", "Check In Date:","Number of Nights: "};
	private JLabel[] labels;
	private JTextField[] textFields;
	private JLabel checkoutnum;
	private JButton checkoutbutton;
	@SuppressWarnings("rawtypes")
	private JComboBox getfreerooms;
	private JButton reservationButton;
	private JButton clearButton;

	//CENTER RIGHT
	private JPanel checkinpanel;
	private JLabel reserid;
	@SuppressWarnings("rawtypes")
	private JComboBox reservincombobox;
	private JButton checkinbutton;
	private JPanel checkoutpanel;
	@SuppressWarnings("rawtypes")
	private JComboBox checkoutcombobox;
	private JPanel eastPanel;
	@SuppressWarnings({ "unused", "rawtypes" })
	private JComboBox availabilityCombo;
	@SuppressWarnings({ "unused", "rawtypes" })
	private JComboBox setRoomTypeCombo1;
	@SuppressWarnings("unused")
	private JLabel currentRateLabel;
	private JButton clearleft;

	public UICustomerViewFrame(){
		super("Customer View");
		setSize(800, 700);
		setLayout(new GridLayout(1,2));		
		//left
		reservationsPanel = new JPanel();
		reservationsPanel.setBackground(Color.ORANGE);
		reservationsPanel.setLayout(new GridLayout(10, 2));
		reservationsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Reservation Information"));


		labels = new JLabel[reservationLabels.length];
		textFields = new JTextField[reservationLabels.length];

		for (int i = 0; i < reservationLabels.length; i++)
		{
			labels[i] = new JLabel(reservationLabels[i]);
			textFields[i] = new JTextField(10);
			reservationsPanel.add(labels[i]);
			reservationsPanel.add(textFields[i]);
		}
		textFields[6].setText("DD/MM/YYYY");
		reservationsPanel.add(new JLabel("Available Rooms: "));
		getfreerooms = getfreeRooms();
		reservationsPanel.add(getfreerooms);

		reservationButton = new JButton("Reservation");
		clearButton = new JButton("Clear Reservation");
		reservationsPanel.add(reservationButton);
		reservationsPanel.add(clearButton);
		add(reservationsPanel);


		//Right
		eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(5, 1));
		eastPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guest Options"));

		checkinpanel = new JPanel();
		checkinpanel.setLayout(new GridLayout(2, 2));
		checkinpanel.setBorder(new TitledBorder(new EtchedBorder(), "Check In Guest"));

		reserid = new JLabel("Reservation ID");
		reservincombobox = getcheckinrooms();


		checkinbutton = new JButton("Check In");

		checkinpanel.add(reserid);
		checkinpanel.add(reservincombobox);
		checkinpanel.add(checkinbutton);
		eastPanel.add(checkinpanel);


		checkoutpanel = new JPanel();
		checkoutpanel.setLayout(new GridLayout(2, 2));
		checkoutpanel.setBorder(new TitledBorder(new EtchedBorder(), "Check out Guest"));

		checkoutnum = new JLabel("Reservation ID");
		checkoutcombobox = getcheckoutrooms();
		checkoutbutton = new JButton("Check out");

		checkoutpanel.add(checkoutnum);
		checkoutpanel.add(checkoutcombobox);
		checkoutpanel.add(checkoutbutton);
		eastPanel.add(checkoutpanel);

		clearleft = new JButton("Clear Guest Options");
		eastPanel.add(clearleft);


		add(eastPanel);

		CheckinListener ci = new CheckinListener();
		checkinbutton.addActionListener(ci);
		checkoutbutton.addActionListener(ci);


		reservationListener reservl = new reservationListener();
		reservationButton.addActionListener(reservl);
		clearButton.addActionListener(reservl);

		clearadminListener clearad = new clearadminListener();
		clearleft.addActionListener(clearad);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getfreeRooms(){
		RoomDB roomdb = new RoomDB();
		ArrayList<Room> rooms = roomdb.getfreerooms();
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("-Rooms-");
		for (int i = 0; i < rooms.size(); i++){
			comboBox.addItem(rooms.get(i).getrid());
		}
		return comboBox;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void updatefreerooms(){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		RoomDB roomdb = new RoomDB();
		ListIterator<Room> iter = roomdb.getfreerooms().listIterator();  
		model.addElement("-Rooms-");
		while (iter.hasNext()) {  
			model.addElement(((Room)iter.next()).getrid());  
		}  
		getfreerooms.setModel(model);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getcheckinrooms(){
		ReservationDB reservdb = new ReservationDB();
		ArrayList<Reservation> reservationlist = reservdb.getuncheckreservations(0);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("-Reservation-");
		for (int i = 0; i < reservationlist.size(); i++){
			comboBox.addItem(reservationlist.get(i).getreservid());
		}
		return comboBox;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void updatecheckinrooms(){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		ReservationDB reservdb = new ReservationDB();
		ListIterator<Reservation> iter = reservdb.getuncheckreservations(0).listIterator();  
		model.addElement("-Reservation-");
		while (iter.hasNext()) {  
			model.addElement(((Reservation)iter.next()).getreservid());  
		}  
		reservincombobox.setModel(model);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getcheckoutrooms(){
		ReservationDB reservdb = new ReservationDB();
		ArrayList<Reservation> reservationlist = reservdb.getuncheckreservations(1);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("-Reservation-");
		for (int i = 0; i < reservationlist.size(); i++){
			comboBox.addItem(reservationlist.get(i).getreservid());
		}
		return comboBox;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void updatecheckoutrooms(){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		ReservationDB reservdb = new ReservationDB();
		ListIterator<Reservation> iter = reservdb.getuncheckreservations(1).listIterator();  
		model.addElement("-Reservation-");
		while (iter.hasNext()) {  
			model.addElement(((Reservation)iter.next()).getreservid());  
		}  
		checkoutcombobox.setModel(model);
	}
	private class CheckinListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == checkinbutton){
				if(reservincombobox.getSelectedIndex() != 0){
					ReservationDB rdb = new ReservationDB();
					rdb.updatereservation(Integer.parseInt(reservincombobox.getSelectedItem().toString()),1);
					updatecheckinrooms();
					updatecheckoutrooms();
					updatefreerooms();
				} else {
					JOptionPane.showMessageDialog(UICustomerViewFrame.this,"Reservation ID not selected.", "Check In", JOptionPane.WARNING_MESSAGE);
				}	
			}
			if (e.getSource() == checkoutbutton){
				if(checkoutcombobox.getSelectedIndex() != 0){
					ReservationDB rdb = new ReservationDB();
					Reservation rs = rdb.getreservation(Integer.parseInt(checkoutcombobox.getSelectedItem().toString()));
					rdb.updatereservation(Integer.parseInt(checkoutcombobox.getSelectedItem().toString()),0);

					JOptionPane.showMessageDialog(UICustomerViewFrame.this, rs.toString(), "Guest Bill", JOptionPane.INFORMATION_MESSAGE);
					updatecheckinrooms();
					updatecheckoutrooms();
					updatefreerooms();
				}
				else{
					JOptionPane.showMessageDialog(UICustomerViewFrame.this,"Reservation ID not selected.", "Check out", JOptionPane.WARNING_MESSAGE);
				}	
			}	
		}
	}

	private class reservationListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == reservationButton){

				if(!textFields[0].getText().equals("") && !textFields[1].getText().equals("") && !textFields[2].getText().equals("") && !textFields[3].getText().equals("") && !textFields[4].getText().equals("") && !textFields[5].getText().equals("") && getfreerooms.getSelectedIndex() != 0){

					GuestDB guestdb = new GuestDB();
					Guest guest = new Guest(guestdb.getcurrentid(),textFields[0].getText(),textFields[1].getText(),textFields[2].getText(),textFields[3].getText(),textFields[4].getText(),textFields[5].getText());
					if(guestdb.checkguest(guest) == true){
						guest = guestdb.getguestbyname(guest);
						System.out.print("\nFound");
					}
					else {
						guestdb.addguest(guest);
					}	
					RoomDB roomdb = new RoomDB();
					Room room = roomdb.getroom(Integer.parseInt(getfreerooms.getSelectedItem().toString()));
					Reservation reservation = new Reservation(0,guest,room,textFields[6].getText(),0,Integer.parseInt(textFields[7].getText()));
					ReservationDB reservdb = new ReservationDB();
					reservdb.addreservation(reservation);
					JOptionPane.showMessageDialog(UICustomerViewFrame.this,"Reservation has been added.", "Add Reservation", JOptionPane.INFORMATION_MESSAGE);
					for (int i = 0; i < reservationLabels.length; i++){
						if(i != 6){
							textFields[i].setText("");
						}
						else{
							textFields[i].setText("DD/MM/YYYY");
						}

					}
					updatecheckinrooms();
					updatecheckoutrooms();
					updatefreerooms();

				}
				else{
					JOptionPane.showMessageDialog(UICustomerViewFrame.this,"Not all sections have been filled.", "Add Reservation", JOptionPane.WARNING_MESSAGE);
				}
			}
			if (e.getSource() == clearButton){
				for (int i = 0; i < reservationLabels.length; i++){
					if(i != 6){
						textFields[i].setText("");
					}
					else{
						textFields[i].setText("DD/MM/YYYY");
					}

				}
				getfreerooms.setSelectedIndex(0);		

			}	
		}
	}
	private class clearadminListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			reservincombobox.setSelectedIndex(0);
			checkoutcombobox.setSelectedIndex(0);
		}

	}					

}
