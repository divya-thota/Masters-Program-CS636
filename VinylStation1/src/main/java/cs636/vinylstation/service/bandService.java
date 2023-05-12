package cs636.vinylstation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs636.vinylstation.dao.bandDAO;
import cs636.vinylstation.dao.trackDAO;
import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.Track;
import cs636.vinylstation.domain.Band;
import cs636.vinylstation.domain.Genre;

public class bandService {
	
private bandDAO bandDAO;
private trackDAO trackDAO;
    
    public bandService(final bandDAO bandDAO, final trackDAO trackDAO) {
        this.bandDAO = bandDAO;
        this.trackDAO = trackDAO;
    }
    //Creates Tracks
    public void createTracks(String track_name, int genre_id, String url, double duration, double price, int band_id) throws ServiceException {
    	try {
    		trackDAO.createTrack(track_name, genre_id, url, duration, price, band_id);
    	} catch (SQLException e) {
			throw new ServiceException("Track was not added successfully: ",e);
		}
    }
    //View Top Tracks
    public HashMap<String, Integer> getTopTracks()throws ServiceException{
    	HashMap<String, Integer> TracknamevsPurchases = new HashMap<String, Integer>();
		try {
			TracknamevsPurchases = trackDAO.getTopTracks();
		} catch(SQLException e) {
			throw new ServiceException("Can't access tracks in db: ", e);
		}
	   return TracknamevsPurchases;	
    }
    //View Total number of Purchases over the years
    public HashMap<Integer, Integer> getPurchases(int band_id) throws ServiceException {
    	HashMap<Integer, Integer> yearvsPurchases = new HashMap<Integer, Integer>();
		try {
			yearvsPurchases = bandDAO.getPurchases(band_id);
		} catch(SQLException e) {
			throw new ServiceException("Can't access tracks in db: ", e);
		}
	   return yearvsPurchases;	
    }
    //View all their tracks
    public List<Track> getTracks(int band_id) throws ServiceException {
    	List<Track> bandTracks = new ArrayList<Track>();
		try {
			bandTracks = bandDAO.getbandTracks(band_id);
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return bandTracks;
    }
    //View Profile
    public Band getBand(int band_id) throws ServiceException {
    	Band objBand = null;
		try {
			objBand = bandDAO.getBand(band_id);
		}catch (SQLException e) {
			throw new ServiceException("Could not fetch band: ",e);
		}
		return objBand;
    }
    public List<Genre> getGenre() throws ServiceException {
    	List<Genre> genreList = new ArrayList<Genre>();
		try {
			genreList = bandDAO.getGenre();
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return genreList;
    }
}