package main.java.org.BackJoon.Graph.Problem13911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class From implements Comparable<From> {
    int to;
    long value;

    public From(int to, long value) {
        this.to = to;
        this.value = value;
    }

    @Override
    public int compareTo(From other) {
        return Long.compare(this.value, other.value);
    }
}

public class Main {
    private static int E, V;
    private static long[] MacResult;
    private static long[] StarBoxResult;
    private static ArrayList<From>[] Graph;
    private static int numOfMac, numOfStarBox, MacDistance, StarBoxDistance;
    private static List<Integer> isMacNode;
    private static List<Integer> isStarBoxNode;
    private static final long INF = 9876543210L; // 무한??�? ?��???��?�� �?
    private static int MacNode, StarBoxNode;

    private static void dijkstra(int start, long[] nodeDist) {
        PriorityQueue<From> pq = new PriorityQueue<>();
        pq.add(new From(start, 0L));
        nodeDist[start] = 0L;

        while (!pq.isEmpty()) {
            From now = pq.poll();
            long currentDistance = now.value;
            int currentNode = now.to;

            if (nodeDist[currentNode] < currentDistance)
                continue;

            for (From nextNode : Graph[currentNode]) {
                if (nextNode.to == MacNode || nextNode.to == StarBoxNode) // �??��?�� ?��?�� ?��?�� ?��?��
                    continue;

                long newCost = currentDistance + nextNode.value;
                if (newCost < nodeDist[nextNode.to]) {
                    nodeDist[nextNode.to] = newCost;
                    pq.add(new From(nextNode.to, newCost));
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        MacResult = new long[V + 3];
        StarBoxResult = new long[V + 3];
        Graph = new ArrayList[V + 3];
        isMacNode = new ArrayList<>();
        isStarBoxNode = new ArrayList<>();

        Arrays.fill(MacResult, INF);
        Arrays.fill(StarBoxResult, INF);

        for (int i = 1; i <= V + 2; i++) {
            Graph[i] = new ArrayList<>();
        }

        int from, to, value;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            value = Integer.parseInt(st.nextToken());

            Graph[from].add(new From(to, (long) value));
            Graph[to].add(new From(from, (long) value));
        }

        st = new StringTokenizer(br.readLine());
        numOfMac = Integer.parseInt(st.nextToken());
        MacDistance = Integer.parseInt(st.nextToken());
        MacNode = V + 1; // 맥날NODE

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numOfMac; i++) {
            int inputData = Integer.parseInt(st.nextToken());
            isMacNode.add(inputData);
            Graph[MacNode].add(new From(inputData, 0L)); // 맥날NODE?? ?���?
            Graph[inputData].add(new From(MacNode, 0L));
        }

        st = new StringTokenizer(br.readLine());
        numOfStarBox = Integer.parseInt(st.nextToken());
        StarBoxDistance = Integer.parseInt(st.nextToken());
        StarBoxNode = V + 2; // ?��벅NODE

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numOfStarBox; i++) {
            int inputData = Integer.parseInt(st.nextToken());
            isStarBoxNode.add(inputData);
            Graph[StarBoxNode].add(new From(inputData, 0L)); // ?��벅NODE?? ?���?
            Graph[inputData].add(new From(StarBoxNode, 0L));
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        dijkstra(MacNode, MacResult);
        dijkstra(StarBoxNode, StarBoxResult);

        long minDistance = INF;
        for (int i = 1; i <= V; i++) {
            if (isMacNode.contains(i) || isStarBoxNode.contains(i)) // 맥도?��?��?�� ?��??벅스�? ?��치한 ?��?��?�� ?��?��
                continue;

            if (MacResult[i] <= MacDistance && StarBoxResult[i] <= StarBoxDistance) {
                long totalDistance = MacResult[i] + StarBoxResult[i];
                if (totalDistance < minDistance) {
                    minDistance = totalDistance;
                }
            }
        }

        if (minDistance == INF) {
            System.out.println(-1);
        } else {
            System.out.println(minDistance);
        }
    }
}
