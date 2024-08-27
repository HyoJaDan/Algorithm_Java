package main.java.org.swea.Problem1267;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static ArrayList<Integer>[] graph;
	private static int[] indegree;
	private static int N,M;
	private static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=1;i<=10;i++) {
			init(br);
			
			sb.append("#").append(i).append(" ");
			BFS();
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			if(indegree[i]==0)	q.add(i);
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append(" ");
			
			for(int i : graph[now]) {
				indegree[i]--;
				
				if(indegree[i]==0) {
					q.add(i);
				}
			}
		}
	}
	public static void init(BufferedReader br) throws IOException{
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			graph[i] = new ArrayList<>();
		}
		indegree = new int[N+1];
		
		int from,to;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			indegree[to]++;
		}
	}
}
