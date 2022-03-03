package com.example.sudokusolve;

public class Solver {

    static boolean solve(int puzzle[][], int row, int col) {
        int puzzleSize = puzzle.length;
        if (row == puzzleSize - 1 && col == puzzleSize) {
            return true;
        }


        if (col == puzzleSize) {
            row++;
            col = 0;
        }


        if (puzzle[row][col] != 0) {
            return solve(puzzle, row, col + 1);
        }


        for (int num = 1; num < puzzleSize+1; num++) {

            if (validInsertion(puzzle, row, col, num)) {
                puzzle[row][col] = num;

                if (solve(puzzle, row, col + 1)) {
                    return true;
                }

            }

            puzzle[row][col] = 0;
        }
        return false;
    }

    static boolean validInsertion(int[][] puzzle, int row, int col, int num) {
        int puzzleSize = puzzle.length;
        for (int x = 0; x < puzzleSize; x++) {
            if (puzzle[row][x] == num) {
                return false;
            }
        }

        for (int x = 0; x < puzzleSize; x++) {
            if (puzzle[x][col] == num) {
                return false;
            }
        }

        int subgridSize = (int) Math.sqrt(puzzleSize);
        int startRow = row - row % subgridSize;
        int startCol = col - col % subgridSize;
        for (int i = 0; i < subgridSize; i++) {
            for (int j = 0; j < subgridSize; j++) {
                if (puzzle[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

//    static void printSudoku(int[][] puzzle) {
//        int puzzleSize = puzzle.length;
//        int space = 3;
//        if (puzzleSize > 9) {
//            space++;
//        }
//        if (puzzleSize > 99) {
//            space++;
//        }
//        String format ="%-"+space+"d";
//
//        for (int currentRow = 0; currentRow < puzzleSize; currentRow++) {
//            for (int currentCol = 0; currentCol < puzzleSize; currentCol++) {
//                System.out.format(format, puzzle[currentRow][currentCol]);
//            }
//            System.out.println();
//        }
//    }
}