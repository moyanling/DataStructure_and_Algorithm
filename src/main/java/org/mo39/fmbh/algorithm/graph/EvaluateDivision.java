package org.mo39.fmbh.algorithm.graph;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 *
 * Equations are given in the format A / B = k, where  A and B are variables represented
 * as strings, and k is a real number (floating point number). Given some queries,
 * return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given  a / b = 2.0, b / c = 3.0.
 * queries are:  a / c = ?,  b / a = ?, a / e = ?,  a / a = ?, x / x = ?.
 * return  [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 *
 *
 * According to the example above:
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 *
 *
 * The input is always valid. You may assume that evaluating the queries will
 * result in no division by zero and there is no contradiction.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/evaluate-division/">Evaluate Division</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum EvaluateDivision {

  SOLUTION_0 {

    @Override
    public double[] solve(String[][] equations, double[] values, String[][] queries) {
      double[] result = new double[queries.length];
      Map<String, Map<String, Double>> map = new HashMap<>();
      for (int i = 0; i < equations.length; i++) {
        if (!map.containsKey(equations[i][0])) map.put(equations[i][0], new HashMap<>());
        if (!map.containsKey(equations[i][1])) map.put(equations[i][1], new HashMap<>());
        map.get(equations[i][0]).put(equations[i][1], values[i]);
        map.get(equations[i][1]).put(equations[i][0], 1.0 / values[i]);
      }
      for (int i = 0; i < result.length; i++) {
        result[i] = recur(queries[i][0], queries[i][1], map, new HashSet<>());
      }
      return result;
    }

    double recur(String src, String dest, Map<String, Map<String, Double>> map, Set<String> set) {
      if (!map.containsKey(src)) return -1.0;
      if (src.equals(dest)) return 1.0;
      if (map.get(src).containsKey(dest)) return map.get(src).get(dest);
      for (String newSrc : map.get(src).keySet()) {
        if (set.contains(newSrc)) continue;
        Set<String> newSet = new HashSet<>(set);
        newSet.add(newSrc);
        double result = recur(newSrc, dest, map, newSet);
        if (result != -1.0) return result * map.get(src).get(newSrc);
      }
      return -1.0;
    }

  },

  /**
   * Better impl using remove instead of creating new set. This saves a lot of time.
   */
  SOLUTION_1 {

    @Override
    public double[] solve(String[][] equations, double[] values, String[][] queries) {
      double[] result = new double[queries.length];
      Map<String, Map<String, Double>> map = new HashMap<>();
      for (int i = 0; i < equations.length; i++) {
        if (!map.containsKey(equations[i][0])) map.put(equations[i][0], new HashMap<>());
        if (!map.containsKey(equations[i][1])) map.put(equations[i][1], new HashMap<>());
        map.get(equations[i][0]).put(equations[i][1], values[i]);
        map.get(equations[i][1]).put(equations[i][0], 1.0 / values[i]);
      }
      for (int i = 0; i < result.length; i++) {
        result[i] = recur(queries[i][0], queries[i][1], map, new HashSet<>());
      }
      return result;
    }

    double recur(String src, String dest, Map<String, Map<String, Double>> map, Set<String> set) {
      if (!map.containsKey(src)) return -1.0;
      if (src.equals(dest)) return 1.0;
      if (map.get(src).containsKey(dest)) return map.get(src).get(dest);
      for (String newSrc : map.get(src).keySet()) {
        if (!set.add(newSrc)) continue;
        double result = recur(newSrc, dest, map, set);
        if (result != -1.0) return result * map.get(src).get(newSrc);
        set.remove(newSrc);
      }
      return -1.0;
    }

  };

  public abstract double[] solve(String[][] equations, double[] values, String[][] queries);

  public static class TestEvaluateDivision {

    String[][] equations = {{"x1", "x2"}, {"x2", "x3"}, {"x3", "x4"}, {"x4", "x5"}};
    double[] values = {3.0, 4.0, 5.0, 6.0};
    String[][] queries =
        {{"x1", "x5"}, {"x5", "x2"}, {"x2", "x4"}, {"x2", "x2"}, {"x2", "x9"}, {"x9", "x9"}};
    double[] expecteds = {360.0, 0.008333333333333333, 20.0, 1.0, -1.0, -1.0};

    @Test
    public void testSolutions() {
      Assert.assertArrayEquals(expecteds, SOLUTION_0.solve(equations, values, queries), 0.0001);
      Assert.assertArrayEquals(expecteds, SOLUTION_1.solve(equations, values, queries), 0.0001);
    }

  }

}
