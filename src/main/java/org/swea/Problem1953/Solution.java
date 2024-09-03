package main.java.org.swea.Problem1953;

/**
 * 2024-09-03 13:27 ~ 02 31
 * @author Jun
 *
 */

import java.io.*;
import java.util.*;

class Point{
	int y;int x;int level;
	public Point(int y,int x,int level) {
		this.y=y;
		this.x=x;
		this.level=level;
	}
}

public class Solution {
	static int[][] map;
	static int t,n,m,r,c,l;
	static int direct[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=t;tc++) {
			init(br);
			
			int ans = bfs();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	public static int bfs() {
		int ans=1;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r,c,1));
		boolean visited[][] = new boolean[n][m];
		visited[r][c]=true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(now.level+1 >l) continue;
			if(map[now.y][now.x]==1) {
				//up
				if(canGoUp(now.y,now.x,visited)) {
					ans++;
					visited[now.y-1][now.x]=true;
					q.add(new Point(now.y-1, now.x, now.level+1));
				}
				if(canGoDown(now.y,now.x,visited)) {
					ans++;
					visited[now.y+1][now.x]=true;
					q.add(new Point(now.y+1,now.x,now.level+1));
				}
				if(canGoRight(now.y,now.x,visited)) {
					ans++;
					visited[now.y][now.x+1]=true;
					q.add(new Point(now.y,now.x+1,now.level+1));
				}
				if(canGoLeft(now.y,now.x,visited)) {
					ans++;
					visited[now.y][now.x-1]=true;
					q.add(new Point(now.y,now.x-1,now.level+1));
				}
			}
			else if(map[now.y][now.x]==2) {
				//up
				if(canGoUp(now.y,now.x,visited)) {
					ans++;
					visited[now.y-1][now.x]=true;
					q.add(new Point(now.y-1, now.x, now.level+1));
				}
				if(canGoDown(now.y,now.x,visited)) {
					ans++;
					visited[now.y+1][now.x]=true;
					q.add(new Point(now.y+1,now.x,now.level+1));
				}
			}
			else if(map[now.y][now.x]==3) {
				if(canGoRight(now.y,now.x,visited)) {
					ans++;
					visited[now.y][now.x+1]=true;
					q.add(new Point(now.y,now.x+1,now.level+1));
				}
				if(canGoLeft(now.y,now.x,visited)) {
					ans++;
					visited[now.y][now.x-1]=true;
					q.add(new Point(now.y,now.x-1,now.level+1));
				}
			}
			else if(map[now.y][now.x]==4) {
				//up
				if(canGoUp(now.y,now.x,visited)) {
					ans++;
					visited[now.y-1][now.x]=true;
					q.add(new Point(now.y-1, now.x, now.level+1));
				}
				if(canGoRight(now.y,now.x,visited)) {
					ans++;
					visited[now.y][now.x+1]=true;
					q.add(new Point(now.y,now.x+1,now.level+1));
				}
			}
			else if(map[now.y][now.x]==5) {
				if(canGoDown(now.y,now.x,visited)) {
					ans++;
					visited[now.y+1][now.x]=true;
					q.add(new Point(now.y+1,now.x,now.level+1));
				}
				if(canGoRight(now.y,now.x,visited)) {
					ans++;
					visited[now.y][now.x+1]=true;
					q.add(new Point(now.y,now.x+1,now.level+1));
				}
			}
			else if(map[now.y][now.x]==6) {
				if(canGoDown(now.y,now.x,visited)) {
					ans++;
					visited[now.y+1][now.x]=true;
					q.add(new Point(now.y+1,now.x,now.level+1));
				}
				if(canGoLeft(now.y,now.x,visited)) {
					ans++;
					visited[now.y][now.x-1]=true;
					q.add(new Point(now.y,now.x-1,now.level+1));
				}
			}
			else if(map[now.y][now.x]==7) {
				//up
				if(canGoUp(now.y,now.x,visited)) {
					ans++;
					visited[now.y-1][now.x]=true;
					q.add(new Point(now.y-1, now.x, now.level+1));
				}
				if(canGoLeft(now.y,now.x,visited)) {
					ans++;
					visited[now.y][now.x-1]=true;
					q.add(new Point(now.y,now.x-1,now.level+1));
				}
			}
		}
		
		
		return ans;
	}
	public static boolean canGoUp(int y,int x,boolean[][] visited) {
		y=y-1;
		if(y<0 || x<0 || y>=n || x>=m) return false;
		if(visited[y][x]) return false;
		
		if(map[y][x]==1 || map[y][x]==2 || map[y][x]==5 || map[y][x]==6) return true;
		
		return false;
	}
	public static boolean canGoDown(int y,int x,boolean[][] visited) {
		y=y+1;
		if(y<0 || x<0 || y>=n || x>=m) return false;
		if(visited[y][x]) return false;
		
		if(map[y][x]==1 || map[y][x]==2 || map[y][x]==4 || map[y][x]==7) return true;
		
		return false;
	}
	public static boolean canGoRight(int y,int x,boolean[][] visited) {
		x=x+1;
		if(y<0 || x<0 || y>=n || x>=m) return false;
		if(visited[y][x]) return false;
		
		if(map[y][x]==1 || map[y][x]==3 || map[y][x]==6 || map[y][x]==7) return true;
		
		return false;
	}
	public static boolean canGoLeft(int y,int x,boolean[][] visited) {
		x=x-1;
		if(y<0 || x<0 || y>=n || x>=m) return false;
		if(visited[y][x]) return false;
		
		if(map[y][x]==1 || map[y][x]==3 || map[y][x]==4 || map[y][x]==5) return true;
		
		return false;
	}
	public static void init(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
	}
}
