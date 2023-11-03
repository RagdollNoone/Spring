package com.sort;

public class SelectSort {
    public static void main(String[] args) {
        int[] array = constructArr();
        printArray(array);

        array = selectSort(array);
        printArray(array);
    }

    public static int[] selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }

        return array;
    }

    public static int[] constructArr() {
        int[] array = new int[]{5, 4, 6, 2, 7, 3, 1};
        return array;
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }

        System.out.println();
    }
}
