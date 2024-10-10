package main.java.org.swea.Problem1263;

import java.io.*;
import java.util.*;

public class Solution {
	static int t,n;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		StringBuilder st = new StringBuilder();
		for(int tc=1; tc<=t; tc++) {
			init(br);
			
			for(int k=1;k<=n;k++) {
				for(int i=1;i<=n;i++) {
					for(int j=1;j<=n;j++) {
						map[i][j] = Math.min(map[i][j], map[k][j]+map[i][k]);
					}
				}
			}
			int minValue = printMap();
		
			st.append("#").append(tc).append(" ").append(minValue).append("\n");
		}
		System.out.println(st);
	}
	public static int printMap() {
		int maxValue=Integer.MAX_VALUE;
		for(int i=1;i<=n;i++) {
			int now=0;
			for(int j=1;j<=n;j++) {
//				System.out.print(map[i][j]+" ");
				now+=map[i][j];
			}
			maxValue = Math.min(maxValue, now);
//			System.out.println();
		}
		return maxValue;
	}
	public static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0 && (i!=j)) {
					map[i][j] = 999999999;
				}
			}
		}
	}
}
