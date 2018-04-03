package shudu;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class SudokuPuzzleSolver {

    /**
     * 解数独，并打印结果
     */
    public void solve(int[][] grids,BufferedWriter bufferedWriter) {

        try {


            if (this.solve(grids, 0, 0)) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        bufferedWriter.write(grids[i][j] + '0');
                        if (j != 8) bufferedWriter.write(" ");
                    }
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("没有");
                bufferedWriter.newLine();

            } else {
                bufferedWriter.write("没有找到相应的解法");
                bufferedWriter.newLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 解数独
     */
    private boolean solve(int[][] grids, int row, int column) {

        if (row == 8 && column == 9) {
            return true;
        }

        if (column == 9) {
            column = 0;
            row++;
        }
        /**
         * 如果当前位置上grids[row][column]的值不为0，则处理下一个
         */
        if (grids[row][column] != 0) {
            return solve(grids, row, column + 1);
        }

        /**
         * 如果当前位置上grids[row][column]的值为0, 尝试在1~9的数字中选择合适的数字
         */
        for (int num = 1; num <= 9; num++) {
            if (isSafe(grids, row, column, num)) {
                grids[row][column] = num;
                if (solve(grids, row, column + 1)) {
                    return true;
                }

            }
        }
        /**
         * 回溯重置
         */
        grids[row][column] = 0;
        return false;
    }

    /**
     * 某一行放置数据是否有冲突
     */
    private boolean isRowSafe(int[][] grids, int row, int value) {
        for (int i = 0; i < 9; i++) {
            if (grids[row][i] == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * 某一列放置数据是否有冲突
     */
    private boolean isColumnSafe(int[][] grids, int column, int value) {
        for (int i = 0; i < 9; i++) {
            if (grids[i][column] == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * 每个区域是 3 X 3 的子块，是否可以可以放置数据
     */
    private boolean isSmallBoxSafe(int[][] grids, int row, int column, int value) {
        int rowOffset = (row / 3) * 3;
        int columnOffset = (column / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grids[rowOffset + i][columnOffset + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 在指定位置是否可以放置数据
     */
    private boolean isSafe(int[][] grids, int row, int column, int value) {
        return this.isColumnSafe(grids, column, value)
                && this.isRowSafe(grids, row, value)
                && this.isSmallBoxSafe(grids, row, column, value);
    }

}
