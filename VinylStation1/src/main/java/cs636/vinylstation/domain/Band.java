package cs636.vinylstation.domain;


public class Band {

	private int band_id;
	private String name;
	private String password;
	private String band_members;
	private String description;	
	
	
	public Band( int band_id, String name, String password, String band_members, String description) {
		this.band_id = band_id;
		this.name = name;
		this.password= password;
		this.band_members= band_members;
		this.description = description;
	}
	
	public int get_band_id() {
		return band_id;
	}
	public String get_name() {
		return name;
	}
	public String get_password() {
		return password;
	}
	public String get_band_members() {
		return band_members;
	}
	public String get_description() {
		return description;
	}
}
