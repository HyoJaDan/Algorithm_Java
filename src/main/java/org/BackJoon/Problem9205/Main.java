package main.java.org.BackJoon.Problem9205;

import java.io.*;
import java.util.*;

class Point{
	int y;
	int x;
	public Point(int y,int x) {
		this.y=y;
		this.x=x;
	}
	public boolean canGo(int y, int x) {
		int distance = (Math.abs(y - this.y) + Math.abs(x - this.x)) / 50;
		if(distance == 20) {
			if( (Math.abs(y - this.y) + Math.abs(x - this.x))%50 !=0) {
				return false;
			}
			return true;
		}
	    return ( (Math.abs(y - this.y) + Math.abs(x - this.x)) / 50 <= 20);
	}
}

public class Main {
	static int n;
	static List<Point> graph;
	static boolean[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<t; tc++) {
			init(br);
			
			for(int k=0;k<n+2;k++) {
				for(int i=0;i<n+2;i++) {
					for(int j=0;j<n+2;j++) {
						if(i==j) continue;
						if(map[k][j] && map[i][k])
							map[i][j]=true;
					}
				}
			}
			System.out.println(map[0][n+1] == true ? "happy" : "sad");
			
		}
	}
	public static void init(BufferedReader br) throws IOException{
		n = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		map = new boolean[n+2][n+2];
		StringTokenizer st;
		for(int i=0;i<n+2;i++) {
			st = new StringTokenizer(br.readLine());
			graph.add(new Point(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		for(int i=0;i<n+2;i++) {
			for(int j=0;j<n+2;j++) {
				if(i==j) continue;
				
				Point from = graph.get(i);
				Point to = graph.get(j);
				boolean canGo = from.canGo(to.y, to.x);
				if(canGo)
					map[i][j]=true;
			}
		}
		
	}
}
