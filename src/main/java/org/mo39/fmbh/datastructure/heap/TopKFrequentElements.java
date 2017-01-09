package org.mo39.fmbh.datastructure.heap;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 
 * 
 * Note: 
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is
 * the array's size.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/top-k-frequent-elements/">Top K Frequent Elements</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum TopKFrequentElements {

  HEAP_SOLUTION {

    @Override
    public List<Integer> solve(int[] nums, int k) {
      List<Integer> list = new LinkedList<>();
      Map<Integer, Integer> map = new HashMap<>();
      PriorityQueue<Entry<Integer, Integer>> queue =
          new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
      for (int i : nums) {
        map.compute(i, (key, v) -> v == null ? 1 : v + 1);
      }
      for (Entry<Integer, Integer> entry : map.entrySet()) {
        queue.add(entry);
        if (queue.size() > k) queue.poll();
      }
      for (int i = 0; i < k; i++) {
        list.add(0, queue.poll().getKey());
      }
      return list;
    }

  };

  public abstract List<Integer> solve(int[] nums, int k);

  public static class TestTopKFrequentElements {

    private int[] nums = {1, 1, 1, 2, 2, 3};
    private int k = 2;
    private List<Integer> expecteds = Arrays.asList(1, 2);

    @Test
    public void testSolutions() {
      Assert.assertEquals(expecteds, HEAP_SOLUTION.solve(nums, k));
    }

  }

}
