package cs636.vinylstation.domain;

import java.util.Date;
import java.util.Set;

public class RecordTrack {

	private int record_track_id;
	private int track_id;	
	private String track_name;
	private int record_id;
	private int added;	
	
	public RecordTrack( int record_track_id, int track_id, String track_name, int record_id, int added) {
		this.record_track_id = record_track_id;
		this.track_id = track_id;
		this.track_name = track_name;
		this.record_id = record_id;
		this.added = added;
	}
	
	public int get_record_track_id() {
		return record_track_id;
	}
	public int get_track_id() {
		return track_id;
	}
	public String get_track_name() {
		return track_name;
	}
	public int get_record_id() {
		return record_id;
	}
	public int get_added() {
		return added;
	}
	
}
