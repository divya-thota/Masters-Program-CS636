package cs636.vinylstation.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import cs636.vinylstation.config.config;
import cs636.vinylstation.service.customerService;

/**
 * Servlet implementation class createCustomerServlet
 */
@WebServlet("/createCustomerServlet")
public class createCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String address = request.getParameter("address");
	        String city = request.getParameter("city");
	        String state = request.getParameter("state");
	        String postal_code = request.getParameter("postal_code");
	        String country = request.getParameter("country");
	        String credit_card_type = request.getParameter("credit_card_type");
	        long credit_card_no = Long.parseLong(request.getParameter("credit_card_no"));
	        String credit_card_expiration_date = request.getParameter("credit_card_expiration_date");
	        System.out.println(request.getParameter("credit_card_expiration_date"));
	        try {
	            config.configureServices();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        final customerService customerService = config.getcustomerService();
	        customerService.CreateCustomer(firstname, lastname, email, password, address, city, state, postal_code, country, credit_card_type, credit_card_no, credit_card_expiration_date);
//	        response.setContentType("text/html");
//			PrintWriter writer = response.getWriter();
//			writer.println("Customer Successfully Created</br>");
//			writer.println("<a href=index.jsp>Click here to login</a>");
	        request.getSession().setAttribute("registration", "Customer Successfully Created!");
	        request.getRequestDispatcher("index.jsp").forward(request, response);
		}catch (Exception e) {
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
