package main.java.org.BackJoon.Problem2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int y;
	int x;
	int level;
	
	public Point(int y,int x,int level) {
		this.y=y;
		this.x=x;
		this.level=level;
	}
}
public class Main {
	private static int n,m;
	private static int[][] map;
	private static int[][] direct= {{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException {
		init();
		BFS();
		System.out.println(map[n-1][m-1]);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for(int i=0;i<n;i++) {
			String temp = br.readLine();
			for(int j=0;j<m;j++) {
				map[i][j]=temp.charAt(j) - '0';
			}
		}
	}
	private static void BFS() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];
		q.add(new Point(0,0,1));
		visited[0][0]=true;
		
		while(!q.isEmpty()) {
			Point now=q.poll();
			int y=now.y;
			int x=now.x;
			
			for(int i=0;i<4;i++) {
				int dy = y+direct[i][0];
				int dx = x+direct[i][1];
				
				if(dy<0 || dx<0 || dy>=n || dx>= m)
					continue;
				if(map[dy][dx]==0)
					continue;
				if(visited[dy][dx]==true)
					continue;
				
				q.add(new Point(dy,dx,now.level+1));
				visited[dy][dx]=true;
				map[dy][dx]=now.level+1;
			}
		}
	}

}
