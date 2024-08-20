package swea.algorithm;

import java.util.Scanner;

/**
 * 깊이 우선 탐색은 맹목적 탐색방법의 하나로 한 노드를 시작으로 인접한 다른 노드를 재귀적으로 탐색해가고 
 * 끝까지 탐색하면 다시 위로 와서 다음을 탐색하여 검색한다.
 * 문제 : 정점의 최대 개수는 30개이다. 간선의 길이를 1이라고 하고, N개의 정점과 M개의 간선이 주어졌을 때, 
 *       DFS를 이용하여 시작점으로부터 도착점까지의 최단 경로를 찾아 거리를 출력하시오. 
 *       시작점에서 도착점으로 갈 수 있는 방법이 없는 경우 -1을 출력하시오.
 * 입력
2 // test case 개수
8 10 1 8// 정점의 수, 간선의 수, 시작점, 도착점
1 2 // 정점 간 연결 관계. 1에서 2로 이동 가능
1 3
2 7
2 4
3 5
3 6
4 7
5 7
6 7
6 8
8 10 2 8
1 2
1 3
2 7
2 4
3 5
3 6
4 7
5 7
6 7
6 8
 * 출력
#1 3 // 최단경로의 길이
#2 -1 
 * @author SSAFY
 *
 */
public class DFS {
    static final int MAX_VERTEX = 30;
    static int[][] map = new int[MAX_VERTEX][MAX_VERTEX];
    static boolean[] visit = new boolean[MAX_VERTEX];
    static int vertex;
    static int edge;
    static int maxEdge;
    static int start;
    static int end;
     
    public static void depthFirstSearch(int v, int depth)
    {
        if (v == end)
        {
            if (maxEdge < 0 || depth < maxEdge)
            {
                maxEdge = depth;
            }
            return;
        }
        visit[v] = true;
        for (int i = 1; i <= vertex; i++) 
        {
            if (map[v][i] == 1 && !visit[i]) 
            {
                depthFirstSearch(i, depth + 1);
                visit[i] = false;
            }
        }       
    }
     
     
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            vertex = sc.nextInt();
            edge = sc.nextInt();
            start = sc.nextInt();
            end = sc.nextInt();
            for (int i = 0; i < edge; i++)
            {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                map[v1][v2] = 1;
            }
            maxEdge = -1;
            depthFirstSearch(start, 0);
            System.out.printf("#%d %d\n", test_case, maxEdge);
        }
        sc.close();
    }
}
