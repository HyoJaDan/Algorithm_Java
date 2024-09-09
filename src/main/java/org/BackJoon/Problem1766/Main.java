package main.java.org.BackJoon.Problem1766;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int from;
    int to;
    int weight;
    public Node(int from, int to,int weight){
        this.from = from;
        this.to=to;
        this.weight=weight;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.weight,other.weight);
    }
}
public class Main {
    private static int N, M;
    private static int[] parent, rank;
    private static ArrayList<Node>graph;

    public static int find(int a){
        if(parent[a] ==a) return a;
        else return parent[a] = find(parent[a]);
    }
    public static void union(int a,int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parentA != parentB){
            if(rank[parentA] > rank[parentB]){
                parent[parentB] = parentA;
            } else if(rank[parentB] > rank[parentA]){
                parent[parentA] = parentB;
            } else{
                parent[parentB] = parentA;
            }
        }
    }
    public static boolean isSameParent(int a, int b){
        return find(a) == find(b);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while((line = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            parent = new int[N+1];
            rank = new int[N+1];
            for(int i=0;i<N;i++){
                parent[i]=i;
            }
            if(N==0 && M==0) break;
            int maxWeight=0;
            graph = new ArrayList<>();
            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                graph.add(new Node(Integer.parseInt(st.nextToken()),
                                Integer.parseInt(st.nextToken()),
                                Integer.parseInt(st.nextToken())));
                maxWeight+=graph.get(graph.size()-1).weight;
            }
            Collections.sort(graph);

            int ans=0;
            for(Node now : graph){
                if(isSameParent(now.from,now.to)) continue;

                union(now.from,now.to);
                ans +=now.weight;
            }
            System.out.println(maxWeight-ans);
        }
    }

 }
