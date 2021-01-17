package Practice4;

import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Rental> rentals = new ArrayList<Rental>();
	
	public Customer(String name) {
		this.name = name;
	}
	
	public void addRental(Rental newRental) {
		rentals.add(newRental);
	}
	
	public String getReport() {
		StringBuilder msg = new StringBuilder("Charge for " + name + "\n");
		double totalCharge = 0;
		for (Rental r : rentals) {
			msg.append(r.getName() + " : " + r.getCharge() + "\n");
			totalCharge += r.getCharge();
		}
		msg.append("Total Charge : ");
		msg.append(totalCharge);
		msg.append("\n");
		msg.append("You earned ");
		msg.append(rentals.size());
		msg.append(" points\n");
		return msg.toString();
	}
}
