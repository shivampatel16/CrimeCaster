/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

// Imports necessary for file input and output
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/***
 * The object of the ListOfCrimes class represents a singly linked list of ListOfCrimesNode
 * with one head pointer, which always points to the start of the ListOfCrimes
 */
public class ListOfCrimes {

    // Stores the reference to a ListOfCrimesNode which points to the start of ListOfCrimes
    private ListOfCrimesNode head;

    // Constructor to initialize the head pointer to null
    public ListOfCrimes() {
        head = null;
    }

    /***
     * Function to add crimes found in the coordinates given by the user to the list of crimes
     * The crime and its related data is stored in the treeNode parameter of the function
     * @param treeNode Reference to the TwoDTreeNode that stores the data of the crime found
     * @pre-condition treeNode points to a valid TwoDTreeNode and it represents a crime in the rectangle
     * @post-condition The crime found is added to the ListOfCrimes and the head pointer points to the
     *                 start of the list
     */
    public void addCrimeToList(TwoDTreeNode treeNode) {

        // Create a new ListOfCrimesNode
        ListOfCrimesNode listOfCrimesNode = new ListOfCrimesNode();

        // Set the node of the listOfCrimesNode to store the value of treeNode
        listOfCrimesNode.setNode(treeNode);

        // If the ListOfCrimes is empty
        if (head == null) {
            // Add the first element to the list
            head = listOfCrimesNode;
        }
        // If the ListOfCrimes has more than one ListOfCrimesNode in it
        else {

            // Create a cursor pointing to the head
            ListOfCrimesNode cursor = head;

            // Make the cursor reach till the end of the list
            while (cursor.getNext() != null) {
                cursor = cursor.getNext();
            }

            // Add the new listOfCrimesNode to the end of the list
            cursor.setNext(listOfCrimesNode);
        }
    }

    /***
     * Function to convert the ListOfCrimes to String output in the desired form
     * @return String representation of the ListOfCrimes
     * @pre-condition The ListOfCrimes is formed with the list of all crimes in the rectangle
     * @post-condition The String representation of the list of crimes in the required form is
     *                 presented to the user
     */
    public String toString() {

        // Create a cursor pointing to the head
        ListOfCrimesNode cursor = head;

        // Initialize the string to be empty
        String toStringCrimes = "";

        // While the cursor does not reach the end of the ListOfCrimes
        while (cursor != null) {

            // Get the TwoDTreeNode pointed to by the cursor
            TwoDTreeNode toStringNode = cursor.getNode();

            // Extract the data related to the crime as pointed to by the toStringNode (TwoDTreeNode)
            double x_coordinate = toStringNode.getX_coordinate();
            double y_coordinate = toStringNode.getY_coordinate();
            int time = toStringNode.getTime();
            String street = toStringNode.getStreet();
            String offence = toStringNode.getOffence();
            String date = toStringNode.getDate();
            int tract = toStringNode.getTract();
            double latitude = toStringNode.getLatitude();
            double longitude = toStringNode.getLongitude();

            // Add the extracted data to toStringCrimes
            toStringCrimes = toStringCrimes + (x_coordinate + "," + y_coordinate + "," +
                    time + "," + street + "," + offence + "," + date + "," + tract + "," +
                    latitude + "," + longitude) + "\n";

            // Increment the cursor
            cursor = cursor.getNext();
        }

        // Return the string representation of the ListOfCrimes
        return toStringCrimes;
    }

    /***
     * Function to create a KML representation of the ListOfCrimes
     * The KML representation would be later loaded to Google Earth Pro
     * @return KML representation (in String) of the ListOfCrimes
     * @pre-condition The ListOfCrimes is formed with the list of all crimes in the rectangle
     * @post-condition The KML String representation of the list of crimes is generated
     */
    public String toKML() {

        // Create a cursor pointing to the head
        ListOfCrimesNode cursor = this.head;

        // Create a String called toKMLCrimes and add initial necessary KML part in the string
        String toKMLCrimes = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n" +
                "<Document>\n" +
                " <Style id=\"style1\">\n" +
                " <IconStyle>\n" +
                " <Icon>\n" +
                " \n" +
                "<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue\n" +
                "-dot.png</href>\n" +
                " </Icon>\n" +
                " </IconStyle>\n" +
                " </Style>\n";

        // While the cursor does not reach the end of the ListOfCrimes
        while (cursor != null) {

            // Get the TwoDTreeNode pointed to by the cursor
            TwoDTreeNode toKMLNode = cursor.getNode();

            // Extracted crime related data required by the KML file
            String street = toKMLNode.getStreet();
            String offence = toKMLNode.getOffence();
            double latitude = toKMLNode.getLatitude();
            double longitude = toKMLNode.getLongitude();

            // Add the extracted data to toKMLCrimes
            toKMLCrimes = toKMLCrimes +
                    " <Placemark>\n" +
                    " <name>" + offence + "</name>\n" +
                    " <description>" + street + "</description>\n" +
                    " <styleUrl>#style1</styleUrl>\n" +
                    " <Point>\n" +
                    " <coordinates>" + longitude + "," + latitude + "," + "0.000000</coordinates>\n" +
                    " </Point>\n" +
                    " </Placemark>\n";

            // Increment the cursor
            cursor = cursor.getNext();
        }

        // Add the ending part required by the KML file
        toKMLCrimes = toKMLCrimes +
                "</Document>\n" +
                "</kml>\n";

        // Return the KML representation of the ListOfCrimes
        return toKMLCrimes;
    }

    /***
     * Function to store the KML String representation of the ListOfCrimes to an actual KML file,
     * which would be later loaded into Google Earth Pro
     * @param kmlString KML String representation of the ListOfCrimes
     * @pre-condition The KML String representation of the list of crimes is generated
     * @post-condition The KML String is added to the PGHCrimes.KML
     */
    public void kmlStringToFile(String kmlString) {

        // Source: https://stackoverflow.com/questions/18725039/java-create-a-kml-file-and-insert-elements-in-existing-file
        try {
            // Create a Writer object pointing to the file PGHCrimes.KML inside the src directory
            Writer fwriter = new FileWriter("src/PGHCrimes.KML");

            // Write the kmlString to the file
            fwriter.write(kmlString);

            // Flush the fwriter file
            fwriter.flush();

            // Close the file
            fwriter.close();
        }
        // Handles exceptions related to IO. Throws an exception if the file is not found (incorrect location)
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The crime data has been written to PGHCrimes.KML. It is viewable in Google Earth Pro.\n");
    }
}
