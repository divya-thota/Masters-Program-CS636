package cs636.vinylstation.domain;

import java.util.Date;

public class Employee {

	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;	
	private String address;	
	private String city;	
	private String state;
	private String postal_code;	
	private String country;	
	private long phone_no;
	private Date date_of_birth;
	private String role;
	
	
	public Employee( int employee_id, String first_name, String last_name, String email, String password, String address, String city, String state, String postal_code, String country, long phone_no, Date date_of_birth, String role) {
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name= last_name;
		this.email = email;
		this.password= password;
		this.address = address;
		this.city= city;
		this.state= state;
		this.postal_code= postal_code;
		this.country = country;
		this.phone_no=phone_no;
		this.date_of_birth=date_of_birth;
		this.role=role;
	}
	
	public int get_employee_id() {
		return employee_id;
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
	public long get_phone_no() {
		return phone_no;
	}
	public Date get_date_of_birth() {
		return date_of_birth;
	}
	public String get_role() {
		return role;
	}
	
	
}
