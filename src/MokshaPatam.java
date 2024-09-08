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
        for (int i = 0; i < cells.size(); i++)
            if (cells.get(i).getStart() > currentPosition) {
                for (int j = 0; j < cells.size() - i; j++)
                    paths.add(cells.get(i + j));
                break;
            }
        for (int i = 0; i < paths.size(); i++)
            System.out.print("[" + paths.get(i).getStart() + ", " + paths.get(i).getEnd() + "] ");
        System.out.println();
        return paths;
    }

    public static int calculatePathRolls(SpecialCell cell, ArrayList<SpecialCell> cells, int boardsize) {
        int numRolls = 0;
        int temp = boardsize - cell.getEnd();
        int start = cell.getEnd();
        numRolls = calculateRolls(cells, cell.getEnd(), boardsize);
        while (cell.getParent() != null) {
            numRolls += calculateRolls(cells, cell.getParent().getEnd(), cell.getStart());
            cell = cell.getParent();
        }
        numRolls += calculateRolls(cells, 1, cell.getStart());
        System.out.println("Rolls: " + numRolls);
        return numRolls;
    }

    public static int calculateRolls(ArrayList<SpecialCell> cells, int start, int end) {
        // Fix this entire thing
        int count = 0;
        int numRolls = 0;
        int remainder = (end - start + MAX_ROLL - 1) % MAX_ROLL;
        for (int i = 0; i < cells.size(); i++)
            if (cells.get(i).getStart() > start && cells.get(i).getStart() < end)
                count++;
        if (count > remainder)
                numRolls = (end - start + MAX_ROLL - 1) / MAX_ROLL + 1;
        else
            numRolls = (end - start + MAX_ROLL - 1) / MAX_ROLL;
        return numRolls;
    }

    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        int test = 0;
        int minRolls = (boardsize + MAX_ROLL - 1) / MAX_ROLL;
        // Sorts the ladder and snake 2D arrays and prevents integer overflow via Intenger.compare
        Arrays.sort(snakes, (a, b) -> Integer.compare(a[0],b[0]));
        Arrays.sort(ladders, (a, b) -> Integer.compare(a[0],b[0]));

        // Checks if the board is possible to complete
        if (!checkPossible(boardsize, ladders, snakes))
            return -1;
        System.out.println(Arrays.deepToString(snakes));
        System.out.println(Arrays.deepToString(ladders));

        int[] map = new int[boardsize + 1];
        for (int i = 0; i < map.length; i++)
            map[i] = i;
        for (int i = 0; i < snakes.length; i++) {
            map[snakes[i][0]] = snakes[i][1];
        }
        for (int i = 0; i < ladders.length; i++)
            map[ladders[i][0]] = ladders[i][1];
        boolean[] previouslyVisited = new boolean[boardsize + 1];
        Queue<int[]> path = new LinkedList<>();
        path.add(new int[]{1,0});
        int[] currentNode;
        while (!path.isEmpty()) {
            previouslyVisited[path.peek()[0]] = true;
            currentNode = path.remove();
            System.out.println("Node: " + map[currentNode[0]]);
            if (currentNode[0] == boardsize) {
                if (currentNode[1]< minRolls) {
                    minRolls = currentNode[1];
                }
            }
            else if (currentNode[0] > boardsize - MAX_ROLL){
                if (currentNode[1] + 1 < minRolls) {
                    System.out.println(currentNode[0]);
                    minRolls = currentNode[1] + 1;
                }
            }
            else
                for (int i = 1; i < MAX_ROLL + 1; i++) {
                // Fix this if statement
                if (!previouslyVisited[map[currentNode[0] + i]] && map[currentNode[0]] + i < boardsize) {
                    System.out.println("Number: " + (map[currentNode[0] + i]));
                    path.add(new int[]{map[currentNode[0] + i], currentNode[1] + 1});
                    System.out.println("Rolls " + path.peek()[1]);
                    previouslyVisited[map[currentNode[0]] + i] = true;
                }
            }
        }


//        ArrayList<SpecialCell> cells = new ArrayList<SpecialCell>();
//        for (int i = 0; i < ladders.length; i++)
//            cells.add(new SpecialCell(ladders[i][0], ladders[i][1]));
//        for (int i = 0; i < snakes.length; i++)
//            cells.add(new SpecialCell(snakes[i][0], snakes[i][1]));
//        cells.sort((a, b) -> Integer.compare(a.getStart(), b.getStart()));
//        Queue<SpecialCell> path = new LinkedList<>();
//        path.add(new SpecialCell(1, 1));
//        boolean validPaths;
//        int currentPos;
//        while (!path.isEmpty()) {
//            System.out.println("Start:" + path.peek().getStart());
//            validPaths = false;
//            currentPos = path.peek().getEnd();
//            System.out.println("currentPos: " + currentPos);
//            path.peek().setExplored(true);
//            for (SpecialCell cell : getPaths(currentPos, cells)) {
//                // Make sure to change so that cells are only considered "explored" once they've been added to the queue and have added all the possible paths.
//                if (cell != null && !cell.isExplored()) {
//                    path.peek().setExplored(true);
//                    path.add(cell);
//                    cell.setParent(path.peek());
//                    validPaths = true;
//                }
//            }
//            if (!validPaths) {
//                test++;
//                if (calculatePathRolls(path.peek(), cells, boardsize) < minRolls)
//                    // CalculatePathRolls completely doesn't work.
//                    minRolls = calculatePathRolls(path.peek(), cells, boardsize);
//                System.out.println("minRolls2: " + minRolls);
//            }
//            path.remove();
//        }



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
