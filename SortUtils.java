package com.fal.alex;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for sorting helper methods.
 * @author Alexander Fal (falalexandr007@gmail.com)
 */
class SortUtils {
    /**
     * Sorts the specified {@link java.util.List}
     * @param array {@link java.util.List} to be sorted
     * @param descending true if sorting should be in descending order
     * @param <T> type of data being sorted
     */
    static <T extends Comparable<T>> void sort(List<T> array, boolean descending) {
        int N = array.size();
        for (int i = 1; i < N; i++)
            for (int j = i; j > 0; j--)
                if (descending) {
                    if (array.get(j).compareTo(array.get(j - 1)) > 0)
                        Collections.swap(array, j, j - 1);
                } else {
                    if (array.get(j).compareTo(array.get(j - 1)) < 0)
                        Collections.swap(array, j, j - 1);
                }
    }
}