/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 11/20/2016
***********************************************************************
* TITLE:				Sort
* PROGRAM DESCRIPTION:  Sort class contains various methods for sorting 
***********************************************************************/
package algs;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Sort {
 
/**********************************************************************************************************
 ** MERGE ************************************************************************************************* 
 **********************************************************************************************************/
	//Merge Without Comparator ****************************************************************************
	/** merge : recursive merge sort method. Sorts array by splitting up in halves, continually
	 * 			breaks down each side until length 2, then calls helper method to sort.	 
	 * 			note: doesn't work for primitives (must use Integer and other wrapper classes)
	 * @param T[] data
	 * @return T[] sorted
	 */
    public static <T extends Comparable<T>> T[] merge(T[] data) {
    	//Done breaking in halves
        if (data.length == 1) {    
            return data;
        }
    	//Create array for left half of data and array for right half of data. Also track center of data array
    	int center = data.length/2;
    	//Array.newInstance implemented due to generics inability to be initialized
    	T[] left = (T[]) Array.newInstance(data.getClass().getComponentType(), center);
        T[] right = (T[]) Array.newInstance(data.getClass().getComponentType(), data.length - center);
        //Copy left elements of data
        for (int i=0; i < center; i++) {
            left[i] = data[i]; 
        }
        //Copy right elements of data (if odd, remaining value goes in front)
        for (int i = center; i < data.length; i++) {
        	right[i-center] = data[i];
        }
        //Keep breaking down the data into half's
        left  = merge(left);
        right = merge(right);
        //Merge the results back together and return result
        mergeSorted(left, right, data);
        return data;
    }
    
    //Helpers ******************************************************
    /** mergeSorted : helper methods, takes left and right half of whole array and adds to whole 
     * 				  according to which value is smaller (left[i] or right[j])	 
	 * 				  note: doesn't work for primitives (must use Integer and other wrapper classes)
	 * @param T[] left //left half
	 * @param T[] right //right half
	 * @param T[] whole //whole thing (sorts this)
	 * @return void
	 */
    public static <T extends Comparable<T>> T[] mergeSorted(T[] left, T[] right) {
    	T[] merged = (T[]) Array.newInstance(left.getClass().getComponentType(), left.length + right.length);
    	Sort.mergeSorted(left, right, merged);
    	return merged;
    }
    /** mergeSorted : helper methods, takes left and right half of whole array and adds to whole 
     * 				  according to which value is smaller (left[i] or right[j])	 
	 * 				  note: doesn't work for primitives (must use Integer and other wrapper classes)
	 * @param T[] left //left half
	 * @param T[] right //right half
	 * @param T[] whole //whole thing (sorts this)
	 * @return void
	 */
    private static <T extends Comparable<T>> void mergeSorted(T[] left, T[] right, T[] whole) {
        //Keep track of current indexes for each array for traversal
    	int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
        //Add to whole from left and right according to which is smallest
        while(leftIndex < left.length || rightIndex < right.length) {
        	//Add to left 
			if(rightIndex == right.length || leftIndex < left.length && left[leftIndex].compareTo(right[rightIndex]) <= 0) {
				whole[wholeIndex] = left[leftIndex];
            	leftIndex++;
			}
			//Add to right
			else if(leftIndex == left.length || rightIndex < right.length && right[rightIndex].compareTo(left[leftIndex]) < 0) {
				whole[wholeIndex] = right[rightIndex];
            	rightIndex++;
			}
			wholeIndex++;
		}
    }
    
    
    //Merge With Comparator *******************************************************************************
    /** merge : recursive merge sort method. Sorts array by splitting up in halves, continually
	 * 			breaks down each side until length 2, then calls helper method to sort.	 
	 * 			note: doesn't work for primitives (must use Integer and other wrapper classes)
	 * @param T[] data
	 * @return T[] sorted
	 */
    public static <T> T[] merge(T[] data, Comparator<T> comp) {
    	//Done breaking in halves
        if (data.length == 1) {    
            return data;
        }
    	//Create array for left half of data and array for right half of data. Also track center of data array
    	int center = data.length/2;
    	//Array.newInstance implemented due to generics inability to be initialized
    	T[] left = (T[]) Array.newInstance(data.getClass().getComponentType(), center);
        T[] right = (T[]) Array.newInstance(data.getClass().getComponentType(), data.length - center);
        //Copy left elements of data
        for (int i=0; i < center; i++) {
            left[i] = data[i]; 
        }
        //Copy right elements of data (if odd, remaining value goes in front)
        for (int i = center; i < data.length; i++) {
        	right[i-center] = data[i];
        }
        //Keep breaking down the data into half's
        left  = merge(left, comp);
        right = merge(right, comp);
        //Merge the results back together and return result
        mergeSorted(left, right, data, comp);
        return data;
    }
    
    //Helper ****************************************************
    /** mergeSorted : helper methods, takes left and right half of whole array and adds to whole 
     * 				  according to which value is smaller (left[i] or right[j])	 
	 * 				  note: doesn't work for primitives (must use Integer and other wrapper classes)
	 * @param T[] left //left half
	 * @param T[] right //right half
	 * @param T[] whole //whole thing (sorts this)
	 * @return void
	 */
    private static <T> void mergeSorted(T[] left, T[] right, T[] whole, Comparator<T> comp) {
        //Keep track of current indexes for each array for traversal
    	int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
        //Add to whole from left and right according to which is smallest
        while(leftIndex < left.length || rightIndex < right.length) {
        	//Add to left 
			if(rightIndex == right.length || leftIndex < left.length && comp.compare(left[leftIndex], right[rightIndex])<= 0) {
				whole[wholeIndex] = left[leftIndex];
            	leftIndex++;
			}
			//Add to right
			else if(leftIndex == left.length || rightIndex < right.length && comp.compare(right[rightIndex], left[leftIndex]) < 0) {
				whole[wholeIndex] = right[rightIndex];
            	rightIndex++;
			}
			wholeIndex++;
		}
    }
    
/**********************************************************************************************************
 ** RADIX ************************************************************************************************* 
 **********************************************************************************************************/
    /** radix : wrapper method for recursive radix sort method (see for more details)
	 * @param int[] numbers
	 * @return int[] sorted
	 */
	public static int[] radix(int[] numbers) {
		return radix(numbers, 0);
	}
	
	/** radix : recursive sort radix method, digit based int[] sorting method (for more info see Google)
	 * 			implements using queue[] to sore and sort value of each digit
	 * @param int[] numbers
	 * @param int position
	 * @return int[] sorted
	 */
	public static int[] radix(int[] numbers, int position) {
		boolean isSorted = true;  //used in base case
		//queue to store values according to current value at position
		Queue<Integer>[] valuesAtPosition = new LinkedList[10];
		//index based on current digit in num
		int queueIndex; 
		//numbers traversal
		for(int num : numbers) { 
			//find queue index (digit at current position of num)
			queueIndex = (num % ((int)Math.pow(10, position+1)) / (int)Math.pow(10, position));
			//ensure offer won't be called on null queue
			if(valuesAtPosition[queueIndex] == null) {
				valuesAtPosition[queueIndex] = new LinkedList<Integer>();
			}
			//check if numbers is sorted 
			if(queueIndex > 0) {
				isSorted = false; //hasn't reached greatest significant digit
			}
			valuesAtPosition[queueIndex].offer(num); //add num to queue[] at position
		}
		//base case
		if(isSorted == true) {
			return numbers;
		}
		//else
		//recursive call
		//fill array with queue
		int i = 0;
		//traverse each queue in queue[], set numbers[i] for each integer in queue (sorts by current position)
		for(Queue<Integer> queue : valuesAtPosition) {
			if(queue != null) {
				for(Integer value : queue) {
					numbers[i] = value;
					System.out.println(value);
					i++;
				}
			}
		}
		System.out.println();
		return radix(numbers, ++position);
	}
}