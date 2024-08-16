package main.java.org.BackJoon.DFS.Problem2961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int sin[];
	static int son[];
	static int n;
	static Long ans=Long.MAX_VALUE;
	static char[] path = new char[10];
	
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		sin = new int[n];
		son = new int[n];
		visited = new boolean[n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			sin[i] = Integer.parseInt(st.nextToken());
			son[i] = Integer.parseInt(st.nextToken());
		}
		run(0, 1L, 0L,0);
		System.out.println(ans);
	}
	static void run(int level,Long nowSin, Long nowSon,int now) {
		if(level==n) {
//			System.out.println(path);
			return;
		}
			
		
		for(int i=now;i<n;i++) {
			if(visited[i]) continue;
			
			Long currentSin = nowSin * sin[i];
			Long currentSon = nowSon + son[i];
			if(Math.abs(currentSin-currentSon)<ans)
				ans = Math.abs(currentSin-currentSon);
			
			visited[i]=true;
			path[level]=(char) (i+'0');
			run(level+1, currentSin, currentSon,i);
			path[level]=0;
			visited[i]=false;
		}
	}

}
