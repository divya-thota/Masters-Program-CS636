package cs636.vinylstation.domain;

import java.util.Date;

public class Customer {

	private int customer_id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;	
	private String address;	
	private String city;	
	private String state;
	private String postal_code;	
	private String country;	
	private String credit_card_type;	
	private long credit_card_no;	
	private Date credit_card_expiration_date;
	
	
	public Customer( int customer_id, String first_name, String last_name, String email, String password, String address, String city, String state, String postal_code, String country, String credit_card_type, long credit_card_no, Date credit_card_expiration_date) {
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name= last_name;
		this.email = email;
		this.password= password;
		this.address = address;
		this.city= city;
		this.state= state;
		this.postal_code= postal_code;
		this.country = country;
		this.credit_card_type=credit_card_type;
		this.credit_card_no=credit_card_no;
		this.credit_card_expiration_date=credit_card_expiration_date;
	}
	
	public int get_customer_id() {
		return customer_id;
	}
	public String get_first_name() {
		return first_name;
	}
	public String get_last_name() {
		return last_name;
	}
	public String get_email() {
		return email;
	}
	public String get_password() {
		return password;
	}
	public String get_address() {
		return address;
	}
	public String get_city() {
		return city;
	}
	public String get_state() {
		return state;
	}
	public String get_postal_code() {
		return postal_code;
	}
	public String get_country() {
		return country;
	}
	public String get_credit_card_type() {
		return credit_card_type;
	}
	public long get_credit_card_no() {
		return credit_card_no;
	}
	public Date get_credit_card_expiration_date() {
		return credit_card_expiration_date;
	}
	
}
