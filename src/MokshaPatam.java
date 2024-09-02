import java.util.Arrays;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
 *
 */

public class MokshaPatam {

    public static final int MAX_ROLL = 6;

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        // Basic version of the project that doesn't account for multiple ladders being required
        // or using snakes to traverse the space or literally anything other than the tallest
        // singular ladder that allows you to move up in the board.
        int minRolls = Integer.MAX_VALUE;
       for (int i = 0; i < ladders.length; i++)
           if ((100 - (ladders[i][1] - ladders[i][0]) + MAX_ROLL - 1) / MAX_ROLL < minRolls)
               minRolls = (100 - (ladders[i][1] - ladders[i][0]) + MAX_ROLL - 1) / MAX_ROLL;
        System.out.println(Arrays.deepToString(snakes));
        System.out.println(Arrays.deepToString(ladders));
        if (!checkPossible(boardsize, ladders, snakes))
            return -1;
        return minRolls;
    }

    public static boolean checkPossible(int boardsize, int[][] ladders, int[][] snakes) {
        // Check for correct parameters for ladders/snakes
        for (int i = 0; i < ladders.length; i++) {
            for (int j = 0; j < ladders[0].length; j++)
                if (ladders[i][j] > boardsize || snakes[i][j] > boardsize)
                    return false;
        }
        // Check for a string of 6 snakes that block the path.
        int count = 1;
        for (int i = 1; i < snakes.length; i++) {
            for (int j = 0; j < snakes[0].length; j++) {
                if (Math.abs(snakes[i][0] - snakes[j][0]) == 1)
                    count++;
                else
                    count = 1;
                if (count == 6)
                    // Make sure to include checking for a ladder that goes past the snake block later
                    return false;
            }
        }
        return true;
    }
}
