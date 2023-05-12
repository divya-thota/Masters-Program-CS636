package cs636.vinylstation.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.vinylstation.config.config;
import cs636.vinylstation.service.employeeService;

/**
 * Servlet implementation class updatedStatusServlet
 */
@WebServlet("/updatedStatusServlet")
public class updatedStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatedStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			int status_id = Integer.parseInt(request.getParameter("status"));
			final employeeService employeeService = config.getemployeeService();
			int invoice_id = (int) request.getSession().getAttribute("invoice_id");
			employeeService.updateInvoiceStatus(invoice_id,status_id);
			request.getRequestDispatcher("employeeServlet").forward(request, response);
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
