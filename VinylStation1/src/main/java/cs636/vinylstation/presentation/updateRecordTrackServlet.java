package cs636.vinylstation.presentation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.vinylstation.config.config;
import cs636.vinylstation.service.employeeService;

/**
 * Servlet implementation class updateRecordTrackServlet
 */
@WebServlet("/updateRecordTrackServlet")
public class updateRecordTrackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateRecordTrackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String[] recordtrackcheckbox= request.getParameterValues("recordtrackcheckbox");
			List<String> checkedRecordTrack = Arrays.asList(recordtrackcheckbox);
			for  (String s: checkedRecordTrack) {
				System.out.println(s);
			}
			final employeeService employeeService = config.getemployeeService();
			int invoice_id = (int) request.getSession().getAttribute("invoice_id");
			List<Integer> allRecordTracks = employeeService.getInvoiceRecordTracks(invoice_id);
			for (int record_track_id: allRecordTracks) {
				if (checkedRecordTrack.contains(String.valueOf(record_track_id))) {
					employeeService.updateRecordTrack(record_track_id, 1);
				} else {
					employeeService.updateRecordTrack(record_track_id, 0);
				}
			}
		}
		catch (Exception e) {
	        e.printStackTrace();
	    }
		request.getRequestDispatcher("employeeServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
