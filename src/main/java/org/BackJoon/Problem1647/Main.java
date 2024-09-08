package main.java.org.BackJoon.Problem1647;

import java.applet.AudioClip;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost){
        this.from=from;
        this.to=to;
        this.cost=cost;
    }
    @Override
    public int compareTo(Edge other){
        return Integer.compare(this.cost, other.cost);
    }
}
public class Main {
    static int V,E;
    static ArrayList<Edge> edges;
    static ArrayList<Integer> ansList = new ArrayList<>();
    static int[] rank,parent;
    public static void main(String[] args) throws IOException {
        init();

        for(Edge edge : edges){
            if( !isSameParent(edge.from, edge.to) ){
                union(edge.from,edge.to);
                ansList.add(edge.cost);
            }
        }
        int ans=0;
        Collections.sort(ansList);
        for(int i=0;i<ansList.size()-1;i++){
            ans+=ansList.get(i);
        }
        System.out.println(ans);
    }

    private static void union(int from, int to) {
        int fromParent = find(from);
        int toParent = find(to);

        if(fromParent != toParent){
            if(rank[fromParent] > rank[toParent]){
                parent[fromParent] = toParent;
            } else if(rank[fromParent] < rank[toParent]){
                parent[toParent] = fromParent;
            } else {
                parent[fromParent] = toParent;
                rank[fromParent]++;
            }
        }
    }

    private static boolean isSameParent(int from, int to) {
        return find(from) == find(to);
    }

    private static int find(int now) {
        if(parent[now] == now) return now;
        else return parent[now] = find(parent[now]);
    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        rank = new int[V+1];
        parent = new int[V+1];
        edges = new ArrayList<>(E+1);
        for(int i=0;i<V;i++){
            rank[i]=0;
            parent[i]=i;
        }


        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        Collections.sort(edges);
    }
}
