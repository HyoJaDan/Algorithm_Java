package main.java.org.BackJoon.Problem2563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] map = new int[101][101];
	private static int ans=100;
	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(ans);
	}

	private static void solve() {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]>1) {
					ans-=map[i][j]-1;
				}
			}
		}
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ans*=n;
		
		for(int i=0;i<n;i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int j=y;j<y+10;j++) {
				for(int k=x; k<x+10;k++) {
					map[j][k]++;
				}
			}
		}
	}

}
