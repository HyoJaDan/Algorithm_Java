package main.java.org.BackJoon.Graph.Project1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge{
	int to;
	int weight;
	public Edge(int to,int weight) {
		this.to=to;
		this.weight=weight;
	}
}
public class Main {
	static int n;
	static int m;
	static ArrayList<Edge>[] graph;
	static int[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		graph = new ArrayList[n+1];
		result = new int[n+1];
		
		for(int i=0;i<= n;i++) {
			graph[i] = new ArrayList<Edge>();
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to,value)); 
		}	
		Arrays.fill(result,Integer.MAX_VALUE);
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int to=Integer.parseInt(st.nextToken());
		dijkstra(start);
		
		System.out.println(result[to]);
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparing(edge -> edge.weight));
        result[start] = 0;
        minHeap.add(new Edge(start, 0));

        while(!minHeap.isEmpty()) {
        	Edge now = minHeap.poll();
        	
        	int currentNode = now.to;
        	int currentDistance = now.weight;
        	
        	if(result[currentNode] < currentDistance) 
        		continue;
        	
        	
        	for(Edge next : graph[currentNode]) {	
        		int nextDistance = currentDistance + next.weight;
        		int pastDistance = result[next.to];
        		
        		if(nextDistance < pastDistance ) {
        			minHeap.add(new Edge(next.to, nextDistance));
        			result[next.to] = nextDistance;
        		}
        	}
        }
	}

}
