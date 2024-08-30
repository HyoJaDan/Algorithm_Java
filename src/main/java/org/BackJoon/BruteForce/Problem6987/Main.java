package main.java.org.BackJoon.BruteForce.Problem6987;

import java.util.*;
import java.io.*;

public class Main{
	public static final int MAX_TEAM = 6;
	public static int size = 15;
	public static int[][] fight;
	public static int result;

	public static void backTracking(int depth, int[][] games, int idx) {
		if(depth == size) {
			result = 1;
			return;
		}

		if(result == 1) {
			return;
		}
		int a = fight[idx][0];
		int b = fight[idx][1];

		if(games[a][0] > 0 && games[b][2] > 0) { //a媛� �씠湲곌퀬 b媛� �뙣諛�
			games[a][0] -= 1;
			games[b][2] -= 1;
			backTracking(depth+1, games, idx+1);
			games[a][0] += 1;
			games[b][2] += 1;
		}
		if(games[a][1] > 0 && games[b][1] > 0) { //鍮꾧만�븣
			games[a][1] -= 1;
			games[b][1] -= 1;
			backTracking(depth+1, games, idx+1);
			games[a][1] += 1;
			games[b][1] += 1;
		}
		if(games[a][2] > 0 && games[b][0] > 0) { //a媛� 吏�怨� b媛� �씠湲�
			games[a][2] -= 1;
			games[b][0] -= 1;
			backTracking(depth+1, games, idx+1);
			games[a][2] += 1;
			games[b][0] += 1;
		}
	}


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		fight = new int[size][2];

		int index = 0;
		for(int i=0;i<MAX_TEAM;i++) { //吏꾪뻾�릺�뒗 寃쎄린
			for(int j=i+1;j<MAX_TEAM;j++) {
				fight[index][0] = i;
				fight[index++][1] = j;
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int tc=0;tc<4;tc++) {
			boolean valid = true;
			int[][] games = new int[6][3];
			result = 0;
			st = new StringTokenizer(br.readLine());

			for(int i=0;i<6;i++) { //媛� 寃쎄린 寃곌낵 ���옣
				games[i][0] = Integer.parseInt(st.nextToken());
				games[i][1] = Integer.parseInt(st.nextToken());
				games[i][2] = Integer.parseInt(st.nextToken());

				if((games[i][0] + games[i][1] + games[i][2]) != 5) {
					valid = false;
				}
			}


			if(valid) backTracking(0,games, 0);
			sb.append(result).append(" ");
		}
		System.out.println(sb);
	}
}