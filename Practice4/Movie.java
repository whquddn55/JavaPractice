package Practice4;

public class Movie {
	public static final int NEW_RELEASE = 0, REGULAR = 1, CHILDREN = 2;
	
	private String name;
	private int type;
	
	public Movie(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public int getType() {
		return type;
	}
}
