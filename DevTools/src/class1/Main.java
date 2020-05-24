package class1;

import java.time.LocalDateTime;
import java.util.Scanner;

import class1.flights.status;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		LocalDateTime d1 = LocalDateTime.of(2019, 9, 5, 17, 0);
		LocalDateTime d2 = LocalDateTime.of(2019, 2, 7, 19, 0);
		LocalDateTime d3 = LocalDateTime.of(2019, 6, 3, 9, 0);
		LocalDateTime d4 = LocalDateTime.of(2019, 6, 1, 17, 0);
		LocalDateTime d5 = LocalDateTime.of(2019, 6, 3, 5, 0);
		
		flights	f1 = new flights("Elal", "OK540",d1 , status.onTime, true, "Petah Tikva");
		flights	f2 = new flights("Air Fly", "YA123", d2, status.onTime, true, "Paris");
		flights	f3  = new flights("Fly Fly", "KOK543", d3, status.onTime, false, "New York");
		flights	f4 = new flights("Air Kaka", "BO122", d4, status.onTime, true, "Montana");
		flights	f5 = new flights("Air Kaka", "BO122", d5, status.onTime, false, "Montana");
		flights f6 = new flights(s);

		
		Natbag n = new Natbag();

		n.addFlight(f1);
		n.addFlight(f2);
		n.addFlight(f3);
		n.addFlight(f4);
		n.addFlight(f5);
		n.addFlight(f6);
		
		int res = 0;
		while (res != -1) {
			System.out.println("To add flight --> 1\n"
					+  "To sort by date --> 2\n"
					+  "To sort by air ports name --> 3\n"
					+  "Exit --> -1\n");
			res = s.nextInt();
			switch (res) {
			case 1: // add flight
				System.out.println("Please enter airline");
				String tempAirline = s.next();
				System.out.println("Please enter flight number");
				String flightNum = s.next();
				System.out.println("Flight status? ( landing, unconclusive, early, late, onTime )");
				status eStatus = status.valueOf(s.next());
				System.out.println("arrivaing-> true, else -> false");
				boolean arriving = Boolean.valueOf(s.next());
				System.out.println("Please enter city");
				String city = s.next();
				System.out.println("Pleasr enter the local date time: (Y, M, D, H, Min)");
				LocalDateTime dateTime = LocalDateTime.of(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
				n.addFlight(new flights(tempAirline, flightNum, dateTime, eStatus, arriving, city));

				break;
			
			case 2: //sort by date
				n.sortFlights(new CompareByDate());
				break;
				
			case 3: //sort by air ports name
				n.sortFlights(new CompareByAirPortName());
				break;
				
			case -1: //Exit
				System.out.println(n.toString());
				break;	

			default:
				throw new IllegalArgumentException("Unexpected value: " + res);
			}
		}

	}

}
