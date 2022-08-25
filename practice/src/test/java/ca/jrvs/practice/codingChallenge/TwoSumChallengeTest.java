package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class TwoSumChallengeTest {

  TwoSumChallenge ts;

  @Before
  public void setUp() {
    this.ts = new TwoSumChallenge();
  }

  @Test
  public void twoSum() {
    int[] nums = new int[]{2, 7, 11, 15};
    int target = 9;
    int[] actual = this.ts.twoSum(nums, target);
    int[] expected = new int[]{0, 1};
    assertArrayEquals(expected, actual);
  }

  @Test
  public void twoSumSort() {
    int[] nums = new int[]{2, 7, 11, 15};
    int target = 9;
    int[] actual = this.ts.twoSumSort(nums, target);
    int[] expected = new int[]{0, 1};
    assertArrayEquals(expected, actual);
  }

  @Test
  public void twoSumHash() {
    int[] nums = new int[]{2, 7, 11, 15};
    int target = 9;
    int[] actual = this.ts.twoSumHash(nums, target);
    int[] expected = new int[]{0, 1};
    assertArrayEquals(expected, actual);
  }
}