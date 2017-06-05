/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 9/17/16 9:00PM
***********************************************************************
* TITLE:				CJSArrayListMethods
* PROGRAM DESCRIPTION:	Contains useful static methods for CJSArrayList 
***********************************************************************/
package model;

import java.util.ArrayList;

public class CJSArrayList<T> extends ArrayList<T> {
	
	/** addToFront : adds student parameter to front of this parameter
	 * @param CJSArrayList<Student> this
	 * @param Student student
	 * @return void
	 */
	public void addToFront(T t) {
		this.add(0, t);
	}
	
	
	/** addToEnd : adds student parameter to end of this parameter
	 * @param CJSArrayList<Student> this
	 * @param Student student
	 * @return void
	 */
	public void addToEnd(T t) {
		if(this.size() > 0)
		{
			this.add(this.size(), t);
		}
		else
		{
			this.add(t);
		}
	}
	
}