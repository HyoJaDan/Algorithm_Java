package main.java.org.BackJoon.Problem16435;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++)
			arr[i]=Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		
		for(int i=0;i<n;i++) {
			if(arr[i]<=L)L++;
		}
		System.out.println(L);
	}

}