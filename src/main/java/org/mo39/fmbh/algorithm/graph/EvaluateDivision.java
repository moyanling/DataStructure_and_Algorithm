package org.mo39.fmbh.algorithm.graph;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

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

  SOLUTION {

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
        result[i] = -1.0;
        if (!map.containsKey(queries[i][0]) || !map.containsKey(queries[i][1])) continue;
        else if (queries[i][0].equals(queries[i][1])) result[i] = 1.0;
        else {
          for (String dest : map.get(queries[i][0]).keySet()) {
            if (dest.equals(queries[i][1])) {
              result[i] = map.get(queries[i][0]).get(queries[i][1]);
              break;
            }
            if (map.get(queries[i][1]).containsKey(dest)) {
              result[i] = map.get(queries[i][0]).get(dest) / map.get(queries[i][1]).get(dest);
              break;
            }
          }
        }
      }
      return result;
    }

  };

  public abstract double[] solve(String[][] equations, double[] values, String[][] queries);

}
