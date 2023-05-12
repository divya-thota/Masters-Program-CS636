package cs636.vinylstation.presentation;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import cs636.vinylstation.config.config;
import cs636.vinylstation.service.customerService;

import org.json.simple.JSONArray;

/**
 * Servlet implementation class placeOrderServlet
 */
@WebServlet("/placeOrderServlet")
public class placeOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public placeOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			int customer_id = (int) request.getSession().getAttribute("customer_id");
			String cart= request.getParameter("cart");
			JSONParser parser = new JSONParser();
			final customerService customerService = config.getcustomerService();
			JSONArray recordList = (JSONArray) parser.parse(cart);  
			customerService.makeInvoice(customer_id);
			int invoice_id= customerService.getLatestInvoice(customer_id);
			for(int i=0; i < recordList.size(); i++)   
			{  
				JSONObject recordObject = (JSONObject) recordList.get(i); 
				customerService.makeRecord(customer_id, ((Long)recordObject.get("record_type_id")).intValue(), invoice_id);
				int recordId = customerService.getLatestRecord(invoice_id);
				JSONArray trackList = ((JSONArray) recordObject.get("tracks"));
				for (int j=0; j < trackList.size(); j++) {
					JSONObject trackObject = (JSONObject) trackList.get(j);
					customerService.makeRecordTrack(recordId, ((Long) trackObject.get("track_id")).intValue());
				}
				customerService.updateRecord(recordId);
			}
			customerService.updateInvoice(invoice_id);
		}
		catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
