/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 9/25/16 9:00PM
***********************************************************************
* TITLE:				Singly<T>
* PROGRAM DESCRIPTION:	Singly linked list model, inner class Node at end 
***********************************************************************/
package model;

//Imports **************************************************************************************************
import java.util.AbstractSequentialList; //Singly Implements
import java.util.ListIterator;
import java.util.NoSuchElementException; //ListIterator Implements
//Note: Index Out of Bounds Exception is Checked Runtime

public class Singly<T> extends AbstractSequentialList<T> {
	

	//Instance Variables ***********************************************************************************
	private int size; 
	private Node<T> head;
	private Node<T> tail;
	
	//Constructors *****************************************************************************************
	/** Singly: Default Constructor for Singly
	 * @param n/a
	 * @return n/a
	 */
	public Singly() {
		this.setSize(0);
		this.head = null;
		this.tail = null;
	}
	
	/** Singly: Filled Constructor for Singly
	 * @param int size, Node<T> head, Node<T> tail
	 * @return n/a
	 */
	public Singly(int size, Node<T> head, Node<T> tail) {
		this.setSize(size);
		this.head = head;
		this.tail = tail;
	}
	
	/** Singly: Filled Constructor for Singly
	 * @param Singly<T> list
	 * @return n/a
	 */
	public Singly(Singly<T> list) {
		this.setSize(list.getSize());
		this.head = list.getHead();
		this.tail = list.getTail();
	}
	
	//No other constructors made to avoid size confusion
	
	//Mutators/Setters **********************************************************************************
	/** setSize : mutator for size instance variable
	 * @param int size
	 * @return n/a
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	//No Set Head or Tail To avoid size issue
	//Head & Tail Setter In Inner Iterator Class
	
	///Accessors/Getters ******************************************************************************
	/** getHead : Accessor for head instance variable
	 * @param Node head
	 * @return n/a
	 */
	public Node<T> getHead() {
		return this.head;
	}
	
	/** getTail : Accessor for tail instance variable
	 * @param Node tail
	 * @return n/a
	 */
	public Node<T> getTail() {
		return this.tail;
	}
	
	/** getSize : Accessor for size instance variable
	 * @param int size
	 * @return n/a
	 */
	public int getSize() {
		return this.size;
	}
	
	/** size : Accessor for size instance variable
	 * @param int size
	 * @return n/a
	 */
	@Override
	public int size() {
		return this.size();
	}
	
	//Other Methods *********************************************************************************
	/** clone : returns clone of this Singly
	 * @param n/a
	 * @return Singly<T> clone
	 */
	public Singly<T> clone() {
		return new Singly<T>(this);
	}
	
	/** equals : returns boolean representation of Singly equality
	 * @param Singly<T> other
	 * @return boolean areEqual
	 */
	public boolean equals(Singly<T> other) {
		if(!(other instanceof Singly)) {
			return false;
		}
		if(other.getTail() == this.getTail()   //check reference
 		 && other.getHead() == this.getHead()
		 && other.getSize() == this.getSize()) {
			return true;
		}
		else return false;
	}
	
	/** toString : returns readable string representation of class
	 * @param n/a
	 * @return String string
	 */
	public String toString() {
		//Declaration/Initialization
		String list = "null<-";
		Node<T> current = this.getHead();
		//Iterate until end of list (null)
		if(current == null) {
			return list+="null->null";
		}
		while(current != null) {
			list += "[" + current + "]->"; //add readability 
			current = current.getNext(); //iterator
		}
		list += "null"; //for singular list
		return list;
	}
	
	/** addFirst : adds item to head of List
	 * @param T item
	 * @return void
	 */
	public void addFirst(T item) {
		if(head == null) {
			head = new Node<T>(item);
			tail = head;
			size++;
		}
		else {
	    	Node<T> node = new Node<T>(item);
	    	head.previous = node;
	    	node.next = head;
	    	head = node;
	    	size++;
		}
    }
	
