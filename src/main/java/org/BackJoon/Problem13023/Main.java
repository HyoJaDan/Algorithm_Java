package main.java.org.BackJoon.Problem13023;
/**
 * 2024-08-27 09:30~09:54
 * @author Jun
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static ArrayList<Integer>[] tree ;//;= new ArrayList[];
	private static int N,M;
	private static boolean[] visited;
	private static boolean isDone = false;
	public static void main(String[] args) throws IOException{
		init();
		
		for(int i=0;i<N;i++) {
			if(isDone) break;
			visited	= new boolean[N+1];
			visited[i]=true;
			dfs(0, i);
		}
		if(isDone) System.out.println("1");
		else System.out.println("0");
	}
	private static void dfs(int level, int now) {
		if(level == 4) {
			isDone=true;
			return;
		}
		
		for(int i : tree[now]) {
			if(visited[i])continue;
			
			visited[i]=true;
			dfs(level+1, i);
			visited[i]=false;
			if(isDone) return;
		}
	}
	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new ArrayList[N+1];
		for(int i=0;i<=N;i++) {
			tree[i] = new ArrayList<>();
		}
		int from,to;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			tree[from].add(to);
			tree[to].add(from);
		}
	}
}
