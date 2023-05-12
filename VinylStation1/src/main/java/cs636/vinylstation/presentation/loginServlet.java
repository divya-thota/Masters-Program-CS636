package cs636.vinylstation.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import cs636.vinylstation.service.customerService;
import cs636.vinylstation.service.employeeService;
import cs636.vinylstation.service.bandService;
import cs636.vinylstation.service.loginService;
import cs636.vinylstation.config.config;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet({ "/loginServlet" })
public class loginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    	// read form fields
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         
        System.out.println("username: " + username);
        System.out.println("password: " + password);
 
        // do some processing here...
        try {
            config.configureServices();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final loginService loginService = config.getloginService();
        String role = loginService.checkloginCredential(username, password);
        request.getSession().setAttribute("role", role);
        if (role.equals("none")){
        	request.getSession().setAttribute("error", "Invalid username/email or password");
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else if (role.contains("customer")) {
        	int customer_id = Integer.parseInt(role.replace("customer", ""));
        	request.getSession().setAttribute("customer_id", customer_id);
        	request.getRequestDispatcher("customerServlet").forward(request, response);
        }
        else if (role.contains("employee")) {
        	int employee_id = Integer.parseInt(role.replace("employee", ""));
        	request.getSession().setAttribute("employee_id", employee_id);
        	request.getRequestDispatcher("employeeServlet").forward(request, response);
        }
        else if (role.contains("band")) {
        	int band_id = Integer.parseInt(role.replace("band", ""));
        	request.getSession().setAttribute("band_id", band_id);
        	request.getRequestDispatcher("bandServlet").forward(request, response);
        }
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}