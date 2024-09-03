package main.java.org.BackJoon.Problem14503;

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

class Map{
	int grid[][];
	int n,m;
	
	public Map(int n,int m) {
		this.n=n;
		this.m=m;
		this.grid = new int[n][m];
	}
}

class WashingMachine{
	Point currentPosition;
	int direction=0; // 0: 合率, 1: 悼率, 2 :巢率, 3 : 辑率
	int n,m;
	int map[][];
	static final int[][] direct = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public WashingMachine() { }

	public void run() {
		while(true) {
//			System.out.println("y: "+currentPosition.y+"x: "+currentPosition.x);
			map[currentPosition.y][currentPosition.x]=2;
			boolean isWorked=false;
			
			for(int i=0;i<4;i++) {
				Point rotate = rotate90();
				
				int dy = currentPosition.y + rotate.y;
				int dx = currentPosition.x + rotate.x;
				
				if(dy<0 || dx<0 || dy>=n || dx >=m ) continue;
				if(map[dy][dx]!=0) continue;

				map[dy][dx]=2;
				currentPosition.y = dy;
				currentPosition.x = dx;
				isWorked=true;
				break;
			}
			if(isWorked==false) {
				int dy=0,dx=0;

				if(direction==0) {
					dy=currentPosition.y+1;
					dx=currentPosition.x;
				} else if(direction==1) {
					dy= currentPosition.y;
					dx = currentPosition.x-1;
				} else if(direction==2) {
					dy=currentPosition.y-1;
					dx=currentPosition.x;
				} else if(direction==3) {
					dy= currentPosition.y;
					dx = currentPosition.x+1;
				}
				if(map[dy][dx]==1) break;
				else {
					currentPosition.y = dy;
					currentPosition.x = dx;
				}
			}
		}
		printAnswer();
		
	}
	private void printAnswer() {
		int ans=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++)
			{
				if(map[i][j]==2) ans++;
			}
		}
		System.out.println(ans);
	}
	private Point rotate90() {
		if(direction==0)
			direction=4;
		int dy = direct[direction-1][0];
		int dx = direct[direction-1][1];
		this.direction = direction-1; 
		
		return new Point(dy, dx);
	}
}

public class Main {
	
	public static void main(String[] args)throws IOException {
		Main main = new Main();
		WashingMachine washingMachine = new WashingMachine();
		main.init(washingMachine);
		washingMachine.run();
	}
	private void init(WashingMachine washingMachine) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		washingMachine.n=Integer.parseInt(st.nextToken());
		washingMachine.m=Integer.parseInt(st.nextToken());
		washingMachine.map = new int[washingMachine.n][washingMachine.m];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		washingMachine.currentPosition = new Point(r,c);
		washingMachine.direction=d;
		
		for(int i=0;i<washingMachine.n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<washingMachine.m;j++) {
				washingMachine.map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	

}
