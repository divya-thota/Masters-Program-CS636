package cs636.vinylstation.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import cs636.vinylstation.service.customerService;
import cs636.vinylstation.config.config;
import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.RecordType;
import cs636.vinylstation.domain.Track;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet({ "/customerServlet" })
public class customerServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    	try {
//    		String searchtext = request.getParameter("searchtext");
        	final customerService customerService = config.getcustomerService();
        	int customer_id = (int) request.getSession().getAttribute("customer_id");
        	String firstTrack = customerService.getFirstTrack();
        	request.getSession().setAttribute("firstTrack", firstTrack);
        	System.out.println("Fetched top trending track: "+firstTrack);
    		HashMap<String, Integer> TracknamevsPurchases = customerService.getTopTracks();
    		request.getSession().setAttribute("TracknamevsPurchases", new JSONObject(TracknamevsPurchases));
    		System.out.println("Fetched top 5 tracks: "+TracknamevsPurchases);
    		List<RecordType> allrecordtypes = customerService.getRecordType();
    		request.getSession().setAttribute("allrecordtypes", allrecordtypes);
    		System.out.println("Fetched RecordTypes: "+allrecordtypes);
    		List<Invoice> customerInvoicesDashboard = customerService.getDashboardOrders(customer_id);
    		request.getSession().setAttribute("customerInvoicesDashboard", customerInvoicesDashboard);
    		System.out.println("Fetched customer's 5 recent invoices: "+ customerInvoicesDashboard);
    		List<Invoice> customerInvoices = customerService.getOrders(customer_id);
    		request.getSession().setAttribute("customerInvoices", customerInvoices);
    		System.out.println("Fetched all customer's invoices: "+ customerInvoices);
    		Customer objCustomer = customerService.getCustomer(customer_id);
    		request.getSession().setAttribute("customer", objCustomer);
    		System.out.println("Fetched customer details:"+objCustomer);
    		List<Track> searchtracks = customerService.searchTracks(null);
    		request.getSession().setAttribute("searchtracks", searchtracks);
    		request.getRequestDispatcher("customer_dashboard.jsp").forward(request, response);
//    		writer.println("<script>window.open('customer_dashboard.jsp')</script>");
//            response.sendRedirect("/customerServlet");
    	}
		catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}