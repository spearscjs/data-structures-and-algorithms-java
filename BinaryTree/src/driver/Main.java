package driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.Controller;
import model.MorseBinaryTree;
import model.MorseBinaryTree.MorseData;

public class Main {

	public static void main(String[] args) {
		
		//Declaration / Initialization ******************************************************
		Scanner inStream = new Scanner(System.in);
		int input;
		String fileName = "morseCode.txt";
		MorseBinaryTree<MorseData> morseTree = new MorseBinaryTree<MorseData>();
		boolean repeatMenu = false;
		//Input Morse Code File *************************************************************
		MorseBinaryTree.fillMorse(morseTree, fileName);
		
		//UserMenu ************************************************************************** 	
		do {
			//Main Menu Output
			System.out.println("\nMAIN MENU (Enter number for option)\n" 
					+ "1. Output morse code table \n"
					+ "2. Convert morse code to text \n" 
					+ "3. Convert text to morse code \n"
					+ "4. Convert text from file to morse code (ignores invalid text non a-z) \n"
					+ "5. Convert morse code from file to text \n"
					+ "6. Exit Program");
			//Get User Input
			input = Controller.getKeyboardInt(1, 6);
			//Execute inputed command
			switch(input) {
				case 1: 
					System.out.println("\nMorse Code Table");
					Controller.outputTable(morseTree);
					System.out.println();
					break;
				case 2:
					String code = Controller.convertMorseToText(morseTree);
					if(code == null) {
						System.out.println("ERROR: invalid morse code entered.");
					}
					else System.out.println("Conversion: " + code);
					break;
				case 3: 
					String text = Controller.convertTextToMorse(morseTree);
					System.out.println("Conversion: " + text);
					break;
				case 4: 
					System.out.println("\nPlease enter valid file name.");
					System.out.print("Input: ");
					String fileMorse = Controller.convertTextFileToMorse(morseTree);
					if(fileMorse != null) {
						System.out.println("Conversion\n" + fileMorse);
					}
					break;
				case 5: 
					System.out.println("\nPlease enter valid file name.");
					System.out.print("Input: ");
					String fileText = Controller.convertMorseFileToText(morseTree);
					if(fileText != null) {
						System.out.println("Conversion\n" + fileText);
					}
					break;
				case 6 :
					//FareWell
					System.out.println("\nThank you for using the morse code conversion application!!!");
					//Close Stream 
					inStream.close();
					System.exit(0);
					break;
					
			}
		}while(true); // will always loop until exit is called in main menu
	}
}
