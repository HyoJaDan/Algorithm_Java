package org.BackJoon.Implementation.Problem14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;
import java.util.StringTokenizer;

enum Direction{
	temp,east,west,north,south;
}
enum Face{
	temp,top,back,right,left,front,bottom
}
class Dice{
	private EnumMap<Face, Integer> faces = new EnumMap<>(Face.class);
	
	public Dice() 
	{
		for(Face face : Face.values()) 
		{
			if( face != Face.temp ) 
			{
				faces.put(face, 0);
			}
		}
	}
	public void printTopValue() {
		System.out.println( faces.get(Face.top));
	}
	public void setBottomValue(int[][] map,int y,int x) {
		if(map[y][x]==0) {
			map[y][x]=faces.get(Face.bottom);
		}else {
			faces.put(Face.bottom, map[y][x]);
			map[y][x]=0;
		}
		printTopValue();
	}
	public void rollDice(Direction direction) {
		if(direction == Direction.east) {
			int temp=faces.get(Face.top);
			faces.put(Face.top, faces.get(Face.left));
			faces.put(Face.left, faces.get(Face.bottom));
			faces.put(Face.bottom, faces.get(Face.right));
			faces.put(Face.right, temp);
		} else if(direction == Direction.west) {
			int temp=faces.get(Face.top);
			faces.put(Face.top, faces.get(Face.right));
			faces.put(Face.right, faces.get(Face.bottom));
			faces.put(Face.bottom, faces.get(Face.left));
			faces.put(Face.left, temp);
		} else if(direction == Direction.north) {
			int temp = faces.get(Face.top);
			faces.put(Face.top, faces.get(Face.front));
			faces.put(Face.front, faces.get(Face.bottom));
			faces.put(Face.bottom, faces.get(Face.back));
			faces.put(Face.back, temp);
		} else if(direction == Direction.south) {
			int temp = faces.get(Face.top);
			faces.put(Face.top, faces.get(Face.back));
			faces.put(Face.back, faces.get(Face.bottom));
			faces.put(Face.bottom, faces.get(Face.front));
			faces.put(Face.front, temp);
		}
	}
}
public class Main {
	private static int[][] map;
	private static int n,m;
	private static int startY,startX;
	private static int processSize;
	private static Direction[] process;
	private static Dice dice = new Dice();
	
	public static void main(String[] args) throws IOException {
		init();
		
		int y = startY;
		int x = startX;
		for(Direction direction : process) {
			if(direction==Direction.east && x+1 < m) {
				x+=1;
				dice.rollDice(direction);
				dice.setBottomValue(map,y,x);
			}
			if(direction==Direction.north && y-1 >= 0) {
				y-=1;
				dice.rollDice(direction);
				dice.setBottomValue(map,y,x);
			}
			if(direction==Direction.south && y+1 < n){
				y+=1;
				dice.rollDice(direction);
				dice.setBottomValue(map,y,x);
			}
			if(direction==Direction.west && x-1 >= 0) {
				x-=1;
				dice.rollDice(direction);
				dice.setBottomValue(map,y,x);
			}
			
		}
	}
	
	private static void init() throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		startY = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		processSize = Integer.parseInt(st.nextToken());
		process = new Direction[processSize];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<processSize;i++) {
			process[i] = Direction.values()[Integer.parseInt(st.nextToken())];
		}
	}
}
