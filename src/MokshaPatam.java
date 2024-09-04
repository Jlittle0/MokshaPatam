import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

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
        // Sorts the ladder and snake 2D arrays and prevents integer overflow via Intenger.compare
        Arrays.sort(snakes, (a, b) -> Integer.compare(a[0],b[0]));
        Arrays.sort(ladders, (a, b) -> Integer.compare(a[0],b[0]));

        // Checks if the board is possible to complete
        if (!checkPossible(boardsize, ladders, snakes))
            return -1;
        int minRolls = (boardsize + MAX_ROLL - 1) / MAX_ROLL;
        for (int i = 0; i < ladders.length; i++)
           if ((ladders[i][0] + MAX_ROLL - 1) / MAX_ROLL + (boardsize - ladders[i][1] + MAX_ROLL - 1) / MAX_ROLL < minRolls)
               minRolls = (ladders[i][0] + MAX_ROLL - 1) / MAX_ROLL + (boardsize - ladders[i][1] + MAX_ROLL - 1) / MAX_ROLL;

//        ArrayList<SpecialCell> cells = new ArrayList<SpecialCell>();
//        for (int i = 0; i < ladders.length; i++)
//            cells.add(new SpecialCell(ladders[i][0], ladders[i][1]));
//        for (int i = 0; i < snakes.length; i++)
//            cells.add(new SpecialCell(snakes[i][0], snakes[i][1]));
//        Stack<int[]> path = new Stack<int[]>();
//        path.push(new int[]{0, 0});
//        boolean validSpecial = true;
//        int initialPosition = 1;
//        int currentPosition = initialPosition;
//        if (ladders.length == 0 && snakes.length == 0)
//            validSpecial = false;
//        while (validSpecial) {
//            currentPosition = path.peek()[1];
//
//        }




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

        // Checks if there's a block of 6 snakes and no ladder to avoid it
        for (int i = 0; i < snakes.length - 1; i++) {
                if (snakes[i][0] - snakes[i + 1][0]== 1)
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
