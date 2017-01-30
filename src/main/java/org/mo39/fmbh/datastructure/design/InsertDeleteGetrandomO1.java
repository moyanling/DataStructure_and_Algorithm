package org.mo39.fmbh.datastructure.design;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Design a data structure that supports all following operations in average O(1)
 * time.
 * 
 * 
 * 
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element
 * must have the same probability of being returned.
 * 
 * 
 * 
 * Example:
 * 
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * 
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * 
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * 
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * 
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * 
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * 
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * 
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/insert-delete-getrandom-o1/">Insert Delete Getrandom
 *      O 1</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum InsertDeleteGetrandomO1 {

  SOLUTION {

    Set<Integer> set = new HashSet<>();
    List<Integer> list = new ArrayList<>();
    Random rand = new Random();

    @Override
    public void init() {}

    @Override
    public boolean insert(int val) {
      list.add(val);
      return set.add(val);
    }

    @Override
    public boolean remove(int val) {
      return set.remove(val);
    }

    @Override
    public int getRandom() {
      Integer result = null;
      while (!set.contains(result = list.get(rand.nextInt(list.size()))));
      return result;
    }

  };

  public abstract void init();

  public abstract boolean insert(int val);

  public abstract boolean remove(int val);

  public abstract int getRandom();

}
