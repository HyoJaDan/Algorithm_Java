package main.java.org.BackJoon.DP.Problem2293;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dp, arr;
	static int n,k,t;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int tc=1;tc<=t;tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n+1];

			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			k = Integer.parseInt(br.readLine());
			
			dp = new int[k+1];
			
			dp[0]=1;
			for(int i=0; i<n;i++) {
				for(int j=1;j<=k;j++) {
					if(j >= arr[i]) {
						dp[j] = dp[j] + dp[j-arr[i]];
					}
				}
			}
			System.out.println(dp[k]);
			
		}
	}
}
