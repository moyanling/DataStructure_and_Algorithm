package org.mo39.fmbh.datastructure.design;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * 
 * 
 * add - Add the number to an internal data structure. find - Find if there exists any pair of
 * numbers which sum is equal to the value.
 * 
 * 
 * 
 * For example,
 * 
 * add(1); 
 * add(3); 
 * add(5); 
 * find(4) -> true; 
 * find(7) -> false;
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/two-sum-iii-data-structure-design/">Two Sum III Data
 *      Structure Design</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum TwoSumIIIDataStructureDesign {

  LINEAR_FIND_SOLUTION {

    private Set<Integer> set = new HashSet<>();
    private List<Integer> list = new ArrayList<>();

    @Override
    public void add(int number) {
      for (Integer i : list) {
        set.add(i + number);
      }
      list.add(number);
    }

    @Override
    public boolean find(int value) {
      return set.contains(value);
    }

  },

  LINEAR_ADD_SOLUTION {

    private Map<Integer, Integer> map = new HashMap<>();

    @Override
    public void add(int number) {
      map.compute(number, (k, v) -> v == null ? 1 : v + 1);
    }

    @Override
    public boolean find(int value) {
      for (Integer key : map.keySet()) {
        if (key * 2 != value && map.containsKey(value - map.get(key))
            || key * 2 == value && map.get(key) > 1)
          return true;
      }
      return false;
    }

  };

  // Add the number to an internal data structure.
  public abstract void add(int number);

  // Find if there exists any pair of numbers which sum is equal to the value.
  public abstract boolean find(int value);

}
