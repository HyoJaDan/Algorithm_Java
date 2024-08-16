package org.BackJoon.MST.Problem1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//class Edge implements Comparable<Edge> {
//    int cost;
//    int from;
//    int to;
//
//    public Edge(int cost, int from, int to) {
//        this.cost = cost;
//        this.from = from;
//        this.to = to;
//    }
//
//    @Override
//    public int compareTo(Edge other) {
//        return Integer.compare(this.cost, other.cost);
//    }
//}

public class 최적화 {
    static int V, E;
    static ArrayList<Edge> edge;
    static int[] parent, rank;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        init();

        for (Edge edge : edge) {
            if (!isSameParent(edge.from, edge.to)) {
                union(edge.from, edge.to);
                ans += edge.cost;
            }
        }
        System.out.println(ans);
    }

    private static int find(int now) {
        if (parent[now] == now) return now;
        else return parent[now] = find(parent[now]); // 경로 압축
    }

    private static void union(int from, int to) {
        int fromParent = find(from);
        int toParent = find(to);

        if (fromParent != toParent) {
            if (rank[fromParent] < rank[toParent]) {
                parent[fromParent] = toParent;
            } else if (rank[fromParent] > rank[toParent]) {
                parent[toParent] = fromParent;
            } else {
                parent[toParent] = fromParent;
                rank[fromParent]++;
            }
        }
    }

    private static boolean isSameParent(int from, int to) {
        return find(from) == find(to);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edge = new ArrayList<>();
        parent = new int[V + 1];
        rank = new int[V + 1]; // 랭크 초기화
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for (int i = 0; i < E; i++) {
            int from, to, value;
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            value = Integer.parseInt(st.nextToken());

            edge.add(new Edge(value, from, to));
        }
        Collections.sort(edge);
    }
}
