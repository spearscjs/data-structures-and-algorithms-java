/**********************************************************************
* AUTHOR: spearscjs 
* LAST MODIFIED: 11/13/2016
***********************************************************************
* TITLE:				KWHashMap
* PROGRAM DESCRIPTION: KWHashMap interface - DataStructuresJavaKoffman implementation 
***********************************************************************/

package model;

public abstract interface KWHashMap<K, V> {
	
	//Abstract HashMap Methods *******************************************************************
	/** get: retrieve item from table using key instance
	 * @param Object key //note: object instead of K due to necessity of hashCode() method
	 * @return V key //null if key not found
	 */
	public abstract V get(Object key);
	
	/** put: add item to table given a key to be hashed and value to be stored
	 * @param K key
	 * @return V key //null if key not found; if key already in table - replaces and returns previous value
	 */
	public abstract V put(K key, V value);
	
	/** remove: remove item from table using key instance
	 * @param Object key //note: object instead of K due to necessity of hashCode() method
	 * @return V key //null if key not found
	 */
	public abstract V remove(Object key);
	
	/** isEmpty: return boolean value based on table being null or empty
	 * @param n/a
	 * @return boolean isEmpty //table is empty
	 */
	public abstract boolean isEmpty();
	
	/** size: return size of table
	 * @param n/a
	 * @return int numKeys
	 */
	public abstract int size();
	
}