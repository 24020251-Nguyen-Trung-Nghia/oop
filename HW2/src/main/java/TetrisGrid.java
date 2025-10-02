//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {
    private boolean[][] grid;

    /**
     * Constructs a new instance with the given grid.
     * Does not make a copy.
     */
    public TetrisGrid(boolean[][] grid) {
        this.grid = grid;
    }

    /**
     * Helper to check if a single row is completely full.
     * Assumes grid[column][row].
     */
    private boolean isRowFull(int row) {
        // grid.length is the width (number of columns)
        for (int col = 0; col < grid.length; col++) {
            if (!grid[col][row]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Does row-clearing on the grid (see handout).
     * This implementation is more efficient using a two-pointer approach,
     * reducing nested loops for the shift operation.
     */
    public void clearRows() {
        if (grid.length == 0 || grid[0].length == 0) return; // Xử lý trường hợp lưới rỗng

        int width = grid.length;
        int height = grid[0].length;

        // 'readRow' là chỉ số đọc (quét qua tất cả các hàng)
        int readRow = 0;
        // 'writeRow' là chỉ số ghi (chỉ ghi các hàng KHÔNG đầy)
        int writeRow = 0;

        //  Quét lưới để sao chép các hàng KHÔNG đầy xuống vị trí thấp hơn
        while (readRow < height) {
            if (!isRowFull(readRow)) {
                // Nếu hàng KHÔNG đầy, ta sao chép nó xuống 'writeRow'

                // Chỉ sao chép nếu 'readRow' lớn hơn 'writeRow' (tránh sao chép đè lên chính nó)
                if (readRow != writeRow) {
                    for (int col = 0; col < width; col++) {
                        // Sao chép grid[cột][readRow] xuống grid[cột][writeRow]
                        grid[col][writeRow] = grid[col][readRow];
                    }
                }

                writeRow++; // Tăng chỉ số ghi để chuẩn bị cho hàng tiếp theo
            }
            // Nếu hàng ĐẦY, ta bỏ qua nó. writeRow KHÔNG tăng,
            // khiến hàng tiếp theo (KHÔNG đầy) sẽ ghi đè lên hàng đầy này.

            readRow++; // Luôn tăng chỉ số đọc để kiểm tra hàng tiếp theo
        }

        //  Điền FALSE vào các hàng trên cùng (từ writeRow đến height - 1)
        // 'writeRow' hiện tại là chỉ số của hàng trống đầu tiên.
        while (writeRow < height) {
            for (int col = 0; col < width; col++) {
                grid[col][writeRow] = false;
            }
            writeRow++;
        }
    }

    /**
     * Returns the internal 2d grid array.
     * @return 2d grid array
     */
    boolean[][] getGrid() {
        return grid;
    }
}