package sideproject;

import java.util.NoSuchElementException;
import java.awt.Color;

public class LinkedList<T extends Color> {
	
	//Node Inner Class ************************************************
	private class Node<T> {
		//INSTANCE VARIABLES
		private T data;
		private Node<T> next;
		
		//CONSTRUCTORS
		public Node() {
			this(null, null);
		}
		
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}


	//List Iterator Class *********************************************
	public class ListIterator { //think of it like an index "counter" object
		private Node<T> position; //current position
		private Node<T> previous; //previous value of position
		
		public ListIterator() {
			restart();
		}
		
		//setup references to beginning of linked list.
		public void restart() {
			this.position = head; 
			this.previous = null;
		}
		
		//returns boolean if there is a next node
		public boolean hasNext() { return this.position != null; }
		
		//returns current value of iterators position, and moves iterator to next node
			//throws error if no next node
		public T next() {
			if(!hasNext()) { 
				throw new NoSuchElementException();
			}
			T currentData = this.position.data;
			//update iterator to next node
			this.previous = this.position; //"old" position becomes previous
			this.position = this.position.next; //updated position to next
			
			return currentData;
		}
		
		//returns current value of iterators position, WITHOUT moving iterator
			//throws error if no next node
		public T peek() {
			if(!hasNext()) { 
				throw new IllegalStateException();
			}
			return this.position.data;
		}
		
		
		//adds a node before current position of iterator (between previous and position)
			//if at end of list, add to end
			//if empty list, add to front
			//else adding to middle of non-empty list, insert node and update iterator
		public void insertHere(T data) {
			if(this.position == null && this.previous != null) { //end of list
				//previous is last node in list, so add to end
				this.previous.next = new Node(data, null);
			} else if(position == null || previous == null) { //empty list
				//empty list, this is the new head then!
				addToFront(data); //use those useful methods!
			} else {
				//adding somewhere in the middle of non-empty list
				Node temp = new Node(data, this.position); //position comes after new node
				this.previous.next = temp; //previous comes before new node
				this.previous = temp; //update previous to new node that has been added
									  //position stays the same, why?
			}
		}
		//changehere
		
		//removes a node at current position of iterator (position node)
			//if empty list, throw error
			//if at start of list, remove head
			//else removing middle of non-empty list, remove position node
		public void removeHere() {
			if(this.position == null) { //empty list, can't delete!
				throw new IllegalStateException();
			} else if(this.previous == null) { //at start of list
				//remove head
				head = head.next;
				this.position = head;
			} else { 
				//deleting somewhere in middle of non-empty list, "delete" position node
				this.previous.next = this.position.next; //route around current node
				this.position = this.position.next; //update position to "deleted" nodes next
			}
		}
		
	}
	/***** END OF INNER CLASS ListIterator *****/
	
	
	private Node<T> head;
	
	public LinkedList() {
		this.head = null;
	}
	
	//NEW METHOD: get iterator for this linked list!
	public ListIterator iterator() { return new ListIterator(); }
	
	public void addToFront(T item) {
		this.head = new Node(item, this.head);
	}
	
	public boolean deleteFromFront() {
		if(this.head != null) {
			this.head = this.head.next;
			return true;
		} else { 
			return false;
		}
	}
	
	public int size() {
		int count = 0;
		Node<T> position = this.head;
		while (position != null) {
			count++;
			position = position.next;
		}
		return count;
	}
	
	public boolean contains(T item) {
		return (this.find(item) != null);
	}
	
	public T findData(T target) {
		Node<T> result = this.find(target);
		
		if(result == null) { 
			return null;
		} else {
			return result.data;
		}
	}
	
	public String toString() {
		Node position = this.head;
		String result = ""; 		
		while(position != null) {
			result += "[" + position.data + "] -> ";
			position = position.next;
		}
		
		return result + "null";
	}
	
	public boolean isEmpty() {
		return this.head == null;
	}
	
	public void clear() {
		this.head = null;
	}
	
	public boolean equals(Object other) {
		if(other == null || this.getClass() != other.getClass()) {
			return false;
		} else {
			LinkedList<T> otherList = (LinkedList<T>)other;
			return equalLists(this, otherList);
		}
	}
	

	
	//HELPERS
	private boolean equalLists(LinkedList<T> first, LinkedList<T> second) {
		boolean areEqual = first.size() == second.size();
		Node<T> position1 = first.head, position2 = second.head;
		
		while(areEqual && position1 != null) {
			areEqual = position1.data.equals(position2.data);
			position1 = position1.next;
			position2 = position2.next;
		}
		
		return areEqual;
	}
	
	private Node<T> find(T target) {
		boolean foundTarget = false;
		Node<T> position = this.head;
		T currentItem;
		while(position != null && !foundTarget) {
			currentItem = position.data;
			foundTarget = currentItem.equals(target);
			if(!foundTarget) {
				position = position.next;
			}
		}
		return position;
	}
	
	
	
}
