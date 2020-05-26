package class1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class flights {

	public enum status {landing, unconclusive, early, late, onTime};
	public static int IDFlight;
	protected String airline;
	protected String flightNum;
	protected status eStatus;
	protected boolean arriving;
	protected String city;
	protected LocalDateTime dateTime;
	public int myIDFlight;
	protected Seat[] allSeats = new Seat[50]; 


	public flights(String airline, String flightNum, LocalDateTime dateTime, status eStatus, boolean arriving, String city) {
		this.airline = airline;
		this.flightNum = flightNum;
		this.eStatus = eStatus;
		this.arriving = arriving;
		this.city = city;
		this.dateTime = dateTime;
		myIDFlight = ++IDFlight;
		setSeats();
	}
	
	public flights(flights f) {
		this.airline = f.airline;
		this.flightNum = f.flightNum;
		this.eStatus = f.eStatus;
		this.arriving = f.arriving;
		this.city = f.city;
		this.dateTime = f.dateTime;
		myIDFlight = ++IDFlight;
		setSeats();
	}

	public flights(Scanner s)  throws FileNotFoundException{
		
		airline = s.next();
		flightNum = s.next();
		eStatus = status.valueOf(s.next());
		arriving = s.nextBoolean();
		s.nextLine(); // Clean Buffer
		city = s.nextLine();
		String dateTime = s.nextLine();
		String dateTimeArr[] = dateTime.split("-");
		int year = Integer.parseInt(dateTimeArr[0]);
		int month = Integer.parseInt(dateTimeArr[1]);
		String tempArr[] = dateTimeArr[2].split("T");
		int day = Integer.parseInt(tempArr[0]);
		String timeArr[] = tempArr[1].split(":");
		int hour = Integer.parseInt(timeArr[0]);
		int minutes = Integer.parseInt(timeArr[1]);
		this.dateTime = LocalDateTime.of(year, month, day, hour, minutes);
		myIDFlight = ++IDFlight;
		
		Scanner s2 = new Scanner(new File("SeatsList"+flightNum+".txt"));
		int sizeOfArr = s2.nextInt();
		int num = 0;
		this.allSeats = new Seat[sizeOfArr];
		s2.nextLine();
		while (s2.hasNext()) {
			this.allSeats[num++] = new Seat(s2, this);
		}
		s2.close();
	}
	
	public void setSeatToPerson(Seat s, Person p) throws FileNotFoundException{
		for (int i = 0; i < allSeats.length; i++) {
			if (allSeats[i] != null) {
				if (s.getSeatID().equals(allSeats[i].getSeatID())) {
					allSeats[i].setMyPerson(p);
					allSeats[i].getMyPerson().setMySeat(allSeats[i]);
				}
			}
		}
	}
	
	public void save(PrintWriter pw) throws FileNotFoundException{
		pw.println(airline);
		pw.println(flightNum);
		pw.println(eStatus);
		pw.println(arriving);
		pw.println(city);
		pw.println(dateTime);
		
		PrintWriter pw2 = new PrintWriter(new File("SeatsList"+flightNum+".txt"));
		pw2.println(allSeats.length);
		for (int i = 0; i < allSeats.length; i++) {
			if (allSeats[i] != null) {
				allSeats[i].save(pw2);
			}
		}
		pw2.close();
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public status geteStatus() {
		return eStatus;
	}

	public void seteStatus(status eStatus) {
		this.eStatus = eStatus;
	}
	
	public Seat getSeatBySeadID(String SeatID) {
		for (int i = 0; i < allSeats.length; i++) {
			if (allSeats[i] != null) {
				if (SeatID.equals(allSeats[i].getSeatID())) {
					return allSeats[i];
				}
			}
		}
		return null;
	}
	
	private void setSeats() {
		int numOfSeats = 0;
		for (int i = 0; i < 5 || numOfSeats == allSeats.length; i++) {
			for (int j = 1; j < 8; j++) {
				String seatID = "Line: "+i+" Row: "+j;
				this.allSeats[numOfSeats++] = new Seat(seatID, this, null);
			}
		}
	}
	
	public void getAllFreeSeats() {
		for (int i = 0; i < allSeats.length; i++) {
			if (allSeats[i] != null) {
				if (allSeats[i].getfreeSeat()) { // true -> free seat
					System.out.println(allSeats[i].getSeatID());
				}
			}
		}
	}

	public String toString() {
		String s = "";
		if (arriving) {
			s += airline + " flight: " + flightNum + " from " + city + " arriving at: " +  dateTime + "\n";
		}
		else {
			s += airline + " flight: " + flightNum + " to " + city + " departing at: " +  dateTime + "\n";
		}
		return s;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		flights other = (flights) obj;
		if (airline == null) {
			if (other.airline != null)
				return false;
		} else if (!airline.equals(other.airline))
			return false;
		if (arriving != other.arriving)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (eStatus != other.eStatus)
			return false;
		if (flightNum == null) {
			if (other.flightNum != null)
				return false;
		} else if (!flightNum.equals(other.flightNum))
			return false;
		return true;
	}
}
