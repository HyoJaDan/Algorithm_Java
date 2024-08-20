package swea.algorithm;

import java.util.Scanner;

/**
 * Network flow란 각각의 간선에 정해진 용량보다 작은 유량이 주어진 방향그래프를 말하며,
 * Maximum flow란 위 수원지(S)에서 수요지(T)까지 공급할 수 있는 최대 유량을 말한다.
 * 
 * 문제:V (2<= V <=10)개의 정점과 E (2<= E <=20)개의 간선이 용량을 갖는 그래프에서 두 정점 사이에 얼마나 많은 유량을 보낼 수 있는지를 계산하여 출력하시오, 각 간선의 유량은 해당 간선의 용량을 초과할 수 없다.
첫 라인은 테스트 케이스 수, 두번째 라인은 수원지, 수요지를 포함한 정점의 수와 그를 연결하는 방향성을 띈 간선의 수가 입력되며, 다음 라인 부터는 간선의 정보가 입력된다.
간선의 정보는 출발지, 도착지, 용량 순이다.
 * 
 * 입력:
2 // 테스트 케이스 수
6 10 // 정점, 간선의 수
0 1 16
0 2 13
1 2 10
1 3 12
2 1 4
2 4 14
3 2 9
3 5 20
4 3 7
4 5 4
6 7
0 1 10
0 3 8
1 2 10
1 4 6
2 5 10
3 2 8
4 5 6 
 * 출력:
#1 23
#2 16 
 * @author SSAFY
 *
 */

class QueueMF 
{
    int[] queueArray;
    int capacity;
    int front;
    int rear;
     
    public QueueMF(int capacity)
    {
        queueArray = new int[capacity];
        this.capacity = capacity;
        front = rear = 0;
    }
     
    void push(int item)
    {
        if ((rear + 1) % capacity == front)
        {
            return;
        }
        queueArray[rear] = item;
        rear = (rear + 1) % capacity;
    }
     
    void pop()
    {
        if (front == rear)
        {
            return;
        }
        front = (front + 1) % capacity;
    }
     
    int getFront()
    {
        return queueArray[front];
    }
     
    boolean isEmpty()
    {
        return (rear == front);
    }
}
 
public class MaximumFlow {
    static final int MAX_V = 10;
    static final int INF = 987654321;
    static int V;
 
    static int networkFlow(int source, int sink, int[][] capacity)
    {
        int[][] flow = new int[MAX_V][MAX_V];
        int[] parent = new int[MAX_V];
        int totalFlow = 0;
        while (true)
        {
            for (int p = 0; p < V; p++)
            {
                parent[p] = -1;
            }
 
            QueueMF q = new QueueMF(MAX_V);
             
            parent[source] = source;
            q.push(source);
 
            while (!q.isEmpty()) 
            {
                int here = q.getFront(); q.pop();
                for (int there = 0; there < V; ++there) 
                {
                    if (capacity[here][there] - flow[here][there] > 0 && parent[there] == -1)
                    {
                        q.push(there);
                        parent[there] = here;
                    }
                }
            }
            if (parent[sink] == -1)
            {
                break;
            }
 
            int amount = INF;
            for (int p = sink; p != source; p = parent[p]) 
            {
                if (capacity[parent[p]][p] - flow[parent[p]][p] > amount) 
                {
                    amount = amount;
                }
                else {
                    amount = capacity[parent[p]][p] - flow[parent[p]][p];
                }
            }
 
            for (int p = sink; p != source; p = parent[p]) 
            {
                flow[parent[p]][p] += amount;
                flow[p][parent[p]] -= amount;
            }
            totalFlow += amount;
        }
        return totalFlow;
    }
     
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int test_case = 1; test_case <= T; test_case++)
        {
            int [][]capacity = new int[MAX_V][MAX_V];
            V = sc.nextInt();
            int E = sc.nextInt();
            for (int i = 0; i < E; i++)
            {
                int here = sc.nextInt();
                int there = sc.nextInt();
                int C = sc.nextInt();
                capacity[here][there] = C;
            }
            int answer = networkFlow(0, V - 1, capacity);
            System.out.printf("#%d %d\n", test_case, answer);
        }
        sc.close();
    }
}
