/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 11/20/2016
***********************************************************************
* TITLE:				MergeFile
* PROGRAM DESCRIPTION:  MergeFile class contains various methods for sorting files
***********************************************************************/
package algs;

//imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
 
public class MergeFile {
	
	//Merge Method **************************************************************************************
	/** merge : sorts files by sorting in increments/runs and outputting each sorted run
	 * 		    to either outFile1 or outFile2 (alternate). Increase runSize and 	
	 * 		    repeat until runSize is equal to amount of lines in the file, then 
	 * 		    outputs sorted array to original file. File merge sort.
	 * @param String fileName
	 * @param String outFile1
	 * @param String outFile2
	 * @param int runSize
	 */
	public static void merge(String fileName, String outFile1, String outFile2, int runSize) {
		//get number of lines in original file
		int numberOfLines = MergeFile.split(fileName, outFile1, outFile2, runSize);
		//get each file in increments/runs, merging each sorted run into original file
		//repeats until run is equal to lines in file - sorts entire file
		while(runSize < numberOfLines) {
			MergeFile.mergeFiles(fileName, outFile1, outFile2, runSize); 
			MergeFile.split(fileName, outFile1, outFile2, runSize); //out original file into 2 out files 
			runSize *= 2; //run now has twice as much data due to merge
			System.out.println("Finished run size " + runSize + "\n");
		}
	}	
	
	//Helpers ********************************************************************************************
	/** merge: merges two sorted files together in increments of linesPerRun, outputs merged 
	 * 		   data to outFile    
	 * @param String outFile
	 * @param String sorted1File
	 * @param String sorted2File
	 * @param int linesPerRun
	 * @return boolean successfulSort
	 */
	public static boolean mergeFiles(String outFile, String sorted1File, String sorted2File, int linesPerRun) {
		// Scanner for file input
		Scanner file1Scan = null;
		Scanner file2Scan = null;
		try {
			file1Scan = new Scanner(new FileInputStream(sorted1File));
			file2Scan = new Scanner(new FileInputStream(sorted2File));
		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			return false;
		}
		//clear files
		MergeFile.outputToFile(new String[0], outFile); //clear file
		//Storage for runs (to be merged and printed to file)
		String[] file1Run;
		String[] file2Run;
		String[] merged;
		MergeFile.outputToFile(new String[0], outFile, true);
		//get run from each, merge, output to file, repeat until files end
		while(file1Scan.hasNextLine() && file2Scan.hasNextLine()) {
			file1Run = MergeFile.getRun(file1Scan, sorted1File, linesPerRun);
			file2Run = MergeFile.getRun(file2Scan, sorted2File, linesPerRun);
			merged = Sort.mergeSorted(file1Run, file2Run);
			System.out.println("Merged files in sorted increments of " + linesPerRun);
			MergeFile.outputToFile(merged, outFile, true);
			System.out.println("Outputted size " + merged.length + " array to " + outFile);
		}
		//Output remaining values
		while(file1Scan.hasNextLine()) {
			file1Run = MergeFile.getRun(file1Scan, sorted1File, linesPerRun);
			MergeFile.outputToFile(file1Run, outFile, true);
			System.out.println("Outputted rest of file1: size " + file1Run.length + " array to " + outFile);
		}
		while(file2Scan.hasNextLine()) {
			file2Run = MergeFile.getRun(file2Scan, sorted1File, linesPerRun);
			MergeFile.outputToFile(file2Run, outFile, true);
			System.out.println("Outputted rest of file 2: size " + file2Run.length + " array to " + outFile);
		}
		System.out.println(outFile + " successfully sorted.");
		return true;
	}
	
