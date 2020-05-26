package class1;

import java.io.PrintWriter;

public class Person {
	private Long creditCard;
	private String passPortID;
	private Seat mySeat;
	
	public Person(Long creditCard, String passPortID, Seat mySeat) {
		this.creditCard = creditCard;
		this.passPortID = passPortID;
		this.mySeat = mySeat;
	}

	public Person(Person myPerson) {
		if (myPerson != null) {
			this.creditCard = myPerson.getCreditCard();
			this.passPortID = myPerson.getPassPortID();
			this.mySeat = myPerson.getMySeat();
		}else {
			this.creditCard = null;
			this.passPortID = null;
			this.mySeat = null;
		}
	}

	public void setCreditCard(Long creditCard) {
		this.creditCard = creditCard;
	}

	public void setPassPortID(String passPortID) {
		this.passPortID = passPortID;
	}

	public void setMySeat(Seat mySeat) {
		this.mySeat = mySeat;
	}

	public Long getCreditCard() {
		return creditCard;
	}

	public String getPassPortID() {
		return passPortID;
	}

	public Seat getMySeat() {
		return mySeat;
	}

	@Override
	public String toString() {
		return "Person = " + passPortID  + (mySeat == null ? "" :  ", mySeat = " + mySeat.getSeatID() )+ "\n";
	}

	public void save(PrintWriter pw) {
		pw.println(passPortID);
		pw.println(creditCard);
		
	}
}
