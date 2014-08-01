public class Room_Category{
	private int rc_id;
	private String rc_name;
	private double rc_rate;

	Room_Category(){
		this.rc_id = 0;
		this.rc_name = "";
		this.rc_rate = 0;
	}
	
	Room_Category(int newrcid,String newrcname, double newrcrate){
		this.rc_id = newrcid;
		this.rc_name = newrcname;
		this.rc_rate = newrcrate;
	}	
	
	public void setrcname(String newname){
		this.rc_name = newname;
	}	
	
	public void setrcrate(double newrate){
		this.rc_rate = newrate;
	}
	
	public int getrcid(){return this.rc_id;}
	
	public String getrcname(){return this.rc_name;}
	
	public double getrcrate(){return this.rc_rate;}		
}