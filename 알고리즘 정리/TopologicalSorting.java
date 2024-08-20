package swea.algorithm;

import java.util.Scanner;

/**
 * 위상 정렬(topological sorting)은 유향 그래프의 정점(vertex)을 간선의 방향을 거스르지 않도록 나열하는 것을 의미한다.
 * 
 * 문제: 그래프 N(<=25)개의 정점과 M(<=25)개의 간선이 주어졌을 때, 도착점 D 까지의 위상정렬을 구하시오.
 * 
 * 입력:
3 // 테스트 개수
8 7 // 정점의 수, 간선의 수
1 // 목적지
2 1 // 2번 정점에서 1번 정점으로의 간선
3 1
4 2
6 3
8 3
5 4
7 5
5 6
1
3 1
2 1
3 2
4 2
4 3
5 4
7 7
1
2 1
4 1
6 2
3 2
2 4
5 4
7 5  
 * 출력:
#1 7 5 4 2 8 6 3 1
#2 5 4 3 2 1
#3 7 5 6 3 2 4 1 
 * @author SSAFY
 *
 */

class Queue_ 
{
    int[] queue;
    int front;
    int rear;
    public Queue_(int capacity)
    {
        queue = new int[capacity];
        front = rear = 0;
    }
     
    boolean has_item()
    {
        return rear > front;
    }
     
    int dequeue()
    {
        front++;
        return queue[front-1];
    }
     
    void enqueue(int item)
    {
        queue[rear++] = item;
    }
}
 
class Stack 
{
    int[] stack_set;
    int last;
    public Stack(int capacity)
    {
        stack_set = new int[capacity];
        last = 0;
    }
     
    boolean has_item()
    {
        return last > 0;
    }
     
    int peek()
    {
        return stack_set[last-1];
    }
     
    int pop()
    {
        return stack_set[--last];
    }
     
    void mark_duplicate(int item)
    {
        for (int i = 0; i < last; i++)
        {
            if (stack_set[i] == item)
            {
                stack_set[i] = TopologicalSorting.DUPLICATE;
            }
        }
    }
     
    void push(int item)
    {
        mark_duplicate(item);
        stack_set[last++] = item;
    }
}
 
class Node
{
    int item;
    Node prev;
     
    public Node(int item)
    {
        this.item = item;
    }
     
    void reset()
    {
        prev = null;
    }
     
    void push(Node other)
    {
        if (prev == null)
        {
            prev = other;
            return;
        }
        Node head = this;
        while (head.prev != null)
        {
            head = head.prev;
        }
        head.prev = other;
    }
}
 
public class TopologicalSorting {
    static final int MAX_N = 25;
    static final int MAX_M = 25;
     
    static final int CONNECTED = 1;
    static final int NOT_CONNECTED = 0;
    static final int NOT_UPDATED_YET = 0;
    static final int NOT_VISITED = -1;
    static final int DUPLICATE = -2;
     
    static int[][] map = new int[MAX_N][MAX_N];
    static int[] count = new int[MAX_N];
    static Node[] nodes = new Node[MAX_N];
    static Stack stack;
    static Queue_ queue;
     
    static void reset()
    {
        for (int i = 0; i < MAX_N; i++) 
        {
            for (int j = 0; j < MAX_N; j++) 
            {
                map[i][j] = 0;
            }
        }
         
        for (int i = 0; i < MAX_N; i++)
        {
            count[i] = 0;
        }
         
        stack = new Stack(MAX_N);
        queue = new Queue_(MAX_N);
         
        for (int i = 0; i < MAX_N; i++)
        {
            nodes[i].reset();
        }
    }
     
    static boolean connected(final int src, final int dest)
    {
        return map[src][dest] == CONNECTED;
    }
     
 
    static void traverse(final int idx)
    {
        stack.push(nodes[idx].item);
        Node cur = nodes[idx].prev;
        while (cur != null) 
        {
            traverse(cur.item);
            cur = cur.prev;
        }
    }
     
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int i = 0; i < MAX_N; i++)
        {
            nodes[i] = new Node(i);
        }
         
        for (int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int dest = sc.nextInt();
            reset();
            for (int i = 0; i < m; i++)
            {
                int from = sc.nextInt();
                int to = sc.nextInt();
                map[from-1][to-1] = CONNECTED;
                count[to-1]++;
            }
            for (int i = 0; i < n; i++)
            {
                if (count[i] == 0)
                {
                    queue.enqueue(i);
                }
            }
            while (queue.has_item())
            {
                int src = queue.dequeue();
                for (int i = 0; i < n; i++){
                    if (connected(src, i))
                    {
                        Node node = new Node(src);
                        nodes[i].push(node);
                        count[i]--;
                        if (count[i] == 0)
                        {
                            queue.enqueue(i);
                        }
                    }
                }
            }
            System.out.printf("#%d ", test_case);
            if (nodes[dest-1].prev == null)
            {
                System.out.printf("Not reached");
            } else
            {
                traverse(dest - 1);
                while (stack.has_item())
                {
                    int item = stack.pop();
                    if (item == DUPLICATE)
                    {
                        continue;
                    }
                    System.out.printf("%d ", item + 1);
                }               
            }
            System.out.printf("\n");
        }
        sc.close();
    }
}
