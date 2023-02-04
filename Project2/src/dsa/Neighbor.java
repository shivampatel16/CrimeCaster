/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

// Import necessary for finding the nearest neighbor (for setting the initial nearest distance to be MAX possible)
import static java.lang.Double.MAX_VALUE;

/***
 * The Neighbor class stores the nearest neighbor of a given point by the user.
 * It has a distance object to store the distance of the given point to the nearest neighbor.
 * The neighborNode points to a TwoDTreeNode which stores the data for the nearest neighbor
 */
public class Neighbor {
    // Stores the distance of the given point (by user) to the nearest neighbor
    private double distance;

    // Stores the reference to a TwoDTreeNode which stores the data for the nearest neighbor
    private TwoDTreeNode neighbourNode;

    // Constructor to initialize the values for distance and neighborNode
    public Neighbor() {
        distance = MAX_VALUE; // Max possible value of a double
        neighbourNode = null;
    }

    // Return the value of distance
    public double getDistance() {
        return distance;
    }

    // Return the TwoDTreeNode reference of the nearest neighbor
    public TwoDTreeNode getNeighbourNode() {
        return neighbourNode;
    }

    // Set the value of distance
    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Set the TwoDTreeNode reference of the nearest neighbor
    public void setNeighbourNode(TwoDTreeNode neighbourNode) {
        this.neighbourNode = neighbourNode;
    }
}

