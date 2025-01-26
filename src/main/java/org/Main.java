import java.util.*;
import java.io.*;

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

public class Main{
    static List<Node>[] graph;
    static int n,m,start;
    static int[] result;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        result = new int[n+1];

        for(int i=0;i<m;i++){
            int from,to,value;
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            value = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to,value));
        }
        Arrays.fill(result,INF);

        dijkstra(start);
    }
    public static void dijkstra(int start){
        PriorityQueue<Node> min_heap = new PriorityQueue<>();

    }
}