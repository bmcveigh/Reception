public class Room{
	private int r_id;
	private Room_Category rc;
	private int r_available;
	private int r_pin;

	Room(){
		this.r_id = 0;
		this.rc = new Room_Category();
		this.r_available = 0;
		this.r_pin = 0;
	}
	Room(int newrid, Room_Category newrc, int newavailable, int newpin){
		this.r_id = newrid;
		this.rc = newrc;
		this.r_available = newavailable;
		this.r_pin = newpin;
	}

	public void setavailable(int newavailable){
		this.r_available = newavailable;
	}	

	public int getrid(){return this.r_id;}
	public Room_Category getrc(){return this.rc;}
	public int getavailable(){return this.r_available;}
	public int getpin(){return this.r_pin;}		
}