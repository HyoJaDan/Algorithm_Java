package main.java.org.BackJoon.Implementation.Problem14503;

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

class WashingMachine{
	Point currentPosition;
	static int[][] direct = {{-1,0},{0,1},{1,0},{0,-1}};
	int direction=0;
	
	public Point rotate90(int direction) {
		if(direction==0)
			direction=4;
		int dy = direct[direction-1][0];
		int dx = direct[direction-1][1];
		this.direction = direction-1; 
		
		return new Point(dy, dx);
	}
	public WashingMachine() {
		
	}
}

public class Main {
	private static int n,m;
	private static int[][] map;
	WashingMachine washingMachine = new WashingMachine();
	
	public static void main(String[] args)throws IOException {
		// 해결 방법 1 : init 메서드를 non-static으로 선언
		Main main = new Main();
		main.init();
		
		// 해결 방법 2 : 의존성 주입(Dependency Injection)
		WashingMachine washingMachine2 = new WashingMachine();
		init2(washingMachine2);
	}
	private static void init2(WashingMachine washingMachine) {
		
	}
	
	private void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		washingMachine.currentPosition = new Point(r,c);
		
	}
}
