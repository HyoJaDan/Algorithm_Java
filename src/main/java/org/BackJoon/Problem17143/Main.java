package main.java.org.BackJoon.Problem17143;

import java.util.*;
import java.io.*;

class Shark{
	int y;
	int x;
	int s;
	int d;
	int z;
	
	public Shark(int y,int x, int s, int d, int z) {
		this.y=y;
		this.x=x;
		this.s = s;
		this.d=d;
		this.z=z;
	}

	public void isNeedToTurn(int mapY,int mapX){
		if(this.d==1 || this.d ==2) {
			if(this.y==1) {
				this.d=2;
			} else if(this.y==mapY) {
				this.d=1;
			}
		}
		else {
			if(this.x==1) {
				this.d=3;
			}
			else if(this.x==mapX)
				this.d=4;
		}
	}
	public void move(int mapY,int mapX) {
		isNeedToTurn(mapY,mapX);
		
		if(d==1) {
			y--;
		}
		else if(d==2) {
			y++;
		}
		else if(d==3) {
			x++;
		}
		else if(d==4) {
			x--;
		}
	}
}

public class Main {
	// r * c 
	// 상어의 수 m
	// r c : 상어위 위치 
	//s :속력
	//d 이동 방향  -> 1 위 2 아래 3 오른쪽 4 왼쪽
	//z 크기
	static int[][] map;
	static int y,x,m, person=1, ans=0;
	static List<Shark> sharks = new ArrayList<>();
	public static void main(String[] args )throws IOException{
		init();
		
		for(int round=1; round<=x; round++) {
//			System.out.println("================round//================"+round);
			catchShark(round);
			moveShark();
//			System.out.println();
		
//			for(Shark s : sharks) {
//				System.out.println(s.y+" "+s.x+" "+s.s+" "+s.d+" "+s.z);
//			}
		}
		System.out.println(ans);
	}

	private static void moveShark() {
		int newMap[][] = new int[y+1][x+1];
		
		for(int i=0;i<sharks.size();i++) {
			Shark shark = sharks.get(i);
			if(shark.y==0 && shark.x==0) continue;
			for(int j=0;j<shark.s;j++) {
				shark.move(y,x);
			}
			if(newMap[shark.y][shark.x]!=0) {
				Shark beforeShark = sharks.get(newMap[shark.y][shark.x]-1);
				if(shark.z > beforeShark.z) {
					beforeShark.y=0;
					beforeShark.x=0;
					newMap[shark.y][shark.x] = i+1;
				} else {
					shark.y=0;
					shark.x=0;
				}
			} else {
				newMap[shark.y][shark.x]=i+1;
			}
		}
		
		map = newMap;
	}

	private static void catchShark(int currentX) {
		for(int currentY=1; currentY<=y; currentY++) {
			if(map[currentY][currentX] !=0){
				int currentSharkIndex = map[currentY][currentX]-1;
				ans += sharks.get(currentSharkIndex).z;
//				System.out.println(currentSharkIndex+" "+ sharks.get(currentSharkIndex).y+" "+sharks.get(currentSharkIndex).x+" "+sharks.get(currentSharkIndex).s+" "+sharks.get(currentSharkIndex).d+" "+sharks.get(currentSharkIndex).z);
				map[currentY][currentX]=0;
				sharks.get(currentSharkIndex).y=0;
				sharks.get(currentSharkIndex).x=0;
				break;
			}
		}
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[y+1][x+1];
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int nowY = Integer.parseInt(st.nextToken());
			int nowX = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			if(direction==1 || direction ==2) {
				if(y==1)
					speed=1;
				else
					speed %= (y-1)*2;
			} else {
				if(x==1)
					speed=1;
				else
					speed %= (x-1)*2;
			}
			sharks.add(new Shark(
					nowY, nowX,speed, direction,Integer.parseInt(st.nextToken())));
			map[nowY][nowX] = sharks.size();
		}
	}
}
