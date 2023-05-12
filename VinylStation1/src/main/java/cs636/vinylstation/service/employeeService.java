package cs636.vinylstation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import cs636.vinylstation.dao.employeeDAO;
import cs636.vinylstation.dao.recordtrackDAO;
import cs636.vinylstation.dao.recordDAO;
import cs636.vinylstation.dao.invoiceDAO;
import cs636.vinylstation.domain.Employee;
import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.RecordTrack;
import cs636.vinylstation.domain.Track;

public class employeeService {
	
private employeeDAO employeeDAO;
private recordtrackDAO recordtrackDAO;
private recordDAO recordDAO;
private invoiceDAO invoiceDAO;
    
    public employeeService(final employeeDAO employeeDAO, final recordtrackDAO recordtrackDAO, final recordDAO recordDAO, final invoiceDAO invoiceDAO) {
        this.employeeDAO = employeeDAO;
        this.recordtrackDAO = recordtrackDAO;
        this.recordDAO = recordDAO;
        this.invoiceDAO = invoiceDAO;
    }
    
    //View Pending Orders
    public List<Invoice> getPendingOrders()throws ServiceException{
    	List<Invoice> pendingInvoices = new ArrayList<Invoice>();
		try {
			pendingInvoices = employeeDAO.getPendingInvoices();
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return pendingInvoices;
    }
    //View monthly progress
    public HashMap<String, Integer> ViewMonthlyProgress(int employee_id)throws ServiceException{
    	HashMap<String, Integer> monthvsPerformance = new HashMap<String, Integer>();
		try {
			monthvsPerformance = employeeDAO.ViewMonthlyProgress(employee_id);
		} catch(SQLException e) {
			throw new ServiceException("Can't access employee in db: ", e);
		}
	   return monthvsPerformance;	
    }
    //View Team Performance
    public HashMap<String, Integer> getTeamPerformance() throws ServiceException {
    	HashMap<String, Integer> EmployeenamevsPerformance = new HashMap<String, Integer>();
		try {
			EmployeenamevsPerformance = employeeDAO.getTeamPerformance();
		} catch(SQLException e) {
			throw new ServiceException("Can't access tracks in db: ", e);
		}
	   return EmployeenamevsPerformance;	
    }
    //Update Invoice Status
    public void updateInvoiceEmployee(int invoice_id, int employee_id) throws ServiceException {
    	try{
			invoiceDAO.updateInvoiceEmployee(invoice_id, employee_id);
		}catch (SQLException e) {
			throw new ServiceException("Invoice status was not updated successfully: ",e);
		}
    }
    
    public void updateInvoiceStatus(int invoice_id, int status_id) throws ServiceException {
    	try{
			invoiceDAO.updateInvoiceStatus(invoice_id, status_id);
		}catch (SQLException e) {
			throw new ServiceException("Invoice status was not updated successfully: ",e);
		}
    }
    
  //Get invoice Records and Tracks
    public HashMap<Integer, List<RecordTrack>> getOrderTracks(int invoice_id) throws ServiceException{
    	HashMap<Integer, List<RecordTrack>> orderTracks = new HashMap<Integer, List<RecordTrack>>();
		try {
			orderTracks = employeeDAO.getOrderTracks(invoice_id);
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return orderTracks;
    }
    
    // Update RecordTrack Added field
    public void updateRecordTrack(int record_track_id, int added) throws ServiceException {
    	try{
    		recordtrackDAO.updateRecordTrack(record_track_id, added);
		}catch (SQLException e) {
			throw new ServiceException("Invoice status was not updated successfully: ",e);
		}
    }
    //View invoices they worked on
    public List<Invoice> getInvoices(int employee_id) throws ServiceException {
    	List<Invoice> employeeInvoices = new ArrayList<Invoice>();
		try {
			employeeInvoices = employeeDAO.getemployeeInvoices(employee_id);
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return employeeInvoices;
		
    }
    
  //View invoices they worked on
    public List<Invoice> getInProgressInvoices(int employee_id) throws ServiceException {
    	List<Invoice> employeeInvoices = new ArrayList<Invoice>();
		try {
			employeeInvoices = employeeDAO.getInProgressInvoices(employee_id);
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return employeeInvoices;
		
    }
  //View invoices they worked on
    public List<Invoice> getOutForDeliveryInvoices(int employee_id) throws ServiceException {
    	List<Invoice> employeeInvoices = new ArrayList<Invoice>();
		try {
			employeeInvoices = employeeDAO.getOutForDeliveryInvoices(employee_id);
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return employeeInvoices;
		
    }
  //View invoices they worked on
    public List<Invoice> getDeliveredInvoices(int employee_id) throws ServiceException {
    	List<Invoice> employeeInvoices = new ArrayList<Invoice>();
		try {
			employeeInvoices = employeeDAO.getDeliveredInvoices(employee_id);
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return employeeInvoices;
		
    }
    
    //List<Integer> allRecordTracks = employeeService.getInvoiceRecordTracks(invoice_id);
    public List<Integer> getInvoiceRecordTracks(int invoice_id) throws ServiceException {
    	List<Integer> allRecordTracks = new ArrayList<Integer>();
		try {
			allRecordTracks = employeeDAO.getInvoiceRecordTracks(invoice_id);
		}catch(SQLException e) {
			throw new ServiceException("Can't access orders in db: ", e);
		}
		return allRecordTracks;
		
    }
    
  //View Profile
    public Invoice getInvoice(int invoice_id) throws ServiceException {
    	Invoice objInvoice = null;
		try {
			objInvoice = invoiceDAO.getInvoice(invoice_id);
		}catch (SQLException e) {
			throw new ServiceException("Could not fetch employee: ",e);
		}
		return objInvoice;
    }
    
    //View Profile
    public Employee getEmployee(int employee_id) throws ServiceException {
    	Employee objEmployee = null;
		try {
			objEmployee = employeeDAO.getEmployee(employee_id);
		}catch (SQLException e) {
			throw new ServiceException("Could not fetch employee: ",e);
		}
		return objEmployee;
    }

}