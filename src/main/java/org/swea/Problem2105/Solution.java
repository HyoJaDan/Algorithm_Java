package main.java.org.swea.Problem2105;

import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] arr = new int[25][25];
	static int func(int x, int y, int k, int l) {
		if(x + k + l >= N || y - l < 0 || y + k >= N) return -1;
		Set<Integer> s = new HashSet<>();
		
		for(int i = 0; i < k; i++) {
			if(!s.add(arr[x++][y++])) return -1;
		}
		for(int i = 0; i < l; i++) {
			if(!s.add(arr[x++][y--])) return -1;
		}
		for(int i = 0; i < k; i++) {
			if(!s.add(arr[x--][y--])) return -1;
		}
		for(int i = 0; i < l; i++) {
			if(!s.add(arr[x--][y++])) return -1;
		}
		
		return s.size();
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = -1;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int k = 1; k < N; k++) {
						for(int l = 1; l < N; l++) {
							max = Math.max(max, func(i,j,k,l));							
						}
					}
				}
			}
			
			System.out.printf("#%d %d\n",t, max);
		}
		
	}

}


