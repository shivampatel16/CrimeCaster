/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

/***
 * The QueueNode represents a node in the Queue class. It objects include node which points to a TwoDTreeNode
 * storing the data of a crime. The other object of this class is next which points to the next QueueNode in the Queue
 */
public class QueueNode {

    // Stores the reference to a TwoDTreeNode which stores the data for a crime
    private TwoDTreeNode node;

    // Stores the reference to a QueueNode which stores the data of the next node in the Queue
    private QueueNode next;

    // Constructor to set the values of next and node
    public QueueNode() {
        next = null;
        node = null;
    }

    // Return the QueueNode reference of the next node in the Queue
    public QueueNode getNext() {
        return next;
    }

    // Return the TwoDTreeNode reference of the crime data stored in the QueueNode
    public TwoDTreeNode getNode() {
        return node;
    }

    // Set the QueueNode reference of the next node in the Queue
    public void setNext(QueueNode next) {
        this.next = next;
    }

    // Set the TwoDTreeNode reference of the crime data stored in the QueueNode
    public void setNode(TwoDTreeNode node) {
        this.node = node;
    }
}
