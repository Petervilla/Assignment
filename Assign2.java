/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Yen Huynh
 * Student Number: 041 068 712
 * Section #: 303
 * Course: CST8130 - Data Structures
 * Professor: George Kriger
 * 
 */

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class uses to run all of the methods
 * @author yenhu
 *
 */
public class Assign2 {
	/**
	 * This methods use to display the menu
	 */
	public static void displayMenu() {
		System.out.println("Please select one of the following:\r\n"
				+ "1: Add Item to Inventory\r\n"
				+ "2: Display Current Inventory\r\n"
				+ "3: Buy Item(s)\r\n"
				+ "4: Sell Item(s)\r\n"
				+ "5: Search for Item\r\n"
				+ "6: Save Inventory to File\r\n"
				+ "7: Read Inventory from File\r\n"
				+ "8: To Exit");
		}
	
	/**
	 * Main method - uses to executes all of the methods
	 * @param args - argument
	 */
	public static void main (String[] args) {
		Inventory i = new Inventory();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
		displayMenu();
		int choice = 0;
		
		try {
			choice = scanner.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Error! Please enter a valid integer");
			scanner.nextLine();
		}
		
		switch(choice) {
			case 1:
			while(true) {
				i.addItem(scanner, true);
				break;
			}
			
			break;
			
			case 2:
				System.out.println("Inventory:");
				Collections.sort(Inventory.inventory, new SortedCom());
				System.out.println(i);
				break;
				
			case 3:
				if (Inventory.inventory.isEmpty()) {
					System.out.println("Error...could not buy item \n");
					break;
				}
				
				boolean buyStock = i.updateQuantity(scanner, true);
				
				if (buyStock) {
					System.out.println("Your order has been purchased.\n");
				}
				
				else {
					System.out.println("Error...could not buy item \n");
				}
				break;
				
			case 4:
				if (Inventory.inventory.isEmpty()) {
					System.out.println("Error...could not sell item \n");
					break;
				}
				
				boolean sellStock = i.updateQuantity(scanner, false);
				
				if (sellStock) {
					System.out.println("Your order has been sold. \n");
				}
				
				else {
					System.out.println("Error...could not sell item \n");
				}
				break;
				
			case 5:
				i.searchForItem(scanner);
				break;
				
			case 6:
				i.saveToFile(scanner);
				break;
			
			case 7:
				i.readFromFile(scanner);
				break;
				
			case 8:
				System.out.println("Program by Yen Huynh");
				System.out.println("Exiting...");
				scanner.close();
				System.exit(0);
				
			default:
				System.out.println("Please choose the options between 1 - 8 \n");
				break;
			}
		}
	}
}