	/** split : outputs each run of file to either  
	 * @param String fileName
	 * @param int linesPerRun
	 * @return int linesInFile //returns total lines in file
	 */
	public static int split(String fileName, String outFile1, String outFile2, int linesPerRun) {
		int numberOfLines = 0;
		// PrintWriter for file output
		PrintWriter file1Writer = null;
		PrintWriter file2Writer = null;
		try {
			file1Writer = new PrintWriter(new FileOutputStream(outFile1, true));
			file2Writer = new PrintWriter(new FileOutputStream(outFile2, true));
			//clear files
			new PrintWriter(outFile1).print("");
			new PrintWriter(outFile2).print("");
		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			return -1;
		}
		// Scanner for file input
		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			return -1;
		}
		// Fill run[] with number of linesPerRun, alternate output of each run to file1 and file2
		String[] run = null;
		boolean outputToFile1 = true; //start outputting to file1, alternate with file2
		while(scan.hasNextLine()) {
			run = MergeFile.getRun(scan, fileName, linesPerRun); //get run
			numberOfLines += run.length; // track lines in file for return
			Sort.merge(run); //sort run
			//output to file 1 or file 2 (alternate)
			if(outputToFile1 == true) {
				for(String record : run) {
					file1Writer.println(record);
				}
				outputToFile1 = false;
			}
			else {
				for(String record : run) {
					file2Writer.println(record);
				}
				outputToFile1 = true;
			}
		}
		//close streams
		file1Writer.close();
		file2Writer.close();
		return numberOfLines;
		
	}
	
	/** getRun : reads in linesPerRun lines in file and stores in array, returns array 
	 * 			 size of linesPerRun or lines left in file. 
	 * @param Scanner scan
	 * @param String fileName
	 * @param int linesPerRun
	 * @return String[] run
	 */
	public static String[] getRun(Scanner scan, String fileName, int linesPerRun) {
		if(!scan.hasNextLine()) {
			return null;
		}
		//Hold each line of file into array
		String[] run = new String[linesPerRun];
		//Scan file for number of linesPerRun, store in run 
		String temp; //used to ignore blank lines
		for(int i = 0; i < linesPerRun; i++) {
			if(scan.hasNextLine()) {
				temp = scan.nextLine();
				if(temp != null && temp.length() > 0) { //ignore blank lines
					run[i] = temp;
				}	
			}
		}
		//Rid of null values at the end if file doesn't fill run[], remainder = remainingLines%numPerRun
		if(!scan.hasNextLine()) {
			ArrayList<String> remainder = new ArrayList<String>();
			//add all non null values to remainder
			for(String record : run) {
				if(record != null && record.length() > 0) { //ignore null and blank values
					remainder.add(record);
				}
			}
			//convert to String[]
			run = new String[remainder.size()];
			run = remainder.toArray(run);
		}
		//Sort run
		Sort.merge(run);
		return run;
	}

	//Output Methods ************************************************************************************
	/** outputToFile : outputs array to file, overwrites - doesn't append 
	 * @param T[] data
	 * @param String fileName
	 * @return boolean successful
	 */
	public static <T> boolean outputToFile(T[] data, String fileName) {
		// PrintWriter for file output
		PrintWriter fileWriter = null;
		try {
			fileWriter = new PrintWriter(new FileOutputStream(fileName));
		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			return false;
		}
		//Output each item to a new line in file
		for(T item : data) {
			fileWriter.println(item.toString());
		}
		//close stream	
		fileWriter.close();
		return true;
	}
	
	/** outputToFile : outputs array to file, appending
	 * @param T[] data
	 * @param String fileName
	 * @return boolean successful
	 */
	public static <T> boolean outputToFile(T[] data, String fileName, boolean append) {
		// PrintWriter for file output
		PrintWriter fileWriterAppend = null;
		try {
			fileWriterAppend = new PrintWriter(new FileOutputStream(fileName, true));
		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			return false;
		}
		if(data.length == 0) {
			fileWriterAppend.print("");
		}
		//Output each item to new line in file
		for(T item : data) {
			fileWriterAppend.println(item.toString());
		}
		//close stream	
		fileWriterAppend.close();
		return true;
	}
}