/*
 * Shigemichi Matsuzaki
 * 2016/12/23
 * 
 * The class that represents one charger.
 *
 */


package my.ICCharger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ListIterator;

public class ShopCharger {
        private String name; // Maybe the place where this charger is placed
	private StudentCard insertedStudentCard;
	private static ArrayList<HashMap<String, String>> chargeHistoryList_;
        private static final String[] keys = {"userID", "diff", "balance", "date", "name"};
        private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        private static final int numberOfHistoryToShow = 5;
	
	public ShopCharger(String name) {
            this.name = name;
            insertedStudentCard = null;
            chargeHistoryList_ = new ArrayList();
	}

        public String getName() {
            return this.name;
        }
        
	// Charge the pre-paid card
	public int chargeMoney(int price) {
                Calendar cal = Calendar.getInstance();
		// negative value for withdrawal, purchasing
		if(insertedStudentCard == null) {
			// If a card isn't inserted yet
			System.out.println("Error: No card is inserted");
			return -1;
		} else if(price == 0) { // No sense
                    return -1;
                } else if(insertedStudentCard.charge(price, name, sdf.format(cal.getTime())) < 0) {
                        // If the payment exceeds the balance, an error occurs
			System.out.println("Error: Your balance is not enough (\\" 
		                        + insertedStudentCard.getBalance() + ")");
			return -1;
		}
		
		// Store only 5 dates.
		// If there are five, remove the oldest(on the top)
		if(chargeHistoryList_.size() == numberOfHistoryToShow) {
			chargeHistoryList_.remove(0);
		}
                
                // Create new hashmap for storing charge history
                HashMap<String, String> hm = new HashMap();
                hm.put(keys[0], insertedStudentCard.getID());
                hm.put(keys[1], sdf.format(cal.getTime()));
                hm.put(keys[2], Integer.toString(price));
                hm.put(keys[3], Integer.toString(insertedStudentCard.getBalance()));
                hm.put(keys[4], name);
                
		// Add new history to chargeHistoryList
		chargeHistoryList_.add(hm);
                
                System.out.println("Balance : \\" + insertedStudentCard.getBalance());
		
		return insertedStudentCard.getBalance();
	}
	
	// Print the account balance
	public void printAccountBalance() {
		System.out.println("Your account balance is \\" + 
							insertedStudentCard.getBalance());
	}
	
	// Insert a student card
	public boolean insertStudentCard(StudentCard cardToInsert) {
		// If another card has been inserted
		if(insertedStudentCard != null) {
			System.out.println("Error: A card is already inserted.");
			return false;
		}
		// Otherwise
		insertedStudentCard = cardToInsert;
		// Greeting is important
		System.out.println(name + ": Welcome, " + insertedStudentCard.getFirstName() + "!");
		return true;
	}
	
	// Remove a student card
	public boolean removeStudentCard() {
		// If there is no card to remove
		if(insertedStudentCard == null) {
			System.out.println("Error: No card is inserted");
			return false;
		}
		// Otherwise
		insertedStudentCard = null;
		// Greeting is important
		System.out.println("Bye");
		return true;
			
	}
	
	// Show charge history
	public void printChargeHistory(boolean isChronological) {
            System.out.println("\n===Charge History===");
            if(isChronological) {
                // Show the history in chronological order
                System.out.println("Old");
                chargeHistoryList_.stream().forEach((hm) -> {
                    System.out.println(hm.get(keys[0]) + " " + hm.get(keys[1])
                            + " " + hm.get(keys[2]) + " " + hm.get(keys[3]) + " " + hm.get(keys[4]));
                });
                System.out.println("New\n");
            } else {
                // Show the history in inverse-chronological order
                System.out.println("New");
                for(ListIterator it=chargeHistoryList_.listIterator(chargeHistoryList_.size()); it.hasPrevious();){
                    HashMap<String, String> hm = (HashMap<String, String>) it.previous();
                    System.out.println(hm.get(keys[0]) + " " + hm.get(keys[1])
                                       + " " + hm.get(keys[2]) + " " + hm.get(keys[3]) + " " + hm.get(keys[4]));
                }
                System.out.println("Old");
            }
	}
	
}
