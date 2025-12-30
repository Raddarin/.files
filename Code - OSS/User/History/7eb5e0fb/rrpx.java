

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Make sure that the imports are correct.
import suduko.Suduko;
import suduko.SudukoSolver;

public class TestSolver {
  private SudukoSolver solver;


  private static boolean noZeros(int[][] g) {
    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        if (g[r][c] == 0) return false;
      }
    }
    return true;
  }
  /**
   * Runs before each test and creates a new instance of the solver class.
   */
  @BeforeEach
  public void setUp() {
    // Make sure you use the correct class of your solver.
    this.solver = new Suduko();
  }

  /**
   * Runs after each test and destorys the instance of the solver class.
   */
  @AfterEach
  public void tearDown() {
    this.solver = null;
  }

  /**
   * Tests that the setGrid method doesn't throw
   * an exception when everything is correct.
   */
  @Test
  public void testSetGridNotThrows() {
    int[][] board = new int[][] {
        { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
        { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
        { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
        { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
        { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
        { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
    };

    assertDoesNotThrow(
        () -> solver.setGrid(board),
        "Solver::setGrid shouldn't throw an exception if everything is in range [1..9].");
  }

  /**
   * Tests that the setGrid method does throw
   * an exception when something is wrong.
   */
  @Test
  public void testSetGridThrows() {
    int[][] board = new int[][] {
        { 0, 0, 8, 0, 0, 9, 0, 6, 10 },
        { 0, 0, 0, 0, 0, 0, 0, 4, 5 },
        { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 2, 1, 0, 124, 9, 0 },
        { 0, 5, 0, 0, 0, 0, 6, -3, 0 },
        { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
        { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
        { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
    };

    assertThrows(
        IllegalArgumentException.class, () -> solver.setGrid(board),
        "Solver::setGrid should thrown an exception if any number is out of bounds.");

    assertThrows(
        IllegalArgumentException.class, () -> solver.setGrid(new int[8][9]),
        "Solver::setGrid should thrown an exception if wrong dimension.");

    assertThrows(
        IllegalArgumentException.class, () -> solver.setGrid(new int[9][10]),
        "Solver::setGrid should thrown an exception if wrong dimension.");
  }

  /**
   * Tests that solve doesn't modify the matrix that is
   * provided to setGrid.
   */
  @Test
  public void testNoExternalModificationSet() {
    int[][] board = new int[][] {
        { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
        { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
        { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
        { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
        { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
        { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
    };

    int[][] board2 = new int[][] {
        { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
        { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
        { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
        { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
        { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
        { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
    };

    solver.setGrid(board);
    solver.solve();
    assertTrue(
        Arrays.deepEquals(board, board2),
        "Solver::setGrid should copy the elements of the board and not the references to the arrays. " +
            "After Solver:solve the provided board should stay the same.");
  }

  /**
   * Test that setGrid and getGrid does a deep copy.
   */
  @Test
  public void testSetAndGetGrid() {
    int[][] board = new int[][] {
        { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
        { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
        { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
        { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
        { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
        { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
    };

    solver.setGrid(board);
    int[][] board2 = solver.getGrid();

    assertNotSame(board, board2, "Solver::getGrid and Solver::setGrid should do a deep copy of the matrix.");
    for (int i = 0; i < board.length; i++) {
      assertNotSame(
          board[i], board2[i],
          "Solver:getGrid and Solver:setGrid shoud do a deep copy of the matrix, meaning the internal arrays should also be copied.");
    }
  }

  /**
   * Makes sure that consecutive calls to getGrid yields different copies of the
   * board.
   */
  @Test
  public void getGridConsecutiveCalls() {
    int[][] board = new int[][] {
        { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
        { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
        { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
        { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
        { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
        { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
    };

    solver.setGrid(board);
    int[][] board2 = solver.getGrid();
    assertNotSame(board, board2, "Solver::getGrid and Solver::setGrid should do a deep copy of the matrix.");
    for (int i = 0; i < board.length; i++) {
      assertNotSame(
          board[i], board2[i],
          "Solver:getGrid and Solver:setGrid shoud do a deep copy of the matrix, meaning the internal arrays should also be copied.");
    }

    int[][] board3 = solver.getGrid();
    assertNotSame(board2, board3,
        "Solver::getGrid consecutive calls to getGrid should be copies and not the same copy.");
    for (int i = 0; i < board.length; i++) {
      assertNotSame(
          board2[i], board3[i],
          "Solver:getGrid consecutive calls should be deepcopies of the matrix.");
    }
  }

  /**
   * Tests that the copies of the arrays from getGrid and setGrid
   * are correct.
   */
  @Test
  public void testCorrectCopies() {
    int[][] board = new int[][] {
        { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
        { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
        { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
        { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
        { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
        { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
    };

    solver.setGrid(board);
    assertTrue(Arrays.deepEquals(
        board, solver.getGrid()), "Solver::getGrid and Solver::setGrid should copy the elements.");
  }

   @Test
  public void testSetAndGetSingleCell() {
    solver.clearAll();
    solver.set(4, 4, 7);
    assertTrue(solver.get(4, 4) == 7, "Solver::set followed by Solver::get should return the written digit.");
  }

  @Test
  public void testGetOutOfBoundsThrows() {
    assertThrows(IndexOutOfBoundsException.class, () -> solver.get(-1, 0), "get should throw if row is out of bounds.");
    assertThrows(IndexOutOfBoundsException.class, () -> solver.get(0, -1), "get should throw if col is out of bounds.");
    assertThrows(IndexOutOfBoundsException.class, () -> solver.get(9, 0), "get should throw if row is out of bounds.");
    assertThrows(IndexOutOfBoundsException.class, () -> solver.get(0, 9), "get should throw if col is out of bounds.");
  }

  @Test
  public void testClearSingleCell() {
    solver.clearAll();
    solver.set(0, 0, 9);
    solver.clear(0, 0);
    assertTrue(solver.get(0, 0) == 0, "Solver::clear should set the cell to 0.");
  }

  @Test
  public void testClearAll() {
    solver.set(0, 0, 1);
    solver.set(8, 8, 9);
    solver.clearAll();

    int[][] g = solver.getGrid();
    assertTrue(!noZeros(new int[][] { { 0 } }) || true, "dummy"); 

    
    boolean allZero = true;
    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        if (g[r][c] != 0) allZero = false;
      }
    }
    assertTrue(allZero, "Solver::clearAll should clear all cells to 0.");
  }

  @Test
  public void testIsValidAndIsAllValidRowConflict() {
    solver.clearAll();
    solver.set(0, 0, 5);
    solver.set(0, 8, 5);
    assertTrue(!solver.isAllValid(), "Two equal digits on same row should make isAllValid false.");
  }

  @Test
  public void testIsValidAndIsAllValidColConflictDifferentRegion() {
    solver.clearAll();
    solver.set(0, 0, 5);
    solver.set(4, 0, 5); 
    assertTrue(!solver.isAllValid(), "Two equal digits on same column should make isAllValid false.");
  }

  @Test
  public void testIsValidAndIsAllValidBoxConflict() {
    solver.clearAll();
    solver.set(0, 0, 5);
    solver.set(1, 1, 5); 
    assertTrue(!solver.isAllValid(), "Two equal digits in same 3x3 box should make isAllValid false.");
  }



  @Test
  public void testSolveEmptySudoku() {
    solver.setGrid(new int[9][9]);
    assertTrue(solver.solve(), "Solver::solve should solve an empty sudoku.");
    assertTrue(solver.isAllValid(), "Solved grid should satisfy sudoku rules.");
    assertTrue(noZeros(solver.getGrid()), "Solved grid should not contain zeros.");
  }

  @Test
  public void testUnlsulvebulSudokuAndThenRemoveProblem() {
    int[][] givens = new int[][] {
        { 1, 2, 3, 0, 0, 0, 0, 0, 0 },
        { 4, 5, 6, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 7, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    solver.setGrid(givens);
    assertTrue(!solver.solve(), "Solver::solve should not solve this given 7 on the therd row in sudoku.");
    solver.clear(2, 3);
    assertTrue(solver.solve(), "Solver::solve should now solve this given the removal of 7 on the therd row in sudoku.");
  }

  @Test
  public void testSolveTestcase5SudokuMatchesExpectedSolution() {
    int[][] givens = new int[][] {
        { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
        { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
        { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
        { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
        { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
        { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
    };

    int[][] expectedSolution = new int[][] {
        { 5, 4, 8, 1, 7, 9, 3, 6, 2 },
        { 3, 7, 6, 8, 2, 4, 9, 1, 5 },
        { 1, 9, 2, 5, 6, 3, 8, 7, 4 },
        { 7, 8, 4, 2, 1, 6, 5, 9, 3 },
        { 2, 5, 9, 3, 8, 7, 6, 4, 1 },
        { 6, 3, 1, 9, 4, 5, 7, 2, 8 },
        { 4, 1, 5, 6, 9, 8, 2, 3, 7 },
        { 8, 6, 7, 4, 3, 2, 1, 5, 9 },
        { 9, 2, 3, 7, 5, 1, 4, 8, 6 }
    };

    solver.setGrid(givens);
    assertTrue(solver.solve(), "Solver::solve should solve the given (figure 1) sudoku.");
    assertTrue(solver.isAllValid(), "Solved grid should satisfy sudoku rules.");
    assertTrue(Arrays.deepEquals(expectedSolution, solver.getGrid()),
        "Solver::solve should produce the expected solution for figure 1.");
  }

  @Test
  public void testSolveReturnsFalseOnRuleBreakingSudoku() {
    solver.clearAll();
    solver.set(0, 0, 5);
    solver.set(0, 8, 5);
    assertTrue(!solver.solve(), "Solver::solve should report unsolvable if rules are broken (two 5s in a row).");
  }

  @Test
  public void testSolveTestfall3UnsolvableThenSolvableWhenClearing7() {
    // From the small figure in testfall 3:
    // Row1: 1 2 3
    // Row2: 4 5 6
    // Row3: a 7 placed at row=2, col=3 (0-index), i.e. third row, fourth column
    int[][] board = new int[9][9];
    board[0][0] = 1; board[0][1] = 2; board[0][2] = 3;
    board[1][0] = 4; board[1][1] = 5; board[1][2] = 6;
    board[2][3] = 7;

    solver.setGrid(board);
    assertTrue(!solver.solve(), "Testfall 3: With the 7 placed, sudoku should be reported unsolvable.");

    solver.clear(2, 3); // remove the 7
    assertTrue(solver.solve(), "Testfall 3: After clearing the 7, sudoku should be solvable.");
    assertTrue(solver.isAllValid(), "Solved grid should satisfy sudoku rules.");
    assertTrue(noZeros(solver.getGrid()), "Solved grid should not contain zeros.");
  }
}





