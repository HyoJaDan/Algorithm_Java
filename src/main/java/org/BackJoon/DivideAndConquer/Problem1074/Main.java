package main.java.org.BackJoon.DivideAndConquer.Problem1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, R, C, ans = 0;

    public static void main(String[] args) throws IOException {
        init();
        DivideAndConquer(N, R, C);
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }

    public static void DivideAndConquer(int n, int r, int c) {
        if (n == 0) {
            System.out.println(ans);
            return;
        }

        int half = 1 << (n - 1); // 2^(n-1)

        if (r < half && c < half) { // 1사분면
            DivideAndConquer(n - 1, r, c);
        } else if (r < half && c >= half) { // 2사분면
            ans += half * half;
            DivideAndConquer(n - 1, r, c - half);
        } else if (r >= half && c < half) { // 3사분면
            ans += 2 * half * half;
            DivideAndConquer(n - 1, r - half, c);
        } else { // 4사분면
            ans += 3 * half * half;
            DivideAndConquer(n - 1, r - half, c - half);
        }
    }
}
