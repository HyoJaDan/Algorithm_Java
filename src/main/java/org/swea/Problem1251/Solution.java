package main.java.org.swea.Problem1251;
/**
 * 2024-08-29 09:04 ~ 09:40
 * @author SSAFY
 *
 */

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int from;
	int to;
	double cost;
	
	public Node(int from, int to, double cost) {
		this.from=from;
		this.to=to;
		this.cost=cost;
	}
	@Override
	public int compareTo(Node other) {
		return Double.compare(this.cost, other.cost);
	}
}
public class Solution {
	static ArrayList<Node> graph;
	static int[] rank, parent,Xaxis, Yaxis;
	static int T,N;
	static double E;
	static Double ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int TC=1;TC<=T;TC++) {
			init(br);
			
			for(Node now : graph) {
				if(isSameparent(now.from, now.to)) continue;
				
				union(now.from, now.to);
				
				ans +=now.cost;
			}
			sb.append("#").append(TC).append(" ").append((Long) Math.round(ans)).append("\n");
		}
		System.out.println(sb);
	}
	public static int find(int a) {
		if(parent[a]==a) return a;
		else return parent[a] = find(parent[a]);
	}
	public static void union(int a,int b) {
		int AParent = find(a);
		int BParent = find(b);
		
		if(AParent != BParent) {
			if(rank[AParent] > rank[BParent]) {
				parent[BParent] = AParent;
			} else if(rank[AParent] < rank[BParent]) {
				parent[AParent] = BParent;
			} else {
				parent[BParent] = AParent;
				rank[AParent]++;
			}
		}
	}
	public static boolean isSameparent(int a,int b) {
		return find(a) == find(b);
	}
	
	public static void init(BufferedReader br) throws IOException{
		ans=(double) 0;
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>(N+1);
		rank = new int[N+1]; parent = new int[N+1]; Xaxis = new int[N+1]; Yaxis = new int[N+1];
		for(int i=0;i<N+1;i++) {
			parent[i]=i;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			Xaxis[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			Yaxis[i] = Integer.parseInt(st.nextToken());
		}
		E = Double.parseDouble(br.readLine());
		
		for(int i=0;i<N;i++) {
			for(int j=i+1;j<N;j++) {
				double distance = getDistance(Xaxis[i],Yaxis[i],Xaxis[j],Yaxis[j]);
				double cost = Math.pow(distance, 2)*E;
				graph.add(new Node(i,j,cost));
			}
		}
		Collections.sort(graph);
	}
	public static double getDistance(int x,int y, int x2,int y2) {
		int nowX = x-x2;
		int nowY = y-y2;
		return Math.sqrt((Math.pow(nowX, 2) + Math.pow(nowY, 2)));
	}
}
