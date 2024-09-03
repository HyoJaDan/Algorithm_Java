package main.java.org.BackJoon.Problem1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static PriorityQueue<Integer> map[];
	private static int n;
	private static int m;
	private static int start;
	private static boolean[] visited;
	public static void main(String[] args) throws IOException {
		init();
		DFS(start);
		System.out.println();
		BFS();
	}

	@SuppressWarnings("unchecked")
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		visited[start]=true;
		
		map = new PriorityQueue[n+1];
		for(int i=0;i<=n;i++) {
			map[i]=new PriorityQueue<>();
		}
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from].add(to);
			map[to].add(from);
		}
	}
	private static void DFS(int now) {
		System.out.printf(now+" ");
		
		PriorityQueue<Integer> temp = new PriorityQueue<>(map[now]);
		while(!temp.isEmpty())
		{
			int next = temp.poll();
			if(visited[next]==true)
				continue;
			visited[next]=true;
			DFS(next);
		}
	}
	
	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		boolean visited2[] = new boolean[n+1];
		visited2[start]=true;
		
		q.add(start);
		while(!q.isEmpty()) {
			int now = q.poll();
			System.out.printf(now+" ");
			
			PriorityQueue<Integer> temp = new PriorityQueue<>(map[now]);
			while(!temp.isEmpty()){
				int next = temp.poll();
				if(visited2[next]==true)
					continue;
				
				q.add(next);
				visited2[next]=true;
			}
		}
	}

}
