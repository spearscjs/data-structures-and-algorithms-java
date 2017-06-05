/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 11/25/16 7:00PM
***********************************************************************
* TITLE:			BinarySearchTreeWithRotate
* CLASS DESCRIPTION: This class extends the BinarySearchTree by adding the rotate
* 	 				 operations. Rotation will change the balance of a search
*  					 tree while preserving the search tree property.
* 	 				 Used as a common base class for self-balancing trees.
* @author Koffman and Wolfgang - majority of methods from book
***********************************************************************/
package model;

public class BinaryTreeRotations <E extends Comparable<E>> extends BinaryTree<E> {
  // Methods
  /** Method to perform a right rotation.
      pre:  root is the root of a binary search tree.
      post: root.right is the root of a binary search tree,
            root.right.right is raised one level,
            root.right.left does not change levels,
            root.left is lowered one level,
            the new root is returned.
      @param root The root of the binary tree to be rotated
      @return The new root of the rotated tree
   */
  protected Node < E > rotateRight(Node < E > root) {
    Node < E > temp = root.left;
    root.left = temp.right;
    temp.right = root;
    return temp;
  }

  /** Method to perform a left rotation (rotateLeft).
/**** EXERCISE ****/
  /** rotateLeft : method to perform left rotation.
	 * pre:  root is root of binary tree
	 * post: root.left is the root of the binary search tree
	 * 		 root.left.left is raised one level
	 * 	 	 root.left.right has no level changes
	 * 		 root.right is lowered a level
	 * 		 the new root is returned
	 * @param root The root of binary tree to be rotated
	 * @return The root of the rotated tree
	 */
  protected Node<E> rotateLeft(Node<E> root) {
		Node<E> temp = root.right;
		root.right = temp.left;
		temp.left = root;
		return temp;
	}
}
