package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumChallenge {

  /**
   * Time complexity: O(n^2)
   * Space complexity: O(1)
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSum(int[] nums, int target) {
    for(int i = 0; i < nums.length; ++i) {
      for(int j = 1; j < nums.length; ++j) {
        if (target - nums[i] == nums[j]) {
          return new int[]{i, j};
        }
      }
    }

    return null;
  }

  /**
   * Time complexity: O(nlogn)
   * Space complexity: O(1)
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSumSort(int[] nums, int target) {
    int[] nums2 = Arrays.copyOf(nums, nums.length);
    Arrays.sort(nums2);
    int a = 0;
    int b = 0;
    int left = 0;
    int right = nums2.length - 1;
    boolean ans = false;

    while(left < right) {
      if (target == nums2[right] + nums2[left]) {
        a = nums2[right];
        b = nums2[left];
        nums2 = new int[2];
        break;
      }

      if (target > nums2[right] + nums2[left]) {
        ++left;
      } else if (target < nums2[right] + nums2[left]) {
        --right;
      }
    }

    int i;
    for(i = 0; i < nums.length; ++i) {
      if (nums[i] == a) {
        nums2[1] = i;
        ans = true;
        break;
      }

      if (nums[i] == b) {
        nums2[0] = i;
        ans = false;
        break;
      }
    }

    if (ans) {
      for(i = nums2[1]; i < nums.length; ++i) {
        if (nums[i] == b) {
          nums2[0] = i;
          break;
        }
      }
    } else if (!ans) {
      for(i = nums2[0]; i < nums.length; ++i) {
        if (nums[i] == a) {
          nums2[1] = i;
          break;
        }
      }
    }

    return nums2;
  }

  /**
   * Time complexity: O(n)
   * Space complexity: O(1)
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSumHash(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap();

    for(int i = 0; i < nums.length; ++i) {
      if (map.containsKey(target - nums[i])) {
        return new int[]{(Integer)map.get(target - nums[i]), i};
      }

      map.put(nums[i], i);
    }

    return null;
  }
}
