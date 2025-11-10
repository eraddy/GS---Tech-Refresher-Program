package org.example.arrays;

import javax.print.attribute.standard.OutputBin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class MedianOfTwoSortedArray {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];

        int len = m + n;
        if (len % 2 == 1) {
            return merged[len / 2];
        } else {
            return (merged[len / 2 - 1] + merged[len / 2]) / 2.0;
        }
    }

    static void main() {
        int[] aar1 = new int[]{1,3,5};
        int[] aar2 = new int[]{2,4};
        System.out.println(findMedianSortedArrays(aar1,aar2));
        Collection list = new ArrayList<String>();
        list.add(new StringBuilder("x"));
    }
}
