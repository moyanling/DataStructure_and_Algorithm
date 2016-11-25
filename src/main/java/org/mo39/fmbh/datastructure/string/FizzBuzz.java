package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * Write a program that outputs the string representation of numbers from 1 to n.<br/>
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of
 * five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
 * <br/>
 * Example:<br/>
 * n = 15,<br/>
 * Return:<br/>
 * [<br/>
 * "1",<br/>
 * "2",<br/>
 * "Fizz",<br/>
 * "4",<br/>
 * "Buzz",<br/>
 * "Fizz",<br/>
 * "7",<br/>
 * "8",<br/>
 * "Fizz",<br/>
 * "Buzz",<br/>
 * "11",<br/>
 * "Fizz",<br/>
 * "13",<br/>
 * "14",<br/>
 * "FizzBuzz"<br/>
 * ]
 * 
 * @see <a href="https://leetcode.com/problems/fizz-buzz/">Fizz Buzz</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FizzBuzz {

  STREAM_SOLUTION {

    @Override
    public List<String> solve(int n) {
      return IntStream.range(1, n + 1).mapToObj(i -> {
        if (i % 3 == 0 && i % 5 == 0) return "FizzBuzz";
        if (i % 3 == 0) return "Fizz";
        if (i % 5 == 0) return "Buzz";
        return String.valueOf(i);
      }).collect(Collectors.toList());
    }

  };

  public abstract List<String> solve(int n);

  public static class TestFizzBuzz {

    private int n = 15;
    private List<String> expected = Lists.newArrayList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7",
        "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz");

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, STREAM_SOLUTION.solve(n));
    }

  }

}
