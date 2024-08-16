package main.java.org.BackJoon.MST.Problem1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int cost;
	int from;
	int to;
	
	public Edge(int cost,int from,int to) {
		this.cost=cost;
		this.from=from;
		this.to=to;
	}
	
	@Override
	public int compareTo(Edge other) {
		return Integer.compare(this.cost, other.cost);
	}
}

public class Main{
	static int V,E;
	static ArrayList<Edge> edge;
	public static void main(String[] args) throws IOException{
		init();
		
		for(Edge edge : edge) {
			if(! isSameParent(edge.from, edge.to)  ) {
				
			}
		}
	}
	private static int 
	
	private static boolean isSameParent(int from, int to) {
		int fromParent = find(from);
		int toParent = find(to);
		return false;
	}
	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edge = new ArrayList<>();
		for(int i=0;i<E;i++) {
			int from,to,value;
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			
			edge.add(new Edge(value,from,to));
		}
		Collections.sort(edge);
	}
}