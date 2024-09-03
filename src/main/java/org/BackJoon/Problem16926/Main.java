package main.java.org.BackJoon.Problem16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int map[][];
	private static int N,M,R;
	public static void main(String[] args) throws IOException {
		init();
		while(R-- >0) {
			rotate();
		}
        		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M; j++)
				System.out.printf(map[i][j]+" ");
			System.out.println();
		}
		
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		
		for(int i=1; i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
	}
	public static void rotate() {
		int top=1,left=1,right=M,bottom=N;
		
		while(top < bottom && left < right) {
			int saveData = map[top][left];
			
			for(int j=left+1; j<= right; j++) {
				map[top][j-1] = map[top][j];
			}
			for(int i=top+1; i<=bottom; i++) {
				map[i-1][right] = map[i][right];
			}
			for(int j=right-1; j>=left; j--) {
				map[bottom][j+1] = map[bottom][j];
			}
			for(int i=bottom-1; i>=top; i--) {
				map[i+1][left] = map[i][left];
			}
			map[top+1][left]=saveData;
			
			top++;left++;right--;bottom--;
		}

	}
}
