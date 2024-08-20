package swea.algorithm;

import java.util.Scanner;

/**
 * Plane sweeping은 여러 개의 직사각형이 주어졌을 때, 총 넓이를 구하는 알고리즘이다.
 * 
 * 문제: 테스트케이스 수 T와 지도의 수 N(1 ≤ N ≤ 10,000)이 주어진다. 다음 N개의 줄에는 각 지도의 정보가 주어진다. 
 * 지도의 정보는 네 정수 x1, y1, x2, y2 (0 ≤ x1 < x2 ≤ 30,000, 0 ≤ y1 < y2 ≤ 30,000)으로 이루어져 있다. 
 * (x1, y1)와 (x2, y2)은 직사각형의 왼쪽 아래 좌표와 오른쪽 위 좌표이다. 모든 지도는 직사각형이고, 
 * 변은 항상 x축 또는 y축에 평행하다. 첫째 줄에 지도를 모두 합쳤을 때, 그 면적을 출력한다. (직사각형을 모두 합쳤을 때 면적)
 * 
 * 입력:
1 // T, # of testcases
2 // N, # of given maps
10 10 20 20
15 15 25 30
 * 출력:
#1 225  
 * @author SSAFY
 *
 */
class Rec
{
    int x;
    int y1;
    int y2;
    int end;
    public Rec(int x, int y1, int y2, int end)
    {
        this.x = x;
        this.y1 = y1;
        this.y2 = y2;
        this.end = end;
    }
    public boolean greater_than(Rec b)
    {
        return this.x != b.x ? this.x > b.x : false;
    }
     
    public static void quick_sort(Rec[] a, int l, int r)
    {
        if (l < r) 
        {
            int j = partition(a, l, r);
            quick_sort(a, l, j - 1);
            quick_sort(a, j + 1, r);
        }
    }
 
    private static int partition(Rec[] a, int l, int r)
    {
        Rec pivot, t;
        int i, j;
        pivot = a[l];
        i = l;
        j = r + 1;
 
        while (true) {
            do{
                ++i;
            } while (!a[i].greater_than(pivot) && i <= r);
 
            do{
                --j;
            } while (a[j].greater_than(pivot));
 
            if (i >= j)
            {
                break;
            }
            t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }   
}
public class Planesweeping {
    static final int MAX_N = 10000;
    static int N;
    static Rec[] v = new Rec[MAX_N * 2];
     
    static int[] tree = new int[65538];
    static int[] cnt = new int[65538];
     
    public static void update(int x, int left, int right, int nodeLeft, int nodeRight, int val)
    {
        if (left > nodeRight || right < nodeLeft)
        {
            return;
        }
        if (left <= nodeLeft && right >= nodeRight)
        {
            cnt[x] += val;
        }
        else
        {
            int mid = (nodeLeft + nodeRight) >> 1;
            update(x * 2, left, right, nodeLeft, mid, val);
            update(x * 2 + 1, left, right, mid + 1, nodeRight, val);
        }
        tree[x] = 0;
        if (cnt[x] > 0)
        {
            tree[x] = nodeRight - nodeLeft + 1;
        }
        if (cnt[x] == 0 && nodeLeft < nodeRight)
        {
            tree[x] = tree[x * 2] + tree[x * 2 + 1];
        }
    }
     
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
             
            int idx = 0, px, ans;
            for (int i = 0; i < N; i++) 
            {
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();
                v[idx++] = new Rec(x1,y1,y2,1);
                v[idx++] = new Rec(x2,y1,y2,-1);
            }
            Rec.quick_sort(v, 0, idx - 1);
            px = v[0].x;
            ans = 0;
            for (int i = 0; i < idx; i++) 
            {
                ans += (v[i].x - px) * tree[1];
                update(1, v[i].y1, v[i].y2-1, 0, 32768, v[i].end);
                px = v[i].x;
            }
            System.out.printf("#%d %d\n", test_case, ans);
        }
        sc.close();
    }

}
