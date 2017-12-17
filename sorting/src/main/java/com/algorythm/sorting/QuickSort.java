package com.algorythm.sorting;

import java.util.Random;

public class QuickSort implements Sort {

    private Random random = new Random();

    @Override
    public void sort(int[] input) {
        quickSort(input, 0, input.length-1);
    }

    private void quickSort(int[] input, int start, int end) {
        int index = partition(input, start, end);
        if(start < index - 1) {
            quickSort(input, start, index - 1);
        }
        if(index + 1 < end) {
            quickSort(input, index, end);
        }
    }

    private int partition(int[] input, int start, int end) {
        int pivotIndex = start + random.nextInt(end - start);
        int pivot = input[pivotIndex];

        while(start <= end) {
            while(input[start] < pivot) {
                start++;
            }
            while(input[end] > pivot) {
                end--;
            }
            if(start <= end) {
                swap(input, start, end);
                start++;
                end--;
            }
        }
        return start;
    }

    private void swap(int[] input, int i, int j) {
        int t = input[i];
        input[i] = input[j];
        input[j] = t;
    }
}
