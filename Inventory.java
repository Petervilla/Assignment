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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class uses to check the value and provides options to run
 * @author yenhu
 *
 */
public class Inventory {
	/**
	 * Array of FoodItem
	 */
	static ArrayList <FoodItem> inventory;

	/**
	 * Number of items
	 */
	@SuppressWarnings("unused")
	private int numItems;
	
	/**
	 * Default value
	 */
	Inventory(){
		inventory = new ArrayList<>();
		numItems = 0;
		
	}
	
	/**
	 * Display in case 2 of main method
	 */
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (FoodItem item : inventory) {	
			b.append(item.toString());
			b.append("\n");
		}
		return b.toString();
	}
	
	/**
	 * This method checks index of a FoodItem in the inventory array with the same itemCode as the FoodItem object in the parameter list
	 * @param item - Checks if item object equals to item parameter
	 * @return i
	 */
	public int alreadyExists(FoodItem item) {
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).isEqual(item)) { // if the code is the same as the input, it will return already exist
				return i;
			}
		}
		return -1; // Does not exist
	}
	
	/**
	 * This method adds an item to the inventory array 
	 * @param scanner - Read from Scanner class to input the value.
	 * @param fromFile - Check if inside the file has value or not
	 * @return true
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		System.out.println("Do you wish to add a fruit(f), vegetable(v), preserve(p)? ");
		String itemChoice;
		itemChoice = scanner.next();
	
		while(true) {
			switch(itemChoice) {
				case "f":
					Fruit fruit = new Fruit();
					fruit.addItem(scanner, true);
					
						if (alreadyExists(fruit) != -1) {
							System.out.println("Item code already exists. Cannot proceed to add... \n");
							continue;
						}
						
						else {
							inventory.add(fruit);
							numItems++;
							System.out.println("Fruit item added to the inventory. \n");
							return true;
						}
				  
				case "v":
					Vegetable ve = new Vegetable();
					ve.addItem(scanner, true);
					
						if (alreadyExists(ve) != -1) {
							System.out.println("Item code already exists");
							return false;
						}
						
						else {
							
							inventory.add(ve);
							numItems++;
							System.out.println("Vegetable item added to the inventory. \n");
							return true;
						}
				
				case "p":
					Preserve pre = new Preserve();
					pre.addItem(scanner, true);
						if (alreadyExists(pre) != -1) {
							System.out.println("Item code already exists");
							return false;
						}
						
						else {
							inventory.add(pre);
							numItems++;
							System.out.println("Preserve item added to the inventory. \n");
							return true;
						}
					
				default:
					System.out.println("Invalid choice! Please choose the options f, v, p: ");
					itemChoice = scanner.next();
					break;
			}
		}
	}
	
	/**
	 * Reads in an itemCode to update and quantity to update by and updates that item by the input quantity in the inventory array.
	 * @param scanner - Read from Scanner class to input the value.
	 * @param buyOrSell - Checks if the value is true(buy) or false(sell).
	 * @return true/false
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		System.out.println("Enter the code for the item: ");
		int itemCode = scanner.nextInt();
		int value = -1;
		
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).itemCode == itemCode) {
				value = i;
				break;
			}
		}
		
		if (value == -1) {
			System.out.println("Item with code "+itemCode+" does not found.");
			System.out.println("Code not found in inventory...");
			return false;
		}
		
		
		FoodItem item = inventory.get(value);
		
		if (buyOrSell) { // For buying
			System.out.println("Enter the valid quantity to buy: ");
			int itemQuantity = scanner.nextInt();
			if (itemQuantity > 0) {
				item.itemQuantityInStock += itemQuantity; // Add the amount if user want to purchase.
			}
			
			else {
				System.out.println("Invalid input! Please enter a negative number.");
				return false;
			}
		}
		
		else { // For selling
			System.out.println("Enter the valid quantity to sell: ");
			int itemQuantity = scanner.nextInt();
			if (itemQuantity > 0) {
				if(item.itemQuantityInStock >= itemQuantity) { // if Quantity in Stock are bigger that the amount to sell, it can sell.
					item.itemQuantityInStock -= itemQuantity;
				}
				
				else {
					System.out.println("Insufficient stock in inventory...");
		            return false;
				}
			}
			else {
				System.out.println("Invalid input! Please enter a negative number.");
				return false;
			}
		}
		
		System.out.println("Quantity updated successfully.");
		return true;
	}
	
	/**
	 * This method searches the value inside the array list
	 * @param scanner - Receive the input
	 */
	public void searchForItem(Scanner scanner) {
		try {
			System.out.println("Enter the code for the item: ");
			scanner.nextLine();
			int itemCode = scanner.nextInt();
			int value = -1;
			
			for (int i = 0; i < inventory.size(); i++) {
				if(inventory.get(i).itemCode == itemCode) {
					value = i;
					break;
				}
			}
			
			if(value == -1) {
				System.out.println("Code not found in inventory... \n");
			}
			
			else {
				System.out.println(inventory.get(value) + "\n");
			}
		} catch(InputMismatchException e) {
			System.out.println("Error... Cannot proceed with your request.");
			System.out.println("Please enter a valid integer. \n");
			scanner.nextLine();
		}
	}
	
	/**
	 * This method saves the memory of the array inside the file
	 * @param scanner - Receive the input
	 */
	public void saveToFile(Scanner scanner){	
		try {
			System.out.println("Enter the filename to save to:");
			String text = scanner.next();
			File file = new File(text);
			
			// If the file does not exist, it will create a new file
			if(!file.exists()) {
				file.createNewFile();
			}
			
			else {
				file.delete(); // delete the information inside the file
			}
			
			try (Formatter writer = new Formatter(new FileWriter(file, true))) {
				for (FoodItem item : inventory) {
					if (item instanceof Fruit) {
						writer.format("f%n");
					}
					
					else if (item instanceof Vegetable) {
						writer.format("v%n");
					}
					
					else {
						writer.format("p%n");
					}
					item.outputItem(writer);
				} // end for loop
			} // end second try	
				System.out.println("File " +text+ " has been saved.\n");
		
		} catch(IOException e) {
			System.out.println("Error Encountered while saving the file, aborting...");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method uses to check the information of the file
	 * @param scanner - Check through the information inside the file
	 * @return new Fruit, Vegetable, Preserve
	 */
	public FoodItem createFile(Scanner scanner) {
		// Set up in order to check the value inside the file
		String itemType = scanner.nextLine().trim();
		int code = Integer.parseInt(scanner.nextLine().trim());
		String name = scanner.nextLine().trim();
		int quantity = Integer.parseInt(scanner.nextLine().trim());
		float cost = Float.parseFloat(scanner.nextLine().trim());
		float price = Float.parseFloat(scanner.nextLine().trim());
		
		if (itemType.equals("f")) {
			String supName = scanner.nextLine().trim();
			return new Fruit(code, name, price, quantity, cost, supName);
		}
		
		else if (itemType.equals("p")) {
			int size = Integer.parseInt(scanner.nextLine().trim());
			return new Preserve(code, name, price, quantity, cost, size);
		}
		
		else {
			String supName = scanner.nextLine().trim();
			return new Vegetable(code, name, price, quantity, cost, supName);
		}
	}
	
	/**
	 * This method uses to read the file and add it into the array list
	 * @param scanner - Read the information and add into the array list
	 */
	public void readFromFile(Scanner scanner) {
		System.out.println("Enter the filename to read from:");
		String text = scanner.next();
		File file = new File(text);
		boolean exists = false;
		
		if (!file.exists()) {
				System.out.println("Error...Could not found " +text+ " file.\n");
				return;
			}
		
		try (Scanner fileScanner = new Scanner(file)){	
			while (fileScanner.hasNextLine()) {
				FoodItem food = createFile(fileScanner);		
				
				if(alreadyExists(food) != -1) {
					System.out.println("Item code already exists.");
					System.out.println("Error Encountered while reading the file, aborting... \n");
					exists = true;
					break;
				}
				
				if (!exists) {
					inventory.add(food);
					exists = false;
				}		
			}
			
			if (!exists) {
				System.out.println("File " +text+ " has been read.\n");
			}
			
		} catch (IOException e){
			System.out.println("Error Encountered while reading the file, aborting... \n");
			e.printStackTrace();
			
		}
	}
}
