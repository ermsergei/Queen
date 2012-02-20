public class Queen {

    static int size = 8;

    public static void main(String[] args) {
        Table table = new Table();
        if (table.Solve(0) == 0) {
            table.Display("Solved:");
        } else {
            System.out.println("Sorry, no solution");
        }
    }


    private static class Table {

        int size = Queen.size;
        // If cell is 0 - it means it's not under attack and a queen can be
        // placed there.  If it's not zero, then it has number of the queen
        // which attacks it
        int cells[][];
        // Here we keep positions of every queen in the table.
        int positions[];

        Table() {
            // At the beginning, all the cells are empty and we can put
            // figures everywhere
            for (int line = 0; line < size; line++) {
                for (int column = 0; column < size; column++) {
                    cells[line][column] = 0;
                }
            }
        }

        int Solve(int line_) {
            for (int column = 0; column < size; column++) {
                if (cells[line_][column] != 0) {
                    continue;
                }

                SetQueen(line_, column);

                if (line_ == size - 1) {
                    // We just placed the queen in the last line - success!
                    return 0;
                } else if (Solve(line_ + 1) == 0) {
                    return 0; // Solved!
                }
                // Ok, so, this columnumn in this line is not what we need.
                UnsetQueen(line_, column);
            }
            // Didn't find a solution
            return 1;
        }

        void SetQueen(int line_, int column_) {
            positions[line_] = column_;

            for (int ii = 0; ii < size; ii++) {
                // Mark the line
                if (cells[line_][ii] == 0) {
                    cells[line_][ii] = line_ + 1;
                }

                // Mark the column
                if (cells[ii][column_] == 0) {
                    cells[ii][column_] = line_ + 1;
                }
            }

            int left, right, up, down;
            left = right = column_;
            up = down = line_;
            for (int ii = 0; ii < size; ii++) {
                if (left >= 0) {
                    if (up >= 0) {
                        if (cells[up][left] == 0) {
                            cells[up][left] = line_ + 1;
                        }
                    }
                    if (down < size) {
                        if (cells[down][left] == 0) {
                            cells[down][left] = line_ + 1;
                        }
                    }
                }

                if (right < size) {
                    if (up >= 0) {
                        if (cells[up][right] == 0) {
                            cells[up][right] = line_ + 1;
                        }
                    }
                    if (down < size) {
                        if (cells[down][right] == 0) {
                            cells[down][right] = line_ + 1;
                        }
                    }
                }
                left--;
                up--;
                down++;
                right++;
            }
        }

        void UnsetQueen(int line, int column) {
            positions[line] = -1;
            for (int ii = 0; ii < size; ii++) {
                for (int jj = 0; jj < size; jj++) {
                    if (cells[ii][jj] == line + 1) {
                        cells[ii][jj] = 0;
                    }
                }
            }
        }

        void Display(String msg) {
            System.out.println(" " + msg);

            for (int line = 0; line < size; line++) {
                for (int column = 0; column < size; column++) {
                    if (column != positions[line]) {
                        // printf("| %1d  ", cells[line][column]);
                        System.out.println("|    " + cells[line][column]);
                    } else {
                        System.out.println("| Q  ");
                    }
                }
                System.out.println("|-----------------------------------------");
            }
            System.out.println(" ");
        }
    }
}
