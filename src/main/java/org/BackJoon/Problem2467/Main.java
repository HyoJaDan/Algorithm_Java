package main.java.org.BackJoon.Problem2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int arr[];
	static int n;
	public static void main(String[] args)throws IOException {
		init();
		
		TwoPointer();
	}
	private static void TwoPointer() {
		int left = 0; 
		int right = n-1;
		int ans = Integer.MAX_VALUE;
		int ansLeft=0,ansRight=0;
		
		while(left < right) {
			int mid = arr[left]+arr[right];
			if(Math.abs(mid) <= ans) {
				ans = Math.abs(mid);
				ansLeft = arr[left];
				ansRight = arr[right];
			}
			if(mid<0)
				left++;
			else
				right--;
		}
		System.out.println(ansLeft+" "+ansRight);
	}
	private static void init()throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) 
			arr[i] = Integer.parseInt(st.nextToken());

	}

}
