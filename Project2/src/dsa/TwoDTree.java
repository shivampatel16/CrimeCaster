/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Assignment_Number: Project 2
 */

// Defining the package for this file
package dsa;

// Imports necessary for file input and output
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/***
 * The TwoDTree class is used to implement a space partitioning 2D tree to store the crime data in Pittsburgh
 * One crime record is store per node in the 2D tree. And each node in the tree will have several attributes
 * to describe the crime such as x_coordinate, y_coordinate, time, street, offence, date, tract, latitude, longitude.
 * The class also defines 5 tree traversal approaches including preOrder, postOrder, inOrder, levelOrder and
 * reverseLevelOrder. Along with these, it defines a findPointsInRange method to find the crimes that occurred in
 * the given range of points. Finally, it defines a function nearestNeighbor() to find the nearest crime that
 * occurred to the given x and y coordinates.
 */
public class TwoDTree {

    // Stores the root of the 2D tree
    private TwoDTreeNode root;

    // Stores the number of nodes travelled during the nearest neighbor search and while finding the points in given range
    private int nodes_visited = 0;

    // Store the number of crimes found while finding the points in given range
    private int crimes_found = 0;

    // Stores the total records in the Pittsburgh crime CSV file
    private int total_records = 0;

    /***
     * Constructor to create a new 2D tree after reading the Pittsburgh Crime CSV file
     * @param crimeDataLocation Location of the CSV file containing the crime data
     * @pre-condition The String crimeDataLocation contains the path to a file
     *                formatted in the exact same way as CrimeLatLonXY.csv
     * @post-condition The 2d tree is constructed and may be printed or queried.
     */
    public TwoDTree(String crimeDataLocation) {

        // Initally we have no nodes in the tree and hecne the root is null
        root = null;

        // Defining the various parameters of a crime
        double x_coordinate;
        double y_coordinate;
        int time;
        String street;
        String offence;
        String date;
        int tract;
        double latitude;
        double longitude;

        // Stores the location of the Pittsburgh Crimes CSV file
        String user_file;

        // Source to read files: https://www.w3schools.com/java/java_files_read.asp
        try {
            // Get the location of the file from the user
            user_file = crimeDataLocation;

            // Create and object for the user file
            File myObj = new File(user_file);

            // Create a scanner object
            Scanner myReader1 = new Scanner(myObj);

            // Stores data of one crime record in one combined String
            String data;

            // Stores the slpit of data based on comma
            String[] data_split;

            // Ignoring the first line of the CSV file (header line)
            data = myReader1.nextLine();

            // Read each line from the user file and store them to a TwoDTreeNode to form the 2D tree
            while (myReader1.hasNextLine()) {
                // Increase total record count
                setTotal_records(getTotal_records() + 1);

                // Read the next line the CSV file
                data = myReader1.nextLine();

                // Split the line based on comma
                data_split = data.split(",");

                // Extract the crime data from the data_split
                x_coordinate = Double.parseDouble(data_split[0]);
                y_coordinate = Double.parseDouble(data_split[1]);
                time = Integer.parseInt(data_split[2]);
                street = data_split[3];
                offence = data_split[4];
                date = data_split[5];
                tract = Integer.parseInt(data_split[6]);
                latitude = Double.parseDouble(data_split[7]);
                longitude = Double.parseDouble(data_split[8]);

                // Create a new twoDTreeNode and store the crime data in it
                TwoDTreeNode twoDTreeNode = new TwoDTreeNode(x_coordinate, y_coordinate, time,
                        street, offence, date, tract, latitude, longitude);

                // Add the twoDTreeNode to the 2D tree
                root = addNodeToTwoDTree(root, twoDTreeNode);
            }
            myReader1.close();
        }
        // Throws an exception if the file is not found (incorrect location)
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /***
     * Function to add a TwoDTreeNode to the 2D tree
     * However, it uses the addNodeToTwoDTreeRecursive() function to add the node
     * @param root Root of the TwoDTree to which the new node is to be added
     * @param node Node to be added to the TwoDTree
     * @return Root node of the 2D tree after the new node has been added
     * @pre-condition root points to the root of the 2D tree where the node is to be added and
     *                the node to be added is in the form os a valid TwoDTreeNode
     * @post-condition The node to be added to the 2D tree is added at the appropriate place and the
     *                 root of the updated 2D tree is returned
     */
    public TwoDTreeNode addNodeToTwoDTree(TwoDTreeNode root, TwoDTreeNode node) {
        return addNodeToTwoDTreeRecursive(root, node, 0);
    }

    /***
     * Function to add a TwoDTreeNode to the 2D tree
     * @param root Root of the TwoDTree to which the new node is to be added
     * @param node Node to be added to the TwoDTree
     * @param depth Current depth of the 2D tree
     * @return Root node of the 2D tree after the new node has been added
     * @pre-condition root points to the root of the 2D tree where the node is to be added and
     *                the node to be added is in the form of a valid TwoDTreeNode
     * @post-condition The node to be added to the 2D tree is added at the appropriate place and the
     *                 root of the updated 2D tree is returned
     */
    public TwoDTreeNode addNodeToTwoDTreeRecursive(TwoDTreeNode root, TwoDTreeNode node, int depth) {

        // Source for logic: https://www.geeksforgeeks.org/k-dimensional-tree/
        // If the 2D tree is empty
        if (root == null) {
            // Make the new node to be root
            root = node;

            // Return the root
            return root;
        }

        // Set the comparison axis based on the depth of the tree
        int comparison_axis = depth % 2;

        // Comparing with x coordinate values
        if (comparison_axis == 0) {
            // If the x coordinate of node is less than the x coordinate of root
            if (node.getX_coordinate() < root.getX_coordinate()) {
                // Add the new node to the left of the root
                root.setLeft(addNodeToTwoDTreeRecursive(root.getLeft(), node, depth + 1));
            } else {
                // Add the new node to the right of the root
                root.setRight(addNodeToTwoDTreeRecursive(root.getRight(), node, depth + 1));
            }
        }
        // Comparing with y coordinate values
        else {
            // If the y coordinate of node is less than the y coordinate of root
            if (node.getY_coordinate() < root.getY_coordinate()) {
                // Add the new node to the left of the root
                root.setLeft(addNodeToTwoDTreeRecursive(root.getLeft(), node, depth + 1));
            } else {
                // Add the new node to the right of the root
                root.setRight(addNodeToTwoDTreeRecursive(root.getRight(), node, depth + 1));
            }
        }
        // Return the root after adding the new node
        return root;
    }

    /***
     * Function to print the pre-order traversal of the 2D tree using the printPreOrderTwoDTree() function
     * @pre-condition The 2d tree has been constructed.
     * @post-condition The 2d tree is displayed with a pre-order traversal.
     */
    public void preOrderPrint() {
        System.out.println("\nPre-Order Print:\n");
        printPreOrderTwoDTree(root);
    }

    /***
     * Function to print the pre-order traversal of the 2D tree in a recursive approach
     * @param root Root of the 2D tree whose pre-order traversal is to be printed
     * @pre-condition The 2d tree has been constructed; root points to the root of the 2D tree and is in
     *                the form of a valid TwoDTreeNode
     * @post-condition The 2d tree is displayed with a pre-order traversal.
     */
    public void printPreOrderTwoDTree(TwoDTreeNode root) {

        // Print the crime data in the root node
        printNode(root);

        // Recursively print the left child of the root
        if (root.getLeft() != null) {
            printPreOrderTwoDTree(root.getLeft());
        }
        // Recursively print the right child of the root
        if (root.getRight() != null) {
            printPreOrderTwoDTree(root.getRight());
        }
    }

    /***
     * Function to print the in-order traversal of the 2D tree using the printInOrderTwoDTree() function
     * @pre-condition The 2d tree has been constructed.
     * @post-condition The 2d tree is displayed with an in-order traversal.
     */
    // Big Theta :Since I am using printInOrderTwoDTree() for this method, I have added
    // the Big Theta computations below in the printInOrderTwoDTree() method.
    public void inOrderPrint() {
        System.out.println("\nIn-Order Print\n");
        printInOrderTwoDTree(root);
    }

    /***
     * Function to print the in-order traversal of the 2D tree in a recursive approach
     * @param root Root of the 2D tree whose in-order traversal is to be printed
     * @pre-condition The 2d tree has been constructed; root points to the root of the 2D tree and is in
     *                the form of a valid TwoDTreeNode
     * @post-condition The 2d tree is displayed with a in-order traversal.
     */
    // Big Theta: θ(n)
    // Reasoning:
    // For each node in the 2D tree we will visit the node and print it
    // If n is the total number of nodes in the 2D tree
    // Total visiting operations: n
    // Total printing operations: n
    // Thus total operations is 2 * n = CONSTANT * n
    // We thus have a Big-O of O(n) and Big Omega of Ω(n), we reach to the conclusion of Big Theta of θ(n)
    // Big Omega is Ω(n) and Big-O is O(n) because every node that is present needs to be visited and needs
    // to be printed. Thus, it needs to perform at least n operations and at most n operations as well.
    public void printInOrderTwoDTree(TwoDTreeNode root) {

        // Recursively print the left child of the root
        if (root.getLeft() != null) {
            printInOrderTwoDTree(root.getLeft());
        }

        // Print the crime data in the root node
        printNode(root);

        // Recursively print the right child of the root
        if (root.getRight() != null) {
            printInOrderTwoDTree(root.getRight());
        }
    }

    /***
     * Function to print the post-order traversal of the 2D tree using the printPostOrderTwoDTree() function
     * @pre-condition The 2d tree has been constructed.
     * @post-condition The 2d tree is displayed with a post-order traversal.
     */
    public void postOrderPrint() {
        System.out.println("\nPost-Order Print\n");
        printPostOrderTwoDTree(root);
    }

    /***
     * Function to print the post-order traversal of the 2D tree in a recursive approach
     * @param root Root of the 2D tree whose post-order traversal is to be printed
     * @pre-condition The 2d tree has been constructed; root points to the root of the 2D tree and is in
     *                the form of a valid TwoDTreeNode
     * @post-condition The 2d tree is displayed with a post-order traversal.
     */
    public void printPostOrderTwoDTree(TwoDTreeNode root) {

        // Recursively print the left child of the root
        if (root.getLeft() != null) {
            printPostOrderTwoDTree(root.getLeft());
        }

        // Recursively print the right child of the root
        if (root.getRight() != null) {
            printPostOrderTwoDTree(root.getRight());
        }

        // Print the crime data in the root node
        printNode(root);
    }

    /***
     * Function to print the level-order traversal of the 2D tree using the printLevelOrderTwoDTree() function
     * @pre-condition The 2d tree has been constructed.
     * @post-condition The 2d tree is displayed with a level-order traversal.
     */
    // Big Theta :Since I am using printLevelOrderTwoDTree() for this method, I have added
    // the Big Theta computations below in the printLevelOrderTwoDTree() method.
    public void levelOrderPrint() {
        System.out.println("\nLevel Order Print\n");
        printLevelOrderTwoDTree(root);
    }

    /***
     * Function to print the level-order traversal of the 2D tree in a recursive approach
     * @param root Root of the 2D tree whose level-order traversal is to be printed
     * @pre-condition The 2d tree has been constructed; root points to the root of the 2D tree and is in
     *                the form of a valid TwoDTreeNode
     * @post-condition The 2d tree is displayed with a level-order traversal.
     */
    // Big Theta: θ(n)
    // Reasoning:
    // For each node in the 2D tree we enqueue and dequeue
    // If n is the total number of nodes in the 2D tree
    // Total enqueue operations: n
    // Total dequeue operations: n
    // Thus total operations is 2 * n = CONSTANT * n
    // We thus have a Big-O of O(n) and Big Omega of Ω(n), we reach to the conclusion of Big Theta of θ(n)
    // Big Omega is Ω(n) and Big-O is O(n) because every node that is present needs to undergo enqueue & dequeue
    // Thus, it needs to perform at least n operations and at most n operations as well.

    public void printLevelOrderTwoDTree(TwoDTreeNode root) {
        // Create a new Queue
        Queue queue = new Queue();

        // Add the root of the tree to queue at the rear pointer
        queue.enqueue(root);

        // While the queue is not empty
        while (queue.getFront() != null && queue.getRear() != null) {

            // Remove the TwoDTree node pointed to be the front pointer in the queue
            TwoDTreeNode dequeuedNode = queue.dequeue();
            // Print the dequeued Node
            printNode(dequeuedNode);

            // If the left child of the dequeued node is not empty
            if (dequeuedNode.getLeft() != null) {
                // Enqueue the left child of the dequeued node
                queue.enqueue(dequeuedNode.getLeft());
            }
            // If the right child of the dequeued node is not empty
            if (dequeuedNode.getRight() != null) {
                // Enqueue the right child of the dequeued node
                queue.enqueue(dequeuedNode.getRight());
            }
        }
    }

    /***
     * Function to print the reverse-level-order traversal of the 2D tree using the printReverseLevelOrderTwoDTree() function
     * @pre-condition The 2d tree has been constructed.
     * @post-condition The 2d tree is displayed with a reverse level order traversal.
     */
    // Big Theta :Since I am using printReverseLevelOrderTwoDTree() for this method, I have added
    // the Big Theta computations below in the printReverseLevelOrderTwoDTree() method.
    public void reverselevelOrderPrint() {
        System.out.println("\nReverse Level Order Print\n");
        printReverseLevelOrderTwoDTree(root);
    }

    /***
     * Function to print the reverse-level-order traversal of the 2D tree in a recursive approach
     * @param root Root of the 2D tree whose reverse-level-order traversal is to be printed
     * @pre-condition The 2d tree has been constructed; root points to the root of the 2D tree and is in
     *                the form of a valid TwoDTreeNode
     * @post-condition The 2d tree is displayed with a reverse level-order traversal.
     */
    // Big Theta: θ(n)
    // Reasoning:
    // For each node in the 2D tree we enqueue, dequeue, push and pop each node
    // If n is the total number of nodes in the 2D tree
    // Total enqueue operations: n
    // Total dequeue operations: n
    // Total push operations: n
    // Total pop operations: n
    // Thus total operations is 4 * n = CONSTANT * n
    // We thus have a Big-O of O(n) and Big Omega of Ω(n), we reach to the conclusion of Big Theta of θ(n)
    // Big Omega is Ω(n) and Big-O is O(n)because every node that is present needs to undergo enqueue, dequeue,
    // push and pop. Thus, it needs to perform at least n operations and at most n operations as well.
    public void printReverseLevelOrderTwoDTree(TwoDTreeNode root) {
        // Create a new stack
        Stack stack = new Stack();

        // Create a new Queue
        Queue queue = new Queue();

        // Add the root of the tree to queue at the rear pointer
        queue.enqueue(root);

        // While the queue is not empty
        while (queue.getFront() != null && queue.getRear() != null) {

            // Remove the TwoDTree node pointed to be the front pointer in the queue
            TwoDTreeNode dequeuedNode = queue.dequeue();

            // Push the dequeued node to stack
            stack.push(dequeuedNode);

            // If the left child of the dequeued node is not empty
            if (dequeuedNode.getLeft() != null) {
                // Enqueue the left child of the dequeued node
                queue.enqueue(dequeuedNode.getLeft());
            }
            // If the right child of the dequeued node is not empty
            if (dequeuedNode.getRight() != null) {
                // Enqueue the right child of the dequeued node
                queue.enqueue(dequeuedNode.getRight());
            }
        }

        // Until the stack is not empty
        while (stack.getFront() != null) {
            // Pop a TwoDTreeNode from the top of the stack
            TwoDTreeNode poppedNode = stack.pop();
            // Print the popped node
            printNode(poppedNode);
        }
    }

    /***
     * Function to print the crime data stored in TwoDTreeNode in the required format
     * @param node TwoDTreeNode whose crime data is to be printed
     * @pre-condition node points to a valid TwoDTreeNode
     * @post-condition The details in the TwoDTreeNode are printed in the required form
     */
    public void printNode(TwoDTreeNode node) {

        // Extract the different parameters of the crime data using the node
        double x_coordinate = node.getX_coordinate();
        double y_coordinate = node.getY_coordinate();
        int time = node.getTime();
        String street = node.getStreet();
        String offence = node.getOffence();
        String date = node.getDate();
        int tract = node.getTract();
        double latitude = node.getLatitude();
        double longitude = node.getLongitude();

        // Print the extracted data in the required format
        System.out.println(x_coordinate + "," + y_coordinate + "," + time + "," + street + "," +
                offence + "," + date + "," + tract + "," + latitude + "," + longitude);
    }

    /***
     * Function to find crimes in the rectangle formed by the (x1, y1) and (x2, y2) coordinates
     * where the pair (x1, y1) is the left bottom of the rectangle and the pair (x2, y2) is the
     * top right of the rectangle. We recursively use the searchTree() function to find the points
     * in the rectangle.
     * @param x1 x coordinate of the left bottom of the rectangle
     * @param y1 y coordinate of the left bottom of the rectangle
     * @param x2 x coordinate of the to right of the rectangle
     * @param y2 y coordinate of the to right of the rectangle
     * @return ListOfCrimes found in the rectangle formed by the (x1, y1) and (x2, y2) coordinates
     * @pre-condition The 2d tree has been constructed. The pair (x1, y1) is the left bottom of the rectangle.
     *                The pair (x2, y2) is the top right of the rectangle.
     * @post-condition A list of 0 or more crimes is returned. These crimes occurred within the
     *                 rectangular range specified by the four parameters.
     */
    public ListOfCrimes findPointsInRange(double x1, double y1, double x2, double y2) {

        // Create a new empty list to store the crimes in the rectangle
        final ListOfCrimes listOfCrimes = new ListOfCrimes();

        System.out.println("\nSearching for points within (" + x1 + "," + y1 + ") and (" + x2 + "," + y2 + ")");

        // Create a new TwoDTreeNode cursor pointing to the root of the 2D tree
        TwoDTreeNode cursor = root;

        // Use the recursive searchTree() function to find the crime points in the ractangle
        searchTree(cursor, listOfCrimes, 0, x1, y1, x2, y2);

        System.out.println("\nExamined " + getNodes_visited() + " nodes during search.");
        System.out.println("Found " + getCrimes_found() + " crimes.\n");

        // Reset the total crimes found and the node visited count to zero for the next run
        setCrimes_found(0);
        setNodes_visited(0);

        // Return the list of crimes found in the rectangle
        return listOfCrimes;
    }

    /***
     * Function to find crimes in the rectangle formed by the (x1, y1) and (x2, y2) coordinates
     * where the pair (x1, y1) is the left bottom of the rectangle and the pair (x2, y2) is the
     * top right of the rectangle.
     * @param root Root of the 2D tree in which we have the crimes data stored
     * @param listOfCrimes Reference to the list of crimes (updated when a new crime is found)
     * @param depth Current depth of the 2D tree
     * @param x1 x coordinate of the left bottom of the rectangle
     * @param y1 y coordinate of the left bottom of the rectangle
     * @param x2 x coordinate of the to right of the rectangle
     * @param y2 y coordinate of the to right of the rectangle
     * @pre-condition The 2d tree has been constructed; root points to the root of the 2D tree and is in
     *                the form of a valid TwoDTreeNode. The pair (x1, y1) is the left bottom of the rectangle.
     *                The pair (x2, y2) is the top right of the rectangle.
     * @post-condition A list of 0 or more crimes is returned. These crimes occurred within the
     *                 rectangular range specified by the four parameters.
     */
    public void searchTree(TwoDTreeNode root, ListOfCrimes listOfCrimes, int depth,
                           double x1, double y1, double x2, double y2) {

        // If the root is null
        if (root == null) {
            // Prune further search
            return;
        }
        // Increase the total nodes visited count
        setNodes_visited(getNodes_visited() + 1);

        // Get the x and y coordinates of the root
        double x = root.getX_coordinate();
        double y = root.getY_coordinate();

        // If the x and y coordinates lie in the rectangle, a new crime is found
        if (x >= x1 && y >= y1 &&
                x <= x2 && y <= y2) {
            // Increase crime found count
            setCrimes_found(getCrimes_found() + 1);
            // Add crime to the list of crimes
            listOfCrimes.addCrimeToList(root);
        }

        // Splitting line to decide horizontal or vertical split
        int splitting_line = depth % 2;

        // If a vertical split is required
        if (splitting_line == 0) {
            // If x coordinate of root lies towards the right of the rectangle
            if (x > x1 && x > x2) {
                // Set the right child of the root to be null
                root.setRight(null);
                // Search the left child of the root
                searchTree(root.getLeft(), listOfCrimes, depth + 1, x1, y1, x2, y2);
            }
            // If x coordinate of root lies towards the left of the rectangle
            else if (x < x1 && x < x2) {
                // Set the left child of the root to be null
                root.setLeft(null);
                // Search the right child of the root
                searchTree(root.getRight(), listOfCrimes, depth + 1, x1, y1, x2, y2);
            }
            // If x coordinate of root lies in between x1 and x2
            else if (x > x1 && x < x2) {
                // Initially search the left child of the root
                searchTree(root.getLeft(), listOfCrimes, depth + 1, x1, y1, x2, y2);
                // Then search the right child of the root
                searchTree(root.getRight(), listOfCrimes, depth + 1, x1, y1, x2, y2);
            }
        }
        // If a horizontal split is required
        else if (splitting_line == 1) {
            // If y coordinate of root lies above the rectangle
            if (y > y1 && y > y2) {
                // Set the right child of the root to be null
                root.setRight(null);
                // Search the left child of the root
                searchTree(root.getLeft(), listOfCrimes, depth + 1, x1, y1, x2, y2);
            }
            // If y coordinate of root lies below the rectangle
            else if (y < y1 && y < y2) {
                // Set the left child of the root to be null
                root.setLeft(null);
                // Search the right child of the root
                searchTree(root.getRight(), listOfCrimes, depth + 1, x1, y1, x2, y2);
            }
            // If y coordinate of root lies in between y1 and y2
            else if (y > y1 && y < y2) {
                // Initially search the left child of the root
                searchTree(root.getLeft(), listOfCrimes, depth + 1, x1, y1, x2, y2);
                // Then search the right child of the root
                searchTree(root.getRight(), listOfCrimes, depth + 1, x1, y1, x2, y2);
            }
        }
    }

    /***
     * Function to find the nearest neighbor (crime) from the given x and y coordinates by the user
     * uisng the recursive searchNeighbor function
     * @param x1 x coordinate of the point given by user
     * @param y1 y coordinate of the point given by user
     * @return Neighbor pointing to the nearest neighbor (crime) from the given x and y coordinates by the user
     * @pre-condition The 2d tree has been constructed. The (x1,y1) pair represents a point in space near
     *                Pittsburgh and in the state plane coordinate system.
     * @post-condition The distance in feet to the nearest node is returned in Neighbor. In addition, the
     *                 Neighbor object contains a reference to the nearest neighbor in the tree.
     */
    public Neighbor nearestNeighbor(double x1, double y1) {

        // Create a new Neighbor object
        final Neighbor neighbor = new Neighbor();

        // Initially setting the nodes visited to be zero
        setNodes_visited(0);

        // Create a TwoDTreeNode cursor pointing to the root of the TwoDTree
        TwoDTreeNode cursor = root;

        // Use the recursive searchNeighbor() function to find the nearest neighbor
        searchNeighbor(cursor, neighbor, 0, x1, y1);

        System.out.println("\nLooked at " + getNodes_visited() + " nodes in tree. Found the nearest crime at:");

        // Reset the node visited count to zero for the next run
        setNodes_visited(0);

        // Return the nearest neighbor
        return neighbor;
    }

    /***
     * Function to find the nearest neighbor (crime) from the given x and y coordinates by the user
     * @param root Root of the 2D tree in which the nearest neighbor is to be found
     * @param neighbor Reference to the nearest neighbor (updated when a new nearest neighbor is found)
     * @param depth Current depth of the tree pointed to by root
     * @param x1 x coordinate of the point given by user
     * @param y1 y coordinate of the point given by user
     * @pre-condition The 2d tree has been constructed. The (x1,y1) pair represents a point in space near
     *               Pittsburgh and in the state plane coordinate system; root points to the root of the
     *               2D tree and is in the form of a valid TwoDTreeNode.
     * @post-condition The distance in feet to the nearest node is returned in Neighbor. In addition, the
     *                 Neighbor object contains a reference to the nearest neighbor in the tree.
     */
    public void searchNeighbor(TwoDTreeNode root, Neighbor neighbor, int depth, double x1, double y1) {

        // If the root is null, prune the search
        if (root == null) {
            return;
        }

        // Increase the total nodes visited count
        setNodes_visited(getNodes_visited() + 1);

        // Get x and y coordinates of the root
        double x = root.getX_coordinate();
        double y = root.getY_coordinate();

        // Calculate distance between the current root node and the points given by the user
        double actual_distance = calcDist(x1, y1, x, y);

        // If the actual distance is less than the current distance set by the nearest neighbor
        if (actual_distance < neighbor.getDistance()) {
            // Update the nearest distance to be the actual distance
            neighbor.setDistance(actual_distance);
            // Update the nearest neighbor
            neighbor.setNeighbourNode(root);
        }

        // Splitting line to decide horizontal or vertical split
        int splitting_line = depth % 2;

        // If a vertical split is required
        if (splitting_line == 0) {
            // Calculate the perpendicular distance of the user point from the splitting line
            double perpendicular_distance = x - x1;
            // If x coordinate of the root is greater than the x coordinate of the user point,
            // search for nearest neighbor in the left child of the root, else in the right child
            searchNeighbor(perpendicular_distance > 0 ? root.getLeft() : root.getRight(), neighbor, depth + 1, x1, y1);
            // Source for logic: https://rosettacode.org/wiki/K-d_tree#Java
            // If the squared of the perpendicular distance is greater than or equal to the current neighbor distance, prune
            if (perpendicular_distance * perpendicular_distance >= neighbor.getDistance()) {
                return;
            }
            // If x coordinate of the root is greater than the x coordinate of the user point,
            // search for nearest neighbor in the right child of the root, else in the left child
            searchNeighbor(perpendicular_distance > 0 ? root.getRight() : root.getLeft(), neighbor, depth + 1, x1, y1);
        }
        // If a horizontal split is required
        else if (splitting_line == 1) {
            // Calculate the perpendicular distance of the user point from the splitting line
            double perpendicular_distance = y - y1;
            // If y coordinate of the root is greater than the y coordinate of the user point,
            // search for nearest neighbor in the left child of the root, else in the right child
            searchNeighbor(perpendicular_distance > 0 ? root.getLeft() : root.getRight(), neighbor, depth + 1, x1, y1);
            // Source for logic: https://rosettacode.org/wiki/K-d_tree#Java
            // If the squared of the perpendicular distance is greater than or equal to the current neighbor distance, prune
            if (perpendicular_distance * perpendicular_distance >= neighbor.getDistance()) {
                return;
            }
            // If y coordinate of the root is greater than the y coordinate of the user point,
            // search for nearest neighbor in the right child of the root, else in the left child
            searchNeighbor(perpendicular_distance > 0 ? root.getRight() : root.getLeft(), neighbor, depth + 1, x1, y1);
        }
    }

    /***
     * Function to calculate distance between two points in the X-Y plane
     * @param x1 x coordinate of point 1
     * @param y1 y coordinate of point 1
     * @param x2 x coordinate of point 2
     * @param y2 y coordinate of point 2
     * @return Distance between the tow points
     * @pre-condition (x1, y1) and (x2, y2) represents valid points in the XY coordinate plane system
     * @post-condition The distance between the two points, (x1, y1) and (x2, y2) is returned
     */
    public double calcDist(double x1, double y1, double x2, double y2) {
        //return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
    }

    // Get the value of the total nodes visited
    public int getNodes_visited() {
        return nodes_visited;
    }

    // Set the value of the total nodes visited
    public void setNodes_visited(int nodes_visited) {
        this.nodes_visited = nodes_visited;
    }

    // Get the value of the total crimes found
    public int getCrimes_found() {
        return crimes_found;
    }

    // Set the value of the total crimes found
    public void setCrimes_found(int crimes_found) {
        this.crimes_found = crimes_found;
    }

    // Get the value of the total records found
    public int getTotal_records() {
        return total_records;
    }

    // Set the value of the total records found
    public void setTotal_records(int total_records) {
        this.total_records = total_records;
    }
}
