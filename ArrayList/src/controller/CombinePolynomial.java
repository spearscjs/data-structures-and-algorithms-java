/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 9/17/16 9:00PM
***********************************************************************
* TITLE:				CombinePolynomial
* PROGRAM DESCRIPTION:	Contains useful methods for combining ArrayList<Term> objects mathmatically
***********************************************************************/
package controller;

import java.util.ArrayList;
import java.util.Collections;
import model.Term;

public class CombinePolynomial
{	
	//assumes they are same exponential (uses one's exponent value)
	/** addLikeTerms : adds coefficient values of terms, uses first term (one) exponent
	 * @param Term one
	 * @param Term other
	 * @return Term sum
	 */
	public static Term addLikeTerms(Term one, Term other)
	{
		double combinedCoefficient = one.getCoefficient() + other.getCoefficient();
		return new Term(combinedCoefficient, one.getExponent());
	}
	
	
	/** combine : combines two arraylist<Term> by adding together the coefficients of like exponent values
	 * @param ArrayList<Term> firstList
	 * @param ArrayList<Term> secondList
	 * @return ArrayList<Term> sum
	 */
	public static ArrayList<Term> combine(ArrayList<Term> firstList, ArrayList<Term> secondList)
	{
		ArrayList<Term> newList = new ArrayList<Term>(firstList.size()); //minimum size it could be
		Term tempTerm;
		Term termOne;
		Term termTwo;
		boolean foundMatch;
		
		for(int i = 0; i < firstList.size(); i++)
		{
			foundMatch = false;
			termOne = firstList.get(i);
			for(int j = 0; j < secondList.size() && foundMatch == false; j++)
			{
				termTwo = secondList.get(j);
				if(termOne.compareTo(termTwo) == 0)
				{
					tempTerm = CombinePolynomial.addLikeTerms(termOne, termTwo);
					newList.add(tempTerm);
					foundMatch = true; //avoids looping through rest 
				}
			}
			if(foundMatch == false)
			{
				newList.add(termOne);
			}
		}
		return newList;
	}
	
	/** combineSort : combines two arraylist<Term> by adding together the coefficients of like exponent values
	 * @param ArrayList<Term> firstList
	 * @param ArrayList<Term> secondList
	 * @return ArrayList<Term> sum
	 */
	public static ArrayList<Term> combineSort(ArrayList<Term> firstList, ArrayList<Term> secondList)
	{
		ArrayList<Term> newList = new ArrayList<Term>(firstList.size()); //minimum size it could be
		int i = 0;
		Term termOne;
		Term termTwo;
		Term tempTerm;
		Collections.sort(firstList);
		Collections.sort(secondList);
		if (firstList.size() == 0)
		{
			return secondList;
		}
		if (secondList.size() == 0)
		{
			return firstList;
		}
		while(i < firstList.size() || i < secondList.size())
		{
			if(i > firstList.size())
			{
				newList.add(secondList.get(i));
			}
			if(i > secondList.size())
			{
				newList.add(firstList.get(i));
			}
			else
			{
				termOne = firstList.get(i);
				for(int j = 0; j < secondList.size(); j++)
				{
					termTwo = secondList.get(j);
					if(termOne.compareTo(termTwo) == 0)
					{
						tempTerm = CombinePolynomial.addLikeTerms(termOne, termTwo);
						newList.add(tempTerm);
					}
					if(termOne.compareTo(termTwo) < 0 && i > j)
					{
						newList.add(termTwo);
					}
				}
			}	
			i++;		
		}
		Collections.sort(newList);
		return newList;
	}
}
