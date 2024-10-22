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
    	//left�� right�� �� �迭�߿��� (������ �ϳ��� �迭������) 
    	//���ʹ迭�� ù��°, ������ �迭�� ù��°�� ���Ѵ�.
    	int left = l;
    	int mid = (l+r)/2;
    	int right = mid+1;
    	int i = left;
    	System.out.println(l + " " + r);
    	while(left <= mid && right <= r) {
    		//���������� <= �� >�� �ٲٸ� �ȴ�
     		if(arr[left] <= arr[right]) {
    			tempArr[i++] = arr[left++];
    		} else {
    			ans += mid - left + 1;
    			tempArr[i++] = arr[right++];
    		}
    	}

    	// �� ��° �����Ϳ� �ִ� ���� �� �ű� ���
    	while(left <= mid ) {
    		tempArr[i++] = arr[left++]; 
    	}
    	// ù ��° �����Ϳ� �ִ� ���� �� �ű� ���
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