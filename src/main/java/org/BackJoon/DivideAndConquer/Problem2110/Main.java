package org.BackJoon.DivideAndConquer.Problem2110;

public class Main {
    private static int[] arr = {1,2,3,4,5,6,7,8,9};
    public static void main(String[] args){
        System.out.println(arr.length);
        for(int i=1;i<10;i++){
            int left=0,right=arr.length;
            int target=i;
            int num=0;
            while(left<=right){
                num++;
                int mid = (left+right)/2;

                if ( arr[mid] > target ){
                    right = mid-1;
                }
                else if( arr[mid] < target) {
                    left = mid+1;
                } else if(arr[mid]==target) {
                    System.out.println(i+"찾기 : "+num);
                    break;
                }
            }
        }

    }
}
