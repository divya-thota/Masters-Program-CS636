package cs636.vinylstation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs636.vinylstation.domain.Band;
import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.Track;
import cs636.vinylstation.domain.Genre;

public class bandDAO {
	
	private Connection conn;

	public bandDAO(Connection a) {

		conn = a;

	}
	
	public HashMap<Integer, Integer> getPurchases(int band_id) throws SQLException {

		HashMap<Integer, Integer> yearvsPurchases = new HashMap<Integer, Integer>();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT EXTRACT(YEAR FROM i.invoice_date) as year, count(rt.track_id) as purchases "
					+ "FROM record_track rt, record r, invoice i, track t, band b "
					+ "WHERE i.invoice_id = r.invoice_id AND "
					+ "r.record_id = rt.record_id AND "
					+ "rt.added = 1 AND "
					+ "rt.track_id = t.track_id AND "
					+ "t.band_id = b.band_id AND "
					+ "b.band_id = "+band_id+" GROUP BY EXTRACT(YEAR FROM i.invoice_date)";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				yearvsPurchases.put(rset.getInt("year"), rset.getInt("purchases"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return yearvsPurchases;
	}
	
	
	public List<Track> getbandTracks(int band_id) throws SQLException {
		List<Track> bandTracks = new ArrayList<Track>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from track where band_id = "+band_id;
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				bandTracks.add(new Track(rset.getInt("track_id"), rset.getString("track_name"), rset.getInt("genre_id"),
						rset.getString("url"), rset.getDouble("duration"), rset.getDouble("price"), rset.getInt("band_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bandTracks;
	}
	
	public List<Genre> getGenre() throws SQLException {
		List<Genre> genreList = new ArrayList<Genre>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from genre";
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				genreList.add(new Genre(rset.getInt("genre_id"), rset.getString("genre_name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return genreList;
	}
	
	public Band getBand(int band_id) throws SQLException {
		Band objBand = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from band where band_id = "+band_id;
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				objBand = new Band(rset.getInt("band_id"), rset.getString("name"), 
						rset.getString("password"), rset.getString("band_members"), rset.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objBand;
	}
}