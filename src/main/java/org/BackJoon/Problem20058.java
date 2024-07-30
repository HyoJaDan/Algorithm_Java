package org.BackJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem20058 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int mapSize = (int) Math.pow(2, N);
		int[][] map = new int[mapSize][mapSize];
		
		for(int i=0;i<mapSize;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<mapSize;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		
		}
		rotate(map, Q);

	}

	private static void rotate(int[][] map,int q) {
		int divideSize = (int) Math.pow(2,q);
	}

}
