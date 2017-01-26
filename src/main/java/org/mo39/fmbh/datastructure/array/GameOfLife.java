package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * According to the Wikipedia's article: "The Game of Life, also known simply
 * as Life, is a cellular automaton devised by the British mathematician John
 * Horton Conway in 1970."
 * 
 * 
 * 
 * Given a board with m by n cells, each cell has an initial state live (1) or
 * dead (0). Each cell interacts with its eight neighbors (horizontal, vertical,
 * diagonal) using the following four rules (taken from the above Wikipedia article):
 * 
 * 
 * 
 * 
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if
 * by reproduction.
 * 
 * 
 * 
 * 
 * Write a function to compute the next state (after one update) of the board
 * given its current state.
 * 
 * 
 * Follow up: 
 * 
 * Could you solve it in-place? Remember that the board needs to be updated at
 * the same time: You cannot update some cells first and then use their updated
 * values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the
 * board is infinite, which would cause problems when the active area encroaches
 * the border of the array. How would you address these problems?
 * 
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/game-of-life/">Game Of Life</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum GameOfLife {

  SOLUTION {

    @Override
    public void solve(int[][] board) {
      List<int[]> dies = new ArrayList<>();
      List<int[]> live = new ArrayList<>();
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          int count = countLive(board, i, j);
          if (board[i][j] == 1 && (count < 2 || count > 3)) dies.add(new int[] {i, j});
          if (board[i][j] == 0 && count == 3) live.add(new int[] {i, j});
        }
      }
      for (int[] posi : dies) {
        board[posi[0]][posi[1]] = 0;
      }
      for (int[] posi : live) {
        board[posi[0]][posi[1]] = 1;
      }
    }

  },

  IN_PLACE {

    @Override
    public void solve(int[][] board) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          int count = countLive(board, i, j);
          if (board[i][j] == 0 && count == 3) board[i][j] = 3;
          if (board[i][j] == 1 && (count < 2 || count > 3)) board[i][j] = 2;
        }
      }
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          board[i][j] %= 2;
        }
      }
    }

  };

  protected int countLive(int[][] board, int i, int j) {
    int count = 0;
    for (int p = -1; p <= 1; p++) {
      for (int q = -1; q <= 1; q++) {
        if (i + p >= 0 && i + p < board.length && j + q >= 0 && j + q < board[0].length) {
          if (p == 0 && q == 0) continue;
          if (board[i + p][j + q] == 1) count++;
        }
      }
    }
    return count;
  }

  public abstract void solve(int[][] board);

  public static class TestGameOfLife {

    int[][] expecteds = {{1, 1}, {1, 1}};

    @Test
    public void testSolutions() {
      for (GameOfLife sol : GameOfLife.values()) {
        int[][] board = {{1, 1}, {1, 0}};
        sol.solve(board);
        Assert.assertArrayEquals(expecteds, board);
      }
    }

  }

}
