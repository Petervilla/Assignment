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
 * This class uses to add the information of the preserves
 * @author yenhu
 *
 */
public class Preserve extends FoodItem {
	/** 
	 * Size of jar
	 */
	private int jarSize;
	
	/**
	 * Default value
	 */
	public Preserve() {
	}
	
	/**
	 * Subclass inherited from the super class
	 * @param code - Code of product
	 * @param name - Name of product
	 * @param price - Sales price of product
	 * @param num - Quantity of product
	 * @param cost - Cost of product
	 * @param size - Size of product (per mL)
	 */
	public Preserve(int code, String name, float price, int num, float cost, int size){
		super(code, name, price, num, cost);
		this.jarSize = size;
	}
	
	/**
	 * Add String value at the last of the super class
	 */
	public String toString() {
		return super.toString() +"size: "+ jarSize+" mL";
	}
	
	/**
	 * Add size of the jar(in mL) by using scanner class
	 */
	public boolean addItem (Scanner scanner, boolean fromFile) {
		boolean result = super.addItem(scanner, fromFile);
		if(result) {
			System.out.println("Enter the size of the jar in millilitres: ");
			jarSize = scanner.nextInt();
		}
		return result;
	}
	
	/**
	 * This method uses to display the size of the jar in the file
	 */
	public void outputItem (Formatter writer) {
		super.outputItem(writer);
		writer.format("%d%n", jarSize);
	}
}
