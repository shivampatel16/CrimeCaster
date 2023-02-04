/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

/***
 * The Queue class represents a queue containing QueueNodes as its elements
 * It has the front and the rear pointers to point to the start and the end of the queue
 */
public class Queue {

    // Stores the reference to a QueueNode which points to the start of the Queue
    private QueueNode front;

    // Stores the reference to a QueueNode which points to the end of the Queue
    private QueueNode rear;

    // Constructor to set the values of front and rear pointers
    public Queue() {
        front = null;
        rear = null;
    }

    /***
     * Function to add QueueNode to the Queue at the rear pointer
     * After adding the QueueNode, the rear pointer updates itself to point to the new rear
     * @param treeNode Represents a crime in the TwoDTree. It is a reference of the crime data to be added to QueueNode
     * @pre-condition The node to be added to the Queue is a valid TwoDTreeNode and front <= rear
     * @post-condition The node is added to the Queue and front <= rear
     */
    public void enqueue(TwoDTreeNode treeNode) {

        // Create a new QueueNode
        QueueNode queueNode = new QueueNode();

        // Set the QueueNode to point to the treeNode crime data
        queueNode.setNode(treeNode);

        // If the Queue is empty, add the first node of the queue
        if (front == null && rear == null) {
            front = queueNode;
            rear = queueNode;
        }
        // If the Queue already has some QueueNodes in it
        else {
            // Add the new QueueNode at the rear
            rear.setNext(queueNode);

            // Update the rear
            rear = queueNode;
        }
    }

    /***
     * Function to remove a QueueNode (from the start of the Queue) from the Queue
     * @return QueueNode removed from the Queue
     * @pre-condition There is at least one node in the Queue and front <= rear
     * @post-condition The TwoDTreeNode pointed to by the front pointer is removed from the queue and returned and front <= rear
     */
    public TwoDTreeNode dequeue() {

        // Get the reference of the QueueNode pointed to by front
        TwoDTreeNode returnNode = front.getNode();

        // If there was only one QueueNode in the Queue
        if (front == rear) {
            // Set the Queue to be empty
            front = null;
            rear = null;
        }
        // If there were more than one QueueNode in the Queue
        else {
            // Update the front pointer
            front = front.getNext();
        }

        // Return the reference of the removed QueueNode
        return returnNode;
    }

    // Return the QueueNode reference pointed to by the front pointer
    public QueueNode getFront() {
        return front;
    }

    // Return the QueueNode reference pointed to by the rear pointer
    public QueueNode getRear() {
        return rear;
    }
}
