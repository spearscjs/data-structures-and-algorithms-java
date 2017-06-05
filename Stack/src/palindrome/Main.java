package palindrome;

import model.ListStack;

public class Main {

	//Constants *************************************************************************************
	//Stack Test *****************************************
	private static final int stack1_SIZE = 10;
	//Palindrome Test ************************************
	private static String PALINDROME_EVEN1 = "redder";
	private static String PALINDROME_EVEN_SPACED = "red der";
	private static String PALINDROME_ODD1 = "redivider";
	private static String PALINDROME_ODD_SPACED = "redi v ider";
	private static String PALINDROME_CAPITALIZED= "Noon";
	private static String PALINDROME_SENTENCE = "Sore was I ere I saw Eros";
	private static String SINGLE_CHAR = "a";
	private static String BLANK_STRING = "";
	private static String NON_PALINDROME1 = "adf hjd vg sfjhsv";
	private static String NON_PALINDROME2 = "word";
	private static String NON_PALINDROME3 = "ac";
	
	
	public static void main(String[] args) {
		
		//STACK TEST ***************************************************************
		ListStack<Integer> stack1 = new ListStack<Integer>();
		
		//isEmpty
		System.out.println("Empty (true): " + stack1.isEmpty());
		
		//push
		stack1.push(10);
		System.out.println("Empty (false): " + stack1.isEmpty());
		
		//peek
		System.out.println(stack1);
		
		//pop
		stack1.pop();
		System.out.println("Empty (true): " + stack1.isEmpty());
		
		//clear test
		stack1.clear();
		System.out.println(stack1);
		
		//equals
		//true
		System.out.println("\nFill 2 identical stacks");
		ListStack<Integer> stack2 = new ListStack<Integer>();
		for(int i = 0; i < 10; i++) {
			stack1.push(i);
			stack2.push(i);
		}
		System.out.println(stack1 + "\n" + stack2 + " size: " + stack2.getSize());
		System.out.println("EQUALS (TRUE): " + stack1.equals(stack2));
		//false
		stack1.pop();
		System.out.println("EQUALS (FALSE): " + stack1.equals(stack2));
		
		
		
		//PALINDROME TEST ************************************************************
		stack1.clear();
		stack2.clear();
		
		//Fill stack1s
		for(int i = 0; i < stack1_SIZE; i++) {
			stack1.push(i);
			stack2.push(i);
		}
		
		//TEST ALGORITHM FOR CHECK PALINDROME METHODS 
		//Palindrome stack1
		String oddPalindrome = "redivider";
		String evenPalindrome = "noon";
		String nonPalindrome = "word";
		ListStack<Character> oddPstack1 = new ListStack<Character>();
		ListStack<Character> evenPstack1 = new ListStack<Character>();
		ListStack<Character> nonPstack1 = new ListStack<Character>();
		
		//Fill Palindrome stacks
		System.out.println("\nFILL PALINDROME stacks");
		//odd
		for(int i = 0; i < oddPalindrome.length(); i++) {
			oddPstack1.push(oddPalindrome.charAt(i));
		}
		//even
		for(int i = 0; i < evenPalindrome.length(); i++) {
			evenPstack1.push(evenPalindrome.charAt(i));
		}
		//non
		for(int i = 0; i < nonPalindrome.length(); i++) {
			nonPstack1.push(nonPalindrome.charAt(i));
		}
		System.out.println("odd: " + oddPstack1 + "\neven: " + evenPstack1 + "\nnon: " + nonPstack1);
		
		
		
		//Palindrome Tests ***********************************************************************************
		//Check Palindrome
		System.out.println("\n\nPALINDROME TESTS");
		System.out.println("ODD(TRUE) : " + CheckPalindrome.checkPalindrome(oddPstack1));
		System.out.println("EVEN(TRUE) : " + CheckPalindrome.checkPalindrome(evenPstack1));
		System.out.println("NONE(FALSE) : " + CheckPalindrome.checkPalindrome(nonPstack1));
		System.out.println(PALINDROME_EVEN1 + ": " + CheckPalindrome.checkPalindrome(PALINDROME_EVEN1));
		System.out.println(PALINDROME_EVEN_SPACED + ": " + CheckPalindrome.checkPalindrome(PALINDROME_EVEN_SPACED));
		System.out.println(PALINDROME_ODD1 + ": " + CheckPalindrome.checkPalindrome(PALINDROME_ODD1));
		System.out.println(PALINDROME_ODD_SPACED + ": " + CheckPalindrome.checkPalindrome(PALINDROME_ODD_SPACED));
		System.out.println(PALINDROME_CAPITALIZED + ": " + CheckPalindrome.checkPalindrome(PALINDROME_CAPITALIZED));
		System.out.println(PALINDROME_SENTENCE + ": " + CheckPalindrome.checkPalindrome(PALINDROME_SENTENCE));
		System.out.println(SINGLE_CHAR + ": " + CheckPalindrome.checkPalindrome(SINGLE_CHAR));
		
		//Non Palindrome Tests *******************************************************************************
		System.out.println("\nNON PALINDROME TESTS");
		System.out.println("(BLANK STRING (FALSE))" + BLANK_STRING + ": " + CheckPalindrome.checkPalindrome(BLANK_STRING));	
		System.out.println(NON_PALINDROME1 + ": " + CheckPalindrome.checkPalindrome(NON_PALINDROME1));
		System.out.println(NON_PALINDROME2 + ": " + CheckPalindrome.checkPalindrome(NON_PALINDROME2));
		System.out.println(NON_PALINDROME3 + ": " + CheckPalindrome.checkPalindrome(NON_PALINDROME3));
		
	}
	
}