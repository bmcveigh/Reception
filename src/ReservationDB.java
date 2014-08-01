import java.util.*;
import java.sql.*;

public class ReservationDB {
	private Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/reception?" +
					"user='root'");
		}
		catch(Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return null;
		}
	}
	//get all reservations
	public ArrayList<Reservation> getreservations() {
		String sql = "SELECT RESERV_ID,G_ID,R_ID,DATE_FORMAT(RESERV_DATE,'DD/MM/YY'),RESERV_STATUS,RESERV_NIGHT "
				+ "FROM RESERVATION ORDER BY RESERV_ID ASC";        
		ArrayList<Reservation> reservations = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				int reservid = rs.getInt("RESERV_ID");
				int guestid = rs.getInt("G_ID");
				int roomid = rs.getInt("R_ID");
				String roomdate = rs.getString("DATE_FORMAT(RESERV_DATE,'DD/MM/YY')");
				int status = rs.getInt("RESERV_STATUS");
				int nights = rs.getInt("RESERV_NIGHT");

				RoomDB roomdb = new RoomDB();
				GuestDB guestdb = new GuestDB();

				Guest guest = guestdb.getguest(guestid);
				Room room = roomdb.getroom(roomid);

				Reservation reservation = new Reservation(reservid,guest,room,roomdate,status,nights);
				reservations.add(reservation);
			}
			connection.close();
			return reservations;
		}
		catch(SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	//get reservations by status, check in or check out 
	public ArrayList<Reservation> getuncheckreservations(int check) {
		String sql = "SELECT RESERV_ID,G_ID,R_ID,DATE_FORMAT(RESERV_DATE,'DD/MM/YY'),RESERV_STATUS,RESERV_NIGHT "
				+ "FROM RESERVATION WHERE RESERV_STATUS = "+check+" ORDER BY RESERV_ID ASC";        
		ArrayList<Reservation> reservations = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				int reservid = rs.getInt("RESERV_ID");
				int guestid = rs.getInt("G_ID");
				int roomid = rs.getInt("R_ID");
				String roomdate = rs.getString("DATE_FORMAT(RESERV_DATE,'DD/MM/YY')");
				int status = rs.getInt("RESERV_STATUS");
				int nights = rs.getInt("RESERV_NIGHT");
				RoomDB roomdb = new RoomDB();
				GuestDB guestdb = new GuestDB();

				Guest guest = guestdb.getguest(guestid);
				Room room = roomdb.getroom(roomid);

				Reservation reservation = new Reservation(reservid,guest,room,roomdate,status,nights);
				reservations.add(reservation);
			}
			connection.close();
			return reservations;    
		}

		catch(SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	//get reservation by ID 
	public Reservation getreservation(int rid) {
		String sql = "SELECT RESERV_ID,G_ID,R_ID,DATE_FORMAT(RESERV_DATE,'DD/MM/YY'),RESERV_STATUS,RESERV_NIGHT "
				+ "FROM RESERVATION WHERE RESERV_ID = "+rid;        

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			if(rs.next()) {
				int reservid = rs.getInt("RESERV_ID");
				int guestid = rs.getInt("G_ID");
				int roomid = rs.getInt("R_ID");
				String roomdate = rs.getString("DATE_FORMAT(RESERV_DATE,'DD/MM/YY')");
				int status = rs.getInt("RESERV_STATUS");
				int nights = rs.getInt("RESERV_NIGHT");
				RoomDB roomdb = new RoomDB();
				GuestDB guestdb = new GuestDB();

				Guest guest = guestdb.getguest(guestid);
				Room room = roomdb.getroom(roomid);

				Reservation reservation = new Reservation(reservid,guest,room,roomdate,status,nights);
				rs.close();
				connection.close();
				return reservation;
			}
			else {
				rs.close();
				connection.close();
				return null;
			}   
		}

		catch(SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	//add new reservation
	public boolean addreservation(Reservation reservation) {
		String sql ="INSERT INTO RESERVATION (RESERV_ID,G_ID,R_ID,RESERV_DATE,RESERV_STATUS,RESERV_NIGHT) " +
				"VALUES (?,?,?,STR_TO_DATE(?,'%m/%d/%Y'),?,?)";
		try (Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)) {	
			ps.setInt(1, reservation.getreservid());
			ps.setInt(2, reservation.getguest().getid());
			ps.setInt(3, reservation.getroom().getrid());
			ps.setString(4,reservation.getreservdate());
			ps.setInt(5, reservation.getstatus());
			ps.setInt(6,reservation.getnumnights());	
			ps.executeUpdate();
			connection.close();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	//update reservation, check in check out.
	public boolean updatereservation(int reserv_id,int status){
		String sql = "UPDATE RESERVATION SET " +
				"RESERV_STATUS = " + status +
				" WHERE RESERV_ID = " + reserv_id;
		int update = 1;
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			if (ps.executeUpdate() == update){
				connection.close();
				return true;
			}
			else{
				connection.close();
				return false;
			}
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	//get total sales
	public String getsalestotal(){
		String sql = "SELECT SUM(RC_RATE * RESERVATION.RESERV_NIGHT) AS TOTAL "
				+"FROM ROOM_CATEGORY,ROOM,RESERVATION "
				+"WHERE ROOM_CATEGORY.RC_ID = ROOM.RC_ID "
				+"AND RESERVATION.R_ID = ROOM.R_ID";
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			if(rs.next()) {
				int total = rs.getInt("TOTAL");
				String str = "Total sales: $"+total;
				return str;
			}
			else{
				connection.close();
				return "";		
			}
		}
		catch(SQLException e) {
			System.err.println(e);
			return "";
		}
	}
	//get weekly sales	
	public String getweeklysales(){
		String sql = "SELECT SUM(RC_RATE * RESERVATION.RESERV_NIGHT) AS TOTAL "
				+"FROM ROOM_CATEGORY,ROOM,RESERVATION "
				+"WHERE ROOM_CATEGORY.RC_ID = ROOM.RC_ID "
				+"AND RESERVATION.R_ID = ROOM.R_ID "
				+"AND RESERV_DATE > SYSDATE() - 7";
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			if(rs.next()) {
				int weekly = rs.getInt("TOTAL");
				String str = "This Week sales: $"+weekly;
				return str;
			}
			else{
				connection.close();
				return "";		
			}
		}
		catch(SQLException e) {
			System.err.println(e);
			return "";
		}
	} 
	//get sales per category	
	public String getsalespercategory(){
		String sql = "SELECT RC_NAME, SUM(RC_RATE * RESERVATION.RESERV_NIGHT) AS TOTAL "
				+"FROM ROOM_CATEGORY,ROOM,RESERVATION "
				+"WHERE ROOM_CATEGORY.RC_ID = ROOM.RC_ID "
				+"AND RESERVATION.R_ID = ROOM.R_ID "
				+"GROUP BY RC_NAME";
		String str = "<html><body>";					
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				String rcname = rs.getString("RC_NAME");
				double total = rs.getDouble("TOTAL");
				str += rcname.trim()+":	$"+total+"<br />";
			}	
			return str+"</body></html>";	
		}
		catch(SQLException e) {
			System.err.println(e);
			return "";
		}
	}
	//get sales per room	
	public String getsalesperroom(){
		String sql = "SELECT R_ID, COUNT(R_ID) AS NUM "
				+"FROM RESERVATION "
				+"GROUP BY R_ID "
				+"ORDER BY NUM DESC";
		String str = "<html><body>";
		int count = 0;					
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next() && count < 3) {
				String rid = rs.getString("R_ID");
				int num = rs.getInt("NUM");
				str += "Room ID: "+rid.trim()+":	Number of sales:"+num+"<br />";
				count++;
			}	
			return str+"</body></html>";	
		}
		catch(SQLException e) {
			System.err.println(e);
			return "";
		}
	}		
	//delete reservation		  
	public boolean deletereservation(Reservation reservation) {
		String sql = "DELETE FROM RESERVATION " +
				"WHERE RESERV_ID = " + reservation.getreservid();        
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.executeUpdate();
			connection.close();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
}