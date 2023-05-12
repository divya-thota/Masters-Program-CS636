package cs636.vinylstation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.Track;
import cs636.vinylstation.domain.loginObject;

public class invoiceDAO {
	
	private Connection conn;

	public invoiceDAO(Connection a) {

		conn = a;

	}
	
	public void createInvoice( int customer_id, LocalDate invoice_date, int status_id, double total_price) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			stmt.executeUpdate( "INSERT INTO invoice VALUES (NULL, " 
												+ customer_id + ", TO_DATE('"
												+invoice_date+"', 'yyyy-mm-dd'), "
												+status_id+", "
												+total_price+ ", NULL)");

		} finally {
			stmt.close();
		}
	}
	
	public int getLatestInvoice( int customer_id) throws SQLException {
		int invoice_id = 0;
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from invoice where status_id = 1 and customer_id = "+customer_id;
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				invoice_id = rset.getInt("invoice_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invoice_id;
	}
	
	public Invoice getInvoice( int invoice_id) throws SQLException {
		Invoice objInvoice = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from invoice i, status s where s.status_id=i.status_id and i.invoice_id="+invoice_id;
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				objInvoice = new Invoice(rset.getInt("invoice_id"), rset.getInt("customer_id"), rset.getDate("invoice_date"),
						rset.getString("status_name"), rset.getDouble("total_price"), rset.getInt("employee_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objInvoice;
	}
	
	public void updateInvoice(int invoice_id) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			String UpdateString = "UPDATE invoice SET "
					+ "total_price= (SELECT sum(price) "
						+ "FROM record r "
						+ "WHERE r.invoice_id = "+invoice_id+") , "
					+ " status_id = 2 WHERE invoice_id ="+invoice_id;
			stmt.executeUpdate(UpdateString);

		} finally {
			stmt.close();
		}
	}
	
	public void updateInvoiceEmployee(int invoice_id, int employee_id) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			String UpdateString = "UPDATE invoice SET employee_id="+employee_id+ ", status_id=3 WHERE invoice_id ="+invoice_id;
			stmt.executeUpdate(UpdateString);

		} finally {
			stmt.close();
		}
	}
	
	public void updateInvoiceStatus(int invoice_id, int status_id) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			String UpdateString = "UPDATE invoice SET status_id="+status_id+ " WHERE invoice_id ="+invoice_id;
			stmt.executeUpdate(UpdateString);

		} finally {
			stmt.close();
		}
	}
}