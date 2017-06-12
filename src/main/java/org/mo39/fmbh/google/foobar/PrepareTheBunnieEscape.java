package org.mo39.fmbh.google.foobar;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * <pre>
 * You're awfully close to destroying the LAMBCHOP doomsday device and freeing Commander Lambda's
 * bunny prisoners, but once they're free of the prison blocks, the bunnies are going to need to
 * escape Lambda's space station via the escape pods as quickly as possible. Unfortunately, the
 * halls of the space station are a maze of corridors and dead ends that will be a deathtrap for the
 * escaping bunnies. Fortunately, Commander Lambda has put you in charge of a remodeling project
 * that will give you the opportunity to make things a little easier for the bunnies. Unfortunately
 * (again), you can't just remove all obstacles between the bunnies and the escape pods - at most
 * you can remove one wall per escape pod path, both to maintain structural integrity of the station
 * and to avoid arousing Commander Lambda's suspicions.
 * 
 * You have maps of parts of the space station, each starting at a prison exit and ending at the
 * door to an escape pod. The map is represented as a matrix of 0s and 1s, where 0s are passable
 * space and 1s are impassable walls. The door out of the prison is at the top left (0,0) and the
 * door into an escape pod is at the bottom right (w-1,h-1).
 * 
 * Write a function answer(map) that generates the length of the shortest path from the prison door
 * to the escape pod, where you are allowed to remove one wall as part of your remodeling plans. The
 * path length is the total number of nodes you pass through, counting both the entrance and exit
 * nodes. The starting and ending positions are always passable (0). The map will always be
 * solvable, though you may or may not need to remove a wall. The height and width of the map can be
 * from 2 to 20. Moves can only be made in cardinal directions; no diagonal moves are allowed.
 * 
 * Inputs: (int) maze = 
 * [
 *  [0, 1, 1, 0], 
 *  [0, 0, 0, 1], 
 *  [1, 1, 0, 0], 
 *  [1, 1, 1, 0]
 * ] 
 * 
 * Output: (int) 7
 * 
 * Inputs: (int) maze = 
 * [
 *  [0, 0, 0, 0, 0, 0], 
 *  [1, 1, 1, 1, 1, 0], 
 *  [0, 0, 0, 0, 0, 0], 
 *  [0, 1, 1, 1, 1, 1], 
 *  [0, 1, 1, 1, 1, 1], 
 *  [0, 0, 0, 0, 0, 0]
 * ] 
 * 
 * Output: (int) 11
 * 
 * Level 3.2
 * </pre>
 * 
 * @author Jihan Chen
 *
 */
public enum PrepareTheBunnieEscape {

  DFS {

    int m, n;

    @Override
    public int solve(int[][] maze) {
      m = maze.length;
      n = maze[0].length;
      int result = dfs(maze, 0, 0);
      return result;
    }

    int dfs(int[][] maze, int i, int j) {
      if (i < 0 || j < 0 || i >= m || j >= n || maze[i][j] == 1) return 500;
      if (i == m - 1 && j == n - 1) return 1;
      maze[i][j] = 1;
      int distance = 500;
      distance = Math.min(distance, 1 + dfs(maze, i, j - 1));
      distance = Math.min(distance, 1 + dfs(maze, i, j + 1));
      distance = Math.min(distance, 1 + dfs(maze, i - 1, j));
      distance = Math.min(distance, 1 + dfs(maze, i + 1, j));
      maze[i][j] = 0;
      return distance;
    }


  },

  BFS {

    int m, n;
    int rowNum[] = {-1, 0, 0, 1};
    int colNum[] = {0, -1, 1, 0};

    @Override
    public int solve(int[][] maze) {
      m = maze.length;
      n = maze[0].length;
      int result = bfs(maze);
      if (result <= m + n - 1) return result;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (maze[i][j] == 1) {
            maze[i][j] = 0;
            result = Math.min(result, bfs(maze));
            maze[i][j] = 1;
          }
        }
      }
      return result;
    }

    int bfs(int[][] maze) {
      boolean[][] visited = new boolean[m][n];
      visited[0][0] = true;

      Queue<int[]> queue = new LinkedList<>();
      queue.offer(new int[] {0, 0, 1});
      while (!queue.isEmpty()) {
        int[] point = queue.poll();
        if (point[0] == m - 1 && point[1] == n - 1) return point[2];
        for (int i = 0; i < 4; i++) {
          int row = point[0] + rowNum[i];
          int col = point[1] + colNum[i];
          if (row >= 0 && col >= 0 && row < m && col < n && maze[row][col] == 0
              && !visited[row][col]) {
            visited[row][col] = true;
            queue.offer(new int[] {row, col, point[2] + 1});
          }
        }
      }
      return 500;
    }

  };


  public abstract int solve(int[][] maze);

  public static class TestPrepareTheBunnieEscape {

    //@formatter:off
    int[][] maze = 
      {
          {0, 0, 0, 0, 0, 0}, 
          {1, 1, 1, 1, 1, 0}, 
          {0, 0, 0, 0, 0, 0}, 
          {0, 1, 1, 1, 1, 1},
          {0, 1, 1, 1, 1, 1}, 
          {0, 0, 0, 0, 0, 0}
      };
    //@formatter:on

    @Test
    public void testSolutions() {
      // DFS.solve(maze);
      BFS.solve(maze);
    }

  }

}
