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

import java.util.Formatter;
import java.util.Scanner;

/**
 * This class uses to add the information of the fruits
 * @author yenhu
 *
 */
public class Fruit extends FoodItem {
	/**
	 * Orchard supplier name
	 */
	private String orchardName;
	
	/**
	 * Default value
	 */
	public Fruit() {
	}
	
	/**
	 * Subclass inherited from the super class
	 * @param code - Code of product
	 * @param name - Name of product
	 * @param price - Sales price of product
	 * @param num - Quantity of product
	 * @param cost - Cost of product
	 * @param supName - Supplier Name
	 */
	public Fruit(int code, String name, float price, int num, float cost, String supName  ){
		super(code, name, price, num, cost);
		this.orchardName = supName;
	}
	
	/**
	 * Add String value at the last of the super class
	 */
	public String toString() {
		return super.toString() + "orchard supplier: " +orchardName;
	}
	
	/**
	 * Add supplier name by using scanner class
	 */
	public boolean addItem (Scanner scanner, boolean fromFile) {
		boolean result = super.addItem(scanner, fromFile);
		if (result) {
			System.out.println("Enter the name of the orchard supplier: ");
			scanner.nextLine(); // Create "space" when input
			orchardName = scanner.nextLine();
		}
		return result;
	}
	
	/**
	 * This method uses to display the name of supplier in the file
	 */
	public void outputItem (Formatter writer) {
		super.outputItem(writer);
		writer.format("%s%n", orchardName);
	}
}
