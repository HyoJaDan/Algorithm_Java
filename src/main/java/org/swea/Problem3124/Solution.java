/**
 * 2024 08 29 08:24 ~ 08:50
 */
package main.java.org.swea.Problem3124;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int weight;
	int from;
	int to;
	
	public Node(int from, int to, int weight) {
		this.from = from;
		this.to=to;
		this.weight=weight;
	}
	@Override
	public int compareTo(Node other) {
		return Integer.compare(this.weight, other.weight);
	}
}

public class Solution {
	static int T,V,E;
	static Long ans=0L;
	static int[] parent,rank;
	static ArrayList<Node> graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int TC=1;TC <=T;TC++) {
			init(br);
			
			for(int i=0;i<E;i++) {
				if(isSameParent(graph.get(i).from,graph.get(i).to)) continue;
				
				union(graph.get(i).from,graph.get(i).to);
				ans+=graph.get(i).weight;
			}
			sb.append("#").append(TC).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	public static int find(int a) {
		if(parent[a]==a) return a;
		else return parent[a] = find(parent[a]);
	}
	public static void union(int a,int b){
		int AParent = find(a);
		int BParent = find(b);
		
		if(AParent != BParent) {
			if(rank[AParent] > rank[BParent]) {
				parent[BParent]=AParent;
			} else if(rank[AParent] < rank[BParent]) {
				parent[AParent]=BParent;
			} else {
				parent[BParent] = AParent;
				rank[AParent]++;
			}			
		}
	}
    public static boolean isSameParent(int from, int to) {
        return find(from) == find(to);
    }

	public static void init(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		ans=0L;
		graph = new ArrayList<>();
		parent = new int[V+1];
		rank = new int[V+1];
		for(int i=0;i<V+1;i++) {
			parent[i]=i;
			rank[i]=0;
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			graph.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(graph);
	}
}
