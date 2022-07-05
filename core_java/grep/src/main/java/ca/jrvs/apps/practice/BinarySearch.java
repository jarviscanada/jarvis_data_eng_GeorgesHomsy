package ca.jrvs.apps.practice;

import java.util.Optional;

public class BinarySearch {

  public <E extends Comparable<E>> Optional<Integer> binarySearchRecursion(E[] arr, E target,
      int start, int end) {

    int middle = (start + (end)) / 2;

    if (start > end) {
      return Optional.empty();
    }

    if (target.compareTo(arr[middle]) == 0) {
      return Optional.of(middle);
    } else if (target.compareTo(arr[middle]) < 0) {
      return binarySearchRecursion(arr, target, start, middle - 1);
    } else {
      return binarySearchRecursion(arr, target, middle + 1, end);
    }

  }

  public <E extends Comparable<E>> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {

      int middle = (start + end) / 2;

      if (target.compareTo(arr[middle]) == 0) {
        return Optional.of(middle);
      } else if (target.compareTo(arr[middle]) > 0) {
        start = middle + 1;
      } else {
        end = middle - 1;
      }
    }

    return Optional.empty();
  }
}
