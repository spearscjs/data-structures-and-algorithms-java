/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 11/13/2016
***********************************************************************
* TITLE:				JUnitHashTableChain
* PROGRAM DESCRIPTION: JUnit Testing for HashTableChain class
***********************************************************************/

package driver;

import org.junit.Assert;
import org.junit.Test;
import model.HashTableChain;

public class HashTableChainJUnit {

	//IMPORTANT **************************************************************************************
	//Method Naming Convention: testKeyTypeOtherInfo (i.e. testIntegerSize7) 
	//Assert Convention: (expected, actual)
	/*************************************************************************************************
	 * KWHashMap<Integer,Integer> Testing : default Integer rehash method implemented ****************
	 *************************************************************************************************/
	//Table Size 7 Testing ***************************************************************************
	//create table - note: table reinitialized before every test to all null values at size
	HashTableChain<Integer,Integer>  hashTableInt = new HashTableChain<Integer,Integer>(7);
	
	//put() testing *****************
	/** putIntKey7 : tests multiple conditions for put method with size 7 table, no rehashing
	 * @Test HashTableChain put()
	 * @Test HashTableChain get()
	 * @Test HashTableChain size()
	 * @Test HashableChain remove()
	 * @Test HashTableChain numCollisions
	 * @Test HashTableChain numOverrides
	 * @Test HashTableChain numKeys
	 */
	@Test
	public void testIntegerSize7() {
		System.out.println("\n\ntestIntegerSize7(): HashTableChain put(), get() testing ***********************");
		//put() test (key: value) ********************************
		//1: 7
		Assert.assertEquals(null, hashTableInt.put(1, 7)); //ensure valid return for put (empty so null)
		Assert.assertEquals(7, (int)hashTableInt.get(1)); //ensure entry was placed in correct position
		//2: 2
		Assert.assertEquals(null, hashTableInt.put(2, 2)); //ensure valid return for put (empty so null)
		Assert.assertEquals(2, (int)hashTableInt.get(2)); //ensure entry was placed in correct position
		//5: 5
		Assert.assertEquals(null, hashTableInt.put(5, 5)); //ensure valid return for put (empty so null)
		Assert.assertEquals(5, (int)hashTableInt.get(5)); //ensure entry was placed in correct position
		//put() override test
		//1: 1
		Assert.assertEquals(7, (int)hashTableInt.put(1, 1)); //ensure valid return for put (replacing previous key 1 so should return 7 (previous value))
		Assert.assertEquals(1, (int)hashTableInt.get(1)); //ensure data was overridden correctly, replaced value at correct index
		Assert.assertEquals(1, hashTableInt.getNumOverrides());
		//put() collision test 
		//8: 8
		Assert.assertEquals(null, hashTableInt.put(8, 8)); //ensure valid return for put (empty so null)
		Assert.assertEquals(8, (int)hashTableInt.get(8)); //ensure entry was placed in correct position
		//numCollisions, numOverrides, numKeys, size() test 
		Assert.assertEquals(2, hashTableInt.getNumCollisions()); //numOfCollisions check (one is due to override)
		Assert.assertEquals(1, hashTableInt.getNumOverrides()); //numOfOverrides re-check
		Assert.assertEquals(4, hashTableInt.getNumKeys()); //numKeys check
		Assert.assertEquals(4, hashTableInt.size()); //size check
		//output table post test
		System.out.println("Table after putting:\n" + hashTableInt);
		
		//remove() test **************************************
		//key: 8 in table 
		Assert.assertEquals(8, (int)hashTableInt.remove(8)); //ensure return value
		Assert.assertEquals(null, hashTableInt.get(8)); //ensure deletion
		//key: 8 not in table
		Assert.assertEquals(null, hashTableInt.remove(8)); //ensure return null when not found
		//output table post test
		System.out.println("Table after removal of 8 key\n" + hashTableInt); 
	}
	
	
	/** testIntSize7Rehash : tests table that exceeds threshold and must be rehashed during put() method (table starts at size 7)
	 * @Test HashTableChain put()
	 * @Test HashTableChain rehash()
	 * @Test HashTableChain get()
	 * @Test HashTableChain size()
	 * @Test HashTableChain remove()
	 * @Test HashTableChain numCollisions 
	 * @Test HashTableChain numOverrides
	 * @Test HashTableChain numKeys
	 */
	@Test
	public void testIntegerRehash() {
		System.out.println("\n\ntestIntegerRehash: put(),  rehash() (within put() method),  getTable(), numOfCollisions ******************************");
		//assumes put method works before rehash call
		hashTableInt.put(3, 3);
		hashTableInt.put(8, 8);
		hashTableInt.put(1, 1);
		hashTableInt.put(4, 4);
		hashTableInt.put(10,10);
		Assert.assertEquals(2, hashTableInt.getNumCollisions()); //numOfCollisions check
		//output table before rehash
		System.out.println("Table before rehash:\n" + hashTableInt);
		
		//rehash
		//add 0
		Assert.assertEquals(null, hashTableInt.put(0, 0)); //ensure valid return for put (empty so null)
		Assert.assertEquals(0, (int)hashTableInt.get(0)); //ensure entry was placed in correct position
		//add 6
		Assert.assertEquals(null, hashTableInt.put(6, 6)); //ensure valid return for put (empty so null)
		Assert.assertEquals(6, (int)hashTableInt.get(6)); //ensure entry was placed in correct position
		//add 12
		Assert.assertEquals(null, hashTableInt.put(12, 12)); //ensure valid return for put (empty so null)
		Assert.assertEquals(12, (int)hashTableInt.get(12)); //ensure entry was placed in correct position
		//add 15
		Assert.assertEquals(null, hashTableInt.put(15, 15)); //ensure valid return for put (empty so null)
		Assert.assertEquals(15, (int)hashTableInt.get(15)); //ensure entry was placed in correct position
		//test rehash table size growth, numCollisions, numOverrides
		Assert.assertEquals(15, hashTableInt.getTable().length); //ensure table length is doubled and odd
		Assert.assertEquals(1, hashTableInt.getNumCollisions()); //numOfCollisions check
		Assert.assertEquals(0, hashTableInt.getNumOverrides()); //numOfOverrides check
		Assert.assertEquals(9, hashTableInt.getNumKeys()); //numKeys check
		Assert.assertEquals(9, hashTableInt.size()); //size check
		//output table after rehash and put(Integer, Integer) calls
		System.out.println("Table after rehash and putting using keys 0, 6, 12, 15:\n" + hashTableInt);
		
		//remove() test **************************************
		Assert.assertEquals(8, (int)hashTableInt.remove(8));
		Assert.assertEquals(null, hashTableInt.get(8)); //ensure deletion
		Assert.assertEquals(15, (int)hashTableInt.remove(15));
		Assert.assertEquals(null, hashTableInt.get(15)); //ensure deletion
		//output table post test
		System.out.println("Table after removal of 8, 15 keys\n" + hashTableInt);
	}
	
