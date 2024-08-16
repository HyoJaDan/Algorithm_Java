package main.java.org.swea.Problem3421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int n,m;
	static int[][] noMatch;
	static int ans=0;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			ans=0;
			visited = new boolean[n+1];
			noMatch= new int[m][2];
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());
				noMatch[i][0] = Integer.parseInt(st.nextToken());
				noMatch[i][1] = Integer.parseInt(st.nextToken());
			}
			
			run(0,1);
			System.out.println("#"+test_case+" "+ans);
		}
	}
	static void run(int level,int now) {
	
		for(int j=0;j<m;j++) {
			if(visited[noMatch[j][0]]==true && visited[noMatch[j][1]]==true)
			{
				return;
			}
		}
		ans++;
		
		if(level==n) {
			return;
		}
		for(int i=now;i<=n;i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			run(level+1,i);
			visited[i] =false;
			
		}
	}
}
