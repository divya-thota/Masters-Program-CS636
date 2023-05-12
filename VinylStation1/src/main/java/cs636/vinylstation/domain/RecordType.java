package cs636.vinylstation.domain;

public class RecordType {

	private int record_type_id;	
	private String record_type_name;
	private int duration;	
	
	public RecordType( int record_type_id, String record_type_name, int duration) {
		this.record_type_id = record_type_id;
		this.record_type_name = record_type_name;
		this.duration = duration;
	}
	
	public int get_record_type_id() {
		return record_type_id;
	}
	public String get_record_type_name() {
		return record_type_name;
	}
	public int get_duration() {
		return duration;
	}

	
}
