package cs636.vinylstation.domain;

import java.util.Date;
import java.util.Set;

public class Track {

	private int track_id;
	private String track_name;
	private int genre_id;
	private String url;
	private double duration;	
	private double price;	
	private int band_id;	
	
	public Track( int track_id, String track_name, int genre_id, String url, double duration, double price, int band_id) {
		this.track_id = track_id;
		this.track_name = track_name;
		this.genre_id = genre_id;
		this.url = url;
		this.duration = duration;
		this.price = price;
		this.band_id = band_id;
	}
	
	public int get_track_id() {
		return track_id;
	}
	public String get_track_name() {
		return track_name;
	}
	public int get_genre_id() {
		return genre_id;
	}
	public String get_url() {
		return url;
	}
	public double get_duration() {
		return duration;
	}
	public double get_price() {
		return price;
	}
	public int get_band_id() {
		return band_id;
	}
	
}
