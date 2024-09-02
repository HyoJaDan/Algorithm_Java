package main.java.org;

import java.io.*;
import java.util.*;

public class Study {
	static int K,N,maxValue,q,w;
	static char[] order;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		init();
		DivideAndConquer(0,maxValue,0,maxValue,0);
		
		for(int i=0;i<maxValue;i++) {
			for(int j=0;j<maxValue;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void DivideAndConquer(int fromY,int toY,int fromX,int toX,int now) {
		if(toY-fromY==1 && toX-fromX==1) {
			for(int i=0;i<maxValue/2+1;i+=2) {
				for(int j=0;j<maxValue/2+1;j+=2) {
					map[i][j]=map[fromY-1][fromX-1];
					map[i][j+1]=map[fromY-1][fromX];
					map[i+1][j]=map[fromY][fromX-1];
					map[i+1][j+1]=map[fromY][fromX];
				}
			}
			return;
		}
		if(N==3) {
			map[fromY][fromX]=0;
			map[fromY][toX-1]=1;
			map[toY-1][fromX]=2;
			map[toY-1][toX-1]=3;
		}
		
		if(order[now] == 'R') {
			DivideAndConquer(fromY, toY, (fromX+toX)/2, toX,now+2);
		} 
		else if(order[now]=='L') {
			DivideAndConquer(fromY, toY, fromX, (fromX+toX)/2,now+2);
		}
		else if(order[now]=='U') {
			DivideAndConquer(fromY, (fromY+toY)/2, fromX, toX,now+2);
		}
		else if(order[now]=='D') {
			DivideAndConquer((fromY+toY)/2, toY, fromX,toX,now+2);
		}
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		maxValue = (int) Math.pow(2, K);
		order = new char[maxValue];
		map = new int[maxValue][maxValue];
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		order = st.toString().toCharArray();
//		br.read
		order = br.readLine().toCharArray();
		N = Integer.parseInt(br.readLine());
	}
}
