/*Anders Stadum
 * 313 Lab1
 * 10/17/2013
 * Queue.java
 * 
 */


class Queue
{
	private CharNode head = null;
	private CharNode tail = null;
	
	public void enqueue(char j) // put item at rear of queue
	{
		CharNode qNode = new CharNode(j);
		if (isEmpty())
			head = qNode;
		else
			tail.next = qNode;
		tail = qNode;
	}
	//--------------------------------------------------------------
	public char dequeue() // take item from front of queue
	{
		char temp = head.data;
		if(head.next == null)
			tail = null;
		head = head.next;
		return temp;
	}
	//--------------------------------------------------------------
	
	public boolean isEmpty() // true if queue is empty
	{
		return (head == null);
	}
}