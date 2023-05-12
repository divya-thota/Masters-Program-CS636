package cs636.vinylstation.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import cs636.vinylstation.service.customerService;
import cs636.vinylstation.service.employeeService;
import cs636.vinylstation.config.config;
import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.domain.Employee;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.Track;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet({ "/employeeServlet" })
public class employeeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    	try {
    		final employeeService employeeService = config.getemployeeService();
    		int employee_id = (int) request.getSession().getAttribute("employee_id");
    		Employee objEmployee = employeeService.getEmployee(employee_id);
    		request.getSession().setAttribute("employee", objEmployee);
    		List<Invoice> employeeInvoices = employeeService.getInvoices(employee_id);
    		request.getSession().setAttribute("employeeInvoices", employeeInvoices);
    		List<Invoice> inProgressInvoices = employeeService.getInProgressInvoices(employee_id);
    		request.getSession().setAttribute("inProgressInvoices", inProgressInvoices);
    		List<Invoice> outForDeliveryInvoices = employeeService.getOutForDeliveryInvoices(employee_id);
    		request.getSession().setAttribute("outForDeliveryInvoices", outForDeliveryInvoices);
    		List<Invoice> DeliveredInvoices = employeeService.getDeliveredInvoices(employee_id);
    		request.getSession().setAttribute("DeliveredInvoices", DeliveredInvoices);
    		List<Invoice> pendingInvoices = employeeService.getPendingOrders();
    		request.getSession().setAttribute("pendingInvoices", pendingInvoices);
    		HashMap<String, Integer> monthvsPerformance = employeeService.ViewMonthlyProgress(employee_id);
    		request.getSession().setAttribute("monthvsPerformance", monthvsPerformance);
    		HashMap<String, Integer> EmployeenamevsPerformance = employeeService.getTeamPerformance();
    		request.getSession().setAttribute("EmployeenamevsPerformance", new JSONObject(EmployeenamevsPerformance));
    		request.getRequestDispatcher("employee_dashboard.jsp").forward(request, response);
    	}
    	catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}