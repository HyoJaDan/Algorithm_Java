package main.java.org.BackJoon.Problem10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point{
	int y,x;
	public Point(int y,int x) {
		this.y=y;
		this.x=x;
	}
}

public class Main {
	private static char[][] map;
	private static boolean[][] visited;
	private static int N,ansA=0,ansB=0;
	private static final int direct[][]= {{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException{
		init();
		
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					BFS(i,j, false);
					ansA++;
				}
			}
		}
		
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					BFS(i,j, true);
					ansB++;
				}
			}
		}
		System.out.println(ansA+" "+ansB);
	}
	private static void BFS(int y, int x, boolean isBlind) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(y,x));
		visited[y][x]=true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i=0;i<4;i++) {
				int dy = now.y+direct[i][0];
				int dx = now.x+direct[i][1];
				
				if(dy<0 || dx<0 || dy>=N || dx>=N) continue;
				if(visited[dy][dx]) continue;
				
				boolean isSameColor = parseColor(y,x,dy,dx,isBlind);
				if(isSameColor) {
					q.add(new Point(dy,dx));
					visited[dy][dx]=true;
				}
			}
		}
		
	}
	private static boolean parseColor(int y, int x, int dy, int dx, boolean isBlind) {
		if(isBlind && ((map[y][x] == 'R' && map[dy][dx] =='G') || (map[y][x]=='G'&& map[dy][dx]=='R'))) {
			return true;
		}	
		if(map[y][x] == map[dy][dx]) 
			return true;

		return false;
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		for(int i=0;i<N;i++	) {
			map[i] = br.readLine().toCharArray();
		}
	}
}
