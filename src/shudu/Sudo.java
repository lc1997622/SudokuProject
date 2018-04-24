package shudu;

import org.apache.commons.cli.*;

import java.io.*;

public class Sudo {
    public static void main(String[] args) throws ParseException {
        
        Options ops = new Options();
        ops.addOption("h", false, "help");
        ops.addOption("c", true, "生成数独");
        ops.addOption("s", true, "求解数独");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(ops, args);

        if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("options", ops);
        }
        if (cmd.hasOption("c")) {
            String number = cmd.getOptionValue("c");
            System.out.println(number);

            try {
                /**
                 * 建立输入输出类对象
                 * 调用SudokuPuzzleGenerator生成数独
                 */
                String fileName = "./BIN/sudoku.txt";
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
                SudokuPuzzleGenerator example = new SudokuPuzzleGenerator();

                for (int k = 1; k <= 1000; k++) {
                    int flag = 0;
                    int[][] grids = example.generatePuzzleMatrix();
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            bufferedWriter.write(grids[i][j] + '0');
                            if (j != 8) bufferedWriter.write(" ");
                        }
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (cmd.hasOption("s")) {

            /**
             * 建立输入输出类对象
             * 调用SudokuPuzzleSolver解数独
             */
            try {
                String pathname = "./BIN/udoku1.txt";
                File file = new File(pathname);
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
                BufferedReader br = new BufferedReader(reader);
                String fileName = "./BIN/sudoku.txt";
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

                String line = "";
                int i = 0, j = 0;
                int[][] grids = new int[9][9];

                line = br.readLine();
                while (line != null) {
                    if (line.length() <= 1) {
                        SudokuPuzzleSolver solver = new SudokuPuzzleSolver();
                        solver.solve(grids, bufferedWriter);
                        for (int k = 0; k < 9; k++) {
                            for (int p = 0; p < 9; p++) ;
                        }
                        i = 0;
                        j = 0;
                    } else {
                        for (int k = 0; k < line.length(); k += 2, j++) grids[i][j] = (int) (line.charAt(k) - '0');
                        i++;
                        j = 0;
                    }
                    line = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
