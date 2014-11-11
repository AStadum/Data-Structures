/*Anders Stadum
 * 313 Lab1
 * 10/17/2013
 * Stack.java
 * 
 */
////////////////////////////////////////////////////////////////
class Stack
{
	CharNode top; // top of stack
//--------------------------------------------------------------
//--------------------------------------------------------------
	public void push(char j) // put item on top of stack
	{
		CharNode firstNode = new CharNode(j);
		firstNode.next = top;
		top = firstNode;
	}

//--------------------------------------------------------------
	public char pop() // take item from top of stack
	{
		if (!isEmpty())
		{
			CharNode temp = top;
			top = top.next;
			return temp.data;
		}
		return 0;
	}
//--------------------------------------------------------------
	public boolean isEmpty() // true if stack is empty
	{
		return (top == null);
	}
//--------------------------------------------------------------
//--------------------------------------------------------------
} 