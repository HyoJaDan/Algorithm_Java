package main.java.org.BackJoon.Problem1277;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge {
    int to;
    double weight;

    public Edge(int to, double weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, W;
    static double M;
    static Point[] map; // i Î≤àÏß∏ ?Ö∏?ìú?ùò Ï¢åÌëú
    static boolean[][] isConnected; // ?ó∞Í≤? ?ó¨Î∂? ???û•
    static ArrayList<Edge>[] graph; // graph[?ãú?ûë] = {?èÑÏ∞?, Í∞?Ï§ëÏπò}
    static final double INF = Double.MAX_VALUE;
    static double[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());
        
        map = new Point[N];
        isConnected = new boolean[N][N];
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[i] = new Point(x, y);
        }

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            isConnected[x][y] = isConnected[y][x] = true;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (isConnected[i][j]) {
                    graph[i].add(new Edge(j, 0));
                } else {
                    double distance = getDistance(i, j);
                    if (distance <= M) {
                        graph[i].add(new Edge(j, distance));
                    }
                }
            }
        }

        result = new double[N];
        Arrays.fill(result, INF);
        
        dijkstra(0);
        
        int output = (int) (1000 * result[N - 1]);
        System.out.println(output);
    }

    public static double getDistance(int i, int j) {
        long dx = map[j].x - map[i].x;
        long dy = map[j].y - map[i].y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.weight));
        result[start] = 0;
        minHeap.add(new Edge(start, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int currentNode = current.to;
            double currentDistance = current.weight;

            if (result[currentNode] < currentDistance) {
                continue;
            }

            for (Edge next : graph[currentNode]) {
                double newCost = currentDistance + next.weight;
                double pastCost = result[next.to];
                
                if (newCost < pastCost) {
                    result[next.to] = newCost;
                    minHeap.add(new Edge(next.to, newCost));
                }
            }
        }
    }
}
