import java.util.*;
import java.sql.*;

public class RoomCategoryDB {
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
	//get all categories
	public ArrayList<Room_Category> getcategories() {
		String sql = "SELECT RC_ID,RC_NAME,RC_RATE "
				+ "FROM ROOM_CATEGORY ORDER BY RC_ID ASC";        
		ArrayList<Room_Category> categories = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				int id = rs.getInt("RC_ID");
				String name = rs.getString("RC_NAME");
				double rate = rs.getDouble("RC_RATE");

				Room_Category category = new Room_Category(id,name, rate);
				categories.add(category);
			}
			connection.close();
			return categories;
		}
		catch(SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	//get category by category ID
	public Room_Category getcategory(int rcid) {
		String sql = "SELECT RC_ID,RC_NAME, RC_RATE FROM ROOM_CATEGORY WHERE RC_ID = "+rcid;        
		Room_Category category = new Room_Category();
		try {  
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);         
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("RC_ID");
				String name = rs.getString("RC_NAME");
				double rate = rs.getDouble("RC_RATE");

				category = new Room_Category(id,name, rate);

				rs.close();
				connection.close();
				return category;
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

	//add new category
	public boolean addcategory(Room_Category newcategory) {
		String sql ="INSERT INTO ROOM_CATEGORY (RC_NAME,RC_RATE) " +
				"VALUES (?,?)";
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {	
			ps.setString(1, newcategory.getrcname());
			ps.setDouble(2, newcategory.getrcrate());
			ps.executeUpdate();
			connection.close();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	//update current category with new rate
	public boolean updatecategory(int id, double newrate){
		String sql = "UPDATE ROOM_CATEGORY SET " +
				"RC_RATE = " + newrate +
				" WHERE RC_ID = " + id;
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
	//delete category
	public boolean deletecategory(Room_Category category) {
		String sql = "DELETE FROM ROOM_CATEGORY " +
				"WHERE RC_NAME = " + category.getrcname();        
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