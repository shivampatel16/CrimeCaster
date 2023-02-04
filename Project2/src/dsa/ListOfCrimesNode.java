/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

/***
 * The ListOfCrimesNode represents a node in the ListOfCrimes that occurred in
 * the given rectangle coordinates by the user. It has the node and the next attributes,
 * wherein the node points to a TwoDTreeNode which stores the data for the crime
 * and the next points to the next crime in the ListOfCrimes
 */
public class ListOfCrimesNode {

    // Stores the reference to a TwoDTreeNode which stores the data for the crime
    private TwoDTreeNode node;

    // Stores the reference to the next crime in the ListOfCrimes
    private ListOfCrimesNode next;

    // Constructor to initialize the values for next and node
    public ListOfCrimesNode() {
        next = null;
        node = null;
    }

    // Return the ListOfCrimesNode reference of the next crime in ListOfCrimes
    public ListOfCrimesNode getNext() {
        return next;
    }

    // Return the TwoDTreeNode reference of the crime
    public TwoDTreeNode getNode() {
        return node;
    }

    // Set the ListOfCrimesNode reference of the next crime in ListOfCrimes
    public void setNext(ListOfCrimesNode next) {
        this.next = next;
    }

    // Set the TwoDTreeNode reference of the crime
    public void setNode(TwoDTreeNode node) {
        this.node = node;
    }
}
