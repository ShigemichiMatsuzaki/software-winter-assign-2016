/*
 * Shigemichi Matsuzaki
 * 2016/12/23
 * 
 * The class that represents student card
 * which has the functionality of pre-paid card.
 *
 */


package my.ICCharger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class StudentCard {
	private String ID; // Student ID
	private String firstName; // First name of the student
	private String lastName; //  Last name of the student
	private int balance; // Balance of pre-paid money
	private final ArrayList<HashMap<String, String>> chargeHistoryList_;
        private static final String[] keys = {"date", "diff", "balance", "name"};
	private static ArrayList<StudentCard> studentCardList_ 
						= new ArrayList(); // The number of issued cards
        private static final int numberOfHistoryToShow = 5;
	
	public StudentCard(String ID, String firstName, String lastName) {
		this.ID   = ID;
		this.firstName = firstName;
		this.lastName  = lastName;
		this.balance = 0; // Wish it were 10,000
                this.chargeHistoryList_ = new ArrayList();
		StudentCard.studentCardList_.add(this);
	}
	
	// Getter and setter for each field
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public static ArrayList<StudentCard> getStudentCardList() {
		return StudentCard.studentCardList_;
	}
	
	// Add up the given price to the balance
	public int charge(int price, String nameOfCharger, String date) {
		if(balance + price < 0 || price == 0) {
                    return -1;
                }
		this.balance += price;

		// Store only 5 dates.
		// If there are five, remove the oldest(on the top)
		if(chargeHistoryList_.size() == numberOfHistoryToShow) {
			chargeHistoryList_.remove(0);
		}                
                
                // Create new hashmap for storing charge history
                HashMap<String, String> hm = new HashMap();
                hm.put(keys[0], date);
                hm.put(keys[1], Integer.toString(price));
                hm.put(keys[2], Integer.toString(balance));
                hm.put(keys[3], nameOfCharger);
                
		// Add new history to chargeHistoryList
		chargeHistoryList_.add(hm);
		
		return this.balance;
	}
	
	// Return the full name of the student which consists of first name and last name
	public String getFullName() {
		return this.lastName + " " + this.firstName;
	}
        
        // Show charge history
	public void printChargeHistory(boolean isChronological) {
            System.out.println("\n===Charge History : " + getFullName() + "===");
            if(isChronological) {
                // Show the history in chronological order
                System.out.println("Old");
                chargeHistoryList_.stream().forEach((hm) -> {
                    System.out.println(hm.get(keys[0]) + " " + hm.get(keys[1])
                            + " " + hm.get(keys[2]) + " " + hm.get(keys[3]));
                });
                System.out.println("New\n");
            } else {
                // Show the history in inverse-chronological order
                System.out.println("New");
                for(ListIterator it=chargeHistoryList_.listIterator(chargeHistoryList_.size()); it.hasPrevious();){
                    HashMap<String, String> hm = (HashMap<String, String>) it.previous();
                    System.out.println(hm.get(keys[0]) + " " + hm.get(keys[1])
                                       + " " + hm.get(keys[2]) + " " + hm.get(keys[3]));
                }
                System.out.println("Old");
            }
	}
}
