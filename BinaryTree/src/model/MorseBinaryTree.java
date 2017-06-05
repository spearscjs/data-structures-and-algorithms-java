package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseBinaryTree<MorseData> extends model.BinaryTree { //E to String?

	//File Input ****************************************************************************
	//wrapper method
	/** fillMorse : wrapper for recursive fillMorse method
	* @param String fileName
	* @return n/a
	*/
	public static void fillMorse(MorseBinaryTree tree, String fileName) {
		//Initialize / Prepare Wrapper
		Scanner scan;
		int index = 0;
		tree.root = new Node("start");
		Node<MorseData> current = tree.root;
		
		try {
			scan = new Scanner(new FileInputStream(fileName));
			MorseBinaryTree.fillMorse(scan, fileName, tree, index, current, "", "");
		}
		catch(FileNotFoundException fnfE) {
			fnfE.printStackTrace();
		}
		
	}

	//assumes wrapper is called
	/** fillMorse : inputs / creates tree from text file containing morse code
	* 				representations for each letter. Morse code must be in 
	* 				order of levels : etianmsurwdkgohvflpjbxcyzq. The formatting for the 
	* 				text file should be charName morseRepresentation\n. (using * 
	* 				for dot and - for dash.) .txt file should be as follows:  
	* 				First line of file below. Last line must be "end"
					e *
					t -
					i **
					a *-
					n -*
					...
					... 
					z --**
					q --*-	
					end		 
	*				Last line of file above. (last line must contain end)
	*				Note: each * represents a left on the tree and each - 
	*					  represents a right on the tree
	* @param Scanner scan //assumes scan is linked to valid FileInputStream(file)
	* @param BinaryTree tree 
	* @return n/a
	*/
	public static void fillMorse(Scanner scan, String fileName, MorseBinaryTree tree, int index, Node<MorseData> current, String letter, String morseCode) {
		//ONCE METHOD IS CALLED AGAIN SCAN FOR NEW DATA
		//scan .txt file
		if(index == 0 && scan.hasNextLine()) {
			letter = scan.next();
		}
		if(index == 0 && scan.hasNextLine()) {
			morseCode = scan.next(); //used in recursion, 
		}
		
		//TRAVERSAL
		//traversal stops one early to avoid end value
		//traverse left if *
		if(index < morseCode.length() - 1 && morseCode.charAt(index) == '*') {
			current = current.left;
			MorseBinaryTree.fillMorse(scan, fileName, tree, ++index, current, letter, morseCode);
		}
		//traverse right if -
		else if (index < morseCode.length() - 1 && morseCode.charAt(index) == '-'){
			current = current.right;
			MorseBinaryTree.fillMorse(scan, fileName, tree, ++index, current, letter, morseCode);
		}
		
		//ADDING TO TREE (FOUND CORRECT PLACE)
		//add to current.left or current.right based on last morseCode index
		if(scan.hasNextLine()) {
			if(index == morseCode.length() - 1 && morseCode.charAt(index) == '*'){
				current.left = new Node(new MorseData(letter, morseCode));
			}
			
			if(index == morseCode.length() - 1 && morseCode.charAt(index) == '-'){
				current.right = new Node(new MorseData(letter, morseCode));
			}
			//once left or right is set, recall method starting at beginning index for new data
			MorseBinaryTree.fillMorse(scan, fileName, tree, 0, tree.root, letter, morseCode);
		}
	}
	
	
	//Decoding Methods **********************************************************************
	/** decode : decodes string message using morse tree parameter
	* @param MorseBinaryTree<MorseData> validTree 
	* @param String morseCode
	* @return String text
	*/
	public static String decode(MorseBinaryTree<MorseData> validTree, String morseCode) {
		//Initialization / Declaration 
		StringBuilder sb = new StringBuilder(); //build valid code one char at a time
		String[] binaryLetters = morseCode.split(" "); //break up letters into seperate strings
		
		//Go through each index of binaryLetters and convert
		for(String code : binaryLetters) {
			if(code.equals("/")) { // / used as delimiter between words, add space
				sb.append(" ");
			}
			else if(MorseBinaryTree.decodeLetter(validTree, code) == null) {
				return null; //invalid code entered
			}
			else 
				sb.append(MorseBinaryTree.decodeLetter(validTree, code)); //add translated value to sb
			}
		
		return sb.toString();
	}
	
	/** decodeLetter : wrapper for recursive decodeLetter method. //decodes one letter (return null if invalid morseCode given)
	* @param MorseBinaryTree<MorseData> validTree 
	* @param String morseCode
	* @return char letter
	*/
	public static Character decodeLetter(MorseBinaryTree morseTree, String morseCode) {
		return MorseBinaryTree.decodeLetter(morseTree, morseCode, morseTree.root);
	}
	
	/** decodeLetter : recursive decodeLetter method. //decodes one letter (return null if invalid morseCode given)
	* @param MorseBinaryTree<MorseData> validTree 
	* @param String morseCode
	* @return char letter
	*/
	public static Character decodeLetter(MorseBinaryTree morseTree, String morseCode, Node current) {
		//check for invalid path
		if(current == null) {
			return null;
		}
		//reached end, return current letter from tree
		if(morseCode.length() == 0) { 
			return current.toString().charAt(0); //convert letter to char
		}
		else{
			// if * go left
			if(morseCode.charAt(0) == '*') {
				current = current.left;
			}
			//if - go right
			else if(morseCode.charAt(0) == '-') {
				current = current.right;
			}
			//else invalid data
			else {
				return null;
			}
		}
		//keep returning and chunking away at morseCode string until empty
		return MorseBinaryTree.decodeLetter(morseTree, morseCode.substring(1), current); 
	}
	
	
	//Encoding Methods **********************************************************************
	/** encodeLetter : wrapper for recursive encodeLetter method. //encodes one letter (skips if invalid data given)
	* @param MorseBinaryTree<MorseData> validTree 
	* @param String target
	* @return String encodedLetter
	*/
	public static String encodeLetter(MorseBinaryTree<MorseData> morseTree, String target){
		StringBuilder sb = new StringBuilder();
		encodeLetter(morseTree, morseTree.root, target, sb);
		return sb.toString();
	}
	
	/** encodeLetter : recursive encodeLetter method. //encodes one letter (skips if invalid data given)
	* @param MorseBinaryTree<MorseData> validTree 	
	* @param Node<MorseData> current
	* @param String target
	* @param StringBuilder sb //wrapper uses to store as String
	* @return void
	*/
	public static void encodeLetter(MorseBinaryTree<MorseData> morseTree, Node<MorseData> current, String target, StringBuilder sb) {
		//search tree until the target letter is found
		//found target, add data to StringBuilder (used so String can be stored without return)
		if(current != null && current.compareTo(target) == 0) {
			sb.append(current.data.code);
		}
		//if it hasn't reached a dead end, keep traversing
		if(current != null) {
			encodeLetter(morseTree, current.left, target, sb);
			encodeLetter(morseTree, current.right, target, sb);
		}
	}
	
	/** encode : encodes given string value into morse using / as delimeter for spaces and a space between each character
	* @param MorseBinaryTree<MorseData> validTree 
	* @param String message
	* @return String encodedMessage
	*/
	public static String encode(MorseBinaryTree<MorseData> morseTree, String message) {
		String letter = "";
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < message.length(); i++) {
			letter = message.substring(i, i+1).toLowerCase();
			if(message.charAt(i) == ' ') {
				sb.append("/ "); //add delimiter between words
			}
			if("abcdefghijklmnopqrstuvwxyz".contains(letter)) {
				sb.append(encodeLetter(morseTree, letter));
				sb.append(" "); //adds space between letters
			}
			//skips invalid characters
		}
		return sb.toString();
	}
	
	//Note: MorseBinaryTree also has BinaryTree Methods and Variables
	
	/****************************************************************************************
	 * INNER CLASS: MorseData ***************************************************************
	 ****************************************************************************************/
	public static class MorseData implements Comparable {
		//Instance Variables ****************************************************************
		protected String letter;
		protected String code;
		
		//Constructors **********************************************************************
		/** MorseData : default constructor
		 * @param n/a
		 * @return n/a
		 */
		public MorseData() {
			letter = "";
			code = "";
		}
		
		/** MorseData : filled constructor
		 * @param String letter
		 * @param String code
		 * @return n/a
		 */
		public MorseData(String letter, String code) {
			this.letter = letter;
			this.code = code;
		}
		
		
		/** toString : returns letter variable (code is "hidden" behind in tree)
		 * @param n/a
		 * @return String letter
		 */
		public String toString() {
			return this.letter;
		}
		
		
		/** compareTo : compares 
		 * @param n/a
		 * @return String letter
		 */
		@Override
		public int compareTo(Object arg0) {
			String other = (String) arg0;
			return this.letter.compareTo(other); //only care about letters in tree
		}
	}
	
}
