package lc.my.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int a[] = {57, 68, 59, 52, 72, 28, 96, 33, 24};
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] array, int low, int high) {
        if(high <= low) {
            return;
        }
        int i = low;
        int j = high;
        int key = array[low];
        while (true) {
            while (array[i] <= key) {
                i++;
                if(i == high) {
                    break;
                }
            }
            while (array[j] >= key) {
                j--;
                if(j == low) {
                    break;
                }
            }
            if(i >= j) {
                break;
            }
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        array[low] = array[j];
        array[j] = key;
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }
}
