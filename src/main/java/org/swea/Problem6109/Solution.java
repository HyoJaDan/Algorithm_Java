package main.java.org.swea.Problem6109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N;
	private static int[][] map;
	private static String order;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int TC=1; TC <=T; TC++) {
			init(br);
			
			switch (order) {
			case "up":
				upFunction();
				break;
			case "right":
				rightFunction();
				break;
			case "down":
				downFunction();
				break;
			case "left":
				leftFunction();
				break;
			}
			System.out.println("Case #"+TC+":");
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++)
					System.out.print(map[i][j]+" ");
				System.out.println();
			}
		}
	}
	public static void init(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		order = st.nextToken();
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
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
