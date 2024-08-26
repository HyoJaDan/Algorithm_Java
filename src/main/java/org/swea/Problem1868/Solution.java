package main.java.org.swea.Problem1868;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int y;
	int x;
	public Point(int y,int x) {
		this.y=y;
		this.x=x;
	}
}

public class Solution {
	private static int N,T,ans;
	private static char[][] map;
	private static boolean[][] visited;
	private static int[][] direct ={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int TC=1; TC<=T; TC++) {
			init(br);
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]=='.' && !visited[i][j] && check(i,j)) {
						 BFS(new Point(i,j));
					}
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]=='.' && !visited[i][j]) {
						visited[i][j]=true;
						ans++;
					}
				}
			}
			System.out.println("#"+TC+" "+ans);
		}
		
	}
	public static void init(BufferedReader br) throws IOException{
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			String inputData = br.readLine();
			map[i] = inputData.toCharArray(); 
		}
		ans=0;
	}
	
	public static void BFS(Point start) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(start.y, start.x));
		visited[start.y][start.x]=true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i=0;i<8;i++) {
				int dy = now.y + direct[i][0];
				int dx = now.x + direct[i][1];
				
				if(dy<0 || dx<0 || dy>=N || dx >=N) continue;
				if(visited[dy][dx])continue;
				
				if(map[dy][dx]=='.' ) {
					if(check(dy,dx)) {
						q.add(new Point(dy,dx));
						visited[dy][dx]=true;
					} else {
						visited[dy][dx]=true;
					}
				}
			}
		}
		ans++;
	}//주변에 *이 없으면 true
	public static boolean check(int y,int x) {
		int numOfStars = 0;
		for(int i=0;i<8;i++) {
			int dy = y+direct[i][0];
			int dx = x + direct[i][1];
			
			if(dy<0 || dx<0 || dy>=N || dx>=N)continue;
			if(map[dy][dx]=='.') continue;
			
			numOfStars++;
		}
		return numOfStars == 0;
	}
}
