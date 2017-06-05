package controller;

//Imports ***********************************************************************************
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.MorseBinaryTree;

import java.util.InputMismatchException;

public class Controller {
	
	//Keyboard Accessors ********************************************************************
	/** getKeyboardLine : returns nextLine from keyboard input
	 * @param n/a
	 * @return String input
	 */
	public static String getKeyboardLine() {
		Scanner inStream = new Scanner(System.in);
		String input = inStream.nextLine();
		return input;
	}
	
	/** getKeyboardInt : returns nextInt from keyboard input (error checks with recursion)
	 * @param n/a
	 * @return Integer input
	 */
	public static Integer getKeyboardInt() {
		//Initialization / Declaration
		Scanner inStream = new Scanner(System.in);
		int input;
		
		//Retrieve Valid Input
		try {
			input = inStream.nextInt();
			return input;
		}
		catch(InputMismatchException nfE) {
			System.out.println("ERROR: please enter a valid integer");
			getKeyboardInt(); //call again if invalid integer called
		}
		
		return null; //keep compiler happy 
	}
	
	/** getKeyboardInt : returns nextInt from keyboard input between min and max (inclusive) 
	 * @param int minValue
	 * @param int maxValue
	 * @return Integer validInput
	 */
	public static Integer getKeyboardInt(int minValue, int maxValue) {
		Scanner inStream = new Scanner(System.in);
		return getKeyboardInt(minValue, maxValue, inStream);
	}
	
	/** getKeyboardInt : returns nextInt from keyboard input between min and max (inclusive) (error checks with recursion)
	 * @param int minValue
	 * @param int maxValue
	 * @param Scanner inStream
	 * @return int validInput
	 */
	public static int getKeyboardInt(int minValue, int maxValue, Scanner inStream) {
		//Initialization / Declaration
		int input;
		boolean validInput = false;
		//Retrieve Input
		try {
			System.out.print("Input: ");
			input = inStream.nextInt(); //get input (fails then catch Mismatch below)
			validInput = input >= minValue && input <= maxValue; //check bounds
			if(!validInput) { //check for non valid integers
				inStream.reset();
				System.out.println("ERROR: please enter a valid integer (" + minValue + "-" + maxValue + ")");
				return getKeyboardInt(minValue, maxValue, inStream); //try again if invalid
			}
			return input; //return valid input 
		}
		catch(InputMismatchException nfE) {
			inStream = new Scanner(System.in); //
			System.out.println("ERROR: please enter a valid integer (" + minValue + "-" + maxValue + ")");
			return getKeyboardInt(minValue, maxValue, inStream);
		}
	}
	
	/** repeatMenu : prompts user to repeat menu or not, iterates until y or n entered for first char
	 * @param n/a	 
	 * @return boolean repeat
	 */
	public static boolean repeatMenu() {
		String input;
		boolean validInput;
		do {
			System.out.println("\nWould you like to go to the main menu (y or n)?");
			System.out.print("Input: ");
			input = Controller.getKeyboardLine();
			validInput = input.toLowerCase().charAt(0) == 'y' || input.toLowerCase().charAt(0) == 'n';
			if(!validInput) {
				System.out.println("ERROR: invalid input. Please enter either y for yes or n for no\n");
			}
		}while(!validInput);
		if(input.charAt(0) == 'y') {
			return true;
		}
		else return false;
	}
	
	//Tree Methods **************************************************************************
	//NOTE: ALL METHODS ASSUME A VALID TREE IS ENTERED *********************
	
	//Encoding Methods ************************
	/** convertTextToMorse : encodes given string input into morse using / as delimeter for spaces and a space between each character
	* @param MorseBinaryTree validTree
	* @return String encodedMessage
	*/
	public static String convertTextToMorse(MorseBinaryTree validTree) {
		String input = "";
		//Prompt / Input
		System.out.println("\nPlease enter a sentence or letter to be converted. (ignores invalid characters not a-z)");
		System.out.print("Input: ");
		input = getKeyboardLine();
		return MorseBinaryTree.encode(validTree, input);
	}
	
	/** convertTextToMorse : encodes given string input from file into morse using / as delimeter for spaces and a space between each character
	* @param MorseBinaryTree validTree
	* @return String encodedMessage
	*/
	public static String convertTextFileToMorse(MorseBinaryTree validTree) {
		//Declaration / Initialization
		StringBuilder sb = new StringBuilder();
		String fileName = Controller.getKeyboardLine();
		String line;
		
		//Attempt to read file given from user
		try{
			Scanner scanner = new Scanner(new FileInputStream(fileName));
			while(scanner.hasNextLine()) { //go through each line
				line = scanner.nextLine();
				sb.append(MorseBinaryTree.encode(validTree, line) + "\n"); //encode each line, add to sb 
			}
		}
		catch(FileNotFoundException fnfE) {
			System.out.println("ERROR: file not found.");
			return null;
		}
		return sb.toString(); //return all encoded data stored in sb
	}
	
	//Decoding Methods ******************************
	//NOTE: methods return null if bad data is given
	
	/** convertMorseFileToText : decodes given string input from file, returns string representation
	* @param MorseBinaryTree validTree
	* @return String sentenceText
	*/
	public static String convertMorseFileToText(MorseBinaryTree validTree) {
		//Initialization / Declaration
		StringBuilder sb = new StringBuilder();
		String fileName = Controller.getKeyboardLine(); //obtain fileName from user
		String line;
		String conversion;
		//Attempt to read file given from user
		try{
			Scanner scanner = new Scanner(new FileInputStream(fileName));
			while(scanner.hasNextLine()) { //traverse file
				line = scanner.nextLine(); //store each line
				conversion = MorseBinaryTree.decode(validTree, line); //convert each line
				if(conversion == null) {
					conversion = "Error: invalid morse code."; //if invalid code print this instead
				}
				sb.append(conversion + "\n"); //append each line conversion to sb
			}
		}
		catch(FileNotFoundException fnfE) {
			System.out.println("ERROR: file not found.");
			return null; 
		}
		return sb.toString();
	}
	
	/** convertMorseToText : decodes given string input from user, returns string representation
	* @param MorseBinaryTree validTree
	* @return String sentenceText
	*/
	public static String convertMorseToText(MorseBinaryTree validTree) {
		System.out.println("\nPlease enter morse code to be converted (use space between letters and / between words).");
		System.out.print("Input: ");
		String input = Controller.getKeyboardLine();
		return MorseBinaryTree.decode(validTree, input);
	}
	
	//Other Useful Tree Methods ********************
	/** outputTable : output table for letter to morse conversion based on given a-z values in MorseBinaryTree 
	* @param MorseBinaryTree validTree
	* @return String table
	*/
	public static void outputTable(MorseBinaryTree validTree) {
		//Initialization / Declaration
		StringBuilder sb = new StringBuilder();
		char current;
		
		//Iterate through Ascii values
		for(int i = 97; i <= 123; i++) { //go through a-z characters
			current = (char) i; 
			sb.append(current + " " + MorseBinaryTree.encode(validTree, "" + current) + "\n"); //add letter morseCode
		}
		sb.setLength(sb.length() - 5); //gets rid of ending value (cleans table up)
		System.out.println(sb);
	}
	
	
}
