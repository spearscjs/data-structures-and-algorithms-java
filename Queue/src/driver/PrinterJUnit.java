/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 10/09/16 10:00PM
***********************************************************************
* TITLE:				A6JUnitTesting
* PROGRAM DESCRIPTION: Unit testing for printer methods 
***********************************************************************/
package driver;

//Imports *********************
//junit
import org.junit.Assert;
import org.junit.Test;

//other
import java.util.LinkedList;
import model.Printer;

public class PrinterJUnit {
	
	/***************************************************************************
	 * Printer Methods *********************************************************
	 ***************************************************************************/
	//timeToPrint(int numberOfPages) *******************************
	@Test
	public void testPositiveNumberOfPages() {
		Assert.assertEquals(1, (int)(Printer.timeToPrint(10) * 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	@Test
	public void testNegativeNumberOfPages() {
		Assert.assertEquals(0, (int)(Printer.timeToPrint(-10) * 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	@Test
	public void testZeroNumberOfPages() {
		Assert.assertEquals(0, (int)(Printer.timeToPrint(0) * 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	
	//timeToPrintHead() **************************************************
	public void testPositiveHead(){
		Printer printer = new Printer(new LinkedList<Integer>());
		printer.getPrintQueue().offer(10);
		printer.getPrintQueue().offer(50);
		Assert.assertEquals(1, (int)(printer.timeToPrintHead() * 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	
	public void testNegativeHead(){
		Printer printer = new Printer(new LinkedList<Integer>());
		printer.getPrintQueue().offer(-10);
		printer.getPrintQueue().offer(50);
		Assert.assertEquals(0, (int)(printer.timeToPrintHead() * 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	
	public void testEmptyHead(){
		Printer printer = new Printer(new LinkedList<Integer>());
		Assert.assertEquals(0, (int)(printer.timeToPrintHead() * 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	//timeToPrintQueue(int numOfPages) **********************************
	@Test
	public void testOnePrintJob(){
		Printer printer = new Printer(new LinkedList<Integer>());
		printer.getPrintQueue().offer(10);
		Assert.assertEquals(1, (int)(printer.timeToPrintQueue() * 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	@Test
	public void testMultiplePrintJobs(){
		Printer printer = new Printer(new LinkedList<Integer>());
		printer.getPrintQueue().offer(10);
		printer.getPrintQueue().offer(10);
		printer.getPrintQueue().offer(10);
		printer.getPrintQueue().offer(10);
		printer.getPrintQueue().offer(10);
		printer.getPrintQueue().offer(10);
		printer.getPrintQueue().offer(10);
		printer.getPrintQueue().offer(10);
		Assert.assertEquals(8, (int)(printer.timeToPrintQueue() * 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	@Test 
	public void testWithZeroAndBelowPageNumbers() {
		Printer printer = new Printer(new LinkedList<Integer>());
		printer.getPrintQueue().offer(30);
		printer.getPrintQueue().offer(50);
		printer.getPrintQueue().offer(90);
		printer.getPrintQueue().offer(5);
		printer.getPrintQueue().offer(5);
		printer.getPrintQueue().offer(0);
		printer.getPrintQueue().offer(-10);
		printer.getPrintQueue().offer(0);
		Assert.assertEquals(18, (int)(printer.timeToPrintQueue() * 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	@Test
	public void testWithEmptyQueue() {
		Printer printer = new Printer(); //default sets printQueue instance to null
		Assert.assertEquals(0, (int)(printer.timeToPrintQueue()));
	}
	
	//minutesToSeconds ****************************************************
	@Test
	public void testWithNonDecimalPositive(){
		Assert.assertEquals(3600, (int)(Printer.minutesToSeconds(60))); 
	}
	@Test
	public void testWithNonDecimalNegative(){
		Assert.assertEquals(-3600, (int)(Printer.minutesToSeconds(-60))); //why does this break if I do *1000000/1000000 or more?
	}
	@Test
	public void testWithPositiveDecimal(){
		Assert.assertEquals(90, (int)(Printer.minutesToSeconds(1.5)* 1000000) / 1000000); //double is not allowed to be compared in Junit so was converted to int, 1000000 is accuracy of comparison
	}
	

}