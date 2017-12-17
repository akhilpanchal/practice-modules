package com.algorythm.sorting;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class SortingTest {

    @Test
    public void sortsUsingBubbleSort() {
        Sort algorithm = new BubbleSort();
        sortsUsing(algorithm);
    }

    @Test
    public void sortsUsingMergeSort() {
        Sort algorithm = new MergeSort();
        sortsUsing(algorithm);
    }

    @Test
    public void sortsUsingQuickSort() {
        Sort algorithm = new QuickSort();
        sortsUsing(algorithm);
    }

    private void sortsUsing(Sort algorithm) {
        sortsArrayWithUniqueElements(algorithm);
        sortsArrayWithDuplicateElements(algorithm);
    }

    private void sortsArrayWithUniqueElements(Sort algorithm) {
        int[] input = InputFactory.uniqueElements();
        int[] result = InputFactory.sortedUniqueElements();
        algorithm.sort(input);
        assertThat(input, is(result));

    }

    private void sortsArrayWithDuplicateElements(Sort algorithm) {
        int[] input = InputFactory.duplicateElements();
        int[] result = InputFactory.sortedDuplicateElements();
        algorithm.sort(input);
        assertThat(input, is(result));
    }
}