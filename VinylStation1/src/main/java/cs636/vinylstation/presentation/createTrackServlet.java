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

import org.json.simple.JSONObject;

import cs636.vinylstation.config.config;
import cs636.vinylstation.domain.Band;
import cs636.vinylstation.domain.Track;
import cs636.vinylstation.service.bandService;
import cs636.vinylstation.service.customerService;

/**
 * Servlet implementation class createTrackServlet
 */
@WebServlet({"/createTrackServlet"})
public class createTrackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String trackname = request.getParameter("trackname");
			int genre = Integer.valueOf(request.getParameter("genre"));
			String url = request.getParameter("url");
			double duration = Double.valueOf( request.getParameter("duration"));
			double price = Double.valueOf( request.getParameter("price"));
	        int band_id = (int) request.getSession().getAttribute("band_id");
	        final bandService bandService = config.getbandService();
	        bandService.createTracks(trackname,genre, url, duration, price, band_id);
//	        
//	        request.getRequestDispatcher("band_dashboard.jsp").forward(request, response);
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
