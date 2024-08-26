package main.java.org.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int y,x;
	public Point(int y,int x) {
		this.y=y;
		this.x=x;
	}
}

class Solution {
	private static int T,N;
	private static int[][] map;
	private static ArrayList<Point>[] numbers = new ArrayList[101];
	private static final int[][] direct = {{-1,0},{1,0,},{0,1},{0,-1}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int TC=1; TC<=T; TC++) {
			init(br);
			int ans=0;
			
			for(int i=0;i<=100;i++) {			
				for(Point now : numbers[i]) {
					map[now.y][now.x]=0;
				}
				ans = Math.max(ans, numOfPiece());
			}			
			System.out.println("#"+TC+" "+ans);
		}
	}
	private static int numOfPiece() {
		int returnValue=0;
		boolean[][] visited	= new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]!=0 && !visited[i][j]) {
					BFS(i,j,visited);
					returnValue++;
				}
			}
		}
		
		return returnValue;
	}
	private static void BFS(int y, int x, boolean[][] visited) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(y,x));
		visited[y][x]=true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i=0;i<4;i++) {
				int dy = now.y+direct[i][0];
				int dx = now.x+direct[i][1];
				
				if(dy<0 || dx<0 || dy>=N || dx>=N )continue;
				if(visited[dy][dx])continue;
				if(map[dy][dx]==0)continue;
				
				visited[dy][dx]=true;
				q.add(new Point(dy,dx));
			}
		}
	}
	public static void init(BufferedReader br) throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<=100;i++) {
			numbers[i]=new ArrayList<Point>();
		}
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(numbers[map[i][j]].add(new Point(i,j)));
			}
		}
	}
}
