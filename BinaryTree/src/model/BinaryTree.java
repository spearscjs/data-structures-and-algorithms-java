package model;

//Imports ***********************************************************************************
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BinaryTree<E extends Comparable> {
	
	//Instance Variables ********************************************************************
	protected Node<E> root;
	
	//Constructors **************************************************************************
	/** BinaryTree : default constructor
	 * @param n/a
	 * @return n/a
	 */
	public BinaryTree() {
		this.root = null;
	}
	
	/** BinaryTree : filled root constructor
	 * @param Node<E> root
	 * @return n/a
	 */
	protected BinaryTree(Node<E> root) {
		this.root = root; 
	}
	
	/** BinaryTree : filled root, root.left tree, rroot.right tree constructor
	 * @param Node<E> root
	 * @param BinaryTree<E> leftTree
	 * @param BinaryTree<E> rightTree
	 * @return n/a
	 */
	public BinaryTree(E data, BinaryTree<E> leftSubtree, BinaryTree<E> rightSubtree) {
		//set root using data
		this.root = new Node<E>(data);
		//set root.left to leftSubTree
		if(leftSubtree != null) {
			this.root.left = leftSubtree.root;
		}
		else this.root.left = null;
		//set root.right to rightSubTree
		if(rightSubtree != null) {
			this.root.right = rightSubtree.root;
		}
		else this.root.right = null;
	}
	
	//Accessors / Getters *******************************************************************
	/** getLeftSubtree : accessor for root.left, returns tree with root.left as root
	 * @param n/a
	 * @return BinaryTree<E> leftSubtree
	 */
	public BinaryTree<E> getLeftSubtree() {
		if(root != null) {
			return new BinaryTree<E>(root.left);
		}
		else return null;
	}
	
	/** getRightSubtree : accessor for root.right, returns tree with root.right as root
	 * @param n/a
	 * @return BinaryTree<E> rightSubtree
	 */
	public BinaryTree<E> getRightSubtree() {
		if(root != null) {
			return new BinaryTree<E>(root.right);
		}
		else return null;
	}
	
	/** getData : accessor for root.data instance variable, returns root.data
	 * @param n/a
	 * @return E data
	 */
	public E getData() {
		if(root != null) {
			return root.data;
		}
		else return null;
	}
	
	//Tree Methods **************************************************************************
	/** isLeaf : checks if current root is a leaf (both left & right are null)
	 * @param n/a
	 * @return boolean isLeaf
	 */
	public boolean isLeaf() {
		return this.root != null && this.root.left == null && this.root.right == null;
	}
	
	/** preOrderTraverse : traverses tree in preOrder (root, leftSubtree, rightSubtree)
	 * 					   stores each Node<E> toString in StringBuilder in order and 
	 * 					   depth tracks the current depth of traversal 
	 * @param Node<E> current, int depth, StringBuilder sb
	 * @return n/a
	 */
	private void preOrderTraverse(Node<E> current, int depth, StringBuilder sb) {
		//adds spacing for depth for readability
		for (int i = 1; i < depth; i++) {
			sb.append(" ");
		} 
		//base case : no recursive call (found leaf)
		if(current == null) {
			sb.append("null\n");
		}
		//traversal, all the way left and then right
		else {
			sb.append(current.toString() + "\n");
			this.preOrderTraverse(current.left, depth+1, sb);
			this.preOrderTraverse(current.right, depth+1, sb);
		}
	}
	
	//File I/O Methods **********************************************************************
	//Binary ***************************
	/** inputFromBinaryFile : inputs / creates tree from binary file
	 * @param String fileName
	 * @param BinaryTree tree 
	 * @return n/a
	 */
	public static void inputFromBinaryFile(String fileName, BinaryTree tree) {
		try {
			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(fileName));
			tree = (BinaryTree)inStream.readObject();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		} 
	}
	
	/** outputToBinaryFile : outputs tree to binary file
	 * @param String fileName
	 * @param BinaryTree tree 
	 * @return n/a
	 */
	public static void readBinaryTree(String fileName, BinaryTree tree) {
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileName));
			outStream.writeObject(tree);
			outStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

	//Text **************************
	/** readBinaryTree : reads binary tree from text file
	 * @param Scanner scan
	 * @return n/a
	 */
	//reads tree from .txt in form of the preorder to string
	public static BinaryTree<String> readBinaryTree(Scanner scan) {
		String data = scan.next();
		if (data.equals("null")) {
				return null;
		} 
		else {
			BinaryTree<String> leftTree = readBinaryTree(scan);
			BinaryTree<String> rightTree = readBinaryTree(scan);
			return new BinaryTree<String>(data, leftTree, rightTree);
		}
	} 

	/** outputToTextFile : outputs tree to text file (appends)
	 * @param String fileName
	 * @param BinaryTree tree 
	 * @return n/a
	 */
	public static void outputToTextFile(BinaryTree<String> tree, String fileName) {
		try {
			BufferedWriter outStream = new BufferedWriter(new FileWriter(fileName, true));
			outStream.write(tree.toString());
			outStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
	

	
	//Other Standard Methods ****************************************************************
	/** toString : BinaryTree toString method - returns each node toString method in preOrder
	 * @param n/a
	 * @return String data
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		this.preOrderTraverse(this.root, 1, sb);
		return sb.toString();
	}
	
	
	/****************************************************************************************
	 * INNER CLASS: Node ********************************************************************
	 ****************************************************************************************/
	protected static class Node<E extends Comparable> implements Serializable, Comparable {
		
		//Instance Variables ****************************************************************
		protected E data;
		protected Node<E> left;
		protected Node<E> right;
		
		//Constructors **********************************************************************
		/** Node : default constructor
		 * @param n/a
		 * @return n/a
		 */
		public Node() {
			this.data = null;
			this.left = null;
			this.right = null;
		}
		
		/** Node : filled data constructor
		 * @param E data
		 * @return n/a
		 */
		public Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		//Other Standard Methods ************************************************************
		/** toString : Node toString method - returns data toString method
		 * @param n/a
		 * @return String data
		 */
		public String toString() {
			return this.data.toString();
		}
		
		/** compareTo : compares letter to other letter
		 * @param Object other
		 * @return int result //0 if same, - if less, + if greater
		 */
		@Override
		public int compareTo(Object other) {
			return this.data.compareTo((E)other);
		}
		
	}
}