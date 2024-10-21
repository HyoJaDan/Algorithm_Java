package org.BackJoon.Problem1517;

import java.util.*;
import java.io.*;

public class Main {
    static long count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N];
        long[] temp = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println(count);
    }

    public static void mergeSort(long[] arr, int left, int right, long[] temp) {
        if (left != right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, right, temp);
        }
    }

    public static void merge(long[] arr, int left, int right, long[] temp) {
        int m = (left + right) / 2;
        int l = left;
        int r = m + 1;
        int i = left;

        while (l <= m && r <= right) {
            if (arr[l] > arr[r]) {
                count += (m - l + 1);
                temp[i++] = arr[r++];
            } else {
                temp[i++] = arr[l++];
            }
        }

        if (l > m) {
            while (r <= right) {
                temp[i++] = arr[r++];
            }
        }

        if (r > l) {
            while (l <= m) {
                temp[i++] = arr[l++];
            }
        }

        for (int j = left; j <= right; j++) {
            arr[j] = temp[j];
        }
    }
}
