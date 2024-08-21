package main.java.org.BackJoon.Array.Problem16935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N,M,R;
	private static int[] orders;
	private static int[][] map;
	public static void main(String[] args) throws IOException{
		init();
		
		for(int order : orders) {
			switch (order) {
			case 1 :
				RunOne();
				break;
			case 2:
				RunTwo();
				break;
			case 3:
				RunThree();
				break;
			case 4:
				RunFour();
				break;
			case 5:
				DivideAndConquer(5);
				break;
			case 6:
				DivideAndConquer(6);
				break;
				
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.printf(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[101][101];
		orders = new int[R];
		for(int i =0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<R;i++) {
			orders[i]=Integer.parseInt(st.nextToken());
		}
	}
	public static void RunOne() {
		int temp[][] = new int[N][M];
		
		int top=0,bottom=N-1;
		while(top<N) {
			for(int i=0;i<M;i++) {
				temp[top][i]=map[bottom][i];
			}
			top++;bottom--;
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j]=temp[i][j];
			}
		}
	}
	public static void RunTwo() {
		int temp[][] = new int[N][M];
		
		int left=0,right=M-1;
		while(left<M) {
			for(int i=0;i<N;i++) {
				temp[i][left]=map[i][right];
			}
			left++;right--;
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j]=temp[i][j];
			}
		}
	}
	private static void RunThree() {
		int temp[][] = new int[101][101];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				temp[j][N-i-1] = map[i][j];
			}
		}
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				map[i][j]=temp[i][j];
			}
		}
		int swap = N;
		N=M;
		M=swap;
	}
	private static void RunFour() {
		int temp[][] = new int[101][101];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				temp[M-j-1][i] = map[i][j];
			}
		}
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				map[i][j]=temp[i][j];
			}
		}
		int swap = N;
		N=M;
		M=swap;
	}
	private static void DivideAndConquer(int level) {
		int midX= (0+M)/2;
		int midY = (0+N)/2;
		
		if(level==5) RunFive(midX,midY);
		if(level==6) RunSix(midX,midY);
	}
	private static void RunFive(int midX, int midY) {
		int[][] temp = new int[N][M];
		for(int i=0;i<midY;i++) {
			for(int j=0;j<midX;j++	) {
				temp[i][j+midX] = map[i][j];
			}
		}
		for(int i=0;i<midY;i++) {
			for(int j=midX;j<M;j++) {
				temp[i+midY][j] = map[i][j];
			}
		}
		for(int i=midY;i<N;i++) {
			for(int j=midX;j<M;j++) {
				temp[i][j-midX] = map[i][j];
			}
		}
		for(int i=midY;i<N;i++) {
			for(int j=0;j<midX;j++) {
				temp[i-midY][j] = map[i][j];
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j]=temp[i][j];
			}
		}
	}
	private static void RunSix(int midX, int midY) {
		int[][] temp = new int[N][M];
		for(int i=0;i<midY;i++) {
			for(int j=0;j<midX;j++	) {
				temp[i+midY][j] = map[i][j];
			}
		}
		for(int i=0;i<midY;i++) {
			for(int j=midX;j<M;j++) {
				temp[i][j-midX] = map[i][j];
			}
		}
		for(int i=midY;i<N;i++) {
			for(int j=midX;j<M;j++) {
				temp[i-midY][j] = map[i][j];
			}
		}
		for(int i=midY;i<N;i++) {
			for(int j=0;j<midX;j++) {
				temp[i][j+midX] = map[i][j];
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j]=temp[i][j];
			}
		}
	}
}
