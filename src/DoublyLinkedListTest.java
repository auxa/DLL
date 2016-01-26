import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 12/10/15 20:43:36
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        assertEquals("Check", "1", testDLL.toString());
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );


        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    
    @Test
    public void testEmpty()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);
        testDLL.isEmpty();
        assertEquals( "Checking empty list", "1,2", testDLL.toString() );
    }
    @Test
    public void testGet(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertEquals("testing the empty list", null, testDLL.get(0));

    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        Integer expect = 1;
        assertEquals("testing the empty list", expect, testDLL.get(0));
        expect = 2;
        assertEquals("testing the empty list", expect, testDLL.get(1));

        assertEquals("Get the value of pos 0", null, testDLL.get(-1));
        testDLL.insertBefore(2,3);
        expect = 3;
        assertEquals("Get the value in the tail", null, testDLL.get(1000));
        testDLL.insertBefore(0,4);
        expect = 2;
        assertEquals("Get the value of pos 2", expect, testDLL.get(2));
        testDLL.insertBefore(4, 5);
        testDLL.insertBefore(5, 6);
        testDLL.insertBefore(6, 7);
        expect = 5;
        assertEquals("Checking value within DLL",expect, testDLL.get(4) );
    	
    }
    @Test
    public void testStack(){
    	
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertEquals("Check null pop", null, testDLL.pop());
        testDLL.push(1);
        testDLL.push(2);
        Integer expect = 2;
        assertEquals("Check pop metod", expect, testDLL.pop());
    }
    
    @Test
    public void testQueue(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertEquals("Testing for null", null, testDLL.dequeue());
    	testDLL.enqueue(1);
    	testDLL.enqueue(2);
    	Integer expect = 1;
    	assertEquals("testing if the value is dequed success", expect, testDLL.dequeue() );
    }
    @Test
    public void testDelete(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertEquals("testing empty", false, testDLL.deleteAt(0));

    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2); 
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);        
        assertEquals("testing below", true, testDLL.deleteAt(0));

        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        
    //    assertEquals("testing below", true, testDLL.deleteAt(0));
        assertEquals("testing below", false, testDLL.deleteAt(7));

        assertEquals("testing below", true, testDLL.deleteAt(6));


        assertEquals("testing below", false, testDLL.deleteAt(-1));
        assertEquals("testing above", false, testDLL.deleteAt(19));
        assertEquals("Testing middle", true, testDLL.deleteAt(2));
    }
}
