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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class provides the information of the products that inherited.
 * @author yenhu
 *
 */
public class FoodItem {
	/**
	 * Item code
	 */
	protected int itemCode;
	/**
	 * Item name
	 */
	protected String itemName;
	/**
	 * Item price
	 */
	private float itemPrice;
	/**
	 * Item Quantity 
	 */
	protected int itemQuantityInStock;
	/**
	 * Item cost
	 */
	private float itemCost;
	
	/**
	 * Default value
	 */
	public FoodItem(){
	}
	
	/**
	 * Declare value for subclass inherited.
	 * @param code - Code of product
	 * @param name - Name of product
	 * @param price - Sales price of product
	 * @param num - Quantity of product
	 * @param cost - Cost of product
	 */
	public FoodItem(int code, String name, float price, int num, float cost){
		this.itemCode = code;
		this.itemName = name;
		this.itemPrice = price;
		this.itemQuantityInStock = num;
		this.itemCost = cost;
	}
	
	/**
	 * Display all of value when case 2 option is active.
	 */
	public String toString() {
		return String.format("Item: %d %s %d price: $%.2f cost: $%.2f ", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost );
	}
	
	/**
	 * This method uses to add the quantity number 
	 * @param amount - Parameter uses for adding index in array.
	 * @return true
	 */
	public boolean updateItem (int amount) {
		if(amount < 0) {
			return false;
		}
		
		itemQuantityInStock += amount;
		return true;
		
	}
	
	/**
	 * This method checks if the value is already existed
	 * @param item - Check if its exists.
	 * @return item.itemCode == this.itemCode
	 */
	public boolean isEqual(FoodItem item) {
		return item.itemCode == this.itemCode;
	}
	
	/**
	 * This method displays the question if each of product has been chosen.
	 * @param scanner - Read from Scanner class to input the value
	 * @param fromFile - Check from Scanner if it is true
	 * @return true
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {	
	while(true) {	
		try {
			// Product code
			System.out.println("Enter the code for the item: ");
			int code = scanner.nextInt();
			
			// Check if it exists
			boolean exists = false;
				
			for(FoodItem item : Inventory.inventory) {
				if (item.getItemCode() == code) {
					exists = true;
					break;
				}
			}
	
			if (exists) {
				System.out.println("Item code already exists.");
				exists = false;
				continue;	
			}
				
			else {
				itemCode = code;
			}
			
			// Name of product
			System.out.println("Enter name for the item:");
			scanner.nextLine(); // Create "space" when input
			itemName = scanner.nextLine();
		
		while(fromFile) {
			try {
				// Quantity of product
				System.out.println("Enter the quantity for the item: ");
				int quantity = scanner.nextInt(); // Cannot use the "itemQuantityInStock" because it will plus by itself (updateItem method)
				
				if(!updateItem(quantity)) {
					System.out.println("Invalid input\n");
					continue; // continue to type the quantity if its negative
				}
				break;
			}catch(InputMismatchException e) {
				System.out.print("Invalid input \n");
				scanner.nextLine();
				continue; // continue to type the quantity if its negative
			}
		}
		
		while(fromFile) {
			try {
				// Cost of product
				System.out.println("Enter the cost of the item: ");
				itemCost = scanner.nextFloat();
					if(itemCost < 0) { // check if its negative
						System.out.println("Invalid input");
						continue; // continue to type the cost if its negative
					}
				break;
			}catch(InputMismatchException e) {
				System.out.print("Invalid input \n");
				scanner.nextLine();
				continue; // continue to type the cost if its negative
			}
		}
			
		while(fromFile) {
			try {
				// Sales price
				System.out.println("Enter the sales price of the item: ");
				itemPrice = scanner.nextFloat();
					if(itemPrice < 0) { // check if its negative
						System.out.println("Invalid input");
						continue; // continue to type the sales price if its negative
					}
				break;
		}catch(InputMismatchException e) {
			System.out.print("Invalid input \n");
			scanner.nextLine();
			continue; // continue to type the sales price if its negative
			}
		}
		return true;
		
		}catch(InputMismatchException e) {
			System.out.println("Please enter a valid value");
			scanner.nextLine();
		}
	}
}
	
	/**
	 * This class uses to check the itemCode if its correct
	 * @param scanner - Read from Scanner class to input the value.
	 * @return true/false
	 */
	public boolean inputCode(Scanner scanner) {
		if (scanner.hasNextInt()) {
			itemCode = scanner.nextInt();
			return true;
		}
		
		else {
			scanner.nextInt();
			return false;
		}
	}
	
	/**
	 * This method uses to get the item code
	 * @return itemCode
	 */
	public int getItemCode() {
		return itemCode;
	}
	
	/**
	 * This method uses to display the memory of the array inside the file
	 * @param writer - Write inside the file
	 */
	public void outputItem (Formatter writer) {
		writer.format("%d%n"
				+ "%s%n"
				+ "%d%n"
				+ "%.2f%n"
				+ "%.2f%n", getItemCode(),itemName, itemQuantityInStock, itemCost, itemPrice);
	}
}