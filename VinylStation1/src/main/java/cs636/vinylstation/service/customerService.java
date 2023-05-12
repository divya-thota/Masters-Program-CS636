package cs636.vinylstation.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import cs636.vinylstation.service.ServiceException;
import cs636.vinylstation.dao.customerDAO;
import cs636.vinylstation.dao.trackDAO;
import cs636.vinylstation.dao.invoiceDAO;
import cs636.vinylstation.dao.recordDAO;
import cs636.vinylstation.dao.recordtrackDAO;
import cs636.vinylstation.domain.Track;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.domain.RecordType;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * This class captures the business logic for student interactions.
 *
 * Only one instance of this class is instantiated, i.e., its object is a
 * singleton object, and this singleton receives references to the singleton DAO
 * objects at its own creation time.
 * 
 */

public class customerService {
	
	private customerDAO customerDAO;
	private trackDAO trackDAO;
	private invoiceDAO invoiceDAO;
	private recordDAO recordDAO;
	private recordtrackDAO recordtrackDAO;
    
    public customerService(final customerDAO customerDAO, final trackDAO trackDAO, final invoiceDAO invoiceDAO, final recordDAO recordDAO, final recordtrackDAO recordtrackDAO) {
        this.customerDAO = customerDAO;
        this.trackDAO = trackDAO;
        this.invoiceDAO = invoiceDAO;
        this.recordDAO = recordDAO;
        this.recordtrackDAO = recordtrackDAO;
        
    }
    
    
    public String getFirstTrack()throws ServiceException{
		String firstTrack;
		try {
			firstTrack = trackDAO.getFirstTrack();
		} catch(SQLException e) {
			throw new ServiceException("Can't access tracks in db: ", e);
		}
	   return firstTrack;	
	}
    
    
	// View Top Tracks
	public HashMap<String, Integer> getTopTracks()throws ServiceException{
		HashMap<String, Integer> TracknamevsPurchases = new HashMap<String, Integer>();
		try {
			TracknamevsPurchases = trackDAO.getTopTracks();
		} catch(SQLException e) {
			throw new ServiceException("Can't access tracks in db: ", e);
		}
	   return TracknamevsPurchases;	
	}
	
	public List<RecordType> getRecordType() throws ServiceException {
		List<RecordType> allrecordtypes = new ArrayList<RecordType>();
		try {
			allrecordtypes = customerDAO.getAllRecordTypes();
		}catch(SQLException e) {
			throw new ServiceException("Can't access tracks in db: ", e);
		}
		return allrecordtypes;
		
	}
	
	//View all tracks
	public List<Track> getTracks() throws ServiceException {
		List<Track> alltracks = new ArrayList<Track>();
		try {
			alltracks = trackDAO.getAllTracks();
		}catch(SQLException e) {
			throw new ServiceException("Can't access tracks in db: ", e);
		}
		return alltracks;
		
	}

	// View all their Orders
	public List<Invoice> getDashboardOrders(int customer_id) throws ServiceException {
		List<Invoice> customerInvoices = new ArrayList<Invoice>();
		try {
			customerInvoices = customerDAO.getcustomerDashboardInvoices(customer_id);
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return customerInvoices;
		
		
	}

	// View all their Orders
		public List<Invoice> getOrders(int customer_id) throws ServiceException {
			List<Invoice> customerInvoices = new ArrayList<Invoice>();
			try {
				customerInvoices = customerDAO.getcustomerInvoices(customer_id);
			}catch(SQLException e) {
				throw new ServiceException("Can't access orders in db: ", e);
			}
			return customerInvoices;
			
			
		}
	// Create Invoice
	public void makeInvoice( int customer_id) throws ServiceException {
		try {
			invoiceDAO.createInvoice(customer_id, java.time.LocalDate.now(), 1, 0.00);
		} catch (SQLException e) {
			throw new ServiceException("Invoice and Record was not added successfully: ",e);
		}
		
	}
	
	public int getLatestInvoice( int customer_id) throws ServiceException {
		int invoice_id = 0;
		try {
			invoice_id = invoiceDAO.getLatestInvoice(customer_id);
		} catch (SQLException e) {
			throw new ServiceException("Invoice and Record was not added successfully: ",e);
		}
		return invoice_id;
	}
	
	// Update Invoice Status
	public void updateInvoice(int invoice_id) throws ServiceException {
		try{
			invoiceDAO.updateInvoice(invoice_id);
		}catch (SQLException e) {
			throw new ServiceException("Invoice was not updated successfully: ",e);
		}
		
	}
		
	public void makeRecord( int customer_id, int record_type_id, int invoice_id) throws ServiceException {
		try {
			recordDAO.createRecord(0.00, 0.00, record_type_id, invoice_id);
		} catch (SQLException e) {
			throw new ServiceException("Invoice and Record was not added successfully: ",e);
		}
		
	}
	
	public int getLatestRecord( int invoice_id) throws ServiceException {
		int record_id = 0;
		try {
			record_id = recordDAO.getLatestRecord(invoice_id);
		} catch (SQLException e) {
			throw new ServiceException("Invoice and Record was not added successfully: ",e);
		}
		return record_id;
	}
	
	public void updateRecord( int record_id) throws ServiceException {
		try {
			recordDAO.updateRecord(record_id);
		} catch (SQLException e) {
			throw new ServiceException("Record was not updated successfully: ",e);
		}
		
	}
	
	// Create RecordTrack
	public void makeRecordTrack(int record_id, int track_id) throws ServiceException {
		try{
			recordtrackDAO.createRecordTrack(record_id, track_id, 0);
		}catch (SQLException e) {
			throw new ServiceException("RecordTrack was not added successfully: ",e);
		}
		
	}

	// Search Tracks
	public List<Track> searchTracks(String searchtext) throws ServiceException {
		List<Track> searchtracks = new ArrayList<Track>();
		try {
			if (searchtext == null) {
				searchtracks = trackDAO.getAllTracks();
			} else {
				searchtracks = trackDAO.searchTracks(searchtext);
			}
			System.out.println(searchtracks);
		}catch(SQLException e) {
			throw new ServiceException("Can't access tracks in db: ", e);
		}
		return searchtracks;
		
	}
	
	// View Profile
	public Customer getCustomer(int customer_id) throws ServiceException {
		Customer objCustomer = null;
		try {
			objCustomer = customerDAO.getCustomer(customer_id);
		}catch (SQLException e) {
			throw new ServiceException("Could not fetch customer: ",e);
		}
		return objCustomer;
	}

	// Create Customer
	public void CreateCustomer(String first_name, String last_name, String email, String password, String address,
			String city, String state, String postal_code, String country, String credit_card_type, long credit_card_no,
			String credit_card_expiration_date) throws ServiceException {
		try {
			customerDAO.createCustomer(first_name, last_name, email, password, address, city, 
					state, postal_code, country, credit_card_type, credit_card_no,
					credit_card_expiration_date);
		}catch (SQLException e) {
			throw new ServiceException("Could not create customer successfully: ",e);
		}
	}

}
