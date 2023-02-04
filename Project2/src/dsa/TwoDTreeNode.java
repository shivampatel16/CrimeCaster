/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

/***
 * The TwoDTreeNode represents a crime in Pittsburgh and it stores the different attributes of the crime that occurred.
 * The attributes for a crime include x coordinate, y coordinate, time, street, offence, date, tract, latitude and
 * longitude. Additionally, it has left and right pointers to point to the left and right parts of the TwoDTree
 */
public class TwoDTreeNode {
    // Stores the reference to a TwoDTreeNode which stores the data for its left child
    private TwoDTreeNode left;

    // Stores the reference to a TwoDTreeNode which stores the data for its right child
    private TwoDTreeNode right;

    // Stores the value of x coordinate of the crime represented by the TwoDTreeNode
    private double x_coordinate;

    // Stores the value of y coordinate of the crime represented by the TwoDTreeNode
    private double y_coordinate;

    // Stores the value of time of the crime represented by the TwoDTreeNode
    private int time;

    // Stores the value of street of the crime represented by the TwoDTreeNode
    private String street;

    // Stores the value of offence of the crime represented by the TwoDTreeNode
    private String offence;

    // Stores the value of date of the crime represented by the TwoDTreeNode
    private String date;

    // Stores the value of tract of the crime represented by the TwoDTreeNode
    private int tract;

    // Stores the value of latitude of the crime represented by the TwoDTreeNode
    private double latitude;

    // Stores the value of longitude of the crime represented by the TwoDTreeNode
    private double longitude;

    // Constructor to set the values of the different attributes of a crime in the TwoDTreeNode
    public TwoDTreeNode(double x_coordinate, double y_coordinate, int time, String street,
                        String offence, String date, int tract, double latitude, double longitude) {
        // Initially the left and right pointers are set to null
        left = null;
        right = null;

        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.time = time;
        this.street = street;
        this.offence = offence;
        this.date = date;
        this.tract = tract;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Return the TwoDTreeNode reference of the left child
    public TwoDTreeNode getLeft() {
        return left;
    }

    // Return the TwoDTreeNode reference of the right child
    public TwoDTreeNode getRight() {
        return right;
    }

    // Return the value of x coordinate
    public double getX_coordinate() {
        return x_coordinate;
    }

    // Return the value of y coordinate
    public double getY_coordinate() {
        return y_coordinate;
    }

    // Return the value of time
    public int getTime() {
        return time;
    }

    // Return the value of street
    public String getStreet() {
        return street;
    }

    // Return the value of offence
    public String getOffence() {
        return offence;
    }

    // Return the value of date
    public String getDate() {
        return date;
    }

    // Return the value of tract
    public int getTract() {
        return tract;
    }

    // Return the value of latitude
    public double getLatitude() {
        return latitude;
    }

    // Return the value of longitude
    public double getLongitude() {
        return longitude;
    }

    // Set the TwoDTreeNode reference of the left child
    public void setLeft(TwoDTreeNode left) {
        this.left = left;
    }

    // Set the TwoDTreeNode reference of the right child
    public void setRight(TwoDTreeNode right) {
        this.right = right;
    }
}
