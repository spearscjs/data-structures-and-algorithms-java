/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 10/09/16 10:00PM
***********************************************************************
* TITLE:				ChangeDispenser
* PROGRAM DESCRIPTION:  ChangeDispenser used to calculate amount of coins that can make up a whole cent amount
***********************************************************************/
package model;

//Import *******************************************************************************************************************************
import java.util.Arrays;

public class ChangeDispenser {		
	
	//Instance Variables ***************************************************************************************************************
	private int numberOfCombinations; //the reason the methods aren't static 
	
	//Constructors *********************************************************************************************************************
	/** ChangeDispenser: default constructor
	 * @param  n/a
	 * @return n/a 
	 */
	public ChangeDispenser() {
		this.setNumberOfCombinations(0);
	}
	
	//Mutators *************************************************************************************************************************
	/** setNumberOfCombinations: sets numberOfCombinations instance variable
	 * @param  int num
	 * @return boolean isPositive
	 */
	public boolean setNumberOfCombinations(int num) {
		if(num >= 0) {
			return true;
		}
		else return false;
	}
	
	//Accessors ************************************************************************************************************************
	/** getNumberOfCombinations: sets numberOfCombinations instance variable
	 * @param  n/a
	 * @return int numberOfCombinations
	 */
	public int getNumberOfCombinations() {
		return this.numberOfCombinations;
	}
	
	//Find Solutions Methods ***********************************************************************************************************
	/** findAllSolutionsStatic: calculate all solutions given denominations and cent/number value.
	 * @param  int index - current index for coinSolution set
	 * @param  int[] denominations - denominations used in dividing of cents
	 * @param  int cents - value to find possibilities of denominations
	 * @param  int[] coinsSolution - solution set for given denomination
	 * @return void //prints to screen
	 */
    public static void findAllSolutionsStatic(int index, int[] denominations, int cents, int[] coinsSolution) {
    	//base cases
    	if(cents < 0) {
    		return;
    	}
	    if(cents == 0) { //found combination
	        System.out.println(Arrays.toString(coinsSolution));
	        return; 
	    }
	    if(index == denominations.length) { //iterated through possible denominations
	    	return;            
	    }
	    //recursive
	    int current = denominations[index]; //current denomination used
	    for(int i = 0; i <= cents/current; i++) { //iterate through, find combinations, stop when no more current fit
	        coinsSolution[index] = i;
	        findAllSolutionsStatic(index+1, denominations, cents - i*current, coinsSolution);
	    }
	}
    
    //Non static needed for JTest
	/** findAllSolutions: calculate all solutions given denominations and cent/number value.
	 * @param  int index - current index for coinSolution set
	 * @param  int[] denominations - denominations used in dividing of cents
	 * @param  int cents - value to find possibilities of denominations
	 * @param  int[] coinsSolution - solution set for given denomination
	 * @return void //prints to screen
	 */
    public void findAllSolutions(int index, int[] denominations, int cents, int[] coinsSolution) {
    	//base cases
    	if(cents < 0) {
    		return;
    	}
	    if(cents == 0) { //found combination
	        System.out.println(Arrays.toString(coinsSolution));
	        this.numberOfCombinations++; //reason method isn't static, increment avoid set method call
	        return; 
	    }
	    if(index == denominations.length) { //iterated through possible denominations
	    	return;            
	    }
	    //recursive
	    int current = denominations[index]; //current denomination used
	    for(int i = 0; i <= cents/current; i++) { //iterate through, find combinations, stop when no more current fit
	        coinsSolution[index] = i;
	        findAllSolutions(index+1, denominations, cents - i*current, coinsSolution);
	    }
	}
    
    //Helpers **************************************************************************************************************************
    /** findAllSolutions: calculate all solutions given denominations and cent/number value. Simplified, used for user input. 
	 * @param  int[] denominations - denominations used in dividing of cents
	 * @param  int cents - value to find possibilities of denominations
	 * @return int numberOfCombinations //prints to screen
	 */
    public int findAllSolutions(int cents, int[] denominations) {
    	int tempNumCombos = 0;
		int[] coinSolution = new int[denominations.length]; //same length, shows all denominations
    	findAllSolutions(0, denominations, cents, coinSolution); //start at index 
    	tempNumCombos = this.getNumberOfCombinations();
    	this.setNumberOfCombinations(0); //reset counter
    	return tempNumCombos;
    }
    
    /** findAllSolutionsStatic: calculate all solutions given denominations and cent/number value. Simplified, used for user input.
	 * @param  int[] denominations - denominations used in dividing of cents
	 * @param  int cents - value to find possibilities of denominations
	 * @return void //prints to screen
	 */
    public static void findAllSolutionsStatic(int cents, int[] denominations) {
		int[] coinSolution = new int[denominations.length]; //same length, shows all denominations
    	findAllSolutionsStatic(0, denominations, cents, coinSolution); //start at index 0
    }
    
}