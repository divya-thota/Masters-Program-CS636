package cs636.vinylstation.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.vinylstation.config.config;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.service.employeeService;

/**
 * Servlet implementation class updateInvoiceServlet
 */
@WebServlet("/updateInvoiceServlet")
public class updateInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateInvoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			final employeeService employeeService = config.getemployeeService();
			int invoice_id = Integer.parseInt(request.getParameter("invoice_id"));
			request.getSession().setAttribute("invoice_id", invoice_id);
			Invoice objInvoice = employeeService.getInvoice(invoice_id);
			System.out.println(objInvoice);
			request.getSession().setAttribute("objInvoice", objInvoice);
			request.getRequestDispatcher("update_invoice_status.jsp").forward(request, response);
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
