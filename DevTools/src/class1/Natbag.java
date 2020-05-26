package class1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Scanner;

public class Natbag {

	protected flights[] allFlights;
	private int numOfFlights = 0;
	private final int MAX_NUM_OF_FLIGHTS = 100;

	public Natbag() {
		this.allFlights = new flights[MAX_NUM_OF_FLIGHTS];
	}

	public Natbag(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		if (s.hasNext()) {
			int sizeOfArr = s.nextInt();
			int num = 0;
			this.allFlights = new flights[sizeOfArr];
			while (s.hasNext()) {
				allFlights[num++] = new flights(s);
				numOfFlights++;
			}
		}
		s.close();
	}

	public void save(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		pw.println(allFlights.length);
		for (int i = 0; i < numOfFlights; i++) {
				allFlights[i].save(pw);
		}
		pw.close();
	}

	public boolean addFlight(flights flight) {
		for (int i = 0; i < numOfFlights; i++) {
			if (allFlights[i].equals(flight)) {
				return false;
			}
		}
		allFlights[numOfFlights++] = new flights(flight);
		return true;
	}

	public void sortFlights(Comparator<?> c) {
		BubbleSort.bubbleSort(allFlights, c);
	}

	public flights[] getFlights() {
		return allFlights;
	}

	public flights searchFlightByFlightNumber(String flightNum) throws Exception {
		for (int i = 0; i < allFlights.length; i++) {
			if (allFlights[i] != null) {
				if (allFlights[i].getFlightNum().equalsIgnoreCase(flightNum)) {
					return allFlights[i];
				}
			}
		}
		return null;
	}

	public String toString() {
		String arr = "";
		String dep = "";
		for (int i = 0; i < allFlights.length; i++) {
			if (allFlights[i] != null && allFlights[i].arriving) {
				arr += allFlights[i].toString();
			}
		}
		for (int i = 0; i < allFlights.length; i++) {
			if (allFlights[i] != null && !allFlights[i].arriving) {
				dep += allFlights[i].toString();
			}
		}
		return "Departing flights: \n" + dep + "\n" + "Arriving flights: \n" + arr;
	}

}
