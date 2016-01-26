import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import junit.framework.Test;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 12/10/15 20:44:01
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}

	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
	}
	private int size =0;
	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic runtime cost: Theta(1)
	 *
	 * Justification:
	 *  The run time is only that of a comparison of a local variable so the run time is low.
	 */
	public boolean isEmpty()
	{
		if ( head == null){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *Worst-case asymptotic runtime cost: Theta(n)
	 *
	 * Justification:
	 *  The worst case scenario would be if we had to insert an element into the second last loaction on the list
	 *  In this case we would have to iterate through the entire array apart from the last element so the run time would be n-1
	 */
	public void insertBefore( int pos, T data ) 
	{
		System.out.println(size + "hello");

		if(isEmpty()){
			head=tail= new DLLNode(data, null, null);
			size++;
			return;
		}



		if(pos <= 0){
			insertFirst(data);
		}
		else if (pos >= size){
			insertLast(data);
		}
		else{
			int temp=1;
			tail.next = null;
			for (DLLNode iter = head; iter != null; iter = iter.next){

				if(pos == temp){
					DLLNode tempor = new DLLNode(data, null, null);
					DLLNode test = iter;
					DLLNode test2 = iter.next;
					tempor.prev=test;
					tempor.next=test2;
					test2.prev=tempor;
					test.next=tempor;
					size++;
					break;

				}
				temp++;

			}
		}



	}
	private void insertLast(T data){
		DLLNode tempNode1 = new DLLNode(data, null, null);
		tempNode1.prev=tail;
		tail.next=tempNode1;
		tail=tempNode1;
		size++;
	}
	private void insertFirst(T data){
		DLLNode tempNode = new DLLNode(data, null, null);
		tempNode.next = head;
		head.prev = tempNode;
		head = tempNode;
		size++;
	}
	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 *Worst-case asymptotic runtime cost: Theta(n)
	 *
	 * Justification:
	 *  In the worst case we would have to get the data from the second last postion so the run time would be n-1 whish is theta of n
	 *
	 * Worst-case precise runtime cost: 4n + 3
	 *
	 * Justification:
	 *  The worst case would be getting data from the second last location
	 *  in this case: 1 + 1 + N + N + N + N + 1
	 *  			: 4N + 3
	 */
	public T get(int pos) 
	{
		if(isEmpty()){
			return null;
		}
		if(pos <0 || pos > size-1){
			return null;
		}else if(pos == 0){
			pos =0;
			return head.data;
		}else if(pos == (size-1)){
			return getLast();
		}else{
			int temp=0;
			for (DLLNode iter = head; iter != null; iter = iter.next){

				if( temp == pos){
					return iter.data;
				}
				temp++;

			}
		}

		return null;
	}
	public T getLast(){

		return tail.data;

	}
	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic runtime cost: Theta(n)
	 *
	 * Justification:
	 *  The worst case would be to delete from the second last node so the run time would be n
	 */
	public boolean deleteAt(int pos) 
	{
		if(isEmpty() || pos < 0 || pos > (size-1)){
			return false;
		}else if(size ==1){
			head = tail = null;
			size--;
			return true;
		}
		else if(pos == 0 ){
			DLLNode test= head.next;
			test.prev=null;
			head = test;
			size--;
			return true;
		}else if(pos == size-1){
			deletelast();
			return true;
		}
		else{
			int temp=1;
			tail.next = null;
			for (DLLNode iter = head; iter != null; iter = iter.next){

				if(pos == temp){
					size--;
					DLLNode temp2 = iter.next;
					DLLNode temp3 = temp2.next;
					iter.next=temp3;
					temp3.prev=iter;
					return true;
				}
				temp++;
			}

		}

		return false;
	}
	private boolean deletelast(){
		DLLNode test= tail.prev;
		tail = test;
		test.next=null;
		size--;
		return true;
	}


	/*----------------------- STACK */
	/**
	 * This method should behave like the usual push method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic runtime cost: Theta(1)
	 *
	 * Justification:
	 *  We are only inserting at the front so we don't need to iterate through the array,
	 *  we already have a pointer and thus the run time is very small
	 */
	public void push(T item) 
	{
		if(isEmpty()){
			head=tail= new DLLNode(item, null, null);
			size++;
			return;
		}
		insertLast(item);

	}

	/**
	 * This method should behave like the usual pop method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: Theta(1)
	 *
	 * Justification:
	 *  We already have a pointer to the end of the array and thus we only need to pop this off and update the pointer
	 *  so the run time is low
	 */
	public T pop() 
	{
		if(isEmpty()){
			return null;
		}else{
			T temp = getLast();
			size--;
			deletelast();
			return temp;
		}
	}

	/*----------------------- QUEUE */

	/**
	 * This method should behave like the usual enqueue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic runtime cost: TODO
	 *
	 * Justification:
	 *  TODO
	 */
	public void enqueue(T item) 
	{
		if(isEmpty()){
			head=tail= new DLLNode(item, null, null);
			size++;
			return;
		}
		insertFirst(item);
	}

	/**
	 * This method should behave like the usual dequeue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the earliest item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: Theta(1)
	 *
	 * Justification:
	 *  Just updating the first pointer and creating the new node
	 */
	public T dequeue() 
	{
		if(isEmpty()){
			return null;
		}
		else{
			T temp =getLast();
			deletelast();
			return temp;
		}
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}
}