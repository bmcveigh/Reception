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
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

/**
 * @project Reception
 * @file UIAdministrativeFrame.java
 * @author BrianMcVeigh
 * @date Jul 30, 2014
 * @time 9:13:44 AM
 */
public class UIAdministrativeFrame extends JFrame
{

	private static final long serialVersionUID = 1L;

	//NORTH
	private JPanel northPanel;
	private JPanel salesPanel;
	private JPanel salesPerRoomGroupPanel;
	private JPanel topSellersPanel;
	private JPanel reservationsPanel;

	//CENTER
	private JPanel centerPanel;

	//CENTER LEFT
	private String[] reservationLabels = {"First Name:", "Last Name:", "Address:", "City:", "State:", "Zip: ", "Check In Date:","Number of Nights: "};
	private JLabel[] labels;
	private JTextField[] textFields;
	private JLabel salesLabel;
	private JLabel weeklylabel;
	private JLabel salesPerRoomGroupLabel;
	private JLabel topSellersLabel;
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
	private JPanel availabilityPanel;
	private JButton availiablebutton;
	@SuppressWarnings("rawtypes")
	private JComboBox availabilityCombo;

	@SuppressWarnings("rawtypes")
	private JComboBox setRoomTypeCombo1;

	private JLabel newRateLabel;
	private JTextField newRateField;
	private JButton setratebutton;
	private JPanel setRatesPanel;

	private JLabel roomNumberLabel;
	private JTextField roomNumberField;
	private JTextField addRoomField;
	private JPanel addRoomPanel;
	private JButton addroombutton;
	@SuppressWarnings("rawtypes")
	private JComboBox setRoomTypeCombo2;
	private JButton clearleft;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UIAdministrativeFrame(){
		super("Administrative View");
		setSize(800, 700);
		setLayout(new BorderLayout());

		northPanel = new JPanel();

		northPanel.setLayout(new GridLayout(1, 3));
		northPanel.setBorder(new TitledBorder(new EtchedBorder(), "Business Data"));

		salesPanel = new JPanel();
		salesPanel.setBackground(Color.YELLOW);
		salesPanel.setLayout(new GridLayout(2, 1));
		salesPanel.setBorder(new TitledBorder(new EtchedBorder(), "Sales"));
		ReservationDB reservationDB = new ReservationDB();
		salesLabel = new JLabel(reservationDB.getsalestotal());
		weeklylabel = new JLabel(reservationDB.getweeklysales());
		salesPanel.add(salesLabel);
		salesPanel.add(weeklylabel);
		northPanel.add(salesPanel);

		salesPerRoomGroupPanel = new JPanel();
		salesPerRoomGroupPanel.setBackground(Color.YELLOW);
		salesPerRoomGroupPanel.setLayout(new GridLayout(1, 1));
		salesPerRoomGroupPanel.setBorder(new TitledBorder(new EtchedBorder(), "Sales Per Room Group"));
		salesPerRoomGroupPanel.setPreferredSize(new Dimension(150, 100));
		salesPerRoomGroupLabel = new JLabel(reservationDB.getsalespercategory());
		salesPerRoomGroupPanel.add(salesPerRoomGroupLabel);
		northPanel.add(salesPerRoomGroupPanel);

		topSellersPanel = new JPanel();
		topSellersPanel.setBackground(Color.YELLOW);
		topSellersPanel.setLayout(new GridLayout(1, 1));
		topSellersPanel.setBorder(new TitledBorder(new EtchedBorder(), "Top Sellers"));
		topSellersPanel.setPreferredSize(new Dimension(150, 100));
		topSellersLabel = new JLabel(reservationDB.getsalesperroom());

		topSellersPanel.add(topSellersLabel);
		northPanel.add(topSellersPanel);

		add(northPanel, BorderLayout.NORTH);

		//center
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.GREEN);
		centerPanel.setLayout(new GridLayout(1, 2));		
		//left
		reservationsPanel = new JPanel();
		reservationsPanel.setBackground(Color.GREEN);
		reservationsPanel.setLayout(new GridLayout(10, 2));
		reservationsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Customer Information"));


		labels = new JLabel[reservationLabels.length];
		textFields = new JTextField[reservationLabels.length];

		for (int i = 0; i < reservationLabels.length; i++)
		{
			labels[i] = new JLabel(reservationLabels[i]);
			textFields[i] = new JTextField(10);
			reservationsPanel.add(labels[i]);
			reservationsPanel.add(textFields[i]);
		}
		textFields[6].setText("MM/DD/YYYY");
		reservationsPanel.add(new JLabel("Available Rooms: "));
		getfreerooms = getfreeRooms();
		reservationsPanel.add(getfreerooms);

		reservationButton = new JButton("Reservation");
		clearButton = new JButton("Clear Reservation");
		reservationsPanel.add(reservationButton);
		reservationsPanel.add(clearButton);
		centerPanel.add(reservationsPanel);


