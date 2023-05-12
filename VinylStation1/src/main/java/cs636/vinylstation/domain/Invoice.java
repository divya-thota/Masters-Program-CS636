package cs636.vinylstation.domain;

import java.util.Date;

public class Invoice {

	private int invoice_id;
	private int customer_id;
	private Date invoice_date;
	private String status;	
	private double total_price;	
	private int employee_id;	
	
	public Invoice( int invoice_id, int customer_id, Date invoice_date, String status, double total_price, int employee_id) {
		this.invoice_id = invoice_id;
		this.customer_id = customer_id;
		this.invoice_date = invoice_date;
		this.status = status;
		this.total_price = total_price;
		this.employee_id = employee_id;
	}
	
	public int get_invoice_id() {
		return invoice_id;
	}
	public int get_customer_id() {
		return customer_id;
	}
	public Date get_invoice_date() {
		return invoice_date;
	}
	public String get_status() {
		return status;
	}
	public double get_total_price() {
		return total_price;
	}
	public int get_employee_id() {
		return employee_id;
	}
	
}