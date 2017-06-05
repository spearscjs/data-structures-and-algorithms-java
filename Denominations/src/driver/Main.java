/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 10/09/16 10:00PM
***********************************************************************
* TITLE:				Main
* PROGRAM DESCRIPTION:  Main used to drive ChangeDispenser methods
***********************************************************************/
package driver;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.ChangeDispenser;

public class Main {

	public static void main(String[] args) {
		
		//Declaration/Initialization ****************************************************************************
		String[] denominationNames = {"Penny", "Nickel", "Dime", "Quarter"};
		int[] usDenominations = {1, 5, 10, 25};
		//user input
		Scanner scanner = new Scanner(System.in);
		int amountInChange = -1;
		boolean validInput = false;
		
		//Introduction *******************************************************************************************
		System.out.println("Welcome to the Change App\n\n");
		System.out.println("Instruction: enter an amount in cents and this app will calculate the possible combinations of the given us denominations\n\n");
		
		System.out.print("FORMAT (AmountOf): [ ");
		for(String str : denominationNames) {
			 System.out.print(str + " ");
		}
		System.out.print("]\n\n");
		

		//User Input *********************************************************************************************
		do {
			try
			{
				System.out.print("Enter the amount of change (in cents): ");
				amountInChange = scanner.nextInt();
				validInput = amountInChange >= 0;
				if(!validInput) {
					System.out.println("Error: invalid input. Please enter positive number.");
				}
			}
			catch(InputMismatchException exception)
			{
			  System.out.println("Error: invalid input. Please enter a valid integer");
			} 
		} while(!validInput);
		
		//Print All Solutions ************************************************************************************
		System.out.println("\n\nPossibilities");
		ChangeDispenser.findAllSolutionsStatic(amountInChange, usDenominations); //non static tested in junit
		
	}
}