	/** addLast : adds item to tail of List
	 * @param T item
	 * @return void
	 */
	public void addLast(T item) {
		if(this.head == null) {
			this.head = new Node<T>(item);
			this.tail = head;
			size++;
		}
		else {
			Node<T> node = new Node<T>(this.tail, item, null);
			this.getTail().setNext(node);;
			this.tail = node;
			size++;
		}
	}
	
	/** clear : clears list to null
	 * @param n/a
	 * @return void
	 */
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size =  0;
    }
	
	/** ListIterator : return a ListIterator that begins at the same place as an existing ListIterator
    * @param ListIterator<T> iterator - other 
    * @return a ListIterator that is a copy of iterator*/
	public ListIterator<T> listIterator(ListIterator<T> iterator) {
		int index=0; //index iterator is at
		while(iterator.hasPrevious()) {
			iterator.previous();
			index++;
		}
		for(int i=0;i<index;i++) {
			iterator.next(); //return iterator to original state
		}
		return new Iterator(index);
   }


   /** get : get the element at position index.
    * @param int index Position of item to be retrieved
    * @return The item at index
    */
   @Override
   public T get(int index) {
       return listIterator(index).next();
   }
   

   /** listIterator : return a ListIterator to the list
    * @param n/a
    * @return a ListItertor to the list
    */
   public ListIterator<T> listIterator() {
   	return new Iterator(0);
   }

   /** listIterator :  a ListIterator that begins at index
    * @param int index - The position the iteration is to begin
    * @return a ListIterator that begins at index
    */
   public ListIterator<T> listIterator(int index) {
   	return new Iterator(index);

   }

  
	/*************************************************************************************************
	 * INNER CLASS ITERATOR **************************************************************************
	 *************************************************************************************************/
	private class Iterator implements ListIterator<T> {
		
		//Instance Variables *************************************************************************
		private Node<T> nextItem;
		private Node<T> lastItemReturned;
		private int index;
		
		//Constructors *******************************************************************************
		//throws iob exception
		/** Iterator : Default Constructor for Iterator
		 * @param n/a
		 * @return n/a
		 */
		public Iterator() {
			nextItem = null;
			lastItemReturned = null;
			index = 0;
		}
		
		/** Iterator : Filled Constructor for Iterator, iterates up to targetIndex
		 * @param int targetIndex
		 * @return n/a
		 */
		public Iterator(int targetIndex) {
			if(targetIndex == 0) {
				this.nextItem = head;
				lastItemReturned = null;
				this.index = 0;
			}
			else if(targetIndex > Singly.this.getSize() || index < 0) {
				throw new IndexOutOfBoundsException("ERROR: index (" + index + ") is out of bounds."); 
			}
			else if(targetIndex == size) {
				index = size; //in front of last node
				this.lastItemReturned = Singly.this.getTail(); 
				this.nextItem = null;
			}
			else {
				nextItem = nextItem.getNext();
				lastItemReturned = null;
				for(int i = 0; i < targetIndex; i++) {
					lastItemReturned = nextItem;
					nextItem = nextItem.getNext();
					index++;
				}
			}
		}
		
		/** Iterator : Copy Constructor for Iterator
		 * @param Iterator original
		 * @return n/a
		 */
		public Iterator(Iterator original) { 
			this.setAll(original.getIndex(), original.getNextItem(), original.getLastItemReturned());
		}
		
		//Mutators/Setters ***************************************************************************
		/** setNextItem : mutator for nextItem instance variable
		 * @param Node<T> next
		 * @return n/a
		 */
		public void setNextItem(Node<T> next) {
			this.nextItem = next;
		}
		
		/** setLastItemReturned : mutator for lastItemReturned instance variable
		 * @param Node<T> last
		 * @return n/a
		 */
		public void setLastItemReturned(Node<T> last) {
			this.lastItemReturned = last;
		}
		
		/** setIndex : mutator for index instance variable
		 * @param int index
		 * @return n/a
		 */
		public void setIndex(int index) {
			this.index = index;
		}
		
		/** setAll : mutator for all instance variables
		 * @param int index, Node<T> next, Node<T> last
		 * @return n/a
		 */
		public void setAll(int index, Node<T> next, Node<T> last) {
			this.setIndex(index);
			this.setNextItem(next);
			this.setLastItemReturned(last);
		}
		
		//Accessors/Getters ***************************************************************************
		/** getNextItem : accessor for nextItem instance variable
		 * @param n/a
		 * @return Node<T> nextItem
		 */
		public Node<T> getNextItem() {
			return this.nextItem;
		}
		
		/** getLastItemReturned : accessor for lastItemReturned instance variable
		 * @param n/a
		 * @return Node<T> last
		 */
		public Node<T> getLastItemReturned() {
			return this.lastItemReturned;
		}
		
		/** getIndex : accessor for index instance variable
		 * @param n/a
		 * @return int index
		 */
		public int getIndex() {
			return this.index;
		}
		
		//Other Methods *********************************************************************************
		/** equals : returns boolean representation of Singly equality
		 * @param Singly<T> other
		 * @return boolean areEqual
		 */
		public boolean equals(Iterator other) {
			if(other == null || !(other instanceof Singly.Iterator)) {
				return false;
			}
			else if(this.getNextItem() == other.getNextItem()
					&& this.getLastItemReturned() == other.getLastItemReturned()
					&& this.getIndex() == other.getIndex()) {
				return true;
			}
			else return false;
		}
		/** clone : returns clone of this Iterator
		 * @param n/a
		 * @return Iterator copy
		 * */
		public Object clone() {
			return new Iterator(this);
		}
		
		/** toString : returns readable string representation of class
		 * @param n/a
		 * @return String string
		 */
		public String toString() {
			return this.getLastItemReturned() + "->{ITERATOR(index:" + this.getIndex() + ")}->" + this.getNextItem();
		}
		
		//Interface Overrides *************************************************************************
		/** hasNext : checks if nextItem is null
		 * @param n/a
		 * @return boolean hasNext
		 */
		@Override
		public boolean hasNext() {
			if(this.getNextItem() != null) {
				return true;
			} 
			else return false;
		}

		/** hasPrevious : checks if previousItem is null
		 * @param n/a
		 * @return boolean hasPrevious
		 */
		@Override
		public boolean hasPrevious() {
			return (nextItem == null && size != 0)
                    || nextItem.previous != null;
		}

		/** next : returns <T> value of nextItem
		 * @param n/a
		 * @return T data
		 */
		//@throws NoSuchElementException if there is no such object
		@Override
		public T next() {
			if(this.hasNext() == false) {
				throw new NoSuchElementException();
			}
			else {
				this.lastItemReturned = this.nextItem; //sets last item returned to current next
				this.nextItem = this.nextItem.getNext(); //sets getNextItem to its own next
				index++;
			}  
			return lastItemReturned.getData();
		}
		
		/** previous : returns <T> value of previous item = lastItemReturned
		 * @param n/a
		 * @return T data
		 */
		//@throws NoSuchElementException if there is no such object
		@Override
		public T previous() {
			if(this.hasPrevious() == false) {   //Iterator is in front
				throw new NoSuchElementException();
			}
			else if(this.getNextItem() == null) { //If iterator is at the end
				this.nextItem = tail;
			}
			else {
				nextItem = nextItem.getPrevious(); //otherwise decrement nextItem and return it
			}
			lastItemReturned = nextItem;
			index--;
			return lastItemReturned.getData();
		}

		/** nextIndex : returns int value of current index
		 * @param n/a
		 * @return int index
		 */
		@Override
		public int nextIndex() {
			return this.getIndex();
		}

		/** previousIndex : returns int value of previous index
		 * @param n/a
		 * @return int index
		 */
		@Override
		public int previousIndex() {
			return this.getIndex() - 1;
		}
		
		/** add : adds object to list
		 * @param T data
		 * @return void
		 */
		@Override
        public void add(T data) {
            if (head == null) { // Add to an empty list.
                head = new Node<T>(data);
                tail = head;
            } else if (nextItem == head) { // Insert at head.
                // Create a new node.
                Node<T> newNode = new Node<T>(data);
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 1
                // Link nextItem to the new node.
                nextItem.previous = newNode; // Step 2
                // The new node is now the head.
                head = newNode; // Step 3
            } else if (nextItem == null) { // Insert at tail.
                // Create a new node.
                Node<T> newNode = new Node<T>(data);
                // Link the tail to the new node.
                tail.next = newNode; // Step 1
                // Link the new node to the tail.
                newNode.previous = tail; // Step 2
                // The new node is the new tail.
                tail = newNode; // Step 3
            } else { // Insert into the middle.
                // Create a new node.s
                Node<T> newNode = new Node<T>(data);
                // Link it to nextItem.prev.
                newNode.previous = nextItem.previous; // Step 1
                nextItem.previous.next = newNode; // Step 2
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 3
                nextItem.previous = newNode; // Step 4
            }
            // Increase size and index and set lastItemReturned.
            size++;
            index++;
            lastItemReturned = null;
        } 
		
		/** remove : removes object from list
		 * @param n/a
		 * @return void
		 */
		@Override
		public void remove() { 
            if (lastItemReturned == null) throw new IllegalStateException();
            Node<T> x = lastItemReturned.previous;
            Node<T> y = lastItemReturned.next;
	        if(x != null && y != null) {
	            x.next = y;
	            y.previous = x;
	            size--;
	            if (nextItem == lastItemReturned)
	                nextItem = y;
	            else
	                index--;
	            lastItemReturned = null;
	        }
        }

		/** set : sets object from list to parameter data
		 * @param T data
		 * @return void
		 */
		@Override
		public void set(T data) {
			if(lastItemReturned == null) {
				throw new IllegalStateException();
			}
			else {
				lastItemReturned.setData(data);
				lastItemReturned = null;
			}	
		}
		
	
	    /** indexOf :  Method to find the index of the first occurrence of an item in the list
	     * @param Object target - target to be searched for
	     * @return The index of the first occurrence of the target item or -1 if the item is not found.
	     */
	    public int indexOf(Object target) {
	    	//Declaration/Initialization
	    	Node<T> node = head;
	    	int index=0;
	    	//Search for Target
	    	if(node.data.equals(target)) { //check first 
	    		return 0;
	    	}
	         while(node.next!=null) { //loop rest
	        	 if(node.data.equals(target)) {
	        		 return index;
	        	 }
	        	 index++;
	        	 node = node.next;
	         }
	         return -1; //not found
	    }
	  
	    /** lastIndexOf: method to find the index of the last occurrence of an item in the list
	     * @param Object target to be found
	     * @return The index of the last occurrence of the target item or -1 if the item is not found.
	     */
	    public int lastIndexOf(Object target) {
	    	//Declaration/Initialization
	    	Node<T> node = tail;
	    	int index = size() - 1;
	    	//Check last value
	    	if(node.data.equals(target)) {
	    		return index;
	    	}
	         while(node.getPrevious() != null) {
	        	 if(node.data.equals(target)) {
	        		 return index;
	        	 }
	        	 index--;
	        	 node = node.getPrevious();
	         }
	         return -1;
	    }
	    
	    /** indexOfMin : Method to return the index of the minimum item in the list. It is assumed that each item in the list implements Comparable
	     * @return Index of the minimum item in the list or -1 if the list is empty
	     * @throws ClassCastExcepition if the list elements do not implement Comparable
	     */
	    public int indexOfMin() {
	        int index = 0;
	        int minIndex = 0;
	        Iterator iterator = new Iterator(this);
	        Comparable<T> minItem = null;
	        if (iterator.hasNext()) {
	            minItem = (Comparable<T>) iterator.next(); //BE CAREFUL!!! MUST BE COMPARABLE
	        } else {
	            return -1;
	        }
	        while (iterator.hasNext()) {
	            T nextItem = iterator.next();
	            index++;
	            if (minItem.compareTo(nextItem) >= 0) {
	                minItem = (Comparable<T>) nextItem;
	                minIndex = index;
	            }
	        }
	        return minIndex;
	    }
	}
	
	/***************************************************************************************************
	 * INNER CLASS Node<T> *****************************************************************************
	 ***************************************************************************************************/
	private static class Node<T> { //Comparable for equals method 
		
		//Instance Variables ***************************************************************************
		private T data;
		private Node<T> next;
		private Node<T> previous;
		
		//Constructors *********************************************************************************
		/** Node : default constructor for Node class
		 * @param n/a
		 * @return void
		 */
		public Node() {
			this.next=this.previous=null;
		}
		
		/** Node : filled constructor for Node class
		 * @param T data
		 * @return void
		 */
		public Node(T data) {
			this.setData(data);
		}
		
		/** Node : filled constructor for Node class
		 * @param Node<T> previous, T data, Node<T> next
		 * @return void
		 */
		public Node(Node<T> previous, T data, Node<T> next) {
			this.next = next;
			this.previous = previous;
			this.setData(data);
		}
		
		/** Node : filled constructor for Node class
		 * @param T data, Node<T> previous, Node<T> next
		 * @return void
		 */
		public Node(T data, Node<T> previous, Node<T> next) {
			this.next = next;
			this.previous = previous;
			this.setData(data);
		}
		
		//Mutators/Setters ******************************************************************************
		/** setData : mutator for data instance variable
		 * @param T data
		 * @return void
		 */
		public void setData(T data) {
			this.data = data;
		}
		
		/** setPrevious : mutator for previous instance variable
		 * @param Node<T> previous
		 * @return void
		 */
		public void setPrevious(Node<T> previous) {
			if(previous instanceof Node) {
				this.previous = next;
			} 
			else this.next = null;
		}
		
		/** setNext : mutator for next instance variable
		 * @param Node<T> next
		 * @return void
		 */
		public void setNext(Node<T> next) {
			if(next instanceof Node) {
				this.next = next;
			} 
			else this.next = null;
		}
		
		/** setAll : mutator for all instance variables
		 * @param T data, Node<T> previous, Node<T> next
		 * @return void
		 */
		public void setAll(T data, Node<T> previous, Node<T> next) {
			this.setData(data);
			this.setPrevious(previous);
			this.setNext(next);
		}
		
		/** setAll : mutator for all instance variables
		 * @param Node<T> previous, T data, Node<T> next
		 * @return void
		 */
		public void setAll(Node<T> previous, T data, Node<T> next) {
			this.setData(data);
			this.setPrevious(previous);
			this.setNext(next);
		}
			
		//Mutators/Getters **************************************************************************
		/** getData : accessor for data instance variable
		 * @param n/a
		 * @return T data
		 */
		public T getData() {
			return this.data;
		}
		
		/** getPrevious : accessor for previous instance variable
		 * @param n/a
		 * @return Node<T> previous
		 */
		public Node<T> getPrevious() {
			return this.previous;
		}
		
		/** getNext : accessor for next instance variable
		 * @param n/a
		 * @return Node<T> next
		 */
		public Node<T> getNext() {
			return this.next;
		}
		
		//Other Methods ******************************************************************************
		/** clone : returns clone of this Node
		 * @param n/a
		 * @return Node copy
		 * */
		@Override
	    public Object clone() {
	        try {
				return (Node)super.clone(); 
			} catch (CloneNotSupportedException e) {
				return null; 
			}
	    }
		
		/** equals : returns boolean representation of Singly equality
		 * @param Singly<T> other
		 * @return boolean areEqual
		 */
		public boolean equals(Node<T> other) {
			if (this.next == other.getNext()  //check reference 
			 && this.previous == other.getPrevious()//check reference
			 && this.getData().equals(other.getData())) {
				 return true;
			 }
			else return false;
		}
		/** toString : returns readable string representation of class
		 * @param n/a
		 * @return String string
		 */
		public String toString() {
			return "" + this.getData();
		}
	
		/*****************************************************************************************
		* END OF INNER CLASS Node ****************************************************************
	    ******************************************************************************************/
	}
	
}