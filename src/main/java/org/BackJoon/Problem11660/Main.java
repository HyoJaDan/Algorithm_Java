package main.java.org.BackJoon.Problem11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int[][] map = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n; j++) {
				map[i][j] = map[i][j-1]+Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuffer sb= new StringBuffer();

		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int fromY=Integer.parseInt(st.nextToken());
			int fromX=Integer.parseInt(st.nextToken());
			int toY=Integer.parseInt(st.nextToken());
			int toX = Integer.parseInt(st.nextToken());
			int ans=0;
			for(int j=fromY; j<=toY;j++	) {
				ans += map[j][toX] - map[j][fromX-1];
			}
			sb.append(ans);sb.append('\n');
		}
		System.out.println(sb);
	}

}
