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

import java.util.Comparator;

/**
 * This class uses to sort the number of the itemCode
 * @author yenhu
 *
 */
public class SortedCom implements Comparator<FoodItem> {

	@Override
	public int compare(FoodItem first, FoodItem second) {
		return first.getItemCode() - second.getItemCode();
	}
}
