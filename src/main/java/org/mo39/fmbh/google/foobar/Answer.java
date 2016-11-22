package org.mo39.fmbh.google.foobar;

import java.util.Arrays;

import org.mo39.fmbh.common.Z;

public class Answer {

  public static void main(String[] args) {
    Z.print(Arrays.toString(answer(new int[][] {{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0},
        {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}})));
  }

  public static int[] answer(int[][] m) {
    int[] sum = new int[m.length];
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m.length; j++) {
        sum[i] += m[i][j];
      }
    }
    int count = 0;
    for (int i = 0; i < sum.length; i++) {
      if (sum[i] != 0) count++;
    }
    Fraction[][] fractions = new Fraction[count][m.length];
    for (int i = 0; i < count; i++) {
      for (int j = 0; j < m.length; j++) {
        fractions[i][j] = new Fraction(m[i][j], sum[i]);
      }
    }
    Fraction[] xFractions = new Fraction[count];
    xFractions[0] = new Fraction(1, 1);
    for (int i = 1; i < xFractions.length; i++) {
      xFractions[i] = new Fraction(fractions[0][i].numerator, fractions[0][i].denominator);
    }
    Fraction[] result = new Fraction[m.length - count];
    for (int i = 0; i < m.length - count; i++) {
      Fraction denominator = null;
      for (int j = 0; j < count; j++) {
        if (denominator == null) {
          denominator = xFractions[j].mul(fractions[j][count + j]);
        } else {
          denominator = denominator.add(xFractions[j].mul(fractions[j][count + j]));
        }
      }
      result[i] = denominator;
    }
    int[] toRet = new int[m.length - count + 1];
    Fraction sFraction = result[0];
    for (int i = 1; i < result.length; i++) {
      sFraction = sFraction.add(result[i]);
    }
    Fraction temp = new Fraction(1, 1).divide(sFraction);
    for (int i = 0; i < result.length; i++) {
      result[i] = temp.mul(result[i]);
    }
    long[] denominators = new long[m.length - count];
    for (int i = 0; i < denominators.length; i++) {
      denominators[i] = result[i].denominator;
    }
    long lcm = lcm(denominators);
    toRet[count] = (int) lcm;
    for (int i = 0; i < m.length - count; i++) {
      toRet[i] = (int) (result[i].numerator * (lcm / result[i].denominator));
    }
    return toRet;
  }

  public static class Fraction {
    public final long numerator;
    public final long denominator;

    public Fraction(long numerator, long denominator) {
      long gcd = gcd(numerator, denominator);
      this.numerator = numerator / gcd;
      this.denominator = denominator / gcd;
    }

    public Fraction add(Fraction toAdd) {
      return new Fraction(this.numerator * toAdd.denominator + toAdd.numerator * this.denominator,
          this.denominator * toAdd.denominator);
    }

    public Fraction minus(Fraction toMinus) {
      return new Fraction(
          this.numerator * toMinus.denominator - toMinus.numerator * this.denominator,
          this.denominator * toMinus.denominator);
    }

    public Fraction divide(Fraction toDivide) {
      return new Fraction(this.numerator * toDivide.denominator,
          toDivide.numerator * this.denominator);
    }

    public Fraction mul(Fraction toMul) {
      return new Fraction(this.numerator * toMul.numerator, toMul.denominator * this.denominator);
    }

    @Override
    public String toString() {
      return numerator + "/" + denominator;
    }
  }

  public static long gcd(long a, long b) {
    while (b > 0) {
      long temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  public static long lcm(long a, long b) {
    return a * (b / gcd(a, b));
  }

  public static long lcm(long[] input) {
    long result = input[0];
    for (int i = 1; i < input.length; i++)
      result = lcm(result, input[i]);
    return result;
  }
}
