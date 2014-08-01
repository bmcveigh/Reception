public class Reservation{
	private int reserv_id;
	private Guest guest;
	private Room room;
	private String reserv_date;
	private int status;
	private int numnights;

	Reservation(){
		this.reserv_id = 0;
		this.guest = new Guest();
		this.room = new Room();
		this.reserv_date = "01/01/13";
		this.status = 0;
		this.numnights = 0;
	}
	
	Reservation(int newreservid, Guest newguest, Room newroom,String newreservdate, int newstatus, int newnumnights){
		this.reserv_id = newreservid;
		this.guest = newguest;
		this.room = newroom;
		this.reserv_date = newreservdate;
		this.status = newstatus;
		this.numnights = newnumnights;
	}
	
	public void setstatus(int newstatus){
		this.status = newstatus;
	}
	
	public int getreservid(){return this.reserv_id;}
	public Guest getguest(){return this.guest;}
	public Room getroom(){return this.room;}
	public String getreservdate(){return this.reserv_date;}
	public int getstatus(){return this.status;}
	public int getnumnights(){return this.numnights;}

	public String toString(){
		String str = "Reservation ID: "+getreservid()+"\n"
				+"Last Name: "+guest.getlast()+"\n"
				+"Room ID: "+room.getrid()+"\n"
				+"Room Category: "+room.getrc().getrcname()+"\n"
				+"Room Rate: $"+room.getrc().getrcrate()+"\n"
				+"Number of Nights: "+getnumnights()+"\n"
				+"Total: $"+room.getrc().getrcrate()*getnumnights();
		return str;
	}		
}