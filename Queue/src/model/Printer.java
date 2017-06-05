/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 10/09/16 10:00PM
***********************************************************************
* TITLE:				Printer
* PROGRAM DESCRIPTION: Printer object used to calculate runtime of printers given queue of pages  
***********************************************************************/
package model;

//Imports
import java.util.Queue;
import java.util.LinkedList;

public class Printer {
	
	//Constants ***************************************************************
	private static int PAGES_PER_MINUTE = 10;
	
	//Instance Variables ******************************************************
	private Queue<Integer> printQueue; //linked list queue used for runtime efficiency o(1) insertion/removal methods, int represents numOfPages
	private boolean isFree; //is the printer busy(already printing)?
	
	//Constructors ************************************************************
	/** Printer: default constructor
	 * @param  n/a
	 * @return n/a 
	 */
	public Printer() {
		this.setPrintQueue(new LinkedList<Integer>());
		this.setIsFree(true);
	}
	
	/** Printer: default constructor
	 * @param  Queue<Integer> printQueue
	 * @return n/a 
	 */
	public Printer(Queue<Integer> printQueue) {
		this.setPrintQueue(printQueue);
		this.setIsFree(true);
	}	
	
	/** Printer: default constructor
	 * @param  Queue<Integer> printQueue, boolean isFree
	 * @return n/a 
	 */
	public Printer(Queue<Integer> printQueue, boolean isFree) {
		this.setPrintQueue(printQueue);
		this.setIsFree(isFree);
	}
	
	//Accessors/Getters *******************************************************
	/** setPrintQueue: setter for printQueue instance var
	 * @param  Queue<Integer> printQueue
	 * @return n/a 
	 */
	public void setPrintQueue(Queue<Integer> printQueue) {
		this.printQueue = printQueue;
	}
	
	/** isFree: setter for isFree instance var
	 * @param  boolean isFree
	 * @return n/a 
	 */
	public void setIsFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	//Mutators/Setters ********************************************************
	/** getPrintQueue: getter for printQueue instance var
	 * @param  n/a
	 * @return Queue<Integer> printQueue
	 */
	public Queue<Integer> getPrintQueue() {
		return this.printQueue;
	}
	
	/** getIsFree: getter for isFree instance var
	 * @param  n/a
	 * @return boolean isFree
	 */
	public boolean getIsFree() {
		return this.isFree;
	}
	
	//Instance printQueue Methods *********************************************
	/** addToQueue: add numOfPages to queue
	 * @param  int numOfPages
	 * @return n/a
	 */
	public void addToQueue(int numOfPages) {
		this.printQueue.offer(numOfPages);
	}
	
	/** removeToQueue: remove numOfPages from queue
	 * @param  n/a
	 * @return n/a
	 */
	public void removeFromQueue() {
		if(this.printQueue != null && this.printQueue.size() > 0) {
			this.printQueue.remove();
		}
	}
	
	//Print Methods ***********************************************************
	//outputs time in minutes
	/** timeToPrint: calculates time to numberOfPages
	 * @param  int numberOfPages
	 * @return double time
	 */
	public static double timeToPrint(int numberOfPages) {
		if(numberOfPages <= 0) {
			return 0;
		}
		else {
			return (double)numberOfPages / PAGES_PER_MINUTE;
		}
	}
	
	//outputs time in minutes
	/** timeToPrintHead: calculates time to numberOfPages in head of list
	 * @param  n/a
	 * @return double time
	 */
	public double timeToPrintHead() {
		return Printer.timeToPrint(printQueue.poll());
	}
	
	//outputs time in minutes
	/** timeToPrintQueue: calculates time to numberOfPages in queue
	 * @param  n/a
	 * @return double time
	 */
	public double timeToPrintQueue() {
		//base case
		if(this.printQueue == null || this.printQueue.size() == 0) {
			return 0;
		}
		//rid of invalid numOfPages values
		else if(this.printQueue.peek() <= 0) {
			this.printQueue.remove();
			return timeToPrintQueue();
		}
		//recursive case
		else {
			 return timeToPrintHead() + timeToPrintQueue(); //i.e. numOfPages/pages per minute = (time taken in minutes) then remove and recall method
		}
	}

	//Conversion Helper Methods ***********************************************
	/** minutesToSeconds: converts minutes double 
	 * @param  double minutes
	 * @return double seconds
	 */
	public static double minutesToSeconds(double minutes) {
		return minutes * 60;
	}
	
	//Other Required Methods **************************************************
	/** toString: converts instances to readable form  
	 * @param  n/a
	 * @return n/a
	 */
	public String toString() {
		return "" + this.getPrintQueue();
	}
}