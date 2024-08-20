package swea.algorithm;

import java.util.Scanner;

/**
 * 그래프의 최대 이분 매칭은 두 간선이 같은 정점을 공유하지 않는 간선의 최대 집합을 말한다.
 * 
 * 문제: A그룹과 B그룹이 있다. 각각의 그룹에는 1부터 시작하는 n명의 멤버들이 있으며, 서로 다른 그룹의 멤버들을 연결하는 선이 존재한다. 
 *      이 때, 서로 다른 그룹의 멤버끼리 2명씩 짝을 지을 때, 가능한 최대 짝의 수를 출력하시오.
 * 
 * 입력:
2 // test case 수
3 // A그룹 멤버 수
2 // B그룹 멤버 수
3 // 연결 선의 수
1 1 // A그룹1과 B그룹1 연결
2 2 // A그룹2와 B그룹2 연결
3 2 // A그룹3과 B그룹2 연결
4
5
8
1 1
1 2
1 3
2 2
2 3
2 4
3 3
4 5 
 * 출력:
#1 2 // 가능한 최대 짝의 수
#2 4
 * @author SSAFY
 *
 */
public class BipartiteMatch {
    static final int MAX = 1000;
    
    static int countA;
    static int countB;
    static int[] matchA = new int[MAX];
    static int[] matchB = new int[MAX];
    static int[][] adj = new int[MAX][MAX];
    static boolean[] visited = new boolean[MAX];
     
    static boolean dfs(int a)
    {
        int b;
 
        if (visited[a])
        {
            return false;
        }
 
        visited[a] = true;
 
        for (b = 0; b < countB; ++b)
        {
            if (adj[a][b] != 0 && (matchB[b] == -1 || dfs(matchB[b])))
            {
                matchA[a] = b;
                matchB[b] = a;
                return true;
            }
        }
 
        return false;
    }
 
    static int bipartiteMatch()
    {
        int size = 0;
        for (int start = 0; start < countA; ++start)
        {
            for (int i = 0; i < countA; i++)
            {
                visited[i] = false;
            }
            if (dfs(start))
            {
                size++;
            }
        }
        return size;
    }
 
    static void initialize() 
    {
        for (int i = 0; i < countA; i++)
        {
            matchA[i] = -1;
            for (int j = 0; j < countB; j++)
            {
                adj[i][j] = 0;
            }
        }
 
        for (int i = 0; i < countB; i++)
        {
            matchB[i] = -1;
        }
    }
     
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            countA = sc.nextInt();
            countB = sc.nextInt();
            initialize();
            int adjCount = sc.nextInt();
             
            for (int i = 0; i < adjCount; i++)
            {
                int a = sc.nextInt();
                int b = sc.nextInt();
                adj[a-1][b-1] = 1;
            }
            System.out.printf("#%d %d\n", test_case, bipartiteMatch());
        }
        sc.close();
    }
}
