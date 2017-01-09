package org.mo39.fmbh.datastructure.design;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * 
 * You may assume the following rules:
 * 
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * 
 * 
 * Example:
 * 
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 * 
 * TicTacToe toe = new TicTacToe(3);
 * 
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 * 
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 * 
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 * 
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 * 
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 * 
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 * 
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * 
 * 
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 * 
 * </pre>
 * 
 * 
 * @see <a href="https://leetcode.com/problems/design-tic-tac-toe/">Design Tic Tac Toe</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum DesignTicTacToe {

  BRUTE_FORCE {

    char[][] grid;

    @Override
    public void init(int n) {
      grid = new char[n][n];
    }

    @Override
    public int move(int row, int col, int player) {
      char mark = player == 1 ? 'X' : 'O';
      grid[row][col] = mark;
      for (int i = 0; i < grid.length; i++) {
        boolean horizontal = true, vertical = true;
        for (int j = 0; j < grid.length; j++) {
          if (grid[i][j] != mark || j > 0 && grid[i][j] != grid[i][j - 1]) horizontal = false;
          if (grid[j][i] != mark || j > 0 && grid[j][i] != grid[j - 1][i]) vertical = false;
        }
        if (horizontal || vertical) return player;
      }
      boolean leftTopDiagonal = true;
      for (int i = 1, j = 1; i < grid.length; i++, j++) {
        if (grid[i][j] != mark || grid[i][j] != grid[i - 1][j - 1]) leftTopDiagonal = false;
      }
      if (leftTopDiagonal) return player;
      boolean leftBottomDiagonal = true;
      for (int i = grid.length - 2, j = 1; i > -1; i--, j++) {
        if (grid[i][j] != mark || grid[i][j] != grid[i + 1][j - 1]) leftBottomDiagonal = false;
      }
      if (leftBottomDiagonal) return player;
      return 0;
    }

  },

  LINEAR_TIME_SOLUTION {

    char[][] grid;

    @Override
    public void init(int n) {
      grid = new char[n][n];
    }

    @Override
    public int move(int row, int col, int player) {
      char mark = player == 1 ? 'X' : 'O';
      grid[row][col] = mark;
      boolean horizontal = true, vertical = true;
      for (int i = 0; i < grid.length; i++) {
        if (grid[row][i] != mark || i > 0 && grid[row][i] != grid[row][i - 1]) horizontal = false;
        if (grid[i][col] != mark || i > 0 && grid[i][col] != grid[i - 1][col]) vertical = false;
      }
      if (horizontal || vertical) return player;
      boolean leftTopDiagonal = true;
      for (int i = 1, j = 1; i < grid.length; i++, j++) {
        if (grid[i][j] != mark || grid[i][j] != grid[i - 1][j - 1]) leftTopDiagonal = false;
      }
      if (leftTopDiagonal) return player;
      boolean leftBottomDiagonal = true;
      for (int i = grid.length - 2, j = 1; i > -1; i--, j++) {
        if (grid[i][j] != mark || grid[i][j] != grid[i + 1][j - 1]) leftBottomDiagonal = false;
      }
      if (leftBottomDiagonal) return player;
      return 0;
    }

  },

  CONSTANT_TIME_SOLUTION {

    Map<Integer, Map<Integer, Integer>> rowOfPlayer;
    Map<Integer, Map<Integer, Integer>> colOfPlayer;
    Map<Integer, int[]> diagonal;
    int n;

    @Override
    public void init(int n) {
      this.n = n;
      rowOfPlayer = new HashMap<>();
      colOfPlayer = new HashMap<>();
      diagonal = new HashMap<>();
      rowOfPlayer.put(1, new HashMap<>());
      rowOfPlayer.put(2, new HashMap<>());
      colOfPlayer.put(1, new HashMap<>());
      colOfPlayer.put(2, new HashMap<>());
      diagonal.put(1, new int[] {0, 0});
      diagonal.put(2, new int[] {0, 0});
    }

    @Override
    public int move(int row, int col, int player) {
      rowOfPlayer.get(player).compute(row, (k, v) -> v == null ? 1 : v + 1);
      colOfPlayer.get(player).compute(col, (k, v) -> v == null ? 1 : v + 1);
      if (row == col) diagonal.get(player)[0]++;
      if (row + col == n - 1) diagonal.get(player)[1]++;
      if (rowOfPlayer.get(player).get(row) == n || colOfPlayer.get(player).get(col) == n
          || diagonal.get(player)[0] == n || diagonal.get(player)[1] == n)
        return player;
      return 0;
    }

  },

  /**
   * Has the same idea I thought above but use a trick and make the code much nicer.
   */
  SOLUTION {

    private int[] rows;
    private int[] cols;
    private int size;
    private int diagonal;
    private int anti_diagonal;

    @Override
    public void init(int n) {
      size = n;
      rows = new int[n];
      cols = new int[n];

    }

    @Override
    public int move(int row, int col, int player) {
      int add = player == 1 ? 1 : -1;
      if (col == row) diagonal += add;
      if (col == size - 1 - row) anti_diagonal += add;
      rows[row] += add;
      cols[col] += add;
      if (//
      Math.abs(rows[row]) == size || //
      Math.abs(cols[col]) == size || //
      Math.abs(diagonal) == size || //
      Math.abs(anti_diagonal) == size//
      ) return player;
      return 0;
    }

  };

  public abstract void init(int n);

  public abstract int move(int row, int col, int player);

  public static class TestDesignTicTacToe {

    private void verify(DesignTicTacToe sol) {
      sol.init(2);
      Assert.assertEquals(0, sol.move(0, 1, 1));
      Assert.assertEquals(0, sol.move(1, 1, 2));
      Assert.assertEquals(1, sol.move(1, 0, 1));
    }

    @Test
    public void testSolutions() {
      verify(BRUTE_FORCE);
      verify(LINEAR_TIME_SOLUTION);
      verify(CONSTANT_TIME_SOLUTION);
      verify(SOLUTION);
    }

  }


}
