/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 11/25/16 7:00PM
***********************************************************************
* TITLE:			Main
* CLASS DESCRIPTION: Tester class for BinarySearchTreeWithRotation, AVLTree
* 					 insertion and traversals toString & depth testing. 
***********************************************************************/

package driver;

import model.AVLTree;
import model.BinaryTree;

public class Main {

	public static void main(String[] args) {
		
		//Integer Test ******************************************
		int treeSize = 100;
		//create Integer trees
		AVLTree<Integer> intAVLTree = new AVLTree<Integer>(); //Binary AVL
		BinaryTree<Integer> intBinaryTree = new BinaryTree<Integer>(); //BinarySearch
		//create integer array
		Integer[] integers = new Integer[treeSize]; //store ints in array
		//fill array with random values (1-1000), add to tree
		for(Integer num : integers) {
			num = (int)(1000*Math.random()) + 1;
			intAVLTree.add(num); //add num to AVLTree
			intBinaryTree.add(num); //add to BinarySearchTree
		}
		
		//output tree pre order
		System.out.println("PRE ORDER *********************************************************************" + 
						    "******************************************************************************");
		System.out.println(intAVLTree.showPreOrder("AVLTree ******************************************************"));
		System.out.println("\n\n");
		System.out.println(intBinaryTree.showPreOrder("BINARYTREE SORTED *****************************************"));
		
		//output tree in order
		System.out.println("\n\n\n\nPOST ORDER ********************************************************************" + 
							"******************************************************************************");
		System.out.println(intAVLTree.showInOrder("AVLTree ********************************************************"));
		System.out.println("\n\n");
		System.out.println(intBinaryTree.showInOrder("BINARYTREE SORTED *******************************************"));
	}

}