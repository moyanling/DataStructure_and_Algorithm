package org.mo39.fmbh.uncategorized;

import org.mo39.fmbh.common.annotation.ProblemSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

/**
 *
 *
 * <pre>
 *
 * A self-dividing number is a number that is divisible by every digit it contains.
 *
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 ==
 * 0, and 128 % 8 == 0.
 *
 * Also, a self-dividing number is not allowed to contain the digit zero.
 *
 * Given a lower and upper number bound, output a list of every possible self
 * dividing number, including the bounds if possible.
 *
 * Example 1:
 *
 * Input:
 * left = 1, right = 22
 * Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 *
 *
 *
 * Note:
 * The boundaries of each input argument are 1 <= left <= right <= 10000.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/self-dividing-numbers/">Self Dividing Numbers</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SelfDividingNumbers {
  SOLUTION {
    @Override
    public List<Integer> selfDividingNumbers(int left, int right) {
      return IntStream.range(left, right + 1)
          .filter(this::isSelfDividing)
          .boxed()
          .collect(Collectors.toList());
    }

    boolean isSelfDividing(int n) {
      for (char c : String.valueOf(n).toCharArray()) {
        if (c == '0' || n % Integer.valueOf("" + c) != 0) return false;
      }
      return true;
    }
  };

  public abstract List<Integer> selfDividingNumbers(int left, int right);
}
