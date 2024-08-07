package main.java.org.BackJoon.Implementation.Problem2564;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private static int mainY,mainX;
	private static Point[] points;
	private static Point startPoint;
	private static int[][] direct = {{-1,0},{0,1},{1,0},{0,-1}};
	public static Point getStartPoint(int a,int b) {
		int dy=0,dx=0;
		if(a==1)
		{
			dy = mainY;
			dx = b;
		}
		else if(a==2) {
			dy = 0;
			dx = b;
		}
		else if(a==3) {
			dy = mainY-b;
			dx=0;
		}
		else if(a==4) {
			dy=mainY-b;
			dx=mainX;
		}
		return new Point(dy,dx);
	}
	
	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		mainX = Integer.parseInt(st.nextToken());
		mainY = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(br.readLine());
		points = new Point[n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			points[i] = getStartPoint(a,b);
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		startPoint =  getStartPoint(a,b);
	}
	
	public static void main(String[] args) throws IOException {
		init();
		rotate();
	}

	private static void rotate() {
		
	}

}
