package main.java.org.BackJoon.TopologicalSort.Problem2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static ArrayList<Integer>[] graph;
	private static int[] indegree;
	private static int N,M;
	public static void main(String[] args) throws IOException{
		init();
		
		BFS();
	}
	public static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			if(indegree[i]==0)	q.add(i);
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			System.out.print(now+" ");
			
			for(int i : graph[now]) {
				indegree[i]--;
				
				if(indegree[i]==0) {
					q.add(i);
				}
			}
		}
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			graph[i] = new ArrayList<>();
		}
		indegree = new int[N+1];
		
		int from,to;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			indegree[to]++;
		}
	}
}
