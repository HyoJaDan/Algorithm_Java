package main.java.org.swea.Problem4012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int N;
    static int[][] synergy;
    static boolean[] selected;
    static int minDifference;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            synergy = new int[N][N];
            selected = new boolean[N];
            minDifference = Integer.MAX_VALUE;

            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // �옱猷뚮�� �몢 洹몃９�쑝濡� �굹�늻湲� �쐞�븳 議고빀 �깮�꽦
            combine(0, 0);
            System.out.printf("#%d %d\n", testCase, minDifference);
        }
    }

    static void combine(int idx, int count) {
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        if (idx >= N) return;

        selected[idx] = true;
        combine(idx + 1, count + 1);
        selected[idx] = false;
        combine(idx + 1, count);
    }

    static void calculateDifference() {
        int sumA = 0;
        int sumB = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selected[i] && selected[j]) {
                    sumA += synergy[i][j];
                } else if (!selected[i] && !selected[j]) {
                    sumB += synergy[i][j];
                }
            }
        }

        int difference = Math.abs(sumA - sumB);
        minDifference = Math.min(minDifference, difference);
    }
}