	/*************************************************************************************************
	 * KWHashMap<String,String> Testing : default String rehash method implemented ****************
	 *************************************************************************************************/
	//Table Size 7 Testing ***************************************************************************
	//create table - note: table reinitialized before every test to all null values at size
	HashTableChain<String, String> hashTableString = new HashTableChain<String,String>(7);
	
	//put() testing *****************
	/** putIntKey7 : tests multiple conditions for put method with size 7 table, no rehashing
	 * @Test HashTableChain put()
	 * @Test HashTableChain get()
	 * @Test HashTableChain size()
	 * @Test HashableChain remove()
	 * @Test HashTableChain numCollisions
	 * @Test HashTableChain numOverrides
	 * @Test HashTableChain numKeys
	 */
	@Test
	public void testStringSize7() {
		System.out.println("\n\ntestStringSize7(): HashTableChain put(), get() testing ***********************");
		//put() test (key: value) ********************************
		//one: seven
		Assert.assertEquals(null, hashTableString.put("one", "seven")); //ensure valid return for put (empty so null)
		Assert.assertEquals("seven", hashTableString.get("one")); //ensure entry was placed in correct position
		//two: two
		Assert.assertEquals(null, hashTableString.put("two", "two")); //ensure valid return for put (empty so null)
		Assert.assertEquals("two", hashTableString.get("two")); //ensure entry was placed in correct position
		//five: five
		Assert.assertEquals(null, hashTableString.put("five", "five")); //ensure valid return for put (empty so null)
		Assert.assertEquals("five", hashTableString.get("five")); //ensure entry was placed in correct position
		//put() override test
		//one: one
		Assert.assertEquals("seven", (hashTableString.put("one", "one"))); //ensure valid return for put (replacing previous key 1 so should return 7 (previous value))
		Assert.assertEquals("one", hashTableString.get("one")); //ensure data was overridden correctly, replaced value at correct index
		Assert.assertEquals(1, hashTableString.getNumOverrides());
		//put() collision test 
		//eight: eight
		Assert.assertEquals(null, hashTableString.put("eight", "eight")); //ensure valid return for put (empty so null)
		Assert.assertEquals("eight", hashTableString.get("eight")); //ensure entry was placed in correct position
		//numCollisions, numOverrides, numKeys, size() test 
		Assert.assertEquals(2, hashTableString.getNumCollisions()); //numOfCollisions check (one is due to override)
		Assert.assertEquals(1, hashTableString.getNumOverrides()); //numOfOverrides re-check
		Assert.assertEquals(4, hashTableString.getNumKeys()); //numKeys check
		Assert.assertEquals(4, hashTableString.size()); //size check
		//output table post test
		System.out.println("Table after putting:\n" + hashTableString);
		
		//remove() test **************************************
		//key: eight in table 
		Assert.assertEquals("eight", hashTableString.remove("eight")); //ensure return value
		Assert.assertEquals(null, hashTableString.get("eight")); //ensure deletion
		//key: eight not in table
		Assert.assertEquals(null, hashTableString.remove("eight")); //ensure return null when not found
		//output table post test
		System.out.println("Table after removal of eight key\n" + hashTableString); 
	}
	
	
	/** testIntSize7Rehash : tests table that exceeds threshold and must be rehashed during put() method (table starts at size 7)
	 * @Test HashTableChain put()
	 * @Test HashTableChain rehash()
	 * @Test HashTableChain get()
	 * @Test HashTableChain size()
	 * @Test HashTableChain remove()
	 * @Test HashTableChain numCollisions 
	 * @Test HashTableChain numOverrides
	 * @Test HashTableChain numKeys
	 */
	@Test
	public void testStringRehash() {
		System.out.println("\n\ntestStringRehash: put(),  rehash() (within put() method),  getTable(), numOfCollisions ******************************");
		//assumes put method works before rehash call
		hashTableString.put("three", "three");
		hashTableString.put("eight", "eight");
		hashTableString.put("one", "one");
		hashTableString.put("four", "four");
		hashTableString.put("ten","ten");
		Assert.assertEquals(1, hashTableString.getNumCollisions()); //numOfCollisions check
		//output table before rehash
		System.out.println("Table before rehash:\n" + hashTableString);
		
		//rehash
		//add "zero"
		Assert.assertEquals(null, hashTableString.put("zero", "zero")); //ensure valid return for put (empty so null)
		Assert.assertEquals("zero", hashTableString.get("zero")); //ensure entry was placed in correct position
		//add "six"
		Assert.assertEquals(null, hashTableString.put("six","six")); //ensure valid return for put (empty so null)
		Assert.assertEquals("six", (hashTableString.get("six"))); //ensure entry was placed in correct position
		//add "twelve"
		Assert.assertEquals(null, hashTableString.put("twelve", "twelve")); //ensure valid return for put (empty so null)
		Assert.assertEquals("twelve", hashTableString.get("twelve")); //ensure entry was placed in correct position
		//add "fifteen"
		Assert.assertEquals(null, hashTableString.put("fifteen", "fifteen")); //ensure valid return for put (empty so null)
		Assert.assertEquals("fifteen", hashTableString.get("fifteen")); //ensure entry was placed in correct position
		//test rehash table size growth, numCollisions, numOverrides
		Assert.assertEquals(15, hashTableString.getTable().length); //ensure table length is doubled and odd
		Assert.assertEquals(1, hashTableString.getNumCollisions()); //numOfCollisions check
		Assert.assertEquals(0, hashTableString.getNumOverrides()); //numOfOverrides check
		Assert.assertEquals(9, hashTableString.getNumKeys()); //numKeys check
		Assert.assertEquals(9, hashTableString.size()); //size check
		//output table after rehash and put(Integer, Integer) calls
		System.out.println("Table after rehash and putting using keys zero, six, twelve, fifteen:\n" + hashTableString);
		
		//remove() test **************************************
		Assert.assertEquals("eight", (hashTableString.remove("eight")));
		Assert.assertEquals(null, hashTableString.get("eight")); //ensure deletion
		Assert.assertEquals("fifteen", hashTableString.remove("fifteen"));
		Assert.assertEquals(null, hashTableString.get("fifteen")); //ensure deletion
		//output table post test
		System.out.println("Table after removal of eight, fifteen keys\n" + hashTableString);
	}

}