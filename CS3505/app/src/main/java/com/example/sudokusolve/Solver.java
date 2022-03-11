package com.example.sudokusolve;

//Our sudoku solving code is based on a piece of code found at https://www.geeksforgeeks.org/backtracking-algorithms/
//The original code was simple and the information had to be hardcoded into the source code to solve a sudoku of a specific size.
//Although most of which was not used in the final product we have expanded to solve Sudoku's of ANY size by computing the required information i.e subgrid size from the puzzles size.
//This did not hinder speed to any noticeable degree.
public class Solver {

    //This function is recursively called until the puzzle has arrived at a valid solution or until it is assessed that the puzzle cannot be solved.
    static boolean solve(int puzzle[][], int row, int col) {
        int puzzleSize = puzzle.length;
        //On the second-last column of the final row we know the puzzle has been solved so we can return true at this point to save needless computation.
        if (row == puzzleSize - 1 && col == puzzleSize) {
            return true;
        }

        //Scanner moves to start of next row.
        if (col == puzzleSize) {
            row++;
            col = 0;
        }

        //If the current space in the puzzle is not blank the function is recursively called on the next column, moving through the puzzle.
        if (puzzle[row][col] != 0) {
            return solve(puzzle, row, col + 1);
        }

        //Runs through the valid range of numbers for the given puzzle i.e (1 - 9 etc.),
        //For each number the feasibility of its addition to the working solution is examined,
        //The first valid number to be met is added to the working solution.
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

    //This function will check the validity of any possible insertion into the sudoku.
    //i.e. a number can only be inserted if it does not exist in the current row, column or subgrid already.

    static boolean validInsertion(int[][] puzzle, int row, int col, int num) {
        int puzzleSize = puzzle.length;
        //Checks current row for number.
        for (int x = 0; x < puzzleSize; x++) {
            if (puzzle[row][x] == num) {
                return false;
            }
        }
        //Checks current column for number.
        for (int x = 0; x < puzzleSize; x++) {
            if (puzzle[x][col] == num) {
                return false;
            }
        }
        //Checks current subgrid for number.
        //A far more complex part of the function compared to the row and column checker.
        //Our expansions on the original code allow the algorithm to correctly assess the size of the subgrid in any given puzzle, its position within it,
        // and correctly scan it for the number.
        int subgridWidth = (int) Math.sqrt(puzzleSize);
        int subgridDepth = (int) Math.sqrt(puzzleSize);
        if (subgridWidth * subgridDepth != puzzleSize) {
            subgridDepth++;
            while (puzzleSize % subgridDepth!= 0) {
                subgridDepth++;
            }
            subgridWidth = puzzleSize / subgridDepth;
        }

        int startRow = row - row % subgridWidth;
        int startCol = col - col % subgridDepth;
        for (int i = 0; i < subgridWidth; i++) {
            for (int j = 0; j < subgridDepth; j++) {
                if (puzzle[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

// Simple function to neatly print the contents of a 2d array.

    static void printSudoku(int[][] puzzle) {
        int puzzleSize = puzzle.length;
        int space = 3;
        if (puzzleSize > 9) {
            space++;
        }
        if (puzzleSize > 99) {
            space++;
        }
        String format ="%-"+space+"d";

        for (int currentRow = 0; currentRow < puzzleSize; currentRow++) {
            for (int currentCol = 0; currentCol < puzzleSize; currentCol++) {
                System.out.format(format, puzzle[currentRow][currentCol]);
            }
            System.out.println();
        }
    }
}