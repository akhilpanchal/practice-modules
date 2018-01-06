package com.algorythm.sorting;

public class BubbleSort implements Sort {

    @Override
    public void sort(int[] input) {
        int size = input.length;
        for(int pass = 0; pass < size; pass++) {
            for(int i = 1; i < size - pass; i++) {
                if(input[i] < input[i-1]) {
                    swap(input, i, i-1);
                }
            }
        }
    }

    private void swap(int[] input, int i, int j) {
        int t = input[i];
        input[i] = input[j];
        input[j] = t;
    }
}
