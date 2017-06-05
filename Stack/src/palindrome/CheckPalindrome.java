/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 10/02/16 5:00PM
***********************************************************************
* TITLE:				CheckPalindrome
* PROGRAM DESCRIPTION: class used to check if given char/string is a palindrome
***********************************************************************/
package palindrome;

import model.ListStack;

public class CheckPalindrome {

	/** checkPalindrome: method used to check if inputted stack chars are palindrome
	 * @param LinkedList<Character> stack
	 * @return boolean isPalindrome
	 */
	public static boolean checkPalindrome(ListStack<Character> stack) {
		//false if empty or null parameter
		if(stack == null || stack.isEmpty()) { 
			return false;
		}
		//true if single character
		else if(stack.getSize() == 1) {
			return true;
		}
		else {
			//declaration
			ListStack<Character> half = new ListStack<Character>(); //takes half of stack data
			//fill half by popping stack
			for(int i = 0; i < stack.getSize(); i++) { //since stack size is decrementing, will stop halfway
				half.push(stack.pop());
			}
			//check for odd number of chars (middle doesn't matter in palindrome)
			if(half.getSize() != stack.getSize()) {
				half.pop();
			}
			//compare and return
			return stack.equals(half);
		}
	}
	
	/** checkPalindrome: method used to check if inputted stack chars are palindrome. 
						overload: (converts string param to stack, then puts stack in 
	 					checkPalindrome(ListStack<Character> stack) method 
	 * @param String str
	 * @return boolean isPalindrome
	 */
	//overload (converts string param to stack, then puts stack in checkPalindrome(ListStack<Character> stack) method 
	public static boolean checkPalindrome(String str) {
		str = str.toLowerCase(); //ensures capitalization doesn't matter
		if(str == null || str.equals("")) {
			return false;
		}
		else if(str.length() == 1) {
			return true;
		}
		else {
			//Create and fill Stack
			ListStack<Character> stack = new ListStack<Character>();
			for(int i = 0; i < str.length(); i++) {
				stack.push(str.charAt(i)); 
			}
			//Use other checkPalindrome that takes in a ListStack<Character> parameter
			return CheckPalindrome.checkPalindrome(stack);
		}
		
		
	}
	
	
}