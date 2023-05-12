package cs636.vinylstation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.RecordType;
import cs636.vinylstation.domain.Track;
import cs636.vinylstation.domain.Customer;

public class customerDAO {

	private Connection conn;

	public customerDAO(Connection a) {

		conn = a;

	}

	public List<Invoice> getcustomerDashboardInvoices(int customer_id) throws SQLException {
		List<Invoice> customerInvoices = new ArrayList<Invoice>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT i.invoice_id, i.customer_id, i.invoice_date, s.status_name, i.total_price, i.employee_id "
					+ "FROM invoice i, status s "
					+ "WHERE s.status_id = i.status_id "
					+ "AND i.customer_id = "+customer_id+" "
							+ "ORDER BY i.invoice_date "
							+ "DESC FETCH NEXT 5 ROWS ONLY";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				customerInvoices.add(new Invoice(rset.getInt("invoice_id"), rset.getInt("customer_id"), rset.getDate("invoice_date"),
						rset.getString("status_name"), rset.getDouble("total_price"), rset.getInt("employee_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerInvoices;
	}
	
	public List<Invoice> getcustomerInvoices(int customer_id) throws SQLException {
		List<Invoice> customerInvoices = new ArrayList<Invoice>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from invoice i, status s where s.status_id = i.status_id and customer_id = "+customer_id+" order by i.invoice_date DESC";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				customerInvoices.add(new Invoice(rset.getInt("invoice_id"), rset.getInt("customer_id"), rset.getDate("invoice_date"),
						rset.getString("status_name"), rset.getDouble("total_price"), rset.getInt("employee_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerInvoices;
	}
	
	public List<RecordType> getAllRecordTypes() throws SQLException {
		List<RecordType> allrecordtypes = new ArrayList<RecordType>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from record_type";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				allrecordtypes.add(new RecordType(rset.getInt("record_type_id"), 
						rset.getString("record_type_name"), rset.getInt("duration")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allrecordtypes;
	}
	
	public Customer getCustomer(int customer_id) throws SQLException {
		Customer objCustomer = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from customer where customer_id = "+customer_id;
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				objCustomer = new Customer(rset.getInt("customer_id"), rset.getString("first_name"), 
						rset.getString("last_name"), rset.getString("email"), rset.getString("password"), 
						rset.getString("address"), rset.getString("city"), rset.getString("state"), 
						rset.getString("postal_code"), rset.getString("country"), rset.getString("credit_card_type"), 
						rset.getLong("credit_card_no"), rset.getDate("credit_card_expiration_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objCustomer;
	}
	
	public void createCustomer(String first_name, String last_name, String email, String password, String address,
			String city, String state, String postal_code, String country, String credit_card_type, long credit_card_no,
			String credit_card_expiration_date) throws SQLException {
		Statement stmt = conn.createStatement();
		try { 
			stmt.executeUpdate( "INSERT INTO customer VALUES ( NULL, '" 
												+ first_name + "', '"
												+last_name+ "', '"
												+email+ "', '"
												+password+ "', '"
												+address+ "', '"
												+city+ "', '"
												+state+ "', '"
												+postal_code+ "', '"
												+country+ "', '"
												+credit_card_type+ "', "
												+credit_card_no+ ", TO_DATE('"
												+credit_card_expiration_date+"','yyyy-mm-dd'))");

		} finally {
			stmt.close();
		}
	}
}