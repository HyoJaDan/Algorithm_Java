package swea.algorithm;

import java.util.Scanner;

/**
 * 너비 우선 탐색은 맹목적 탐색방법의 하나로 시작 정점을 방문한 후 시작 정점에 인접한 모든 정점들을 우선 방문하는 방법
 * 문제:0과 1로 이뤄진 지도에서 상하좌우 만으로 이동이 가능할 때, 좌상단(1, 1)에서 우하단(R, C)까지 최단 거리를 구하시오.
 *     이때, 범위 외의 지역은 벽으로 가정하고, 1로 표시된 곳으로만 이동이 가능하다. 
 *     지도의 크기는 R, C로 주어지며, 그 범위는 1 <= R, C <= 50로 한정한다.
 * 입력:
2 // test case 개수
4 5 // R, C
1 1 1 1 0 // MAP,
1 0 1 0 0 // 0: 벽
1 1 0 0 1 // 1: 길
0 1 1 1 1 // 테두리는 벽으로 가정
10 10
1 1 1 1 1 1 0 1 1 1
1 0 0 0 0 1 0 1 0 1
1 0 0 0 0 1 1 1 0 1
1 0 0 1 1 1 0 0 0 1
1 0 0 1 0 1 0 1 1 1
1 0 0 1 0 1 1 1 0 1
1 0 0 1 0 0 1 0 0 1
1 1 0 1 1 1 0 0 0 1
0 1 0 1 0 1 1 1 0 1
0 1 1 1 0 0 1 1 1 1
 * 출력:
#1 7
#2 20  
 * @author SSAFY
 *
 */

class Queue
{
    Point queue[];
    int head;
    int rear;
     
    class Point
    {
        int x;
        int y;
        int c;
         
        public Point(int x, int y, int c)
        {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
     
    public Queue(int capacity)
    {
        queue = new Point[capacity];
        head = rear = 0;
    }
     
    boolean isEmpty()
    {
        return (head <= rear);
    }
     
    boolean enqueue(int x, int y, int c)
    {
        queue[head++] = new Point(x, y, c);
        return true;
    }
     
    Point dequeue()
    {
            if (isEmpty())
            {
                return null;
            }
            rear++;
            return queue[rear-1];
    }
}
 
public class BFS
{
    static final int MAX_N = 50;
    static int[][] MAP;
    static int row;
    static int column;
     
    public static int breadthFirstSearch()
    {
        Queue queue = new Queue(MAX_N * MAX_N);
        queue.enqueue(1, 1, 0);
        MAP[1][1] = 0;
        while(!queue.isEmpty())
        {
            Queue.Point p = queue.dequeue();
            if (p.x == column && p.y == row)
            {
                return p.c;
            }
            if (p.x + 1 <= column && MAP[p.x + 1][p.y] != 0)
            {
                queue.enqueue(p.x + 1, p.y, p.c + 1);
                MAP[p.x + 1][p.y] = 0;
            }
            if (p.y + 1 <= row && MAP[p.x][p.y + 1] != 0) 
            {
                queue.enqueue(p.x, p.y + 1, p.c + 1);
                MAP[p.x][p.y + 1] = 0;
            }
            if (p.x - 1 > 0 && MAP[p.x - 1][p.y] != 0) 
            {
                queue.enqueue(p.x - 1, p.y, p.c + 1);
                MAP[p.x - 1][p.y] = 0;
            }
            if (p.y - 1 > 0 && MAP[p.x][p.y - 1] != 0) 
            {
                queue.enqueue(p.x, p.y - 1, p.c + 1);
                MAP[p.x][p.y - 1] = 0;
            }
        }
        return -1;
    }
     
     
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            row = sc.nextInt();
            column = sc.nextInt();
            MAP = new int[column+1][row+1];
            for (int i = 1; i <= row; i++){
                for (int j = 1; j <= column; j++)
                {
                    MAP[j][i] = sc.nextInt();
                }
            }
            System.out.printf("#%d %d\n", test_case, breadthFirstSearch());
        }
        sc.close();
    }
}