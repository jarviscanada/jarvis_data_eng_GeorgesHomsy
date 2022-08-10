package ca.jrvs.apps.practice;

import java.util.Arrays;

public class SortingAlgorithms {

  public void BubbleSort (int[] numbers) {
    int temp;
    for (int i = 0; i < numbers.length; i++) {
      for (int j = 0 ; j < numbers.length - 1 - i; j++) {
        if (numbers[j] > numbers[j+1]) {
          temp = numbers[j];
          numbers[j] = numbers[j+1];
          numbers[j+1] = temp;
        }
      }
    }
  }

  public  void insertionSort(int[] numbers) {
    int temp;

    for (int i = 1; i < numbers.length; i++) {
      temp = numbers[i];
      int j = i -1;

      while (j>=0 && numbers[j]>temp) {
        numbers[j+1]= numbers[j];
        j = j - 1;
      }
      numbers[j + 1] = temp;
    }
  }

  public void quickSort(int arr[],int start,int end) {
    if (start < end) {
      int partition = partition(arr,start,end);

      quickSort(arr,start,partition-1);
      quickSort(arr,partition + 1,end);
    }
  }

  public int partition (int arr[],int start,int end) {
    int pivot = arr[end];
    int i = start - 1;
    int temp,swapPivot;

    for (int j = start; j < end; j++) {
      if (arr[j] <= pivot) {
        i = i + 1;

        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    swapPivot = arr[i+1];
    arr[i+1] = pivot;
    arr[end] = swapPivot;

    return i+1;
  }

  public static void mergeSort(int[] a, int n) {
    if(n<2){
      return;
    }

    int middle =  n/2;

    int [] l = new int [middle];
    int [] r = new int [n-middle];

    for (int i = 0; i < middle; i++) {
      l[i] = a[i];
    }

    for (int i = middle; i < n; i++) {
      r[i-middle] = a[i];
    }

    mergeSort(l,middle);
    mergeSort(r,n-middle);

    merge(a,l,r,middle,n-middle);
  }

  public static void merge(int[] a, int[] l, int[] r, int left, int right) {
    int i = 0, j = 0, k = 0;
    while (i < left && j < right) {
      if (l[i] <= r[j]) {
        a[k++] = l[i];
        i++;
      } else {
        a[k++] = r[j];
        j++;
      }
    }
    while (i < left) {
      a[k++] = l[i];
      i++;
    }
    while (j < right) {
      a[k++] = r[j];
      j++;
    }
  }

  public static void main(String[] args) {
    SortingAlgorithms sa = new SortingAlgorithms();
    int [] a = {8,2,3,9,1};
    // sa.BubbleSort(a);
    // sa.insertionSort(a);
    //sa.quickSort(a,0,a.length-1);
    sa.mergeSort(a,5);
    System.out.println(Arrays.toString(a));

  }
}
