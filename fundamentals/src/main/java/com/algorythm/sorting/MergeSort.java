package com.algorythm.sorting;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MergeSort implements Sort {

    @Override
    public void sort(int[] input) {
        int[] helper = new int[input.length];
        mergeSort(helper, input, 0, input.length-1);
    }

    private void mergeSort(int[] helper, int[] input, int start, int end) {
        if(start < end) {
            int mid = start + (end-start)/2;
            mergeSort(helper, input, start, mid);
            mergeSort(helper, input, mid+1, end);
            merge(helper, input, start, mid, end);
        }
    }

    private void merge(int[] helper, int[] input, int start, int mid, int end) {
        /*
        * 1. Copy all elements to helper array
        * 2. Starting from the beginning of left and right array in the helper array,
        *       copy the smaller element back into the input array
        * 3. If left array has remaining elements, copy them over to the input array.
        *       (No need to worry about right array as it will already be there)
        *
        * */

        for(int i = start; i <= end; i++) {
            helper[i] = input[i];
        }

        int leftStart = start;
        int rightStart = mid + 1;
        int curr = start;

        while(leftStart <= mid && rightStart <= end) {
            if(helper[leftStart] > helper[rightStart]) {
                input[curr] = helper[rightStart++];
            } else {
                input[curr] = helper[leftStart++];
            }
            curr++;
        }
        int remaining = mid + 1 - leftStart;
        for(int i = 0; i < remaining; i++) {
            input[curr+i] = helper[leftStart + i];
        }
    }





}
