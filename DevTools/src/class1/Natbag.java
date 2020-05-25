package class1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Scanner;

public class Natbag {

	private flights[] allFlights;
	private int numOfFlights = 0;
	private static int flightNumGen;
	private final int MAX_NUM_OF_FLIGHTS = 100;

	public Natbag() {
		this.allFlights = new flights[MAX_NUM_OF_FLIGHTS];
		this.numOfFlights = flightNumGen++;
	}
	
	public Natbag(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		int sizeOfArr = s.nextInt();
		int num = 0;
		this.allFlights = new flights[sizeOfArr];
		while (s.hasNext()) {
				allFlights[num++] = new flights(s);
				numOfFlights++;
		}s.close();
	}

	public void save(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		pw.println(allFlights.length);
		for (int i = 0; i < allFlights.length; i++) {
			if (allFlights[i] != null) 
				allFlights[i].save(pw);
			
		}pw.close();
	}
	
	public boolean addFlight(flights flight) {
		allFlights[numOfFlights++] = flight;
		return true;
	}
	
	public void addFlight(Scanner s) throws FileNotFoundException {
		addFlight(new flights(s));
	}

	public void sortFlights(Comparator<?> c) {
		BubbleSort.bubbleSort(allFlights, c);
	}

	public flights[] getFlights() {
		return allFlights;
	}

	public flights searchFlight(String flightNum) {
		flights f = null;
		for (int i = 0; i < allFlights.length; i++) {
			if (allFlights[i].getFlightNum().equals(flightNum)) {
				f = allFlights[i];
			}
		}
		return f;
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
