package sideproject;

import java.util.ArrayList;
import java.io.Serializable;

public class DataList implements Cloneable, Serializable
{
	//CONSTANTS *******************************************************
	private static final int DEFAULT_ARRAY_SIZE = 10;
	//INSTANCE VARIABLES **********************************************
	private ArrayList<Data> dataList;
	
	//CONSTRUCTORS ****************************************************
	public DataList()
	{
		dataList = new ArrayList<Data>(DEFAULT_ARRAY_SIZE);
	}
	
	public DataList(String fileName)
	{
		this.setDataList(fileName);
	}
	
	public DataList(ArrayList<Data> dataList)
	{
		this.setDataList(dataList);
	}
	
	public DataList(Data[] dataList)
	{
		this.setDataList(dataList);
	}
	
	//ACCESSORS *******************************************************
	public ArrayList<Data> getDataList()
	{
		return this.dataList;
	}
	
	//MUTATORS & FILE INPUT METHODS ***********************************
	public int setDataList(String fileName)
	{
		this.dataList = ProgramIO.fillList(fileName);
		return dataList.size();
	}
	
	public int setDataListBinary(String fileName)
	{
		this.dataList = ProgramIO.inputObjectsBinary(fileName);
		return dataList.size();
	}
	
	public int setDataList(ArrayList<Data> dataList)
	{
		//check for null, return -1 if true
		if(dataList == null)
		{
			this.dataList = null;
			return -1;
		}
		//if dataList is empty
		if(dataList.size() == 0)
		{
			this.dataList = new ArrayList<Data>(0);	
		}
		else
		{
			this.dataList = (ArrayList<Data>)dataList.clone();  //uses ArrayList api
		}
		this.dataList.trimToSize();
		return this.dataList.size();
	}
	
	public int setDataList(Data[] dataList)
	{
		//check for null, return -1 if true
		if(dataList == null)
		{
			this.dataList = null;
			return -1;
		}
		//if dataList is empty
		if(dataList.length == 0)
		{
			this.dataList = new ArrayList<Data>(0);
		}
		else
		{
			//adds all data from array to ArrayList instance variable 
			for (Data data: dataList)
			{
				this.dataList.add(data);
			}
		}
		return this.dataList.size(); 
	}
	
	//TRAVERSING METHODS **********************************************
	public static int[] findFrequencyExtrema(ArrayList<Data> dataList)
	{
		if(dataList == null || dataList.size() == 0)
		{
			return null;
		}
		else
		{
			int[] extrema = new int[2];
			int current = dataList.get(0).getFrequency(); //(sets to first frequency value)
			extrema[0] = current;   //minimum frequency 
			extrema[1] =  current;	 //maximum frequency 
			for(Data data : dataList)
			{
				current = data.getFrequency();
				if(extrema[0] < current)     //checks 
				{
					extrema[0] = current;
				}
				if(extrema[1] > current)
				{
					extrema[1] = current;
				}
			}
			return extrema;
		}
	}
	
	public static int[] findFrequencyExtrema(DataList dataList)
	{
		ArrayList<Data> dataArray = dataList.getDataList();
		if(dataList == null || dataArray == null|| dataArray.size() == 0)
		{
			return null;
		}
		else
		{
			int[] extrema = new int[2];
			int current = dataArray.get(0).getFrequency(); //(sets to first frequency value)
			extrema[0] = current;   //minimum frequency 
			extrema[1] =  current;	 //maximum frequency 
			for(Data data : dataArray)
			{
				current = data.getFrequency();
				if(extrema[0] > current)     //checks 
				{
					extrema[0] = current;
				}
				if(extrema[1] < current)
				{
					extrema[1] = current;
				}
			}
			return extrema;
		}
	}
	public static ArrayList<Integer> searchArray(ArrayList<Data> dataList, String input)
	{
		//Declaration & Initialization
		ArrayList<Integer> matchIndexes = new ArrayList<Integer>();			//match for user input	
		int counter = -1; 				//counter for current position in matchIndexes 	
		
		dataList.trimToSize();
		//Search
		for (Data data : dataList)
		{		
			if (data.getName().indexOf(input) >= 0) //doesnt match to frequency (only name)
			{
				counter++; //used as counter for index
				matchIndexes.add(dataList.indexOf(data));
			}
		} 
		return matchIndexes;
	}
	
	//Sorting Methods ************************************************
	public boolean swapData(int index1, int index2) 
	{
		if(this.dataList == null || this.dataList.size() == 0)
		{
			return false;
		}
		try{
			Data temp = (Data)this.dataList.get(index1).clone();
			Data temp2 = (Data)this.dataList.get(index2).clone();
			this.dataList.set(index1, temp2);
			this.dataList.set(index2, temp);
		}
		catch(IndexOutOfBoundsException ioobE) 
		{
			return false;
		}
		return false; //keep compiler happy
		
	}
	
	public void sortByName()
	{
		String name1;
		String name2;
		String temp;
		for(int i=0; i<this.dataList.size(); i++)
        {
            for(int j=i+1; j<this.dataList.size(); j++)
            {
				name1 = this.dataList.get(i).getName();
				name2 = this.dataList.get(j).getName();
                if(name2.compareTo(name1)<0)
                {
					this.swapData(i,j);
                }
            }
		}
	}
	
	public void sortByFrequency()
	{
		int freq1;
		int freq2;
		int temp;
		for(int i=0; i<this.dataList.size(); i++)
		{
			for(int j=i+1; j<dataList.size(); j++)
			{
				freq1 = this.dataList.get(i).getFrequency();
				freq2 = this.dataList.get(j).getFrequency();
				if(freq2<freq1)
				{
					this.swapData(i,j);
				}
			}
		}
	}
	
	//STRING METHODS **************************************************
	public static void printArray(int[] arrayList)
	{
		for(int i : arrayList)
		{
			System.out.println(i);
		}
	}
	public static void printArray(ArrayList<Data> dataList)
	{
		for(Data data: dataList)
		{
			System.out.println(data + "\n");
		}
	}
	
	public static void printMatches(ArrayList<Data> dataList, ArrayList<Integer> matchIndexes)
	{
		for(int i: matchIndexes)
		{
			System.out.println(dataList.get(i) + "\n");
		}
	}
	
	public String toString()
	{
		String result;
		result = "";
		for(Data data: dataList)
		{
			result += data + "\n";
		}
		return result;
	}
	
	//OTHER REQUIRED METHODS ******************************************
	/** clone() overrides default clone() 
	 * @param  n/a
	 * @return clone of this object */
	@Override
    public Object clone() {
        try {
			return super.clone(); 
		} catch (CloneNotSupportedException e) {
			return null; 
		}
    }
    
	public boolean equals(DataList other)
	{
		if(other == null || !(other instanceof DataList))
		{
			return false;
		}
		//check for same size ArrayList instance variable
		if(other.getDataList().size() != this.dataList.size()) 
		{
			return false;
		}
		else
		{
			Data otherData;
			Data data;
			int count = 0;  //used to traverse list (as they are same size)
			boolean areEqual = true;
			while(areEqual)
			{
				data = dataList.get(count);		
				otherData = other.getDataList().get(count);
				areEqual = data.equals(otherData);
				count++;
			}
			return areEqual; 
		}
	}
	
	
}
