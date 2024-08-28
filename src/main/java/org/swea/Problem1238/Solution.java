package main.java.org.swea.Problem1238;

import java.io.*;
import java.util.*;

class Temp{
	int index;
	int level;
	public Temp(int index, int level) {
		this.index=index;
		this.level=level;
	}
}

public class Solution {
	static ArrayList<Integer>[] graph;
	static int N,start,lastIndex,heighLevel;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		for(int tc=1;tc<=10;tc++) {
			init(br);
			
			bfs();
			sb.append("#").append(tc).append(" ").append(lastIndex).append("\n");
		}
		System.out.println(sb);
	}
	public static void init(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		graph = new ArrayList[101];
		for(int i=1;i<=100;i++) {
			graph[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N/2;i++) {
			graph[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}
	}
	public static void bfs() {
		lastIndex=0;heighLevel=-1;
		boolean[] visited = new boolean[101];
		visited[start]=true;
		Queue<Temp> q = new LinkedList<Temp>();
		for(int now : graph[start]) {
			q.add(new Temp(now,0));
			visited[now]=true;
		}
		

		while(!q.isEmpty()) {
			Temp now = q.poll();
			if(now.level > heighLevel) {
				lastIndex=now.index;
				heighLevel=now.level;
			} else if(now.level == heighLevel) {
				lastIndex = Math.max(lastIndex, now.index);
			}
			
			for(int currentIndex : graph[now.index]) {
				if(visited[currentIndex]) continue;
				
				visited[currentIndex] = true;
				q.add(new Temp(currentIndex, now.level+1));
			}
		}
	}
}
