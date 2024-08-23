package main.java.org.swea.Problem4008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int maxResult, minResult;
    static int[] numbers;
    static int[] operators;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            numbers = new int[N];
            operators = new int[4];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                operators[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            maxResult = Integer.MIN_VALUE;
            minResult = Integer.MAX_VALUE;

            dfs(1, numbers[0]);

            int result = maxResult - minResult;
            System.out.println("#" + testCase + " " + result);
        }
    }

    static void dfs(int index, int currentResult) {
        if (index == N) {
            maxResult = Math.max(maxResult, currentResult);
            minResult = Math.min(minResult, currentResult);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;

                switch (i) {
                    case 0:
                        dfs(index + 1, currentResult + numbers[index]);
                        break;
                    case 1:
                        dfs(index + 1, currentResult - numbers[index]);
                        break;
                    case 2:
                        dfs(index + 1, currentResult * numbers[index]);
                        break;
                    case 3:
                        dfs(index + 1, currentResult / numbers[index]);
                        break;
                }

                operators[i]++;
            }
        }
    }
}
