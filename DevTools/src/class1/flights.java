package class1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

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

	public flights(Scanner s)  throws FileNotFoundException{
		airline = s.next();
		flightNum = s.next();
		eStatus = status.valueOf(s.next());
		arriving = s.nextBoolean();
		s.nextLine();
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
	}
	
	public void save(String fileName) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(new File(fileName));
		
		pw.println(airline);
		pw.println(flightNum);
		pw.println(eStatus);
		pw.println(arriving);
		pw.println(city);
		pw.println(dateTime);
	}
	
	public void save(PrintWriter pw) throws FileNotFoundException{
		pw.println(airline);
		pw.println(flightNum);
		pw.println(eStatus);
		pw.println(arriving);
		pw.println(city);
		pw.println(dateTime);
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
