package class1;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class flights {

	public enum status {landing, unconclusive, early, late, onTime};

	protected String airline;
	protected String flightNum;
	protected status eStatus;
	protected boolean arriving;
	protected String city;
	protected LocalDateTime dateTime;


	public flights(String airline, String flightNum, LocalDateTime dateTime, status eStatus, boolean arriving, String city) {
		this.airline = airline;
		this.flightNum = flightNum;
		this.eStatus = eStatus;
		this.arriving = arriving;
		this.city = city;
		this.dateTime = dateTime;
	}

	public flights(Scanner s) {
		System.out.println("Please enter airline");
		this.airline = s.nextLine();
		System.out.println("Please enter flight number");
		this.flightNum = s.nextLine();
		System.out.println("Flight status? ( landing, unconclusive, early, late, onTime )");
		this.eStatus = eStatus.valueOf(s.nextLine());
		System.out.println("arrivaing-> true, else -> false");
		this.arriving = Boolean.valueOf(s.nextLine());
		System.out.println("Please enter city");
		this.city = s.nextLine();
		s.findInLine("(\\d\\d)\\.(\\d\\d)\\. (\\d\\d):(\\d\\d)");
		try {
			MatchResult mr = s.match();
			int month = Integer.parseInt(mr.group(2));
			int day = Integer.parseInt(mr.group(1));
			int hour = Integer.parseInt(mr.group(3));
			int minute = Integer.parseInt(mr.group(4));
			LocalDateTime start = LocalDateTime.of(2015, month, day, hour, minute);
			System.out.println(start);
		} catch (IllegalStateException e) {
			System.err.println("Invalid input!");
		}
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

}
