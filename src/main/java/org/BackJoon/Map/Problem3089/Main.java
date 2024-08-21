package main.java.org.BackJoon.Map.Problem3089;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	private static Map<Integer, TreeSet<Integer>> mapY;
	private static Map<Integer, TreeSet<Integer>> mapX;
	private static int N,M;
	private static char[] orders;
	public static void main(String[] args) throws IOException{
		init();
		
		int currentY=0,currentX=0;
		for(int i=0;i<M;i++) {
			switch (orders[i]) {
			case 'R' : 
				currentX = mapY.get(currentY).higher(currentX);
				break;
			case 'U' :
				currentY = mapX.get(currentX).higher(currentY);
				break;
			case 'L' :
				currentX = mapY.get(currentY).lower(currentX);
				break;
			case 'D' :
				currentY = mapX.get(currentX).lower(currentY);
				break;
			}
		}
		System.out.println(currentX+" "+currentX);
	}
	
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		mapY = new HashMap<Integer, TreeSet<Integer>>(N+1);
		mapX = new HashMap<Integer, TreeSet<Integer>>(N+1);
		orders = new char[M];
		int y,x;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			x= Integer.parseInt(st.nextToken());
			y= Integer.parseInt(st.nextToken());
			mapY.putIfAbsent(y, new TreeSet<>());
			mapX.putIfAbsent(x, new TreeSet<>());
			mapY.get(y).add(x);
			mapX.get(x).add(y);
		}
		orders = br.readLine().toCharArray();
	}
}
