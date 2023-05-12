package cs636.vinylstation.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.vinylstation.config.config;
import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.service.customerService;

/**
 * Servlet implementation class cartServlet
 */
@WebServlet("/checkoutServlet")
public class checkoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		final customerService customerService = config.getcustomerService();
		int customer_id = (int) request.getSession().getAttribute("customer_id");
		Customer objCustomer = customerService.getCustomer(customer_id);
		request.getSession().setAttribute("customer", objCustomer);
		request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
