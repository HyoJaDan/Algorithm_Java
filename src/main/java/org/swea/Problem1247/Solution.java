package main.java.org.swea.Problem1247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point {
   int x;
   int y;
 
   Point(int x, int y) {
      this.x = x;
      this.y = y;
   }
 
   int getDistance(Point other) {
      return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
   }
}

public class Solution {
	private static int T,N,ans;
	private static boolean[] visited;
	private static Point home, company;
	private static Point customerHouse[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int TC=1; TC<=T; TC++) {
			init(br);	
			
			bruteForce(0,company,0);
			System.out.println("#"+TC+" "+ans);
		}
	}
	public static void init(BufferedReader br) throws IOException{
		N = Integer.parseInt(br.readLine());
		customerHouse = new Point[N];
		visited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		company = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		home = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		
		for(int i=0;i<N;i++) {
			customerHouse[i] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		ans=Integer.MAX_VALUE;
	}
	public static void bruteForce(int level, Point current, int cost) {
		if(cost>ans) return;
		
		if(level == N) {
			ans = Math.min(cost+home.getDistance(current), ans);
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			
			visited[i]=true;
			bruteForce(level+1, customerHouse[i], cost+customerHouse[i].getDistance(current));
			visited[i]=false;
		}
	}
}
