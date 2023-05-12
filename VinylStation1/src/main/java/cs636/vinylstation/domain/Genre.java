package cs636.vinylstation.domain;

public class Genre {

	private int genre_id;	
	private String genre_name;
	
	public Genre( int genre_id, String genre_name) {
		this.genre_id = genre_id;
		this.genre_name = genre_name;
	}
	
	public int get_genre_id() {
		return genre_id;
	}
	public String get_genre_name() {
		return genre_name;
	}
	
}
