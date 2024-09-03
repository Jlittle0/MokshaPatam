import java.util.Arrays;

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
        // Basic version of the project that doesn't account for multiple ladders being required
        // or using snakes to traverse the space or literally anything other than the tallest
        // singular ladder that allows you to move up in the board.
        // Add a check path method that returns minRolls here for each ladder;
        if (!checkPossible(boardsize, ladders, snakes))
            return -1;
        int minRolls = Integer.MAX_VALUE;
       for (int i = 0; i < ladders.length; i++)
           if ((ladders[i][0] + MAX_ROLL - 1) / MAX_ROLL + (boardsize - ladders[i][1] + MAX_ROLL - 1) / MAX_ROLL < minRolls)
               minRolls = (ladders[i][0] + MAX_ROLL - 1) / MAX_ROLL + (boardsize - ladders[i][1] + MAX_ROLL - 1) / MAX_ROLL;
//        System.out.println(Arrays.deepToString(snakes));
//        System.out.println(Arrays.deepToString(ladders));
//        System.out.println(boardsize);
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

        // Sorts the array of snakes into chronological order
        int count = 1;
        int[][] temp = new int[1][1];
        for (int i = 0; i < snakes.length; i++)
            for (int j = 0; j < snakes.length; j++)
                if (snakes[i][0] > snakes[j][0]) {
                    temp[0] = snakes[i];
                    snakes[i]= snakes[j];
                    snakes[j] = temp[0];
                }

        // Checks if there's a block of 6 snakes (doesn't check for ladders passing it)
        for (int i = 0; i < snakes.length - 1; i++) {
                if (snakes[i][0] - snakes[i + 1][0]== 1)
                    count++;
                else
                    count =1;
            if (count == 6)
                // Make sure to include checking for a ladder that goes past the snake block later
                return false;
        }
        return true;
    }
}
