package class1;

import java.time.LocalDateTime;
import java.util.Scanner;
import natbad2020.flights.status;

public class Main {
	
	static Scanner s = new Scanner(System.in);
	static Natbag n = new Natbag();
	
	public static void main(String[] args) throws Exception {
		n = new Natbag("Natbag2020.txt");
		
		n.addFlight(new flights("Elal", "OK540",LocalDateTime.of(2019, 9, 5, 17, 0) , status.onTime, true, "Petah Tikva"));
		n.addFlight(new flights("Air-Fly", "YL123", LocalDateTime.of(2019, 2, 7, 19, 0), status.onTime, true, "Paris"));
		n.addFlight(new flights("Fly-Fly", "KOK543", LocalDateTime.of(2019, 6, 30, 9, 0), status.onTime, false, "New York"));
		n.addFlight(new flights("Air-Kaka", "BO122", LocalDateTime.of(2019, 6, 1, 17, 0), status.onTime, true, "Montana"));
		n.addFlight(new flights("Air-Kaka", "BO132", LocalDateTime.of(2019, 7, 3, 5, 0), status.onTime, false, "Montana"));
		n.addFlight(new flights("Air-Kaka", "BO158", LocalDateTime.of(2019, 8, 13, 5, 0), status.onTime, false, "Montana"));
		
		int res = 0;
		while (res != -1) {
			System.out.println( "To add flight --> 1\n"
					+			"To sort by date --> 2\n"
					+			"To sort by air ports name --> 3\n"
					+			"To buy ticket --> 4\n"
					+			"For list of all free seats --> 5\n"
					+			"Exit --> -1\n");
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
				System.out.println(n.toString());
				break;
				
			case 3: //sort by air ports name
				n.sortFlights(new CompareByAirPortName());
				System.out.println(n.toString());
				break;
				
			case 4: //paymentApp
				paymentApp();
				break;

			case 5: //All free seats
				for (int i = 0; i < n.allFlights.length; i++) {
					if (n.allFlights[i] != null) {
						System.out.println(n.allFlights[i]);
						n.allFlights[i].getAllFreeSeats();
						System.out.println("---------------------");
					}
				}
				break;
				
			case -1: //Exit
				n.save("Natbag2020.txt");
				s.close();
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + res);
			}
		}
		
		
	}

	public static void paymentApp() throws Exception {
		System.out.println("You arrived to The Payment App.\nFor EXIT  please enter -1, to continue enter 2");
		int res = s.nextInt();
		if (res != -1) {
			System.out.println("Please enter the flight number:");
			String flightNum = s.next();
			flights tempFlight = n.searchFlightByFlightNumber(flightNum);
			System.out.println("Is that the flight you mean to:\n"
								+ tempFlight
								+"\nY to continue.\n");
			String res2 =s.next();
			if (res2.equalsIgnoreCase("Y")) { //if NOT wrong flight!
				System.out.println("Please enter yor credit catd:\n"); // FOR now without exceptions
				Long creditCard = s.nextLong();
				System.out.println("\nPlease enter youer passport number:\n");
				String passportNumber = s.next();
				Person tempPerson = new Person(creditCard, passportNumber, null); //create temp person
				System.out.println(tempPerson);
				s.nextLine(); // Clean Buffer
				System.out.println("Please pick one seat:\n");
				n.searchFlightByFlightNumber(flightNum).getAllFreeSeats(); // pick a seat
				System.out.println("\nPlease enter 'Line: <numLine> Row: <numRow>'");
				String tempSeatID = s.nextLine();
				Seat tempSeat = tempFlight.getSeatBySeadID(tempSeatID);
				tempPerson.setMySeat(tempSeat); // initial seat for user
				tempSeat.setMyPerson(tempPerson); // initial user into seat
				n.searchFlightByFlightNumber(flightNum).setSeatToPerson(tempSeat, tempPerson);
				System.out.println("\nYour ticket has been sent to your Email.\n\n"+tempPerson.getMySeat() + "\n" + tempSeat.getMyPerson() + "\n");
				n.save("Natbag2020.txt");
			}
		}
	}

}
