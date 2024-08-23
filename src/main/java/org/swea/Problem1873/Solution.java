package main.java.org.swea.Problem1873;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

enum Direction{
	right,left,up,down
}
class Point{
	int y;
	int x;
	public Point(int y,int x) {
		this.y=y;
		this.x=x;
	}
}
class Map{
	char[][] grid;
	int N,M;
	public Map(int N,int M) {
		grid = new char[N][M];
		this.N=N;
		this.M=M;
	}
}
class Tank{
	Direction direction;
	Point currentPosition;
	public Tank(Direction nextDirection, Point currentPosition) {
		this.direction=nextDirection;
		this.currentPosition=currentPosition;
	}
	public void goUp(Map map) {
		this.direction = Direction.up;
		int dy = this.currentPosition.y -1;
		int dx = this.currentPosition.x;
		
		boolean isValid=isValidToGo(dy,dx,map);
		if(isValid) map.grid[dy][dx]='^';
		else map.grid[currentPosition.y][currentPosition.x]='^';
	}
	public void goDown(Map map) {
		this.direction = Direction.down;
		int dy = this.currentPosition.y + 1;
		int dx = this.currentPosition.x;
		
		boolean isValid=isValidToGo(dy,dx,map);
		if(isValid) map.grid[dy][dx]='v';
		else map.grid[currentPosition.y][currentPosition.x]='v';
	}
	public void goRight(Map map) {
		this.direction = Direction.right;
		int dy = this.currentPosition.y;
		int dx = this.currentPosition.x+1;
		
		boolean isValid=isValidToGo(dy,dx,map);
		if(isValid) map.grid[dy][dx]='>';
		else map.grid[currentPosition.y][currentPosition.x]='>';
	}
	public void goLeft(Map map) {
		this.direction = Direction.left;
		int dy = this.currentPosition.y;
		int dx = this.currentPosition.x-1;
		
		boolean isValid=isValidToGo(dy,dx,map);
		if(isValid) map.grid[dy][dx]='<';
		else map.grid[currentPosition.y][currentPosition.x]='<';
	}
	private boolean isValidToGo(int dy, int dx,Map map) {
		if(dy <0 || dx <0 || dy>= map.N || dx >= map.M) return false;
		if(map.grid[dy][dx]!='.') return false;
		
		map.grid[currentPosition.y][currentPosition.x]='.';
		currentPosition = new Point(dy,dx);
		
		return true;
	}
	public void shoot(Map map) {
		if(direction == Direction.right) {
			for(int j=currentPosition.x; j<map.M; j++) {
				if(map.grid[currentPosition.y][j] == '*') {
					map.grid[currentPosition.y][j]='.';
					break;
				} else if(map.grid[currentPosition.y][j] == '#') {
					break;
				}
			}
		} else if(direction == Direction.left) {
			for(int j=currentPosition.x; j>=0; j--) {
				if(map.grid[currentPosition.y][j] == '*') {
					map.grid[currentPosition.y][j]='.';
					break;
				} else if(map.grid[currentPosition.y][j] == '#') {
					break;
				}
			}
		} else if(direction == Direction.up) {
			for(int i=currentPosition.y; i>=0; i--) {
				if(map.grid[i][currentPosition.x] == '*') {
					map.grid[i][currentPosition.x]='.';
					break;
				} else if(map.grid[i][currentPosition.x] == '#') {
					break;
				}
			}
		} else if(direction == Direction.down) {
			for(int i=currentPosition.y; i<map.N; i++) {
				if(map.grid[i][currentPosition.x] == '*') {
					map.grid[i][currentPosition.x]='.';
					break;
				} else if(map.grid[i][currentPosition.x] == '#') {
					break;
				}
			}
		}
	}
}

public class Solution {
	private static Tank tank;
	private static Map map;
	private static int T,N;
	private static char[] orders;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int TC=1; TC<=T; TC++) {
			init(br);
			
			for(int i=0;i<N;i++) {
				switch(orders[i]) {
				case 'U':
					tank.goUp(map);
					break;
				case 'D':
					tank.goDown(map);
					break;
				case 'L':
					tank.goLeft(map);
					break;
				case 'R':
					tank.goRight(map);
					break;
				case 'S':
					tank.shoot(map);
					break;
				}
			}
			System.out.print("#"+TC+" ");
			for(int k=0;k<map.N;k++) {
				for(int j=0;j<map.M;j++) {
					System.out.print(map.grid[k][j]);
				}
				System.out.println();
			}
		}
	}
	public static void init(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		String inputData;
		map = new Map(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		
		for(int i=0;i<map.N;i++) {
			inputData = br.readLine();
			for(int j=0;j<map.M;j++) {
				map.grid[i][j] = inputData.charAt(j);
			
				switch (map.grid[i][j]) {
				case '^':
					tank = new Tank(Direction.up, new Point(i,j));
					break;
				case 'v':
					tank = new Tank(Direction.down, new Point(i,j));
					break;
				case '<':
					tank = new Tank(Direction.left, new Point(i,j));
					break;
				case '>':
					tank = new Tank(Direction.right, new Point(i,j));
					break;
				}
			}
		}
		N = Integer.parseInt(br.readLine());
		orders = new char[N];
		orders = br.readLine().toCharArray();
	}
}
