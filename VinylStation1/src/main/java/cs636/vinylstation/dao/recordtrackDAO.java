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

import cs636.vinylstation.domain.Track;
import cs636.vinylstation.domain.loginObject;

public class recordtrackDAO {
	
	private Connection conn;

	public recordtrackDAO(Connection a) {

		conn = a;

	}
	
	public void createRecordTrack(int record_id, int track_id, int added) throws SQLException {
		Statement stmt = conn.createStatement();
		try { 
			stmt.executeUpdate( "INSERT INTO record_track VALUES (NULL, " 
												+ record_id + ", "
												+track_id+", "
												+added+")");

		} finally {
			stmt.close();
		}
	}
	
	public void updateRecordTrack(int record_track_id, int added) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			stmt.executeUpdate( "UPDATE record_track SET added ="+added+" WHERE record_track_id = "+record_track_id);

		} finally {
			stmt.close();
		}
	}
}