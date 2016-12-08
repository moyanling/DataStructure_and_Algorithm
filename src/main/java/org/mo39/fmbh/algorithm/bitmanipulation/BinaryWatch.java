package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the
 * bottom represent the minutes (0-59).<br/>
 * Each LED represents a zero or one, with the least significant bit on the right.<br/>
 * For example, the above binary watch reads "3:25".<br/>
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return
 * all possible times the watch could represent.<br/>
 * Example:<br/>
 * Input: n = 1Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16",
 * "0:32"]<br/>
 * Note:<br/>
 * The order of output does not matter.<br/>
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * <br/>
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is
 * not valid, it should be "10:02".
 * 
 * @see <a href="https://leetcode.com/problems/binary-watch/">Binary Watch</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BinaryWatch {

  BIT_MANIPULATION {

    @Override
    public List<String> solve(int num) {
      List<String> toRet = new ArrayList<>();
      for (int i = 0; i < 12; i++) {
        for (int j = 0; j < 60; j++) {
          if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
            /**
             * times.add(String.format("%d:%02d", h, m));<br>
             * The above line looks nice and clean but takes a much longer run time.
             */
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(':');
            if (j < 10) sb.append('0');
            sb.append(j);
            toRet.add(sb.toString());
          }
        }
      }
      return toRet;
    }

  };

  public abstract List<String> solve(int n);

  public static class TestBinaryWatch {

    private int num = 1;
    private String[] expected =
        {"0:01", "0:02", "0:04", "0:08", "0:16", "0:32", "1:00", "2:00", "4:00", "8:00"};

    @Test
    public void testSolutions() {
      Assert.assertThat(BIT_MANIPULATION.solve(num), containsInAnyOrder(expected));
    }

  }

}
