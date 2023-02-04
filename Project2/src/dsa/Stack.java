/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

/***
 * The Stack class represents a stack containing StackNodes as its elements
 * It has the front pointer to point to the top of the stack where the push and pop operations would be performed
 */
public class Stack {

    // Stores the reference to a StackNode which points to the top of the Stack
    private StackNode front;

    // Constructor to initialize the value of front pointer
    public Stack() {
        front = null;
    }

    /***
     * Function to push elements at the top of the Stack
     * @param treeNode Represents a crime in the TwoDTree. It is a reference of the crime data to be added to StackNode
     * @pre-condition The node to be added to the Stack is a valid TwoDTreeNode and front points to the top of the Stack
     * @post-condition The node is added to the Stack at the top of the Stack and front = top of Stack
     */
    public void push(TwoDTreeNode treeNode) {

        // Create a new StackNode
        StackNode stackNode = new StackNode();

        // Set the StackNode to point to the treeNode crime data
        stackNode.setNode(treeNode);

        // If the Stack is empty
        if (front == null) {
            // Add the first StackNode in the Stack
            front = stackNode;
        }
        // If the Stack has one or more StackNodes in it
        else {
            // Add stackNode to the top of the Stack
            stackNode.setNext(front);
            // Update front to point to the newly added StackNode
            front = stackNode;
        }
    }

    /***
     * Function to pop elements from the top of the Stack
     * @return StackNode removed from the top of the Stack
     * @pre-condition There is at least one node in the Stack and front points to the top of the Stack
     * @post-condition The TwoDTreeNode pointed to by the front pointer is popped from the Stack and returned and front = top of Stack
     */
    // Pre-condition: There is at least one node
    public TwoDTreeNode pop() {

        // Get the reference of the StackNode pointed to by front
        TwoDTreeNode returnNode = front.getNode();

        // If there was only one StackNode in the Stack
        if (front.getNext() == null) {
            // Empty the Stack
            front = null;
        }
        // If the Stack had more than one StackNodes
        else {
            // Update the front to point to the next Node
            front = front.getNext();
        }

        // Return the reference of the popped StackNode
        return returnNode;
    }

    // Return the StackNode reference of the first node in the Stack pointed to by front pointer
    public StackNode getFront() {
        return front;
    }
}
