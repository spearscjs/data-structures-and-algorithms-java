/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 9/17/16 9:00PM
***********************************************************************
* TITLE:				Term
* PROGRAM DESCRIPTION:	Class constructor useful for representation of exponential numbers and their coefficient 
***********************************************************************/
package model;

public class Term implements Cloneable, Comparable<Term>
{
	//Constants
	private static final int DEFAULT_EXPONENT = 1;
	private static final int DEFAULT_COEFFICIENT = 0;
	
	//Instance Variables
	double exponent;
	double coefficient;
	
	//Constructors
	/** Term : default constructor for Term class
	 * @param n/a
	 * @return n/a
	 */
	public Term()
	{
		this.setExponent(DEFAULT_EXPONENT);
		this.setCoefficient(DEFAULT_COEFFICIENT);
	}
	
	/** Term : filled constructor for Term class
	 * @param double coefficient, double exponent
	 * @return n/a
	 */
	public Term(double coefficient, double exponent)
	{
		this.setExponent(exponent);
		this.setCoefficient(coefficient);
	}

	/** Term : copy constructor for Term class
	 * @param Term other
	 * @return n/a
	 */
	public Term(Term other)
	{
		if(other != null && other instanceof Term)
		{
			this.setExponent(other.getExponent());
			this.setCoefficient(other.getCoefficient());
		}
	}
	
	//Mutators/Setters
	/** setExponent : setter for exponent (double) instance variable
	 * @param double exponent
	 * @return void
	 */
	public void setExponent(double exponent)
	{
		this.exponent = exponent;
	}

	/** setExponent : setter for coefficient (double) instance variable
	 * @param double coefficient
	 * @return void
	 * */
	public void setCoefficient(double coefficient)
	{
		this.coefficient = coefficient;
	}

	
	//Accessors/Getters
	/** getExponent : getter for exponent instance variable
	 * @param n/a
	 * @return double exponent
	 */
	public double getExponent()
	{
		return this.exponent;
	}

	/** getExponent : getter for coefficient instance variable
	 * @param n/a
	 * @return double coefficient
	 */
	public double getCoefficient()
	{
		return this.coefficient;
	}

	//Other Required Methods
	/** equals : checks equality based on instance variables
	 * @param Term one
	 * @param Term another
	 * @return boolean areEqual
	 */
	public static boolean equals(Term one, Term another)
	{
		if(another == null)
		{
			return false; //can't call method on null object
		}
		if(another instanceof Term && one.getExponent() == another.getExponent() && one.getCoefficient() == another.getCoefficient())
		{ 
			return true;
		}
		else 
		{
			return false; 
		}
	}
	
	/** clone() overrides default clone() 
	 * @param  n/a
	 * @return clone of this object 
	 */
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
    
    /** compareTo : overrides Comparable method, based off exponent value
     * @param Term other
     * @return int num
     */
    @Override
    public int compareTo(Term other)
    {
		if(this.getExponent() == other.getExponent())
		{
			return 0;
		} 
		if(this.getExponent() > other.getExponent())
		{
			return 1;
		}
		else return -1; 
	}
	
    /** toString : overrides default toString method for object by putting instance variables in readable format
     * @param n/a
     * @return String instances
     */
	public String toString()
	{
		double coefficient = this.getCoefficient();
		double exponent = this.getExponent();
		if(coefficient == (int)coefficient && exponent == (int)exponent)
		{
			return "" + (int)this.getCoefficient() + "^" + (int)this.getExponent();
		}
		else
		{
			return "" + this.getCoefficient() + "^" + this.getExponent();
		}	
	}
	
}