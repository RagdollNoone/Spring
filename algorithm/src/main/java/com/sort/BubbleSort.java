package com.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = constructArr();
        printArray(array);

        array = bubbleSort(array);
        printArray(array);
    }

    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) { // 循环数组长度的次数
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
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