		//Right
		eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(5, 1));
		eastPanel.setBorder(new TitledBorder(new EtchedBorder(), "Administrative Options"));

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

		availabilityPanel = new JPanel();
		availabilityPanel.setLayout(new GridLayout(3, 2));
		availabilityPanel.setBorder(new TitledBorder(new EtchedBorder(), "Set Availability"));

		JLabel roomnum = new JLabel("Room Number: ");

		roomNumberField = new JTextField(10);
		availabilityCombo = new JComboBox();
		availabilityCombo.addItem("Set");
		availabilityCombo.addItem("Vacant");
		availabilityCombo.addItem("Occupied");

		availabilityPanel.add(roomnum);
		availabilityPanel.add(roomNumberField);
		availabilityPanel.add(new JLabel("Set Availability:"));
		availabilityPanel.add(availabilityCombo);

		availiablebutton = new JButton("Set Availability");
		availabilityPanel.add(availiablebutton);
		eastPanel.add(availabilityPanel);


		setRatesPanel = new JPanel();
		setRatesPanel.setLayout(new GridLayout(3, 2));
		setRatesPanel.setBorder(new TitledBorder(new EtchedBorder(), "Set Room Rates"));

		setRoomTypeCombo1 = getcategories();


		newRateLabel = new JLabel("New Rate: ");
		newRateField = new JTextField(5);

		setRatesPanel.add(new JLabel("Room Category: "));
		setRatesPanel.add(setRoomTypeCombo1);
		setRatesPanel.add(newRateLabel);
		setRatesPanel.add(newRateField);

		setratebutton = new JButton("Set Room Rate");
		setRatesPanel.add(setratebutton);

		eastPanel.add(setRatesPanel);

		addRoomPanel = new JPanel();
		addRoomPanel.setLayout(new GridLayout(3, 2));
		addRoomPanel.setBorder(new TitledBorder(new EtchedBorder(), "Add Room"));
		roomNumberLabel = new JLabel("Room Pin: ");
		addRoomField = new JTextField(5);

		setRoomTypeCombo2 = getcategories();

		addroombutton = new JButton("Add Room");
		clearleft = new JButton("Clear Administrative");
		addRoomPanel.add(new JLabel("Room Category: "));
		addRoomPanel.add(setRoomTypeCombo2);
		addRoomPanel.add(roomNumberLabel);
		addRoomPanel.add(addRoomField);
		addRoomPanel.add(addroombutton);
		addRoomPanel.add(clearleft);


		eastPanel.add(addRoomPanel);
		centerPanel.add(eastPanel);
		add(centerPanel,BorderLayout.CENTER);

		CheckinListener ci = new CheckinListener();
		checkinbutton.addActionListener(ci);
		checkoutbutton.addActionListener(ci);

		availableListener al = new availableListener();
		availiablebutton.addActionListener(al);

		rateListener rl = new rateListener();
		setratebutton.addActionListener(rl);

		addroomListener addl = new addroomListener();
		addroombutton.addActionListener(addl);

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
	@SuppressWarnings("unchecked")
	private void updatefreerooms(){
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		RoomDB roomdb = new RoomDB();
		ListIterator<Room> iter = roomdb.getfreerooms().listIterator(0);  
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
	@SuppressWarnings("unchecked")
	private void updatecheckinrooms(){
		@SuppressWarnings("rawtypes")
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
	//update checkout rooms
	@SuppressWarnings("unchecked")
	private void updatecheckoutrooms(){
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		ReservationDB reservdb = new ReservationDB();
		ListIterator<Reservation> iter = reservdb.getuncheckreservations(1).listIterator();  
		model.addElement("-Reservation-");
		while (iter.hasNext()) {  
			model.addElement(((Reservation)iter.next()).getreservid());  
		}  
		checkoutcombobox.setModel(model);
	}
	//gets current categories
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getcategories(){
		RoomCategoryDB categorydb = new RoomCategoryDB();
		ArrayList<Room_Category> categories = categorydb.getcategories();
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("-Categories-");
		for (int i = 0; i < categories.size(); i++){
			comboBox.addItem(categories.get(i).getrcname());
		}
		return comboBox;
	}
	//check in and check out button listener
	private class CheckinListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == checkinbutton){
				if(reservincombobox.getSelectedIndex() != 0){
					ReservationDB rdb = new ReservationDB();
					rdb.updatereservation(Integer.parseInt(reservincombobox.getSelectedItem().toString()),1);
					updatecheckinrooms();
					updatecheckoutrooms();
					updatefreerooms();
				}
				else{
					JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Reservation ID not selected.", "Check In", JOptionPane.WARNING_MESSAGE);					}	
			}
			if (e.getSource() == checkoutbutton){
				if(checkoutcombobox.getSelectedIndex() != 0){
					ReservationDB rdb = new ReservationDB();
					Reservation rs = rdb.getreservation(Integer.parseInt(checkoutcombobox.getSelectedItem().toString()));
					rdb.updatereservation(Integer.parseInt(checkoutcombobox.getSelectedItem().toString()),0);
					updatecheckinrooms();
					updatecheckoutrooms();
					updatefreerooms();
					JOptionPane.showMessageDialog(UIAdministrativeFrame.this, rs.toString(), "Guest Bill", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Reservation ID not selected.", "Check out", JOptionPane.WARNING_MESSAGE);
				}	
			}	
		}
	}
	//set avaiable of room	
	private class availableListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!roomNumberField.getText().equals("") && availabilityCombo.getSelectedIndex()-1 != -1){			
				RoomDB roomrb = new RoomDB();
				roomrb.updateroom(Integer.parseInt(roomNumberField.getText()),availabilityCombo.getSelectedIndex()-1);
				JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Room status has been changed.", "Room Availability", JOptionPane.INFORMATION_MESSAGE);
				roomNumberField.setText("");
				availabilityCombo.setSelectedIndex(0);
				updatecheckinrooms();
				updatecheckoutrooms();
				updatefreerooms();
			}
			else{
				JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Not all sections have been selected.", "Set Room Availability", JOptionPane.WARNING_MESSAGE);
			}	
		}
	}
	//set rate	
	private class rateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!newRateField.getText().equals("") && setRoomTypeCombo1.getSelectedIndex() != 0){			
				RoomCategoryDB rcdb = new RoomCategoryDB();
				rcdb.updatecategory(setRoomTypeCombo1.getSelectedIndex(), Double.parseDouble(newRateField.getText()));
				JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Room rate has been changed.", "Room rate", JOptionPane.INFORMATION_MESSAGE);
				setRoomTypeCombo1.setSelectedIndex(0);
				newRateField.setText("");
			}
			else{
				JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Not all sections have been selected.", "Set Room Availability", JOptionPane.WARNING_MESSAGE);
			}	
		}
	}
	//Room added	
	private class addroomListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(setRoomTypeCombo2.getSelectedIndex() != 0 && Integer.parseInt(addRoomField.getText())>= 0){			
				RoomDB roomdb = new RoomDB();
				RoomCategoryDB rc = new RoomCategoryDB();
				Room_Category roomcat = rc.getcategory(setRoomTypeCombo2.getSelectedIndex());
				Room room = new Room(0,roomcat,0,Integer.parseInt(addRoomField.getText()));				
				roomdb.addroom(room);
				JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Room has been added.", "Add Room", JOptionPane.INFORMATION_MESSAGE);
				addRoomField.setText("");
				setRoomTypeCombo2.setSelectedIndex(0);
			}
			else{
				JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Not all sections have been selected.", "Add Room", JOptionPane.WARNING_MESSAGE);
			}	
		}
	}
	//reservation submitted	
	private class reservationListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == reservationButton){
				if(!textFields[0].getText().equals("") && !textFields[1].getText().equals("") && !textFields[2].getText().equals("") && !textFields[3].getText().equals("") && !textFields[4].getText().equals("") && !textFields[5].getText().equals("") && getfreerooms.getSelectedIndex() != 0){
					GuestDB guestdb = new GuestDB();
					Guest guest = new Guest(guestdb.getcurrentid(),textFields[0].getText(),textFields[1].getText(),textFields[2].getText(),textFields[3].getText(),textFields[4].getText(),textFields[5].getText());
					System.out.print("check: "+guestdb.checkguest(guest)+"\n");
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
					JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Reservation has been added.", "Add Reservation", JOptionPane.INFORMATION_MESSAGE);
					updatecheckinrooms();
					updatecheckoutrooms();
					updatefreerooms();
					for (int i = 0; i < reservationLabels.length; i++){
						if(i != 6){
							textFields[i].setText("");
						}
						else{
							textFields[i].setText("MM/DD/YYYY");
						}

					}
				}
				else{
					JOptionPane.showMessageDialog(UIAdministrativeFrame.this,"Not all sections have been filled.", "Add Reservation", JOptionPane.WARNING_MESSAGE);
				}
			}
			if (e.getSource() == clearButton){
				for (int i = 0; i < reservationLabels.length; i++){
					if(i != 6){
						textFields[i].setText("");
					}
					else{
						textFields[i].setText("MM/DD/YYYY");
					}	
				}
				getfreerooms.setSelectedIndex(0);		

			}	
		}
	}
	//clear reservation information	
	private class clearadminListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			reservincombobox.setSelectedIndex(0);
			checkoutcombobox.setSelectedIndex(0);
			roomNumberField.setText("");
			availabilityCombo.setSelectedIndex(0);
			setRoomTypeCombo1.setSelectedIndex(0);
			newRateField.setText("");
			addRoomField.setText("");
			setRoomTypeCombo2.setSelectedIndex(0);
		}

	}					

}
