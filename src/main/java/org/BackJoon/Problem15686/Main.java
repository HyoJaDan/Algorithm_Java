package main.java.org.BackJoon.Problem15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	private static ArrayList<Point> house = new ArrayList<>();
	private static ArrayList<Point> chickenHouse = new ArrayList<>();
	private static int[] currentChickenHouse;
	private static int[][] map;
	private static int N,M;
	private static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		init();
		bruteForce(0,0);
		System.out.println(ans);
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		currentChickenHouse = new int[M];
		
		int inputData;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				inputData = Integer.parseInt(st.nextToken());
				
				if(inputData == 1) house.add(new Point(i,j));
				else if(inputData ==2) chickenHouse.add(new Point(i,j));
				map[i][j] = inputData;
			}
		}
	}
	public static void bruteForce(int level, int now) {
		if(level==M) {
			int currentDistance = getChickenToHouseDistence();
			ans = Math.min(currentDistance, ans);
			
			return;
		}
		
		for(int i=now; i<chickenHouse.size(); i++) {
			currentChickenHouse[level]=i;
			bruteForce(level+1, i+1);
			currentChickenHouse[level]=0;
		}
	}
	public static int getChickenToHouseDistence() {
		int[] minValues = new int[house.size()];
		int currentAns=0;
		
		for(int i=0;i<M;i++) {
			Point from = chickenHouse.get(currentChickenHouse[i]);
			
			for(int j=0; j<house.size(); j++){
				Point to = house.get(j);
				int distance = getDistance(from, to);
				
				if(minValues[j] == 0)
					minValues[j]=distance;
				else minValues[j] = Math.min(minValues[j] , distance);
			}
		}
		for(int i=0;i<house.size();i++) {
			currentAns+=minValues[i];
		}
		
		return currentAns;
	}
	public static int getDistance(Point from, Point to) {
		return ( Math.abs(from.y-to.y) + Math.abs(from.x - to.x));
	}
}
