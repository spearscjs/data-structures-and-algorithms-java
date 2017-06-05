/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 10/09/16 10:00PM
***********************************************************************
* TITLE:				Main
* PROGRAM DESCRIPTION: Main for printer methods 
***********************************************************************/

package driver;
//Imports *********************************************************************
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import model.Printer;

public class Main {
	
	public static void main(String[] args) {
		
		//Declaration & Initialization*****************************************
		//User Initialized
		Scanner scanner = new Scanner(System.in);
		boolean validInput = false; //false, used for each user initialization
		int numberOfPrinters; 
		//printer info (size based on number of printer input)
		int[] maxPagesPerPrinter;
		int[] printersTotalJobs; //keeps tracks of each printers jobs based on index
		int[] printersPagesPrinted; //keeps track of total pages printed based on index
		double[] timeInMinutesPrinter; 
		int numberOfJobs = 0;
		
		//Printer Objects & Queue
		Queue<Integer> jobs = new LinkedList<Integer>();
		Printer[] printers = new Printer[0];
		int totalJobsForAll = 0;
		int totalPagesForAll = 0;
		double totalTimeForAllPrinters = 0;
		
		//Greeting/Intro ******************************************************
		System.out.println("Welcome to the Printer Time Calculator App\n");
		System.out.println("Instruction (IMPORTANT): this program calculates the time to" 
				+ " print a queue of pages. If multiple printers are used, boundaries" 
				+ " will be set for each. The first printer prints jobs with a number of \npages" 
				+ " less than the max first printer boundary. The second printer prints jobs" 
				+ " with jobs greater than the first printer but less than the second boundary." 
				+ "\nYou will be prompted to enter the number of printers and their boundaries.\n" 
				+ "Note: the first printer should take the least amount of pages"
				+ " printer 2 the second least, and so on. (i.e. ascending order)" 
				+ " if you do not do this the program will not work correctly.\n\n");
		
		//User Input **********************************************************
		//numberOfPrinters input **************************
		numberOfPrinters = 0; //keep compiler happy, update isValid boolean
		do {
			try
			{
				System.out.print("Enter the amount of printers to use: ");
				numberOfPrinters = scanner.nextInt();
				validInput = numberOfPrinters > 0;
				if(!validInput) {
					System.out.println("Error: invalid input. Please enter integer (1-3).");
				}
			}
			catch(InputMismatchException exception)
			{
			  System.out.println("Error: invalid input. Please enter a valid integer");
			} 
		} while(!validInput);
		
		//Update arrays to match number of printers
		printers = new Printer[numberOfPrinters];
		maxPagesPerPrinter = new int[numberOfPrinters];
		printersTotalJobs = new int[numberOfPrinters];
		printersPagesPrinted = new int[numberOfPrinters];
		timeInMinutesPrinter = new double[numberOfPrinters];
		//Initialize Printers
		for(int i= 0; i < printers.length; i++) {
			printers[i] = new Printer();
		}
		
		//maxPageNumberPrinter[i] input ***************************
		System.out.println("\nNote: When inputting page limits for printers, the first printer should" 
				+ " print the least amount, printer 2 the second least, and so on. (i.e. ascending order)" 
				+ " if you do not do this the program will not work correctly.\n");
		//fill maxPageNumberPrinter 
		for(int i = 0; i < numberOfPrinters; i++) {
			do {
				try
				{
					System.out.print("Enter the max pages for printer" + (i + 1) + ": ");
					maxPagesPerPrinter[i] = scanner.nextInt();  
					if(maxPagesPerPrinter[i] < 0) { //check for negatives
						System.out.println("ERROR: invalid input. Max pages for this printer cannot be negative.");
						validInput = false;
					}
					if(i != 0 && maxPagesPerPrinter[i] < maxPagesPerPrinter[i-1]) { //ensure maxPage for this printer is more than previous printer, not relevant if first printer
						System.out.println("ERROR: invalid input. Max pages for this printer must be more than previous.");
						validInput = false;
					}
					else {
						validInput = true;
					}
				}
				catch(InputMismatchException exception) //check for non int
				{
				  System.out.println("Error: invalid input. Please enter a valid integer");
				} 
			} while(!validInput);
		}
		System.out.println(); //newLine
		
		//numberOfJobs input ***************************
		do {
			try
			{
				System.out.print("Enter the amount of jobs to test (generates random pages for each): ");
				numberOfJobs = scanner.nextInt();
				validInput = numberOfJobs > 0;
				if(!validInput) {
					System.out.println("Error: invalid input. Please enter integer (1-3).");
				}
			}
			catch(InputMismatchException exception)
			{
			  System.out.println("Error: invalid input. Please enter a valid integer");
			} 
		} while(!validInput);
		
		scanner.close(); //Done with input
		
		//Fill Jobs ***********************************************************
		Random randNum = new Random();
		for(int i = 0; i < numberOfJobs; i++) {
			jobs.offer(randNum.nextInt(maxPagesPerPrinter[maxPagesPerPrinter.length-1]) + 1); //random numbers between (last index of maxPagesPerPrinter) since the printers only print that or less
		}
		
		//Calculate Time ******************************************************
		
		//Output JobQueue
		System.out.println("Jobs list (based on # of pages)\n" + jobs + "\n");
		//Fill each printQueues
		int numberOfPages;
		boolean foundPrinter = false;
		while(jobs.size() > 0) 
		{
			foundPrinter = false;
			numberOfPages = jobs.poll();
			int i = 0; //reset index for each numberOfPages
			while(i < printers.length && !foundPrinter) {
				if(maxPagesPerPrinter[i] >= numberOfPages){
					printers[i].addToQueue(numberOfPages);
					printersTotalJobs[i]++;
					printersPagesPrinted[i] += numberOfPages;
					totalPagesForAll += numberOfPages;
					totalJobsForAll ++;
					System.out.println("Printer" + (i+1) + " received job: " + numberOfPages + " pages");
					foundPrinter = true;
				}
				else i++;
			}
		}
		
		//Print Jobs **********************************************************
		//declare & initialize printTimes, print from queue for each printer 
		for(int i = 0; i < numberOfPrinters; i++) {
			timeInMinutesPrinter[i] = printers[i].timeToPrintQueue();
			totalTimeForAllPrinters += timeInMinutesPrinter[i];
			System.out.println("\n\nPRINTER" + (i+1) + " INFO______________________________________________________________");
			System.out.printf("Printer%d total jobs: %d",i+1, printersTotalJobs[i]);
			System.out.printf("\nPrinter%d pages printed: %d",i+1, printersPagesPrinted[i]);
			System.out.printf("\nPrinter%d time to print: %.2f seconds (%.2f minutes)",i+1, Printer.minutesToSeconds(timeInMinutesPrinter[i]), timeInMinutesPrinter[i]);
		}
		//output all printer info
		System.out.println("\n\nALL PRINTER INFO____________________________________________________________");
		System.out.println("Total Jobs For All Printers: " + totalJobsForAll);
		System.out.println("Total Pages Printed For All Printers: " + totalPagesForAll);
		System.out.printf("Total Time Taken To Print All Pages: %.2f minutes (%.2f seconds)", totalTimeForAllPrinters, Printer.minutesToSeconds(totalTimeForAllPrinters));
	}

}