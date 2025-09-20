import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class SpiralWalk {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setScale(-n - 0.5, n + 0.5);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();

        int x = 0, y = 0;
        int steps = 0;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIndex = 0;
        int stepSize = 1;

        while (Math.abs(x) < n && Math.abs(y) < n) {
            for (int k = 0; k < 2; k++) {
                int dx = directions[dirIndex][0];
                int dy = directions[dirIndex][1];
                for (int s = 0; s < stepSize; s++) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledSquare(x, y, 0.45);
                    x += dx;
                    y += dy;
                    steps++;
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.filledSquare(x, y, 0.45);
                    StdDraw.show();
                    StdDraw.pause(40);

                    if (Math.abs(x) >= n || Math.abs(y) >= n) {
                        StdOut.println("Total steps = " + steps);
                        return;
                    }
                }
                dirIndex = (dirIndex + 1) % 4;
            }
            stepSize++;
        }
        StdOut.println("Total steps = " + steps);
    }
}