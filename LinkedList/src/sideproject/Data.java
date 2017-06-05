package sideproject;

import java.io.Serializable;

public class Data implements Cloneable, Serializable{

	//CONSTANTS *******************************************************
	private static final String DEFAULT_NAME = "DEFAULT NAME";
	private static final int DEFAULT_FREQUENCY = 0; 
	
	//INSTANCE VARIABLES **********************************************
	private String name;
	private int frequency;
	
	//CONSTRUCTORS ****************************************************
	public Data()
	{
		this.setName(DEFAULT_NAME);
		this.setFrequency(DEFAULT_FREQUENCY);
	}
	
	public Data(String name)
	{
		this.setName(name);
		this.setFrequency(1);
	}
	
	public Data(String name, int frequency)
	{
		this.setAll(name, frequency);
	}
	
	public Data(Data original)
	{
		this.setAll(name, frequency);
	}
	//MUTATORS ********************************************************
	public void setName(String name)
	{
		this.name = name; 
	}

	public boolean setFrequency(int frequency)
	{
		if(frequency >= 0)
		{
			this.frequency = frequency;
			return true;
		}
		else return false;
	}

	public boolean setAll(String name, int frequency)
	{
		if(this.setFrequency(frequency) == true)
		{
			this.setName(name);
			return true;
		}
		else return false;
	}
	
	public void incrementFrequency()
	{
		this.frequency++;
	}
	
	//ACCESSORS *******************************************************
	public String getName()
	{
		return this.name;
	}
	
	public int getFrequency()
	{
		return this.frequency;
	}
	
	//OTHER REQUIRED METHODS ******************************************
	public String toString()
	{
		return this.name + " : " + this.frequency;
	}
	
	/** clone() overrides default clone() 
	 * @param  n/a
	 * @return clone of this object */
	@Override
    public Object clone() 
    {
		try 
		{
			return super.clone(); 
		} 
		catch (CloneNotSupportedException e) 
		{
			return null; 
		}
    }
	
	public boolean equals(Data other)
	{
		if(other == null)
		{
			return false;
		}
		if(!(other instanceof Data))
		{
			return false;
		}
		String otherName = other.getName();
		int otherFreq = other.getFrequency();
		
		//compare name and frequency instance variables
		if(otherName.equals(this.getName()) &&
			otherFreq == this.getFrequency())
		{
			return true;
		}
		else return false; 
	}
	
}
