package main.java.org.BackJoon.Problem11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int arr[] = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++)
		{
			int x=Integer.parseInt(st.nextToken());
			arr[i] = arr[i-1]+x;
		}
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			System.out.println(arr[to]-arr[from-1]);
		}
			
	}

}
