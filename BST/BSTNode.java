/*
 *Anders Stadum
 *BSTNode.java
 *11/4/2013
 */
public class BSTNode {
	
	public String iData; //data...
	public BSTNode leftChild , rightChild; //pointer towards nodes children
	public BSTNode(String dd) {
		iData = dd;        //data stored in node
		leftChild = null;  //set left child to null
		rightChild = null; //set right child to null
	}
}
