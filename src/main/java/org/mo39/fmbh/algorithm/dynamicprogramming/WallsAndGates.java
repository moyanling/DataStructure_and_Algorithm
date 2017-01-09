package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * You are given a m x n 2D grid initialized with these three possible values.
 * 
 * 
 * -1 - A wall or an obstacle. 0 - A gate. INF - Infinity means an empty room. We use the value
 * 2147483647 to represent INF as you may assume that the distance to a gate is less than
 * 2147483647.
 * 
 * 
 * 
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate,
 * it should be filled with INF.
 * 
 *  For example, given the 2D grid:
 * 
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * 
 * 
 * 
 * After running your function, the 2D grid should be:
 * 
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/walls-and-gates/">Walls And Gates</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum WallsAndGates {

  BOTTOM_UP_METHOD_0 {

    @Override
    public void solve(int[][] rooms) {
      List<int[]> pre = new ArrayList<>(), cur = new ArrayList<>();
      for (int i = 0; i < rooms.length; i++) {
        for (int j = 0; j < rooms[0].length; j++) {
          if (rooms[i][j] == 0) cur.add(new int[] {i, j});
        }
      }
      boolean hasLeftRooms = false;
      do {
        hasLeftRooms = false;
        for (int[] posi : cur) {
          int distance = rooms[posi[0]][posi[1]] + 1;
          hasLeftRooms |= update(rooms, posi[0] - 1, posi[1], distance, pre);
          hasLeftRooms |= update(rooms, posi[0] + 1, posi[1], distance, pre);
          hasLeftRooms |= update(rooms, posi[0], posi[1] - 1, distance, pre);
          hasLeftRooms |= update(rooms, posi[0], posi[1] + 1, distance, pre);
        }
        cur = pre;
        pre = new ArrayList<>();
      } while (hasLeftRooms);
    }

    private boolean update(int[][] rooms, int i, int j, int distance, List<int[]> pre) {
      if (i > -1 && j > -1 && i < rooms.length && j < rooms[0].length
          && rooms[i][j] == Integer.MAX_VALUE) {
        rooms[i][j] = distance;
        pre.add(new int[] {i, j});
        return true;
      }
      return false;
    }

  },
  BOTTOM_UP_METHOD_1 {

    @Override
    public void solve(int[][] rooms) {
      if (rooms.length == 0 || rooms[0].length == 0) return;
      Queue<int[]> queue = new LinkedList<>();
      for (int i = 0; i < rooms.length; i++) {
        for (int j = 0; j < rooms[0].length; j++) {
          if (rooms[i][j] == 0) queue.add(new int[] {i, j});
        }
      }
      while (!queue.isEmpty()) {
        int[] posi = queue.remove();
        int distance = rooms[posi[0]][posi[1]] + 1;
        update(rooms, posi[0] - 1, posi[1], distance, queue);
        update(rooms, posi[0] + 1, posi[1], distance, queue);
        update(rooms, posi[0], posi[1] - 1, distance, queue);
        update(rooms, posi[0], posi[1] + 1, distance, queue);
      }
    }

    private void update(int[][] rooms, int i, int j, int distance, Queue<int[]> queue) {
      if (i > -1 && j > -1 && i < rooms.length && j < rooms[0].length
          && rooms[i][j] == Integer.MAX_VALUE) {
        rooms[i][j] = distance;
        queue.add(new int[] {i, j});
      }
    }

  };

  public abstract void solve(int[][] rooms);

  public static class TestWallsAndGates {

    private int[][] rooms =
        {{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1},
            {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}};
    private int[][] expecteds = {{3, -1, 0, 1}, {2, 2, 1, -1}, {1, -1, 2, -1}, {0, -1, 3, 4}};

    @Test
    public void testSolution0() {
      BOTTOM_UP_METHOD_0.solve(rooms);
      Assert.assertArrayEquals(expecteds, rooms);
    }

    @Test
    public void testSolution1() {
      BOTTOM_UP_METHOD_1.solve(rooms);
      Assert.assertArrayEquals(expecteds, rooms);
    }

  }

}
