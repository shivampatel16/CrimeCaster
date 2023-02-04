/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

/***
 * The StackNode represents a node in the Stack class. It objects include node which points to a TwoDTreeNode
 * storing the data of a crime. The other object of this class is next which points to the next StackNode in the Stack
 */
public class StackNode {

    // Stores the reference to a TwoDTreeNode which stores the data for a crime
    private TwoDTreeNode node;

    // Stores the reference to a QueueNode which stores the data of the next node in the Queue
    private StackNode next;

    // Constructor to set the values of next and node
    public StackNode() {
        next = null;
        node = null;
    }

    // Return the StackNode reference of the next node in the Stack
    public StackNode getNext() {
        return next;
    }

    // Return the TwoDTreeNode reference of the crime data stored in the StackNode
    public TwoDTreeNode getNode() {
        return node;
    }

    // Set the StackNode reference of the next node in the Stack
    public void setNext(StackNode next) {
        this.next = next;
    }

    // Set the TwoDTreeNode reference of the crime data stored in the StackNode
    public void setNode(TwoDTreeNode node) {
        this.node = node;
    }
}

