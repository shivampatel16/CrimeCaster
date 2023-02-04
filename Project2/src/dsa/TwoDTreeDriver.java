/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

// Imports necessary for file input and output
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/***
 * The TwoDTreeDriver is the driver class for constructing a 2D tree and performing various operation on the tree such
 * as different traversals, finding the nearest neighbor and finding the crimes in the given coordinates of rectangle
 */
public class TwoDTreeDriver {

    public static void main(String[] args) throws IOException {

        // Stores the location of the CSV which has the crime data
        String crimeDataLocation = "src/CrimeLatLonXY.csv";

        // Creates a new 2D tree with the crime data
        TwoDTree twoDTree = new TwoDTree(crimeDataLocation);

        System.out.println("\nCrime file loaded into 2d tree with " + twoDTree.getTotal_records() + " records.");

        // Set the total records count to be zero after printing the above message
        twoDTree.setTotal_records(0);

        // Create an object for the TwoDTreeDriver class
        TwoDTreeDriver driver = new TwoDTreeDriver();

        // Create a scanner object to get user input
        Scanner s = new Scanner(System.in);

        // Print the inital driver options
        driver.printOptions();

        // Get user's desired option
        int user_option = s.nextInt();

        // While the user does not request to quit using the input 8
        while (user_option != 8) {
            if (user_option == 1) { // In Order Traversal
                twoDTree.inOrderPrint();
            }
            else if (user_option == 2) {  // Pre Order Traversal
                twoDTree.preOrderPrint();
            }
            else if (user_option == 3) {  // Level Order Traversal
                twoDTree.levelOrderPrint();
            }
            else if (user_option == 4) {  // Post Order Traversal
                twoDTree.postOrderPrint();
            }
            else if (user_option == 5) {  // Reverse Level Order Traversal
                twoDTree.reverselevelOrderPrint();
            }
            else if (user_option == 6) {  // Find crimes in the given rectangle

                // Create a new TwoDTree with the crime data
                twoDTree = new TwoDTree(crimeDataLocation);

                System.out.println("\nEnter a rectangle bottom left (X1,Y1) and top right (X2, Y2) as " +
                        "four doubles each separated by a space.");

                // Create a BufferReader object
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                // Read the user input of x1, y1, x2 and y2 as one single string
                String four_points = reader.readLine();

                // Split the user input to x1, y1, x2 and y2
                String[] four_points_split = four_points.split(" ");

                // Set the values of x1, y1, x2 and y2
                double x1 = Double.parseDouble(four_points_split[0]);
                double y1 = Double.parseDouble(four_points_split[1]);
                double x2 = Double.parseDouble(four_points_split[2]);
                double y2 = Double.parseDouble(four_points_split[3]);

                // Find the list of crimes in the given rectangle
                ListOfCrimes crimesList = twoDTree.findPointsInRange(x1, y1, x2, y2);

                // Print the string representation of the crimes found
                System.out.println(crimesList.toString());

                // Create a KML representation of the crimes found and store the result in a KML file
                String kmlResult = crimesList.toKML();
                crimesList.kmlStringToFile(kmlResult);
            }
            else if (user_option == 7) {  // Find the nearest neighbor to the given point

                // Create a new TwoDTree with the crime data
                twoDTree = new TwoDTree(crimeDataLocation);

                System.out.println("\nEnter a point to find nearest crime. Separate with a space.");

                // Create a BufferReader object
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                // Read the user input of x1 and y1 as one single string
                String two_points = reader.readLine();

                // Split the user input to x1, y1
                String[] two_points_split = two_points.split(" ");

                // Set the values of x1 and y1
                double x1 = Double.parseDouble(two_points_split[0]);
                double y1 = Double.parseDouble(two_points_split[1]);

                // Find the nearest neighbor
                Neighbor neighbor = twoDTree.nearestNeighbor(x1, y1);

                // Print the nearest neighbor
                System.out.println(neighbor.getNeighbourNode().getX_coordinate() + "," +
                        neighbor.getNeighbourNode().getY_coordinate() + "," +
                        neighbor.getNeighbourNode().getTime() + "," +
                        neighbor.getNeighbourNode().getStreet() + "," +
                        neighbor.getNeighbourNode().getOffence() + "," +
                        neighbor.getNeighbourNode().getDate() + "," +
                        neighbor.getNeighbourNode().getTract() + "," +
                        neighbor.getNeighbourNode().getLatitude() + "," +
                        neighbor.getNeighbourNode().getLongitude() + "\n");
            }

            // Request user for another option on the 2D tree
            driver.printOptions();

            // Get user input
            user_option = s.nextInt();
        }

        System.out.println("\nThank you for exploring Pittsburgh crimes in the 1990’s.");
    }

    /***
     * Function to print the list of available options to the user
     */
    public void printOptions() {
        System.out.print("""
                
                What would you like to do?
                1: Print inOrder
                2: Print preOrder
                3: Print levelOrder
                4: Print postOrder
                5: Print reverseLevelOrder
                6: Search for points within rectangle
                7: Search for nearest neighbor
                8: Quit
                
                Enter your option:\040""");
    }
}
