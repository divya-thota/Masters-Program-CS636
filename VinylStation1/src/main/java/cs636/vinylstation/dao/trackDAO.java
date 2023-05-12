package cs636.vinylstation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs636.vinylstation.domain.Track;
import cs636.vinylstation.domain.loginObject;

public class trackDAO {
	
	private Connection conn;

	public trackDAO(Connection a) {

		conn = a;

	}

	
	public void createTrack(String track_name, int genre_id, String url, double duration, double price, int band_id) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			stmt.executeUpdate( "INSERT INTO track VALUES (NULL, '" 
												+ track_name + "', "
												+genre_id+", '"
												+url+"', "
												+duration+ ", "
												+price+", "
												+band_id+")");

		} finally {
			stmt.close();
		}
	}
	
	public String getFirstTrack() throws SQLException {
		String firstTrack=null;
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT t.track_name as track_name, count(r.track_id) as purchases "
					+ "FROM record_track r, track t "
					+ "WHERE added = 1 AND "
					+ "r.track_id = t.track_id	"
					+ "GROUP BY r.track_id, t.track_name "
					+ "ORDER BY count(r.track_id) DESC FETCH NEXT 1 ROWS ONLY";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				firstTrack = rset.getString("track_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return firstTrack;
	}
	
	public HashMap<String, Integer> getTopTracks() throws SQLException {

		HashMap<String, Integer> TracknamevsPurchases = new HashMap<String, Integer>();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT t.track_name as track_name, count(r.track_id) as purchases "
					+ "FROM record_track r, track t "
					+ "WHERE added = 1 AND "
					+ "r.track_id = t.track_id	"
					+ "GROUP BY r.track_id, t.track_name "
					+ "ORDER BY count(r.track_id) DESC FETCH NEXT 5 ROWS ONLY";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				TracknamevsPurchases.put(rset.getString("track_name"), rset.getInt("purchases"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return TracknamevsPurchases;
	}

	public List<Track> getAllTracks() throws SQLException {
		List<Track> alltracks = new ArrayList<Track>();
		try {

			Statement stmt = conn.createStatement();
			String query = "select * from track";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				alltracks.add(new Track(rset.getInt("track_id"), rset.getString("track_name"), rset.getInt("genre_id"),
						rset.getString("url"), rset.getDouble("duration"), rset.getDouble("price"), rset.getInt("band_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return alltracks;
	}
	
	public List<Track> searchTracks(String Name) throws SQLException {
		List<Track> searchtracks = new ArrayList<Track>();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM track WHERE upper(track_name) LIKE upper('%"+Name+"%' )";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				searchtracks.add(new Track(rset.getInt("track_id"), rset.getString("track_name"), rset.getInt("genre_id"),
						rset.getString("url"), rset.getDouble("duration"), rset.getDouble("price"), rset.getInt("band_id")));
			}
			
			query = "SELECT * from track t, band b where t.band_id = b.band_id and upper(b.name) LIKE upper('%"+Name+"%' )";
			rset = stmt.executeQuery(query);
			while (rset.next()) {
				searchtracks.add(new Track(rset.getInt("track_id"), rset.getString("track_name"), rset.getInt("genre_id"),
						rset.getString("url"), rset.getDouble("duration"), rset.getDouble("price"), rset.getInt("band_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return searchtracks;
	}
}