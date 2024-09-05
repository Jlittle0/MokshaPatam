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
    public static ArrayList<SpecialCell> getPaths(int currentPosition, ArrayList<SpecialCell> cells) {
        ArrayList<SpecialCell> paths = new ArrayList<>();


        return paths;
    }

    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        // Sorts the ladder and snake 2D arrays and prevents integer overflow via Intenger.compare
        Arrays.sort(snakes, (a, b) -> Integer.compare(a[0],b[0]));
        Arrays.sort(ladders, (a, b) -> Integer.compare(a[0],b[0]));

        // Checks if the board is possible to complete
        if (!checkPossible(boardsize, ladders, snakes))
            return -1;

        ArrayList<SpecialCell> cells = new ArrayList<SpecialCell>();
        for (int i = 0; i < ladders.length; i++)
            cells.add(new SpecialCell(ladders[i][0], ladders[i][1]));
        for (int i = 0; i < snakes.length; i++)
            cells.add(new SpecialCell(snakes[i][0], snakes[i][1]));
        cells.sort((a, b) -> Integer.compare(a.getStart(), b.getStart()));
        Queue<SpecialCell> path = new LinkedList<>();
        path.add(new SpecialCell(1, 1));
        boolean validPaths;
        int currentPos;
        while (!path.isEmpty()) {
            validPaths = false;
            currentPos = path.peek().getEnd();
            for (SpecialCell cell : getPaths(currentPos, cells)) {

            }
        }



//        int minRolls = (boardsize + MAX_ROLL - 1) / MAX_ROLL;
//        for (int i = 0; i < ladders.length; i++)
//           if ((ladders[i][0] + MAX_ROLL - 1) / MAX_ROLL + (boardsize - ladders[i][1] + MAX_ROLL - 1) / MAX_ROLL < minRolls)
//               minRolls = (ladders[i][0] + MAX_ROLL - 1) / MAX_ROLL + (boardsize - ladders[i][1] + MAX_ROLL - 1) / MAX_ROLL;




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
        // Check the remainder of the min_roll function above and if there are more than that number of snakes within the space, increase the min roll by 1 to compensate.



        // Create a queue and a boolean variable first special. If this is true, only allow ladders to be selected since snakes make no sense, and then after that if it's false then include both possible ladders and snakes.




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

        int count = 1;
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
