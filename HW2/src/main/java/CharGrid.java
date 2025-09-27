// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

public class CharGrid {
    private char[][] grid;

    /**
     * Constructs a new CharGrid with the given grid.
     * Does not make a copy.
     *
     * @param grid
     */
    public CharGrid(char[][] grid) {
        this.grid = grid;
    }

    /**
     * Returns the area for the given char in the grid. (see handout).
     *
     * @param ch char to look for
     * @return area for given char
     */
    public int charArea(char ch) {
        // Tính max, min x,y;
        int maxX, maxY;
        maxX = maxY = -100;
        int minX, minY;
        minX = minY = 100;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == ch) {
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, j);
                }
            }
        }

        return ((maxY - minY + 1) * (maxX - minX + 1)); // YOUR CODE HERE
    }

    /**
     *
     * check xem day co la tam cua mot hinh chu thap co do dai canh la length hay khong
     */
    public boolean checkPattern(int i, int j, int length) {
        char ch = grid[i][j];
        if (grid[i][j + length] != ch) return false;
        if (grid[i][j - length] != ch) return false;
        if (grid[i - length][j] != ch) return false;
        if (grid[i + length][j] != ch) return false;
        return true;
    }

    /**
     * Returns the count of '+' figures in the grid (see handout).
     *
     * @return number of + in grid
     */
    public int countPlus() {
        boolean[][] isGrid = new boolean[grid.length][grid[0].length];
        int count = 0;

        int max_length = Math.min((grid.length - 1) / 2, (grid[0].length - 1) / 2);
        for (int length = 1; length <= max_length; length++) {  // bắt đầu với check đọ dài cánh là 1;
            for (int i = length; i < grid.length - length; i++) {
                for (int j = length; j < grid[i].length - length; j++) {
                    if (checkPattern(i, j, length)) {
                        count++;
                        isGrid[i][j] = true; // lần sau với đọ dài cánh là 2 thì ta chỉ check các tâm đã là hình chữ thập cánh =1;
                    } else {
                        isGrid[i][j] = false;
                    }
                }
            }
        }
        return count; // YOUR CODE HERE
    }
}
