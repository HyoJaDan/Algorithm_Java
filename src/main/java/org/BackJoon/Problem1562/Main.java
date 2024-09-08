package main.java.org.BackJoon.Problem1562;

import java.util.Arrays;

public class Main {
    static int n;
    static int[][][] dp = new int[101][10][1024];

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        n = sc.nextInt();
        sc.close();

        for (int[][] arr2d : dp) {
            for (int[] arr1d : arr2d) {
                Arrays.fill(arr1d, -1);
            }
        }

        int cnt = 0;
        for (int i = 1; i < 10; i++) {
            cnt += dfs(i, 1, 1 << i);
            cnt %= 1000000000;
        }

        System.out.println(cnt);
    }

    static int dfs(int now, int deep, int bit) {
        if (deep == n) {
            boolean allExist = true;
            for (int i = 0; i < 10; i++) {
                if ((bit >> i & 1) == 0) {
                    allExist = false;
                    break;
                }
            }
            if (allExist) {
                dp[deep][now][bit] = 1;
                return 1;
            }
            dp[deep][now][bit] = 0;
            return 0;
        }

        if (dp[deep][now][bit] != -1) {
            return dp[deep][now][bit];
        }

        dp[deep][now][bit] = 0;
        if (now + 1 < 10) {
            if (dp[deep + 1][now + 1][bit | 1 << (now + 1)] != -1) {
                dp[deep][now][bit] += dp[deep + 1][now + 1][bit | 1 << (now + 1)];
            } else {
                dp[deep][now][bit] += dfs(now + 1, deep + 1, bit | 1 << (now + 1));
            }
            dp[deep][now][bit] %= 1000000000;
        }
        if (now - 1 >= 0) {
            if (dp[deep + 1][now - 1][bit | 1 << (now - 1)] != -1) {
                dp[deep][now][bit] += dp[deep + 1][now - 1][bit | 1 << (now - 1)];
            } else {
                dp[deep][now][bit] += dfs(now - 1, deep + 1, bit | 1 << (now - 1));
            }
            dp[deep][now][bit] %= 1000000000;
        }

        return dp[deep][now][bit];
    }
}
