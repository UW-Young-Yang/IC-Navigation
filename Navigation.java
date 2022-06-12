// --== CS400 File Header Information ==--
// Name: Young Yang
// Email: xyang532@wisc.edu
// Team: IC
// Role: Front End Developer
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This class simulates a navigation application with a given map and a list of names of locations. 
 * Users can use Navigation to find the shortest path between their starting point and their destination.
 * In addtion, they can set a number of stopovers and a destination.
 * 
 * @author Young Yang
 */
public class Navigation {
    
    static CS400Graph<Location> map = new CS400Graph<>();
    static List<Location> locations = new LinkedList<>();

    /**
     * Find the shortest path for the given starting point and the given destination.
     * @param startingPoint the starting point for navigation.
     * @param destinationPoint the ending point for navigation.
     * @return a list that store the Location object on the shortest path.
     * @throws IllegalArgumentException when starting point and ending point are identical.
     */
    public static List<Location> navigation(Location startingPoint, Location destinationPoint) {
        if (startingPoint.equals(destinationPoint)) {
            throw new IllegalArgumentException("Starting point and destination are identical!!!");
        }
        List<Location> shortestPath = map.shortestPath(startingPoint, destinationPoint);
        return shortestPath;
    }

    /**
     * Determine whether food is provided on the shortest path.
     * @param shortestPath a list that store the Location object on the shortest path.
     * @return true if food is provided on the path, false otherwise.
     */
    public static boolean pathCanProvideFood(List<Location> shortestPath) {
        for (Location location : shortestPath) {
            if (canProvideFood(location)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether food is provided at a location.
     * @param location a Location object
     * @return true if food is provided at the location, false otherwise.
     */
    private static boolean canProvideFood(Location location) {
        return location.isCanProvideFood();
    }

    /**
     * List the names of all the locations on the map.
     * @return all names of the locations on the map.
     */
    public static List<String> listAllNames() {
        List<String> listOfNames = new LinkedList<>();
        locations.forEach(location -> listOfNames.add(location.getName()));
        return listOfNames;
    }

    /**
     * Find the Location object for a given location name.
     * @param locationName the name of the location to find.
     * @return a Location object
     * @throws IllegalArgumentException when the location do not exist in the map.
     */
    public static Location findLocation(String locationName) {
        locationName = locationName.trim().toUpperCase();
        for (Location location : locations) {
            if (location.getName().toUpperCase().equals(locationName)) return location;
        }
        throw new IllegalArgumentException("The given location does not exist in the map!!!");
    }

    /**
     * Path objects store a discovered path of Location and the overal distance of this path.
     */
    public static class Path implements Comparable<Path> {
        Location startLocation, endLocation;
        List<Location> listOfCurrentPath = new LinkedList<>();
        int distance = 0;
        
        /**
         * Creates a new path containing the start location.
         * @param start the start location
         */
        public Path(Location start) {
            this.startLocation = start;
            this.endLocation = start;
            listOfCurrentPath.add(start);
        }

        /**
         * Makes a copy of the path passed into it as an argument.
         * @param other the path that is being copied.
         */
        public Path(Path other) {
            startLocation = other.startLocation;
            endLocation = other.endLocation;
            this.listOfCurrentPath.addAll(other.listOfCurrentPath);
            this.distance = other.distance;
        }

        /**
         * Extends the current path by a list of Location object.
         * @param shortestPath a list of Location object
         */
        private void extend(List<Location> shortestPath) {
            this.listOfCurrentPath.remove(shortestPath.get(0));
            this.listOfCurrentPath.addAll(shortestPath);
            endLocation = shortestPath.get(shortestPath.size()-1);
            distance += map.getPathCost(shortestPath.get(0), shortestPath.get(shortestPath.size()-1));
        }

        /**
         * Extends the current path by the path passed into it as an argument.
         * @param other the path that used to extend the current path.
         */
        private void extend(Path other) {
            endLocation = other.endLocation;
            distance += other.distance;
            other.listOfCurrentPath.remove(0); // remove duplicate starting point
            listOfCurrentPath.addAll(other.listOfCurrentPath);
        }

        /**
         * Allows the natural ordering of paths to be increasing with path distance.
         * @param other 
         * @return -1 when this path has a smaller distance than the other,
         *         +1 when this path has a larger distance that the other,
         *         0 when two path have the same distance
         */
        @Override
        public int compareTo(Path other) {
            return this.distance - other.distance;
        }
        
    }

    /**
     * Automatically plans an optimal route through all the points after setting the starting point, then store in Path object.
     * @param path a Path object that store a start point.
     * @param listOfStopover a list contains the Location object that must be going through.
     * @param startPoint a starting point comes from the list of stopover.
     * @return a Path object store the information of distance and list of Location object in the shortest path.
     */
    public static Path planRoute(Path path, List<Location> listOfStopover, String startPoint) {
        PriorityQueue<Path> comparePath = new PriorityQueue<>();
        for (Location currentLocation : listOfStopover) {
            Location nextLocation;
            List<Location> copyOfList = new LinkedList<>(listOfStopover);
            Path currentPath = new Path(currentLocation);
            do {
                nextLocation = null;
                copyOfList.remove(currentLocation);
                for (Location anotherLocation : copyOfList) {                
                    if (nextLocation == null
                        || copyOfList.size() == 1) {
                        nextLocation = anotherLocation;
                        continue;
                    }
                    if (map.getPathCost(currentLocation, nextLocation) > map.getPathCost(currentLocation, anotherLocation)) {
                        nextLocation = anotherLocation;
                    }
                }
                if (nextLocation == null) break;
                currentPath.extend(navigation(currentLocation, nextLocation));
                currentLocation = nextLocation;
            } while (copyOfList.size() != 1);
            if (currentPath.startLocation.equals(findLocation(startPoint))) {
                comparePath.offer(currentPath);
            } else if (currentPath.endLocation.equals(findLocation(startPoint))) {
                List<Location> temp = new LinkedList<>();
                for (int i=currentPath.listOfCurrentPath.size()-1; i>-1; i--) {
                    temp.add(currentPath.listOfCurrentPath.get(i));
                }
                currentPath.startLocation = temp.get(0);
                currentPath.endLocation = temp.get(temp.size()-1);
                currentPath.listOfCurrentPath = temp;
                comparePath.offer(currentPath);
            }
        }
        path.extend(comparePath.peek());
        return path;
    }

    /**
     * Run the navigation application.
     * @param args user input
     */
    public static void main(String[] args) {
        LoadLocation.createMap(); // load the data
        Scanner inputScanner = null;
        String userInput = "";
        Path path; // the shortest path for the given starting point and the given destination

        while (!userInput.trim().toUpperCase().equals("Q")) {
            if (inputScanner == null) {
                inputScanner = new Scanner(System.in);
                System.out.println("------------------------------------------");
                System.out.println("|    Welcome to use the IC Navigation    |");
                System.out.println("------------------------------------------");
                System.out.println("Madison has the following locations to choose from the navigation:\n");
                listAllNames().forEach(name -> System.out.println(name));
                System.out.println();
            }
            System.out.println("Type your command to continue:\n" + 
                               "Navigation: N\n" + 
                               "Quit: Q");

            userInput = inputScanner.nextLine();
            System.out.println();

            // Navigation --------------------------------------------------------

            if (userInput.trim().toUpperCase().equals("N")) {
                // Set the Start point
                System.out.println("Please set your starting point:");
                String startPoint = inputScanner.nextLine();
                System.out.println();
                String stopoverPoint = null;
                String endPoint = null;
                List<String> listOfDestination = new LinkedList<>();
                Location stopoverLocation;
                path = new Path(findLocation(startPoint));
                boolean isIncludeDestination = true;

                // Set the destination
                do {
                    System.out.println("Please enter the name of your destination (leaving blank to skip, "
                    + "then will select one location from stopovers for destination):");
                    endPoint = inputScanner.nextLine();
                    System.out.println();
                    if (endPoint.equals("")) {
                        isIncludeDestination = false;
                        break;
                    }
                    findLocation(endPoint);
                    if (endPoint.equals(startPoint)) {
                        System.out.println("*The given point has been set to be start point. Please try again*");
                        continue;
                    } else {
                        listOfDestination.add(endPoint);
                    }
                } while (false);

                // Set the Stopover point
                int numOfStopover;
                List<Location> listOfStopover = new LinkedList<>();
                listOfStopover.add(path.endLocation);
                while (true) {
                    System.out.println("Please enter the number of your stopovers(You can have at most THREE stopovers):");
                    numOfStopover = inputScanner.nextInt();
                    inputScanner.nextLine();
                    System.out.println();
                    if (numOfStopover == 0) {
                        if (!isIncludeDestination) {
                            System.out.println("You need to set at least one stopover, because you don't have the destination!!!");
                            continue;
                        }
                    }
                    if (numOfStopover > 3) {
                        System.out.println("You can have at most THREE stopovers!!! Please try again.");
                        continue;
                    }
                    break;
                }
                for (int i=0; i<numOfStopover; i++) {
                    int num = i+1;
                    System.out.println("Please enter the name of your stopover " + num + "(leaving blank to skip):");
                    stopoverPoint = inputScanner.nextLine();
                    System.out.println();
                    if (stopoverPoint.equals("")) break;
                    stopoverLocation = findLocation(stopoverPoint);
                    if (stopoverPoint.equals(startPoint)) {
                        System.out.println("*The given point has been set to be start point. Please try again*");
                        i--;
                    } else if (listOfDestination.contains(stopoverPoint)) {
                        System.out.println("*The given point has been set to a destination. Please try again*");
                        i--;
                    } else if (listOfStopover.contains(stopoverLocation)) { // the given point has been set
                        System.out.println("*The given point has been set to a stopover. Please try again*");
                        i--;
                    } else { // add the stopoverLocation to the list
                        listOfStopover.add(stopoverLocation);
                    }
                }

                // Plan route for stopover point(s)
                List<Location> listOfEnd = new LinkedList<>();
                if (isIncludeDestination) {
                    if (listOfStopover.size() != 0) {
                        path = planRoute(new Path(path), listOfStopover, startPoint);
                    }
                    listOfEnd.add(path.listOfCurrentPath.get(path.listOfCurrentPath.size()-1));
                    listOfDestination.forEach(destination -> listOfEnd.add(findLocation(destination)));
                    path = planRoute(new Path(path), listOfEnd, listOfEnd.get(0).getName());
                } else {
                    path = planRoute(new Path(path), listOfStopover, startPoint);
                    listOfDestination.add(path.listOfCurrentPath.get(path.listOfCurrentPath.size()-1).getName());
                }
                listOfStopover.remove(path.listOfCurrentPath.get(path.listOfCurrentPath.size()-1));
                listOfStopover.remove(0);

                // Print the path
                System.out.println("*The stopover point(s) are shown in the form of [stopover]*");
                System.out.println("*The destination(s) are shown in the form of (destination)*\n");
                System.out.println("The shortest path from \"" + startPoint + "\" to \"" 
                + listOfDestination.toString().substring(1,listOfDestination.toString().length()-1) + "\" is:\n");

                for (Location location : path.listOfCurrentPath) {
                    if (listOfStopover.size() == 0
                        && listOfDestination.size() == 1
                        && path.listOfCurrentPath.get(path.listOfCurrentPath.size()-1).equals(location)) {
                        System.out.println("(" + location.getName() + ")\n");
                        break;
                    }
                    if (listOfStopover.contains(location)) { // stopover point
                        listOfStopover.remove(location); // only show once
                        System.out.print("[" + location.getName() + "]" + "----");
                    } else if(listOfStopover.size() == 0 && listOfDestination.contains(location.getName())) {
                        listOfDestination.remove(location.getName()); // only show once
                        System.out.print("(" + location.getName() + ")" + "----");
                    } else {
                        System.out.print(location.getName() + "----");
                    }
                }
                System.out.println("The total distance is " + path.distance + "m.");
                if (pathCanProvideFood(path.listOfCurrentPath)) {
                    System.out.println("You can buy some food or drink on this path.");
                }
                System.out.println();
            }
        }
        System.out.println("Thank you for using IC Navigation!\n");
        inputScanner.close();
    }

}
