package cs636.vinylstation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.Track;
import cs636.vinylstation.service.ServiceException;
import cs636.vinylstation.domain.RecordTrack;
import cs636.vinylstation.domain.Employee;

public class employeeDAO {
	
	private Connection conn;

	public employeeDAO(Connection a) {

		conn = a;

	}
	
	public List<Invoice> getPendingInvoices() throws SQLException {
		List<Invoice> pendingInvoices = new ArrayList<Invoice>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select i.invoice_id, i.customer_id, i.invoice_date, s.status_name, i.total_price, i.employee_id from invoice i, status s where s.status_id = i.status_id and i.status_id = 2 order by i.invoice_date DESC";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				pendingInvoices.add(new Invoice(rset.getInt("invoice_id"), rset.getInt("customer_id"), rset.getDate("invoice_date"),
						rset.getString("status_name"), rset.getDouble("total_price"), rset.getInt("employee_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pendingInvoices;
	}
	
	public List<Invoice> getemployeeInvoices(int employee_id) throws SQLException {
		List<Invoice> employeeInvoices = new ArrayList<Invoice>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from invoice i, status s where s.status_id = i.status_id and employee_id = "+employee_id+" order by i.invoice_date DESC";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				employeeInvoices.add(new Invoice(rset.getInt("invoice_id"), rset.getInt("customer_id"), rset.getDate("invoice_date"),
						rset.getString("status_name"), rset.getDouble("total_price"), rset.getInt("employee_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeeInvoices;
	}
	
	public List<Invoice> getInProgressInvoices(int employee_id) throws SQLException {
		List<Invoice> employeeInvoices = new ArrayList<Invoice>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from invoice i, status s where s.status_id = i.status_id and i.employee_id = "+employee_id+" and i.status_id=3 order by i.invoice_date DESC";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				employeeInvoices.add(new Invoice(rset.getInt("invoice_id"), rset.getInt("customer_id"), rset.getDate("invoice_date"),
						rset.getString("status_name"), rset.getDouble("total_price"), rset.getInt("employee_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeeInvoices;
	}
	
	public List<Invoice> getOutForDeliveryInvoices(int employee_id) throws SQLException {
		List<Invoice> employeeInvoices = new ArrayList<Invoice>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from invoice i, status s where s.status_id = i.status_id and i.employee_id = "+employee_id+" and i.status_id=4 order by i.invoice_date DESC";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				employeeInvoices.add(new Invoice(rset.getInt("invoice_id"), rset.getInt("customer_id"), rset.getDate("invoice_date"),
						rset.getString("status_name"), rset.getDouble("total_price"), rset.getInt("employee_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeeInvoices;
	}
	
	public List<Invoice> getDeliveredInvoices(int employee_id) throws SQLException {
		List<Invoice> employeeInvoices = new ArrayList<Invoice>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from invoice i, status s where s.status_id = i.status_id and i.employee_id = "+employee_id+" and i.status_id=5 order by i.invoice_date DESC";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				employeeInvoices.add(new Invoice(rset.getInt("invoice_id"), rset.getInt("customer_id"), rset.getDate("invoice_date"),
						rset.getString("status_name"), rset.getDouble("total_price"), rset.getInt("employee_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeeInvoices;
	}
	
	public Employee getEmployee(int employee_id) throws SQLException {
		Employee objEmployee = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from employee where employee_id = "+employee_id;
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				objEmployee = new Employee(rset.getInt("employee_id"), rset.getString("first_name"), 
						rset.getString("last_name"), rset.getString("email"), rset.getString("password"), 
						rset.getString("address"), rset.getString("city"), rset.getString("state"), 
						rset.getString("postal_code"), rset.getString("country"), rset.getLong("phone_no"),
						rset.getDate("date_of_birth"), rset.getString("role"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objEmployee;
	}
	
	public HashMap<String, Integer> ViewMonthlyProgress(int employee_id) throws SQLException {

		HashMap<String, Integer> monthvsPerformance = new HashMap<String, Integer>();
		try {
			HashMap<Integer,String> monthmap = new HashMap();
			monthmap.put(1, "January");
			monthmap.put(2, "February");
			monthmap.put(3, "March");
			monthmap.put(4, "April");
			monthmap.put(5, "May");
			monthmap.put(6, "June");
			monthmap.put(7, "July");
			monthmap.put(8, "August");
			monthmap.put(9, "September");
			monthmap.put(10, "October");
			monthmap.put(11, "November");
			monthmap.put(12, "December");
			Statement stmt = conn.createStatement();
			String query = "SELECT  EXTRACT(MONTH FROM invoice_date) as month, count(invoice_id) as performance "
					+ "FROM invoice WHERE status_id = 5 and employee_id = "+employee_id+" GROUP BY EXTRACT(MONTH FROM invoice_date)";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				monthvsPerformance.put(monthmap.get(rset.getInt("month")), rset.getInt("performance"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return monthvsPerformance;
	}
	
	public HashMap<String, Integer> getTeamPerformance() throws SQLException {

		HashMap<String, Integer> EmployeenamevsPerformance = new HashMap<String, Integer>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT e.first_name, e.last_name, count(i.invoice_id) as performance "
					+ "FROM invoice i, employee e "
					+ "WHERE i.status_id = 5 and e.employee_id = i.employee_id "
					+ "GROUP BY i.employee_id, e.first_name, e.last_name";
			ResultSet rset = stmt.executeQuery(query);
			while (rset.next()) {
				EmployeenamevsPerformance.put(rset.getString("first_name")+" "+rset.getString("last_name"), rset.getInt("performance"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EmployeenamevsPerformance;
	}
	
	public HashMap<Integer, List<RecordTrack>> getOrderTracks(int invoice_id) throws SQLException {

		HashMap<Integer, List<RecordTrack>> orderTracks = new HashMap<Integer, List<RecordTrack>>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT r.record_id,rt.record_track_id, rt.track_id, t.track_name, rt.added "
					+ "FROM record r, record_track rt, track t "
					+ "WHERE r.record_id = rt.record_id AND t.track_id = rt.track_id and r.invoice_id ="+invoice_id;
			ResultSet rset = stmt.executeQuery(query);
			while (rset.next()) {
				if (orderTracks.containsKey(rset.getInt("record_id"))) {
					orderTracks.get(rset.getInt("record_id")).add(new RecordTrack(rset.getInt("record_track_id"),rset.getInt("track_id"), rset.getString("track_name"), rset.getInt("record_id"), rset.getInt("added")));
				}
				else {
					RecordTrack objRecordTrack = new RecordTrack(rset.getInt("record_track_id"), rset.getInt("track_id"), rset.getString("track_name"), rset.getInt("record_id"), rset.getInt("added"));
					orderTracks.put(rset.getInt("record_id"), new ArrayList<RecordTrack> (Arrays.asList(objRecordTrack)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderTracks;
	}
	
	public List<Integer> getInvoiceRecordTracks(int invoice_id) throws SQLException {
    	List<Integer> allRecordTracks = new ArrayList<Integer>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT rt.record_track_id "
					+ "FROM record_track rt, record r, invoice i "
					+ "WHERE rt.record_id=r.record_id "
						+ "AND r.invoice_id=i.invoice_id "
						+ "AND i.invoice_id="+invoice_id;
			ResultSet rset = stmt.executeQuery(query);
			while (rset.next()) {
				allRecordTracks.add(rset.getInt("record_track_id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return allRecordTracks;
		
    }
}