package org;

import java.io.*;

abstract class Sort{
    protected int n = 1;
    public abstract void sort(int[] arr);
    public void printSwap(int from, int to){
        System.out.println(n+"번째 :"+ from +" <-> "+ to);
        n++;
    }
}


class bubbleSort extends Sort{

    @Override
    public void sort(int[] arr){
        int n = arr.length;

        for(int i=n-1; i>0; i--){
            for(int j=0; j<i; j++){
                if(arr[j+1] < arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    printSwap(j, j+1);
                }
            }
        }
    }

}

class SelectionSort extends Sort{

    @Override
    public void sort(int[] arr){
        int n = arr.length;

        for(int i=0; i<n-1; i++){
            int minIndex = i;

            for(int j=i+1; j<n; j++){
                if(arr[minIndex] > arr[j])
                    minIndex = j;
            }

            if(minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;

                printSwap(i, minIndex);
            }
        }
    }
}

class insertionSort extends Sort{

    @Override
    public void sort(int[] arr){
        int n = arr.length;

        int j;
        for(int i=1; i<n; i++){
            int now = arr[i];

            for(j = i-1; j>=0; j--){
                if(arr[j] > now){
                    arr[j+1] = arr[j];
                }
                else break;
            }

            arr[j+1] = now;
        }
    }

}

class QuickSort extends Sort{

    @Override
    public void sort(int[] arr) {
        divide(arr, 0, arr.length-1);
    }

    private void divide(int[] arr, int left, int right){
        if(left < right){

            int pivot = conquer(arr, left, right);

            divide(arr, left, pivot-1);
            divide(arr, pivot+1, right);
        }
    }

    private int conquer(int[] arr, int left, int right) {
        int pivot = arr[(left+right)/2];

        while(left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;

            if (left <= right) {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;

                printSwap(left, right);

                right--;
                left++;
            }
        }

        return left;
    }

}

class MergeSort extends Sort{
    public static int[] tempArr;

    @Override
    public void sort(int[] arr){
        divide(arr, 0, arr.length);
    }

    public void divide(int[] arr, int left, int right){
        if(left < right){
            int mid = (left+right)/2;

            divide(arr, left, mid);
            divide(arr, mid+1, right);

            merge(arr, left, right);
        }
    }

    public void merge(int[] arr, int l, int r){
        int left = l;
        int mid = (l+r)/2;
        int right = mid+1;
        int i = left;

        while(left <= mid && right <= r){
            if(arr[left] < arr[right]){
                tempArr[i++] = arr[left++];
            } else {
                tempArr[i++] = arr[right++];
            }
        }

        while(left <= mid){
            tempArr[i++] = arr[left++];
        }

        while(right <= l){
            tempArr[i++] = arr[right++];
        }

        for(int j=l; j <=r; j++){
            arr[j] = tempArr[j];
        }
    }

}

class Practice{

    public static void main(String[] args) throws IOException{
        int[] arr = {9,8,7,6,5,4,3,2,1};
//        Sort sort = new bubbleSort();
//        Sort sort = new SelectionSort();
        Sort sort = new QuickSort();
        sort.sort(arr);


        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

}
