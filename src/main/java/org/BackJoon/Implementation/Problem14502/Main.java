package main.java.org.BackJoon.Implementation.Problem14502;

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

public class Main {

	private static int n,m;
	private static int[][] map;
	private static Integer answer = Integer.MIN_VALUE;
	private static int[][] direct = {{-1,0},{1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		init();
		dfs(0);
		System.out.println(answer);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void dfs(int level) {
		if(level==3) {
			bfs();
			return;
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]!=0)
					continue;
				
				map[i][j]=1;
				dfs(level+1);
				map[i][j]=0;
			}
		}
	}

	private static void bfs() {
		int[][] currentMap = new int[n][m];
		Queue<Point> q = new LinkedList<>();

		for(int i=0;i<n;i++) {
			currentMap[i] = map[i].clone();
			for(int j=0;j<m;j++) {
				if(map[i][j]==2) {
					q.add(new Point(i,j));

				}
			}
		}
	
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i=0;i<4;i++	) {
				int dy = now.y+direct[i][0];
				int dx = now.x+direct[i][1];
				
				if(dy<0 || dx<0 || dy>=n || dx>=m )
					continue;
				if(currentMap[dy][dx]!=0)
					continue;
				
				currentMap[dy][dx]=2;

				q.add(new Point(dy,dx));
			}
		}
		
		int safeZone=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(currentMap[i][j]==0)
					safeZone++;
			}
		}
		if(safeZone>answer)
			answer=safeZone;
	}

}
