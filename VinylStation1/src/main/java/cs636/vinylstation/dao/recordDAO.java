package cs636.vinylstation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.Track;
import cs636.vinylstation.domain.loginObject;

public class recordDAO {
	
	private Connection conn;

	public recordDAO(Connection a) {

		conn = a;

	}
	
	public void createRecord( double duration, double price, int record_type_id, int invoice_id) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			stmt.executeUpdate( "INSERT INTO record VALUES (NULL, " 
												+ duration + ", "
												+price+", "
												+record_type_id+", "
												+invoice_id+",6)");

		} finally {
			stmt.close();
		}
	}
	
	public int getLatestRecord( int invoice_id) throws SQLException {
		int record_id = 0;
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from record where status_id = 6 and invoice_id = "+invoice_id;
			ResultSet rset = stmt.executeQuery(query);
			while (rset.next()) {
				record_id = rset.getInt("record_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return record_id;
	}
	
	public void updateRecord(int record_id) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			String UpdateString = "UPDATE record SET "
					+ "price= (SELECT sum(price) "
						+ "FROM track t, record_track rt "
						+ "WHERE t.track_id = rt.track_id and rt.record_id= "+record_id+"), "
					+ "duration = (SELECT sum(duration) "
						+ "FROM track t, record_track rt "
						+ "WHERE t.track_id = rt.track_id and "
						+ "rt.record_id= "+record_id+"), status_id = 7 WHERE record_id ="+record_id;
			stmt.executeUpdate( UpdateString);
		} finally {
			stmt.close();
		}
	}
}