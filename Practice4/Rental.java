package Practice4;

public class Rental {
	private Movie movie;
	private int period;
	
	public Rental(Movie movie, int period) {
		this.movie = movie;
		this.period = period;
	}
	
	public String getName() {
		return movie.getName();
	}
	
	public double getCharge() {
		double result = 0;
		final int type = movie.getType();
		switch(type) {
		case Movie.NEW_RELEASE :
			result = period * 3;
			break;
		case Movie.REGULAR :
			result = 2 + 1.5 * Math.max(0, period - 2);
			break;
		case Movie.CHILDREN :
			result = 1.5 * Math.max(1, period - 2);
			break;
		}
		
		return result;
	}
}
