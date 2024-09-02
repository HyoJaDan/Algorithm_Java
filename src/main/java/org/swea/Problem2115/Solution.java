package main.java.org.swea.Problem2115;

import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, C;
    static int[][] map;
    static int[][] maxProfit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            maxProfit = new int[N][N - M + 1]; 

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    maxProfit[i][j] = getMaxProfit(i, j);
                }
            }

            int answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    for (int k = i; k < N; k++) {
                        for (int l = 0; l <= N - M; l++) {
                            if (i == k && Math.abs(j - l) < M) continue;
                            answer = Math.max(answer, maxProfit[i][j] + maxProfit[k][l]);
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    private static int getMaxProfit(int row, int start) {
        int[] honey = Arrays.copyOfRange(map[row], start, start + M);
        int maxProfit = 0;
        for (int i = 1; i < (1 << M); i++) {
            int sum = 0, profit = 0;
            for (int j = 0; j < M; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += honey[j];
                    profit += honey[j] * honey[j];
                }
            }
            if (sum <= C) {
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }
}
