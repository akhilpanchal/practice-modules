package com.algorythm.sorting;

import java.util.Arrays;

public class InputFactory {

    private static final int[] UNIQUE_ELEMENTS = new int[] { 4, 9, 2, 7, 8, 0, 1, 5, 12, 13 };
    private static  final int[] DUPLICATE_ELEMENTS = new int[] { 1, 4, 9, 2, 7, 8, 0, 1, 5, 8, 6, 6 };

    public static int[] uniqueElements() {
        return UNIQUE_ELEMENTS;
    }

    public static int[] duplicateElements() {
        return DUPLICATE_ELEMENTS;
    }

    public static int[] sortedUniqueElements() {
        int sorted[] = UNIQUE_ELEMENTS.clone();
        Arrays.sort(sorted);
        return sorted;
    }

    public static int[] sortedDuplicateElements() {
        int sorted[] = DUPLICATE_ELEMENTS.clone();
        Arrays.sort(sorted);
        return sorted;
    }
}
