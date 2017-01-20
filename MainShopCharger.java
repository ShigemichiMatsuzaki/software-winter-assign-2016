/*
 * Shigemichi Matsuzaki
 * 2016/12/23
 * 
 * The class that operates multiple ShopChargers.
 *
 */

package my.ICCharger;

public class MainShopCharger {

	public static void main(String[] args) {
		// There are two chargers in the school
		ShopCharger sc1 = new ShopCharger("Shop");
                ShopCharger sc2 = new ShopCharger("Canteen");
		
                // Generate new cards
		StudentCard card1 = new StudentCard("B163373", "Shigemichi", "Matsuzaki");
		StudentCard card2 = new StudentCard("B163374", "Known", "Un"); // Korean student

		
		// Charge money (An error will occur since no card is inserted)
		sc1.chargeMoney(1000);
		
		// Insert a card
		sc1.insertStudentCard(card1);
		
		// Try to charge again
		sc1.chargeMoney(1000);
		
                // Remove the card
                sc1.removeStudentCard();
                
                // Insert the card to another charger
                sc2.insertStudentCard(card1);
		// Use some money
		sc2.chargeMoney(-300);
		sc2.chargeMoney(1000);
		sc2.chargeMoney(1000);
		sc2.chargeMoney(1000);
		sc2.chargeMoney(-400);
		sc2.chargeMoney(1500);
		sc2.chargeMoney(000);
                // Show the history in both chronological and inversed order
                sc1.printChargeHistory(true);
                sc2.printChargeHistory(false);
                
                card1.printChargeHistory(true);
                card1.printChargeHistory(false);                
	}

}
