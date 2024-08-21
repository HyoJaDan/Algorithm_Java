package main.java.org.swea.Problem2112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Glass{
	int level;
	boolean ZeroOrOne;
	
	public Glass(int level, boolean ZeroOrOne) {
		this.level=level;
		this.ZeroOrOne=ZeroOrOne;
	}
}

public class Solution {
	private static Glass[][] map;
	private static int T,N,M,K;
	private static int[] path;
	private static boolean[] visited;
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for(int TC=1; TC<=T; TC++) {
			init(br, st);
			
			for(int i=1;i<=N;i++) {
				dfs(0,0,i);
			}
		}
	}
	public static void init(BufferedReader br, StringTokenizer st) throws IOException{
		st = new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Glass[N+1][M];
		path = new int[N];
		visited = new boolean[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				int inputData = Integer.parseInt(st.nextToken());
				if(inputData==1)
					map[i][j] = new Glass(1,true);
				else map[i][j] = new Glass(1,false);
			}
		}
		for(int j=0;j<M;j++) {
			map[N][j] = new Glass(1,true);
		}
		for(int j=0;j<M;j++) {
			int maxLevel=0;
			for(int i=0; i<N; i++) {
				int currentLevel=1,k=i;
				while(k+1 < N && map[k+1][j].ZeroOrOne == map[i][j].ZeroOrOne) {
					currentLevel++; k++;
				}
				while(i<=k) {
					map[i++][j].level=currentLevel;
				}
				i--;
				if(maxLevel < currentLevel) maxLevel = currentLevel;
			}
			map[N][j].level=maxLevel;
		}
		for(int i=0;i<=N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j].level+" ");
			}
			System.out.println();
		}
	}
	public static void dfs(int level, int now, int maxLevel) {
		if(level==maxLevel) {
			run();
			for(int i=0;i<maxLevel;i++)
				System.out.print(path[i]+" ");
			System.out.println();
			return;
		}
		
		for(int i=now;i<N;i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			path[level] = i;
			dfs(level+1, i, maxLevel);
			path[level] = 0;
			visited[i] = false;
		}
	}
	public static void run() {
		
	}
}
