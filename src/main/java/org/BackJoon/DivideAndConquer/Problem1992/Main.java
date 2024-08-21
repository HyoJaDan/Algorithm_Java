package main.java.org.BackJoon.DivideAndConquer.Problem1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int map[][];
	public static void main(String[] args) throws IOException{
		init();
		
		DivideAndConquer(1,N,1,N);
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
	
		for(int i=1; i<=N;i++) {
			String s = br.readLine();
			
			for(int j=1;j<=N;j++) {
				map[i][j] = s.charAt(j-1)-'0';
			}
		}
	}
	public static void DivideAndConquer(int left, int right, int top, int bottom) {
		boolean isAPicture = pictureDitector(left, right, top, bottom);
		
		if(!isAPicture) {
			System.out.printf("(");
			
			int midCol = (right+left)/2;
			int midRow = (bottom+top)/2;
			
			DivideAndConquer(left,midCol,top,midRow);
			DivideAndConquer(midCol+1,right,top,midRow);
			DivideAndConquer(left,midCol,midRow+1,bottom);
			DivideAndConquer(midCol+1,right,midRow+1, bottom);
			
			System.out.printf(")");
		} else {
			System.out.print(map[top][left]);
		}
	}
	public static boolean pictureDitector(int left, int right, int top, int bottom) {
		int isTrue = map[top][left];
		
		for(int i=top; i<=bottom; i++) {
			for(int j=left; j<=right; j++)
				if(map[i][j]!=isTrue) return false;
		}
		return true;
	}
}
