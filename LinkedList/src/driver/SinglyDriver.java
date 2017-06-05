/******************************************************************************
 *  Compilation:  javac DoublySingly.java
 *  Execution:    java DoublySingly
 *  Dependencies: System.out.java
 *
 *  A list implemented with a doubly linked list. The elements are stored
 *  (and iterated over) in the same order that they are inserted.
 * 
 ******************************************************************************/
package driver;

import java.util.ListIterator;
import model.Singly;

public class SinglyDriver {
    // a test client
    public static void main(String[] args) {

        // add elements 1, ..., n
        System.out.println("integers between 0 and 99\n\n");
        Singly<Integer> list = new Singly<Integer>();
        for (int i = 0; i < 100; i++)
            list.addLast(i);
        System.out.println(list);
        System.out.println();

        ListIterator<Integer> iterator = list.listIterator(0);

        // go forwards with next() and set()
        System.out.println("add 1 to each element via next() and set()");
        while (iterator.hasNext()) {
            int x = iterator.next();
            iterator.set(x + 1);
        }
        System.out.println(list + "\n");

        // go backwards with previous() and set()
        System.out.println("multiply each element by 3 via previous() and set()");
        while (iterator.hasPrevious()) {
            int x = iterator.previous();
            iterator.set(x + x + x);
        }
        System.out.println(list);
        System.out.println();


        // remove all elements that are multiples of 4 via next() and remove()
        System.out.println("remove elements that are a multiple of 4 via next() and remove()");
        while (iterator.hasNext()) {
            int x = iterator.next();
            if (x % 4 == 0){
            	iterator.remove();
            }
        }
        System.out.println(list);
        System.out.println();


        // remove all even elements via previous() and remove()
        System.out.println("remove elements that are even via previous() and remove()");
        while (iterator.hasPrevious()) {
            int x = iterator.previous();
            if (x % 2 == 0) iterator.remove();
        }
        System.out.println(list);
        System.out.println();


        // add elements via next() and add()
        System.out.println("add elements via next() and add()");
        while (iterator.hasNext()) {
            int x = iterator.next();
            iterator.add(x + 1);
        }
        System.out.println(list);
        System.out.println();

        // add elements via previous() and add()
        System.out.println("add elements via previous() and add() - multiplies next by 10");
        while (iterator.hasPrevious()) {
            int x = iterator.previous();
            iterator.add(x * 10);
            iterator.previous();
        }
        System.out.println(list);
        System.out.println();
    }
}