/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 10/02/16 5:00PM
***********************************************************************
* TITLE:				ListStack<T>
* PROGRAM DESCRIPTION: class used in construction of stack using a linked list
***********************************************************************/
package model;

//Imports ***************************************************************************
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import java.util.Iterator;

public class ListStack<T> implements Iterable<T> {
	
	//Instance Variables *************************************************************
	private Node head; //inner classes are at the end
	private int size; //topOfStack
	
	//Constructors **********************************
	/** ListStack: Default Constructor for ListStack
	 * @param n/a
	 * @return n/a
	 */
	public ListStack() {
		this.head = null;
		this.size = 0; 
	}
	
	//Other Constructors avoided to ensure correct sizing/topOfStack
	
	//Accessors / Getters *************************************************************
	/** getHead: accessor for Node head instance variable
	 * @param n/a
	 * @return Node head
	 */
	public Node getHead() {
		return this.head;
	}
	
	/** getData: accessor for T data instance variable
	 * @param n/a
	 * @return T data
	 */
	public T getData() {
		return this.head.data;
	}
	
	/** getSize: accessor for int size instance variable
	 * @param n/a
	 * @return int size
	 */
	public int getSize() {
		return this.size;
	}
	
	
	//Mutators / Setters
	/** setData: mutator for T data instance variable
	 * @param T data
	 * @return boolean wasSet
	 */
	public boolean setData(T data) {
		if(this.isEmpty()) {
			return false;
		}
		else {
			this.head.data = data; 
			return true;
		}
	}
	//NOTE: sizing & head setter not added to avoid sizing errors
	
	//Stack Methods ********************************************************&*********
	/** push: adds T data to front/top of stack (default stack method)
	 * @param T data
	 * @return T data
	 */
	public T push(T data) {
		this.head = new Node(data, this.head);
		size++;
		return data;
	}
	
	/** peek: views topOfStack without removal (default stack method)
	 * @param n/a
	 * @return T data
	 */
	public T peek() {
		return this.head.data;
	}
	
	/** pop: views topOfStack with removal (default stack method)
	 * @param n/a
	 * @return T data
	 */
	public T pop() {
		if(this.isEmpty()){
			throw new EmptyStackException();
		}
		else {
			T temp = this.head.data;
			this.head = this.head.next;
			this.size--;
			return temp;
		}
	}
	
	/** isEmpty: checks if stack is empty (ie. head == null) (default stack method)
	 * @param n/a
	 * @return boolean isEmpty
	 */
	public boolean isEmpty() {
		return this.head == null;
	}
	
	/** clear: clears stack, sets head to null and size to 0 
	 * @param n/a
	 * @return void
	 */
	public void clear() {
		head = null;
		size = 0;
	}
	
	//List Methods *************************************************************************
	/** iterator: iterator for stack's internal list 
	 * @param n/a
	 * @return ListIterator() iter
	 */
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	
	//Other Methods ************************************************************************
	/** toString : returns readable string representation of class
	 * @param n/a
	 * @return String string
	 */
	public String toString() {
		String stack = "[ ";
		for(T data : this) {
			stack += data + " ";
		}
		return stack + "]";
	}
	
	/** equals : returns boolean representation of ListStack<T> equality
	 * @param ListStack<T> other
	 * @return boolean areEqual
	 */
	public boolean equals(ListStack<T> other) {
		if(other == null || other.getSize() != this.getSize()) {
			return false;
		}
		else {
			Node temp = this.getHead();
			Node temp2 = other.getHead();
			if(temp.equalData(temp2) == false) { //checks in case of size 1 (points to null)
				return false;
			}
			while(temp.next() != null && temp2.next() != null) {
			    if(temp.equalData(temp2)) {
			    	temp = temp.next();
			    	temp2 = temp2.next();
			    } 
			    else return false;
			}	
			return true;
		}
	}
	

	
	/**************************************************************************************
	 **INNER CLASSES***********************************************************************
	 **************************************************************************************/
	
	//NODE INNER CLASS **********************************************************************
	private class Node {
		//Instance Variables
		private T data;
		private Node next;
		
		//Constructors **********************************************************************
		/** Node : default constructor for Node class
		 * @param n/a
		 * @return void
		 */
		public Node() {
			this.data = null;
			this.next = null;
		}
		
		/** Node : filled constructor for Node class
		 * @param T data, Node next
		 * @return void
		 */
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
		
		//Accessors / Getters
		/** next : getter for next instance variable
		 * @param n/a
		 * @return Node next
		 */
		public Node next() {
			return next;
		}
		
		/** getNext : getter for next instance variable
		 * @param n/a
		 * @return Node next
		 */
		public Node getNext() {
			return next;
		}

		//Other Methods *******************************************************************
		/** equals : returns boolean representation of Node equality
		 * @param Node<T> other
		 * @return boolean areEqual
		 */
		public boolean equals(Node other) {
			if (this.next == other.next && this.data.equals(other.data)) { //checks next reference
				 return true;
			 }
			else return false;
		}
		
		/** equalData : returns boolean representation of Node data instance equality (next has no effect)
		 * @param Node<T> other
		 * @return boolean areEqual
		 */
		public boolean equalData(Node other) {
			if (this.data.equals(other.data)) { //doesn't check next reference
				 return true;
			 }
			else return false;
		}
		
		/** toString : returns readable string representation of class
		 * @param n/a
		 * @return String string
		 */
		public String toString() {
			return "" + this.data;
		}
	}
	//END OF NODE INNER CLASS ****************************************************************
	
	
	//ITERATOR INNER CLASS *******************************************************************
    // an iterator, doesn't implement remove() - optional
    private class ListIterator implements Iterator<T> {
	//Instance Variables ***********
        private Node current = head;
	
        //Iterator Methods *******************************************************************
	/** hasNext : checks if list has next value (current of iterator)
	 * @param n/a
	 * @return boolean hasNext
	 */
        public boolean hasNext()  { 
		return current != null; 
	}
	
        /** remove : throws exception due to the list being in a stack. Remove from stack if needed. 
	* @param n/a
	* @return void
        */
        public void remove() { 
		throw new UnsupportedOperationException();  //can't remove in middle of stack, method doesn't work
	}
	
        /** next : traverses list to currents next value, if null - throws exception 
	* @param n/a
	* @return T currentData
        */
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.data;
            current = current.next; 
            return item;
        }
    }	
    //END OF ITERATOR INNER CLASS ************************************************************
	
}