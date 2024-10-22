package main.java.org.BackJoon.Problem1517;

import java.util.*;
import java.io.*;

public class Main {
    static int n,arr[],tempArr[];
    static Long ans = 0L;
    
    public static void main(String[] args) throws IOException{
    	init();
    	devideAndConquer(0,arr.length-1);
    	
		System.out.println(ans);
    }
    public static void devideAndConquer(int left, int right) {
    	if(left < right) {
    		int mid = (left + right) / 2;
    		devideAndConquer(left, mid);
    		devideAndConquer(mid+1, right);
    		
    		merge(left, right);

    	}
    }
    public static void merge(int l, int r) {
    	//left와 right는 두 배열중에서 (실제론 하나의 배열이지만) 
    	//왼쪽배열의 첫번째, 오른쪽 배열의 첫번째를 말한다.
    	int left = l;
    	int mid = (l+r)/2;
    	int right = mid+1;
    	int i = left;
    	System.out.println(l + " " + r);
    	while(left <= mid && right <= r) {
    		//내림차순은 <= 를 >로 바꾸면 된다
     		if(arr[left] <= arr[right]) {
    			tempArr[i++] = arr[left++];
    		} else {
    			ans += mid - left + 1;
    			tempArr[i++] = arr[right++];
    		}
    	}

    	// 두 번째 데이터에 있는 값을 다 옮긴 경우
    	while(left <= mid ) {
    		tempArr[i++] = arr[left++]; 
    	}
    	// 첫 번째 데이터에 있는 값을 다 옮긴 경우
    	while(right <= r) {
    		tempArr[i++] = arr[right++];
    	}
    	
    	for(int j=l; j<=r;j++) {
    		arr[j] = tempArr[j];
    	}
    }
    public static void init() throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	tempArr = new int[n];
    	arr = new int[n];
    	for(int i=0;i<n;i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    }
}