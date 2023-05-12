package cs636.vinylstation.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.vinylstation.config.config;
import cs636.vinylstation.domain.RecordTrack;
import cs636.vinylstation.service.employeeService;

/**
 * Servlet implementation class makeOrderServlet
 */
@WebServlet("/makeOrderServlet")
public class makeOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int invoice_id = Integer.parseInt(request.getParameter("invoice_id"));
			request.getSession().setAttribute("invoice_id", invoice_id);
			int employee_id = (int) request.getSession().getAttribute("employee_id");
			final employeeService employeeService = config.getemployeeService();
			employeeService.updateInvoiceEmployee(invoice_id,employee_id);
			HashMap<Integer, List<RecordTrack>> orderTracks = employeeService.getOrderTracks(invoice_id);
			request.getSession().setAttribute("orderTracks", orderTracks);
//			response.sendRedirect("./employee_makeorder.jsp");
			request.getRequestDispatcher("employee_makeorder.jsp").forward(request, response);
//			response.setContentType("text/html");
//			PrintWriter writer = response.getWriter();
//			writer.println("<%@include file='employee_makeorder.jsp'%>");
		} catch (Exception e) {
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
