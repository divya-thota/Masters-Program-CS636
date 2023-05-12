package cs636.vinylstation.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import cs636.vinylstation.service.bandService;
import cs636.vinylstation.service.customerService;
import cs636.vinylstation.service.employeeService;
import cs636.vinylstation.config.config;
import cs636.vinylstation.domain.Band;
import cs636.vinylstation.domain.Customer;
import cs636.vinylstation.domain.Employee;
import cs636.vinylstation.domain.Invoice;
import cs636.vinylstation.domain.Track;
import cs636.vinylstation.domain.Genre;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.List;

@WebServlet({ "/bandServlet" })
public class bandServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    	try {
    		int band_id = (int) request.getSession().getAttribute("band_id");
    		final bandService bandService = config.getbandService();
    		HashMap<String, Integer> TracknamevsPurchases = bandService.getTopTracks();
    		request.getSession().setAttribute("TracknamevsPurchases", new JSONObject(TracknamevsPurchases));
    		HashMap<Integer, Integer> yearvsPurchases = bandService.getPurchases(band_id);
    		request.getSession().setAttribute("yearvsPurchases", new JSONObject(yearvsPurchases));
    		Band objBand = bandService.getBand(band_id);
    		request.getSession().setAttribute("band", objBand);
    		List<Track> bandTracks = bandService.getTracks(band_id);
    		request.getSession().setAttribute("bandTracks", bandTracks);
    		List<Genre> genreList = bandService.getGenre();
    		request.getSession().setAttribute("genreList", genreList);
    		request.getRequestDispatcher("band_dashboard.jsp").forward(request, response);
    	}
    	catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}