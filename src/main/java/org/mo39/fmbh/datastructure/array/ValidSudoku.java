package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * 
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * 
 * A partially filled sudoku which is valid.
 * 
 * 
 * Note: A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells
 * need to be validated.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/valid-sudoku/">Valid Sudoku</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ValidSudoku {

  HASHMAP_SOLUTION {

    @Override
    public boolean solve(char[][] board) {
      Map<String, HashSet<Character>> map = new HashMap<>();
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (board[i][j] == '.') continue;
          String rowKey = "row" + i;
          String colKey = "col" + j;
          String boxKey = i / 3 + "" + j / 3;
          if (!map.containsKey(rowKey)) map.put(rowKey, new HashSet<>());
          if (!map.get(rowKey).add(board[i][j])) return false;
          if (!map.containsKey(colKey)) map.put(colKey, new HashSet<>());
          if (!map.get(colKey).add(board[i][j])) return false;
          if (!map.containsKey(boxKey)) map.put(boxKey, new HashSet<>());
          if (!map.get(boxKey).add(board[i][j])) return false;
        }
      }
      return true;
    }

  },

  SET_SOLUTION {

    @Override
    public boolean solve(char[][] board) {
      Set<String> set = new HashSet<>();
      for (int i = 0; i < 9; ++i) {
        for (int j = 0; j < 9; ++j) {
          if (board[i][j] != '.') {
            String b = "(" + board[i][j] + ")";
            if (!set.add(b + i) || !set.add(j + b) || !set.add(i / 3 + b + j / 3)) return false;
          }
        }
      }
      return true;
    }

  };

  public abstract boolean solve(char[][] board);

}
