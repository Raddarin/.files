package suduko;

import suduko.Suduko;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.util.HashSet;
import java.util.Set;

import java.util.Comparator;
import java.util.Map;
import java.awt.*;
import java.awt.event.*;

public class GUI {
  private Suduko board;
  private int global_col;
  private int global_row;
  private JToggleButton[][] cell_matrix;

  public GUI(Suduko board) {
    this.board = board;
    SwingUtilities.invokeLater(() -> createWindow(board, "Suduko", 600, 600));
  }

  private static final Color ORANGE = new Color(255, 148, 00);
  private static final Color WHITE = Color.WHITE;
  private static final Color RED = new Color(255, 00, 00);
  private static final Color GREEN = new Color(00, 255, 00);

  private void createWindow(Suduko suduko, String title, int width, int height) {
    JFrame frame = new JFrame(title);

    frame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        int[][] g = board.getGrid();
        for (int r = 0; r < 9; r++) {
          for (int c = 0; c < 9; c++) {
            System.out.print(g[r][c] + (c == 8 ? "" : " "));
          }
          System.out.println();
        }
      }
    });

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container pane = frame.getContentPane();

    // =====================Vy================================

    JPanel board_outer = new JPanel(new GridLayout(3, 3));

    cell_matrix = new JToggleButton[9][9];

    board.setCellListener((row, col, value) -> {
      SwingUtilities.invokeLater(() -> {
        if (cell_matrix != null) {
          cell_matrix[row][col].setText(value == 0 ? "" : String.valueOf(value));
        }
      });
    });

    // Font font = new Font("SansSerif", Font.BOLD, 20);
    Color useColor;

    ButtonGroup group = new ButtonGroup();

    for (int i = 0; i < 9; i++) {
      JPanel board_inner = new JPanel(new GridLayout(3, 3));

      boolean orange_box = ((i / 3) + (i % 3)) % 2 == 0;
      if (orange_box) {
        useColor = ORANGE;
      } else {
        useColor = WHITE;
      }

      for (int j = 0; j < 9; j++) {
        int row = (i / 3) * 3 + (j / 3);
        int col = (i % 3) * 3 + (j % 3);

        JToggleButton cell;
        if (board.get(row, col) != 0) {
          cell = new JToggleButton(String.valueOf(board.get(row, col)));
        } else {
          cell = new JToggleButton("");
          // cell = new JToggleButton(String.valueOf(board.get_num(col, row)));
        }

        cell_matrix[row][col] = cell;

        cell.setBackground(useColor);
        cell.setForeground(Color.BLACK);
        cell.setOpaque(true);
        cell.setContentAreaFilled(true);
        cell.setFocusPainted(false);

        group.add(cell);

        cell.addActionListener(e -> {
          global_col = col;
          global_row = row;
        });

        board_inner.add(cell);
      }

      board_outer.add(board_inner);
    }

    pane.add(board_outer, BorderLayout.CENTER);

    // ===================Knappar================================
    JPanel interactionBar = new JPanel();

    JButton clear = new JButton("Clear");
    JButton solve = new JButton("Solve");
    interactionBar.add(clear);
    interactionBar.add(solve);

    pane.add(interactionBar, BorderLayout.SOUTH);

    clear.addActionListener(e -> {
      board.clearAll();

      for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {

          boolean orangeBox = ((row / 3) + (col / 3)) % 2 == 0;
          Color bg = orangeBox ? ORANGE : WHITE;

          cell_matrix[row][col].setBackground(bg);
          cell_matrix[row][col].setText("");
          cell_matrix[row][col].setSelected(false);
        }
      }
    });

    solve.addActionListener(e -> {
      if (board.isAllValid()) {
        if (board.solve()) {
          for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
              cell_matrix[i][j].setText(String.valueOf(board.get(i, j)));
              cell_matrix[i][j].setBackground(GREEN);
            }
          }

        } else {
          for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
              cell_matrix[i][j].setBackground(RED);
            }
          }

        }
      } else {
        Set<Point> invalidPoints = board.getWrongPoints(); 
        for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
            if (invalidPoints.contains(new Point(i, j))) {
              cell_matrix[i][j].setBackground(RED);
            }
          }
        }
      }
    });

    // ======================KeyBinds==========================

    JComponent comp = board_outer;
    InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = comp.getActionMap();

    im.put(KeyStroke.getKeyStroke('0'), "zero");
    am.put("zero", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(0);
      }
    });

    // 1
    im.put(KeyStroke.getKeyStroke('1'), "one");
    am.put("one", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(1);
      }
    });

    // 2
    im.put(KeyStroke.getKeyStroke('2'), "two");
    am.put("two", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(2);
      }
    });

    // 3
    im.put(KeyStroke.getKeyStroke('3'), "three");
    am.put("three", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(3);
      }
    });

    // 4
    im.put(KeyStroke.getKeyStroke('4'), "four");
    am.put("four", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(4);
      }
    });

    // 5
    im.put(KeyStroke.getKeyStroke('5'), "five");
    am.put("five", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(5);
      }
    });

    // 6
    im.put(KeyStroke.getKeyStroke('6'), "six");
    am.put("six", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(6);
      }
    });

    // 7
    im.put(KeyStroke.getKeyStroke('7'), "seven");
    am.put("seven", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(7);
      }
    });

    // 8
    im.put(KeyStroke.getKeyStroke('8'), "eight");
    am.put("eight", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(8);
      }
    });

    // 9
    im.put(KeyStroke.getKeyStroke('9'), "nine");
    am.put("nine", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        change_number(9);
      }
    });

    frame.pack();
    frame.setVisible(true);
  }

  private void change_number(int number) {
    board.set(global_row, global_col, number);
    cell_matrix[global_row][global_col].setText(number == 0 ? "" : String.valueOf(number));

  }

}
