package sideproject;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.PrintWriter;

public class ProgramIO{

	//CONSTANTS *******************************************************
	private static final int DEFAULT_LIST_SIZE = 25;
	private static final String DEFAULT_DELIMETER = " : ";
	
	//FILLING METHODS *************************************************
	public static ArrayList<Data> fillList(String fileName)
	{
		ArrayList<Data> dataList = new ArrayList<Data>(DEFAULT_LIST_SIZE);
		fileName = ProgramIO.ensureExtension(fileName); //checks for .txt(adds if not at the end)
		//File Import
		Scanner fileInput;
		fileInput = null; //keep compiler happy
		try{
			fileInput = new Scanner(new FileInputStream(fileName));
		}
		catch(FileNotFoundException fnfE)
		{
			System.out.println("ERROR: FILE NOT FOUND...Please try again");
			return  null;
		}
		//Mutation
		Data currentData;
		String current;
		boolean isDuplicate;
		isDuplicate = false;
	
		while(fileInput.hasNextLine()) // loops through entire file
		{
			current = fileInput.nextLine();	
			for(int i = 0; i < dataList.size() && !isDuplicate; i++) //loops entire list, stops if duplciate
			{
				currentData = dataList.get(i);	//gets Data at i (counter)
				if(current.equals(currentData.getName())) //if currentData name matches current file String
				{
					currentData.incrementFrequency();  //increments freq
					isDuplicate = true;				   //used to exit for loop
				}
			}
			if(!isDuplicate)
			{
				dataList.add(new Data(current)); //adds to list if no matched Data name 
			}
			isDuplicate = false;  //update
		}
		dataList.trimToSize();
		return dataList;
	}
	
	//BINARY IO *******************************************************
	public static void outputObjectsBinary(String fileName, ArrayList<Data> dataList)
    {
		fileName = ProgramIO.ensureExtension(fileName); //checks for .txt(adds if not at the end)
		try{
			ObjectOutputStream outputStream;
			
			outputStream = new ObjectOutputStream(new FileOutputStream(
					fileName));
			for(Data data : dataList)
			{
				outputStream.writeObject(data);
			}
			outputStream.close();
		}
		catch(IOException ioE)
		{
			ioE.printStackTrace();
		}
	}

	public static ArrayList<Data> inputObjectsBinary(String fileName)
	{
		//declaration & initialization
		ArrayList<Data> dataList;
		Data temp;
		boolean hasReachedEnd = false;
		dataList = new ArrayList<Data>(0);
		ObjectInputStream inputStream = null;
		fileName = ProgramIO.ensureExtension(fileName); //checks for .txt(adds if not at the end)
		
		try
        {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));

			//OBJECT format:
			while(!hasReachedEnd)
			{
				try{
					temp = (Data)inputStream.readObject();
					dataList.add(temp);
				}
				catch(EOFException eofE)
				{
					hasReachedEnd = true;
				}
			}
			
            inputStream.close();
        }
        catch (ClassNotFoundException cnfE)
        {
			System.out.println("ERROR: cannot read object from " + fileName);
			cnfE.printStackTrace();
            return null;
		}
        catch (FileNotFoundException fnfE)
        {
			fnfE.printStackTrace();
            System.out.println("ERROR: cannot find file " + fileName);
            return null;
        }
        catch (IOException ioE)
        {
			ioE.printStackTrace();
            System.out.println("ERROR: problem with file input from " + fileName);
            return null;
        }
		return dataList;
	}
	
	//TXT IO **********************************************************
	public static boolean outputTXT(String fileName, ArrayList<Data> dataList)
	{
		
		fileName = ProgramIO.ensureExtension(fileName); //checks for .txt(adds if not at the end)
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(new FileOutputStream(fileName));
		}
		catch(FileNotFoundException fnfE)
		{
			fnfE.printStackTrace();
			return false;
		}
			
		if(dataList != null && dataList.size() > 0)
		{
			for(Data data : dataList)
			{
				outputStream.println(data);
			}
		}
		outputStream.close();
		return true;
	}
	
	public static ArrayList<Data> inputTXT(String fileName)
	{
		Scanner inputStream;
		int frequency;
		String[] temp = new String[2];
		ArrayList<Data> dataList = new ArrayList<Data>(DEFAULT_LIST_SIZE); 
		fileName = ProgramIO.ensureExtension(fileName); //checks for .txt(adds if not at the end)
		inputStream = null; //required to keep compiler happy
		try
		{
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		
		catch (FileNotFoundException fnfE)
		{
			System.out.println("ERROR: File " + fileName
			+ " was not found or could not be opened.");
			fnfE.printStackTrace();
			return null;
		}
		//file loop
		while(inputStream.hasNextLine())
		{
			//assumes name : frequency
			//temp[0] = name, temp[1] = frequency(as string)
			temp = inputStream.nextLine().split(DEFAULT_DELIMETER);
			frequency = Integer.parseInt(temp[1]);
			dataList.add(new Data(temp[0], frequency));
		}
		dataList.trimToSize();
		inputStream.close();
		return dataList;
	}
	
	public static ArrayList<Data> inputTXT(String fileName, String delimeter)
	{
		Scanner inputStream;
		int frequency;
		String[] temp = new String[2];
		ArrayList<Data> dataList = new ArrayList<Data>(DEFAULT_LIST_SIZE); 
		fileName = ProgramIO.ensureExtension(fileName); //checks for .txt(adds if not at the end)
		inputStream = null; //required to keep compiler happy
		try
		{
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		catch (FileNotFoundException fnfE)
		{
			System.out.println("ERROR: File " + fileName
			+ " was not found or could not be opened.");
			return null;
		}
		//file loop
		while(inputStream.hasNextLine())
		{
			//assumes name : frequency
			//temp[0] = name, temp[1] = frequency(as string)
			temp = inputStream.nextLine().split(delimeter);
			frequency = Integer.parseInt(temp[1]);
			dataList.add(new Data(temp[0], frequency));
		}
		inputStream.close();
		return dataList;
	}
	
	//OTHER USEFUL METHODS
	private static String ensureExtension(String text)
	{
		if(text == null)
		{
			return null;
		}
		if(text.length() < 5) //needs at least one char .txt - 5 total chars
		{
			text += ".txt";
			return text;
		}
		if(!text.substring(text.length()-4, text.length()-1).equals(".txt"))
		{
			text += ".txt";
			return text;
		}
		else 
		{
			return text;
		}			
	}
	


}
