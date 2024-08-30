package main.java.org.swea.Problem2383;

/**
 * 2024-08-29 09:42 : 11:19
 * @author Jun
 */

import java.io.*;
import java.util.*;
class Point{
	int y;int x;
	public Point(int y,int x) {
		this.y=y;this.x=x;
	}
}

public class Solution {
	static int T,N,ans=Integer.MAX_VALUE;
	static int[][]map;
	//true일때 0번 false일때 1번
	static boolean whichStair[];
	static ArrayList<Point> people,stairs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			init(br);
			dfs(0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	public static int getDistance(int x, int y, int x2, int y2) {
		return (Math.abs(x-x2) + Math.abs(y-y2));
	}
	public static int run() {
		// 거리를 저장해둠
		ArrayList<Integer> stair1 = new ArrayList<>();
		ArrayList<Integer> stair2 = new ArrayList<>();
		
		for(int i=0;i<people.size();i++) {
			if(whichStair[i]==true) {
				stair1.add(getDistance(people.get(i).x,people.get(i).y,stairs.get(0).x,stairs.get(0).y));
			} else {
				stair2.add(getDistance(people.get(i).x,people.get(i).y,stairs.get(1).x,stairs.get(1).y));
			}
		}
		Collections.sort(stair1); Collections.sort(stair2);
		
		ArrayDeque<Integer> path1 = new ArrayDeque<>();
		ArrayDeque<Integer> path2 = new ArrayDeque<>();
		int now1=0,now2=0, minutes=1;
		while(true) {
			if(path1.isEmpty() && path2.isEmpty() && now1 > stair1.size()-1 && now2 > stair2.size()-1) {
					break;
			}
			while (!path1.isEmpty() && map[stairs.get(0).y][stairs.get(0).x] - (minutes - path1.getFirst()) <= 0) {
			    path1.pollFirst();
			}
			while (!path2.isEmpty() && map[stairs.get(1).y][stairs.get(1).x] - (minutes - path2.getFirst()) <= 0) {
			    path2.pollFirst();
			}
			while(now1 <= stair1.size()-1 && path1.size()<3 && stair1.get(now1) <= minutes) {
				path1.addLast(minutes);
				now1++;
			}
			while(now2 <= stair2.size()-1 && path2.size()<3 && stair2.get(now2) <= minutes) {
				path2.addLast(minutes);
				now2++;
			}
			
			minutes++;
		}
		return minutes;
	}
	public static void dfs(int level) {
		if(level == people.size()) {
			ans = Math.min(ans, run());
			return;
		}
		
		whichStair[level] = true;
		dfs(level+1);
		
		whichStair[level] = false;
		dfs(level+1);
		
		
	}
	public static void init(BufferedReader br) throws IOException{
		ans=Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		people = new ArrayList<>();
		stairs = new ArrayList<>();
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) people.add(new Point(i,j));
				if(map[i][j]>1) stairs.add(new Point(i,j));
			}
		}
		whichStair = new boolean[people.size()];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]>1) stairs.add(new Point(i,j));
			}
		}
	}
}
