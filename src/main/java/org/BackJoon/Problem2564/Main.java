package main.java.org.BackJoon.Problem2564;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
	int y;
	int x;
	public Point(int y,int x) {
		this.y=y;
		this.x=x;
	} 
	@Override
    public int compareTo(Point other) {
        if (this.y != other.y) {
            return Integer.compare(this.y, other.y);
        } else {
            return Integer.compare(this.x, other.x);
        }
    }
}

public class Main {
	private static int mainY,mainX;
	private static int n;
	private static Point[] points;
	private static int[] ans;
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
		n = Integer.parseInt(br.readLine());
		points = new Point[n];
		ans = new int[n];
		for(int i=0;i<n;i++)
			ans[i]=Integer.MAX_VALUE;
		
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

		int output = 0;
		for(int i=0;i<n;i++) {
			output += ans[i];
		}
		System.out.println(output);
	}
	
	public static void reverseDirection(){
		int tempY = direct[0][0];
		int tempX = direct[0][1];
		direct[0][0] = direct[3][0];
		direct[0][1] = direct[3][1];
		direct[3][0]=tempY;
		direct[3][1]=tempX;
	}
	
	private static void rotate() {
		
		for(int k=0;k<2;k++) {
			reverseDirection();
			
			int[][] map = new int[mainY+1][mainX+1];
			int y = startPoint.y;
			int x = startPoint.x;	
			map[y][x]=0;
			PriorityQueue<Point> pq = new PriorityQueue<>();
			pq.add(new Point(y,x));
			
			while(!pq.isEmpty()) {
				Point now = pq.poll();
				y = now.y;
				x=now.x;
				for(int i=0;i<4;i++) {
					int dy = direct[i][0] + y;
					int dx = direct[i][1] + x;
					
					if(dy<0 || dx<0 || dy> mainY || dx > mainX)
						continue;
					if(map[dy][dx]!=0)
						continue;
					
					if((dy ==0 || dy==mainY) || (dx ==0 || dx == mainX))
					{
						map[dy][dx]=map[y][x]+1;
						pq.add(new Point(dy,dx));
						
						for(int j=0;j<n;j++) {
							if(dy == points[j].y && dx == points[j].x && map[dy][dx] < ans[j]) 
							{
								ans[j]=map[dy][dx];
								//System.out.println("---"+ans[j]+"---");
							}
						}
						//System.out.println(dy +" " + dx + "=" + map[dy][dx]);
						break;
					}
				}
			}
			 // System.out.println("----------------------------------");System.out.println("----------------------------------");System.out.println("----------------------------------");
		}
	}

}
