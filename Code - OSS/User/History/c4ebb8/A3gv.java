
package suduko;

import suduko.GUI;
import suduko.App;
import java.awt.Point;
//import java.lang.classfile.instruction.ReturnInstruction;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JToggleButton;

public class Suduko implements SudukoSolver {

  private int[][] board = new int[9][9];
  private Set<Integer> digits = Set.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
  private Set<Point> invalidPoints = new HashSet<>();
  // private GUI visual_board;

  /*
   * public Suduko(GUI visual_board) {
   * this.visual_board = visual_board;
   * }
   */
  @Override
  public boolean solve() {
    if(isAllValid()) {
      return (solve(0, 0));
    }
    else return false;
  }

  private boolean solve(int r, int c) {
    invalidPoints.clear();
    if (c == 9) {
      return solve(r + 1, 0);
    }
    if (r == 9) {
      return true;
    }
    if (board[r][c] != 0) {
      return solve(r, c + 1);
    }
    for (int i = 1; i <= 9; i++) {
      board[r][c] = i;
      if (isValid(r, c)) {
        if (solve(r, c + 1)) {
          return true;
        }
      }
      board[r][c] = 0;
    }
    return false;
  }

  public interface CellListener {
    void cellChanged(int row, int col, int value);
  }

  private CellListener listener;

  public void setCellListener(CellListener l) {
    this.listener = l;
  }

  @Override
  public void set(int row, int col, int digit) {
    if(digits.contains(digit)) {
      board[row][col] = digit;
      if (listener != null) {
        listener.cellChanged(row, col, digit);
      }
    }
    else throw new IndexOutOfBoundsException("Iligal input");
    /*
     * if (row < 0 || row > 8 || col < 0 || col > 8) {
     * throw new IndexOutOfBoundsException("Row or column is out of bounds.");
     * }
     * if (digit < 0 || digit > 9) {
     * throw new IllegalArgumentException("Digit must be between 0 and 9.");
     * }
     */
    // int square_num = (col / 3) * 3 + (row / 3);
    // int old_val = get(row, col);

    
  }

  @Override
  public int get(int row, int col) {
    if (row < 0 || row > 8 || col < 0 || col > 8) {
      throw new IndexOutOfBoundsException("Row or column is out of bounds.");
    }
    return board[row][col];
  }

  @Override
  public void clear(int row, int col) {
    if (row < 0 || row > 8 || col < 0 || col > 8) {
      throw new IndexOutOfBoundsException("Row or column is out of bounds.");
    }
    int old_val = get(row, col);
    int square_num = (col / 3) * 3 + (row / 3);

    board[row][col] = 0;
  }

  @Override
  public void clearAll() {
    board = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        clear(i, j);
      }
    }
  }

  @Override
  public boolean isValid(int row, int col) {
    boolean output = true;
    if (row < 0 || row > 8 || col < 0 || col > 8) {
      throw new IndexOutOfBoundsException("Row or column is out of bounds.");
    }

    int digit = get(row, col);
    if (digit == 0)
      return true;
    if (digits.contains(digit) == false) {
      invalidPoints.add(new Point(row, col));
      output = false;
    }
    // Row
    for (int c = 0; c < 9; c++) {
      if (c != col && board[row][c] == digit) {
        invalidPoints.add(new Point(row, c));
        output = false;
      }
    }

    // Col
    for (int r = 0; r < 9; r++) {
      if (r != row && board[r][col] == digit) {
        invalidPoints.add(new Point(r, col));
        output = false;
      }
    }

    // 3x3
    int r0 = (row / 3) * 3;
    int c0 = (col / 3) * 3;
    for (int r = r0; r < r0 + 3; r++) {
      for (int c = c0; c < c0 + 3; c++) {
        if ((r != row || c != col) && board[r][c] == digit) {
          invalidPoints.add(new Point(r, c));
          output = false;
        }
      }
    }

    return output;

  }

  @Override
  public boolean isAllValid() {
    invalidPoints.clear();

    boolean no_problem = true;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (!isValid(i, j)) {
          no_problem = false;
        }
      }
    }
    return no_problem;
  }

  @Override
  public void setGrid(int[][] m) {
    if (m.length != 9 || m[0].length != 9) {
      throw new IllegalArgumentException("Matrix must be of dimension 9x9.");
    }

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        set(i, j, m[i][j]);
      }
    }
    if (!isAllValid()) {
      StringBuilder sb = new StringBuilder("Error in: ");
      for (Point p : invalidPoints) {
        sb.append("(").append(p.x).append(", ").append(p.y).append(") ");
      }
      throw new IllegalArgumentException(sb.toString());
    }
  }

  @Override
  public int[][] getGrid() {
    int[][] copy = new int[9][9];
    for (int r = 0; r < 9; r++) {
      copy[r] = board[r].clone(); // kopierar varje rad
    }
    return copy;
  }

  public Set<Point> getWrongPoints() {
    return invalidPoints;
  }
}
