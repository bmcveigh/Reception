import java.sql.*;
public class UserDB{
	
	private boolean isAdmin;
	
	public UserDB() {
		isAdmin = false;
	}
	
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
	public boolean checkUser(String user, String pw) {
		String sql = "SELECT A_NAME, IS_ADMIN FROM USER WHERE A_NAME = '"+user+"' AND A_PASS = '"+pw+"'";
		boolean found = false;
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) { 
				int a = rs.getInt("IS_ADMIN");
				if (a == 1) {
					this.isAdmin = true;
				}
				found = true;
				break;
			}
			connection.close();
			return found;
		}
		catch(SQLException e) {
			System.err.println(e);
			return found;
		}
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	

}