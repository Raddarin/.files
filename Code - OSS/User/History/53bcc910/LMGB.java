package suduko;

import suduko.Suduko;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class App {

  public static void main(String[] args) {
    Suduko board = new Suduko();

    // board.change_board(2, 3, 9);

    int[][] board_in = new int[][] {
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

    //board.setGrid(board_in);

    GUI gui = new GUI(board);

  }

}
