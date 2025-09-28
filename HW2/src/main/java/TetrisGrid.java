//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {
    private boolean[][] grid;

    /**
     * Constructs a new instance with the given grid.
     * Does not make a copy.
     *
     * @param grid
     */
    public TetrisGrid(boolean[][] grid) {
        int I = grid.length;
        int J = grid[0].length;
        this.grid = new boolean[I][J];
        for( int i =0 ; i < I ; i++ ) {
            for ( int j =0 ; j < J ; j++ ) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    /**
     * Does row-clearing on the grid (see handout).
     */
    /**
     * Thực hiện xóa tất cả các hàng đầy và dịch chuyển các khối còn lại xuống.
     * Đây là logic chuẩn của Tetris (giống như code gợi ý trước).
     */
    public void clearRows() {
        int nextRowIndex = grid.length - 1;

        for (int i = grid.length - 1; i >= 0; i--) {

            boolean isFull = true;
            for (int j = 0; j < grid[0].length; j++) {
                if (!grid[i][j]) { // Nếu tìm thấy một ô trống (false)
                    isFull = false;
                    break;
                }
            }

            // Xử lý dịch chuyển (chỉ áp dụng cho hàng KHÔNG đầy)
            if (!isFull) {
                // Nếu vị trí hàng đích (nextRowIndex) khác vị trí hiện tại (i):
                // Tức là có hàng đã bị xóa (đã có khoảng trống)
                if (nextRowIndex != i) {
                    // Sao chép nội dung hàng i xuống vị trí mới nextRowIndex
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[nextRowIndex][j] = grid[i][j];
                    }
                }
                // Giảm chỉ số để chuẩn bị cho hàng không đầy tiếp theo (đi lên)
                nextRowIndex--;
            }

            // Nếu hàng ĐẦY, ta bỏ qua nó. nextRowIndex không thay đổi,
            // khiến hàng không đầy phía trên sẽ ghi đè lên hàng đầy này.
        }

        //  Làm trống các hàng trên cùng (các hàng đã bị xóa)
        // Các hàng từ 0 đến nextRowIndex đã bị xóa và cần được làm trống.
        for (int i = 0; i <= nextRowIndex; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = false;
            }
        }
    }

    /**
     * Returns the internal 2d grid array.
     *
     * @return 2d grid array
     */
    boolean[][] getGrid() {
        boolean[][] newGrid = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                newGrid[i][j] = this.grid[i][j];
            }
        }

        return newGrid; // YOUR CODE HERE
    }
}
