import java.util.*;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Josh Little
 *
 */

public class MokshaPatam {

    public static final int MAX_ROLL = 6;

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */


    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        // Set the current rolls to the max integer value, easier to see errors
        int minRolls = Integer.MAX_VALUE;

        // Sorts the ladder and snake 2D arrays and prevents integer overflow via Intenger.compare
        Arrays.sort(snakes, (a, b) -> Integer.compare(a[0],b[0]));
        Arrays.sort(ladders, (a, b) -> Integer.compare(a[0],b[0]));

        // Checks if the board is possible to complete
        if (!checkPossible(boardsize, ladders, snakes))
            return -1;

        // Initializes a map of the board and adds all the snakes/ladders by entering their end
        // position for the index where they would start.
        int[] map = new int[boardsize + 1];
        for (int i = 0; i < map.length; i++)
            map[i] = i;
        for (int i = 0; i < snakes.length; i++) {
            map[snakes[i][0]] = snakes[i][1];
        }
        for (int i = 0; i < ladders.length; i++)
            map[ladders[i][0]] = ladders[i][1];

        // Create a previouslyVisited copy of the map to see which squares have been visited already.
        boolean[] previouslyVisited = new boolean[boardsize + 1];
        // Path for the route of rolls using a queue that stores the current location as well as
        // the number of rolls required to get to that space given the previous squares/moves.
        Queue<int[]> path = new LinkedList<>();
        // Add the starting square to the queue and begin
        path.add(new int[]{1,0});
        int[] currentNode;
        // While loop to continuously run until the queue is empty and there are no more paths
        // to explore.
        while (!path.isEmpty()) {
            previouslyVisited[path.peek()[0]] = true;
            currentNode = path.remove();
            // If statements to check if the end has been reached and the new minimum rolls needs
            // to be checked. Did an else if to prevent problems on the last square. Replaces min
            // rolls as long as the current rolls tracked via the 2nd array element is less than it.
            if (currentNode[0] == boardsize) {
                if (currentNode[1]< minRolls) {
                    minRolls = currentNode[1];
                }
            }
            else if (currentNode[0] >= boardsize - MAX_ROLL){
                if (currentNode[1] + 1 < minRolls) {
                    minRolls = currentNode[1] + 1;
                }
            }
            else
                // Add the next six spaces for each roll/current position to the pat as long as
                // they haven't already been visited.
                for (int i = 1; i < MAX_ROLL + 1; i++) {
                if (!previouslyVisited[map[currentNode[0] + i]] && map[currentNode[0]] + i < boardsize) {
                    path.add(new int[]{map[currentNode[0] + i], currentNode[1] + 1});
                    // Sets the status to all added elements as previously visited.
                    previouslyVisited[map[currentNode[0]] + i] = true;
                }
            }
        }
        // After the while loop is done, return the minimum rolls.
        return minRolls;
    }

    public static boolean checkPossible(int boardsize, int[][] ladders, int[][] snakes) {
        // Check for correct parameters for ladders
        for (int i = 0; i < ladders.length; i++) {
            for (int j = 0; j < ladders[0].length; j++)
                if (ladders[i][j] > boardsize)
                    return false;
        }

        // Check for correct parameters for snakes
        for (int i = 0; i < snakes.length; i++) {
            for (int j = 0; j < snakes[0].length; j++)
                if (snakes[i][j] > boardsize)
                    return false;
        }

        int count = 1;
        // Checks if there's a block of 6 snakes and no ladder to avoid it
        for (int i = 0; i < snakes.length - 1; i++) {
                if (snakes[i][0] - snakes[i + 1][0] == -1)
                    count++;
                else
                    count =1;
            if (count == 6) {
                for (int j = 0; i < ladders.length; j++)
                    if (ladders[j][0] > snakes[i-4][0])
                        return true;
                return false;
            }
        }
        return true;
    }
}
