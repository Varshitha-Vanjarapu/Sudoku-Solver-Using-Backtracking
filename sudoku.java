class SudokuSolver {

    static final int N = 9;

    // Function to print the Sudoku grid
    static void printGrid(int[][] grid) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }
    // Check if it is safe to place num in grid[row][col]
    static boolean isSafe(int[][] grid, int row, int col, int num) {

        // Check row and column
        for (int x = 0; x < N; x++) {
            if (grid[row][x] == num || grid[x][col] == num) {
                return false;
            }
        }
        // Check 3x3 sub-grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Solve Sudoku using backtracking
    static boolean solveSudoku(int[][] grid) {
        int row = 0, col = 0;
        boolean emptyFound = false;

        // Find an empty cell
        for (row = 0; row < N; row++) {
            for (col = 0; col < N; col++) {
                if (grid[row][col] == 0) {
                    emptyFound = true;
                    break;
                }
            }
            if (emptyFound) break;
        }
        // No empty cell means puzzle solved
        if (!emptyFound)
            return true;
        // Try numbers 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(grid))
                    return true;

                // Backtrack
                grid[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        };

        if (solveSudoku(grid)) {
            System.out.println("Solved Sudoku:");
            printGrid(grid);
        } else {
            System.out.println("No solution exists");
        }
    }
}
