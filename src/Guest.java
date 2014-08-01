public class Guest{
	private int g_id;
	private String g_first;
	private String g_last;
	private String g_address;
	private String g_city;
	private String g_state;
	private String g_zip;

	Guest(){
		this.g_id = 0;
		this.g_first = "";
		this.g_last = "";
		this.g_address = "";
		this.g_city = "";
		this.g_state = "";
		this.g_zip = "";
	}
	Guest(int newid, String newfirst, String newlast, String newaddress, String newcity, String newstate, String newzip){
		this.g_id = newid;
		this.g_first = newfirst;
		this.g_last = newlast;
		this.g_address = newaddress;
		this.g_city = newcity;
		this.g_state = newstate;
		this.g_zip = newzip;
	}
	public int getid(){return this.g_id;}
	public String getfirst(){return this.g_first;}
	public String getlast(){return this.g_last;}
	public String getaddress(){return this.g_address;}
	public String getcity(){return this.g_city;}
	public String getstate(){return this.g_state;}
	public String getzip(){return this.g_zip;}
}