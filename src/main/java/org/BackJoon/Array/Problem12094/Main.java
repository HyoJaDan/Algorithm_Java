package main.java.org.BackJoon.Array.Problem12094;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, ans;
	private static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);		
		bruteForce(0);
		System.out.println(ans);
	}
	public static void init(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	public static void bruteForce(int level) {
		if(level==10) {
			int maxBlock = getMaxBlock();
			if(maxBlock > ans) ans =maxBlock;
			
			return;
		}
		
		int[][] backupMap = backup();
		
		for(int i=0;i<4;i++) {
			move(i);
			bruteForce(level+1);
			restore(backupMap);
		}
	}
	private static void restore(int[][] backupMap) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				 map[i][j]=backupMap[i][j];
			}
		}
	}
	private static void move(int i) {
		switch (i) {
		case 0:
			upFunction();
			break;
		case 1:
			rightFunction();
			break;
		case 2:
			downFunction();
			break;
		case 3:
			leftFunction();
			break;
		}
	}
	private static int[][] backup() {
		int[][] backupMap = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				backupMap[i][j] = map[i][j];
			}
		}
		return backupMap;
	}
	private static int getMaxBlock() {
		int maxBlock=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]>maxBlock)
					maxBlock = map[i][j];
			}
		}
		return maxBlock;
		
	}
	public static void downFunction() {
		for(int j=0;j<N;j++) {
			int bottom = N-1;
			int now = -1;
			for(int i=N-1; i>=0; i--) {
				if(map[i][j]==0) continue;
				else if(now == -1) {
					now = map[i][j];
				}
				else {
					// 같은 숫자라면 ( 겹치기)
					if(now == map[i][j]) {
						map[bottom--][j] = now*2;
						now=-1;
					}
					// 아니면  숫자 넣고 넘어가기
					else {
						map[bottom--][j] = now;
						now=map[i][j];
					}
				}
			}
			if(now!=-1) map[bottom--][j]=now;
			for(int i=bottom; i>=0; i--)
				map[i][j]=0;
		}
	}
	public static void upFunction() {
		for(int j=0;j<N;j++) {
			int top = 0;
			int now = -1;
			for(int i=0; i<N ; i++) {
				if(map[i][j]==0) continue;
				else if(now == -1) {
					now = map[i][j];
				}
				else {
					// 같은 숫자라면 ( 겹치기)
					if(now == map[i][j]) {
						map[top++][j] = now*2;
						now=-1;
					}
					// 아니면  숫자 넣고 넘어가기
					else {
						map[top++][j] = now;
						now=map[i][j];
					}
				}
			}
			if(now!=-1) map[top++][j]=now;
			for(int i=top; i<N; i++)
				map[i][j]=0;
		}
	}
	public static void rightFunction() {
		for(int i=0;i<N;i++) {
			int right = N-1;
			int now = -1;
			for(int j=N-1; j>=0; j--) {
				if(map[i][j]==0) continue;
				else if(now == -1) {
					now = map[i][j];
				}
				else {
					// 같은 숫자라면 ( 겹치기)
					if(now == map[i][j]) {
						map[i][right--] = now*2;
						now=-1;
					}
					// 아니면  숫자 넣고 넘어가기
					else {
						map[i][right--] = now;
						now=map[i][j];
					}
				}
			}
			if(now!=-1) map[i][right--]=now;
			for(int j=right; j>=0; j--)
				map[i][j]=0;
		}
	}
	public static void leftFunction() {
		for(int i=0;i<N;i++) {
			int left = 0;
			int now = -1;
			for(int j=0; j<N ; j++) {
				if(map[i][j]==0) continue;
				else if(now == -1) {
					now = map[i][j];
				}
				else {
					// 같은 숫자라면 ( 겹치기)
					if(now == map[i][j]) {
						map[i][left++] = now*2;
						now=-1;
					}
					// 아니면  숫자 넣고 넘어가기
					else {
						map[i][left++] = now;
						now=map[i][j];
					}
				}
			}
			if(now!=-1) map[i][left++]=now;
			for(int j=left; j<N; j++)
				map[i][j]=0;
		}
	}
}
