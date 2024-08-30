package main.java.org.swea.Problem3289;

import java.io.*;
import java.util.*;

public class Solution {
	private static int[] parents,rank;
	private static int T,N,M;
	private static int[][] inputData;
	
	private static int find(int now) {
		if(parents[now]==now) return now;
		else return parents[now] = find(parents[now]);
	}
	private static void union(int from,int to) {
		int fromParent = find(from);
		int toParent = find(to);
		
		if(fromParent != toParent) {
			if (rank[fromParent] < rank[toParent]) {
				parents[fromParent] = toParent;
            } else if (rank[fromParent] > rank[toParent]) {
            	parents[toParent] = fromParent;
            } else {
            	parents[toParent] = fromParent;
                rank[fromParent]++;
            }
		}
	}
	private static boolean isSameParents(int a,int b) {
		return find(a) == find(b);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc =1; tc<=T; tc++) {
			init(br);
			
			sb.append("#").append(tc).append(" ");
			for(int i=0;i<M;i++) {
				if(inputData[i][0]==0) {
					union(inputData[i][1], inputData[i][2]);
				} else if(inputData[i][0]==1) {
					if(isSameParents(inputData[i][1], inputData[i][2])) sb.append(1);
					else sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static void init(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		rank = new int[N+1];
		for(int i=1;i<=N;i++) parents[i]=i;
		inputData = new int[M+1][3];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				inputData[i][j]=Integer.parseInt(st.nextToken());
			}
		}
	}
}