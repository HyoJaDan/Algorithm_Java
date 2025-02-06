import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int to,cost;
    public Node(int to,int cost){
        this.to=to;
        this.cost=cost;
    }

    @Override
    public int compareTo(Node other){
        return Integer.compare(this.cost, other.cost);
    }
}

public class Main {
    static ArrayList<Node>[] graph;
    static int[] result;
    public static void main(String[] args) throws IOException{
        int n,m,start;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        result = new int[n+1];
        Arrays.fill(result,999999999);

        for(int i=0;i<m;i++){
            int from,to,value;
            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            value = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to,value));
        }
        dijkstra(start);
        for (int i = 1; i <= n; i++) {
            if (result[i] == 999999999) {
                System.out.println("INFINITY");
            } else {
                System.out.println(result[i]);
            }
        }
    }
    public static void dijkstra(int start){
        result[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int currentNode = now.to;
            int currentValue = now.cost;

            if(result[currentNode] < currentValue){
                continue;
            }
            for(Node edge : graph[currentNode]){
                int nextCost = edge.cost + currentValue;
                int nextNode = edge.to;

                if(result[nextNode] > nextCost){
                    result[nextNode] = nextCost;
                    pq.add(new Node(nextNode, nextCost));
                }
            }
        }
    }
}
