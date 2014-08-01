import java.util.*;
import java.sql.*;

public class RoomDB {
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
	//get all rooms
	public ArrayList<Room> getrooms() {
		String sql = "SELECT R_ID,RC_ID,R_AVAILABLE,R_PIN "
				+ "FROM ROOM ORDER BY R_ID ASC";        
		ArrayList<Room> rooms = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				int rid = rs.getInt("R_ID");
				int rcid = rs.getInt("RC_ID");
				int ravailable = rs.getInt("R_AVAILABLE");
				int rpin = rs.getInt("R_PIN");
				RoomCategoryDB categorydb = new RoomCategoryDB();
				Room_Category category = categorydb.getcategory(rcid);	
				Room room = new Room(rid,category,ravailable,rpin);
				rooms.add(room);
			}
			connection.close();
			return rooms;
		}
		catch(SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	//get all rooms that are currently not occupied.
	public ArrayList<Room> getfreerooms() {
		String sql = "SELECT R_ID,RC_ID,R_AVAILABLE,R_PIN "
				+ "FROM ROOM WHERE R_AVAILABLE = 0 ORDER BY R_ID ASC";        
		ArrayList<Room> rooms = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				int rid = rs.getInt("R_ID");
				int rcid = rs.getInt("RC_ID");
				int ravailable = rs.getInt("R_AVAILABLE");
				int rpin = rs.getInt("R_PIN");
				RoomCategoryDB categorydb = new RoomCategoryDB();
				Room_Category category = categorydb.getcategory(rcid);	
				Room room = new Room(rid,category,ravailable,rpin);
				rooms.add(room);
			}
			connection.close();
			return rooms;
		}
		catch(SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	//get room by room ID
	public Room getroom(int roomid) {
		String sql = "SELECT R_ID,RC_ID,R_AVAILABLE,R_PIN "
				+ "FROM ROOM WHERE R_ID = "+roomid;        

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				int rid = rs.getInt("R_ID");
				int rcid = rs.getInt("RC_ID");
				int ravailable = rs.getInt("R_AVAILABLE");
				int rpin = rs.getInt("R_PIN");
				RoomCategoryDB categorydb = new RoomCategoryDB();
				Room_Category category = categorydb.getcategory(rcid);

				Room room = new Room(rid,category,ravailable,rpin);				
				rs.close();
				connection.close();
				return room;
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
	//add new room
	public boolean addroom(Room room) {
		String sql ="INSERT INTO ROOM (R_ID,RC_ID,R_AVAILABLE,R_PIN) " +
				"VALUES (room_seq.nextval,?,?,?)";
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {	
			ps.setInt(1, room.getrc().getrcid());
			ps.setInt(2, room.getavailable());
			if(room.getpin() > 0 ){
				ps.setInt(3,room.getpin());
			}
			else{
				ps.setNull(3, java.sql.Types.INTEGER);
			}	
			ps.executeUpdate();
			connection.close();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	//update room status
	public boolean updateroom(int id, int available){
		String sql = "UPDATE ROOM SET " +
				"R_AVAILABLE = " + available +
				" WHERE R_ID = " + id;
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
}