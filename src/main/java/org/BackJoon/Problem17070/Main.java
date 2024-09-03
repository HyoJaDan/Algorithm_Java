package main.java.org.BackJoon.Problem17070;

import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int read() throws Exception{
        if(st==null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }


public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = read();
        int[][] map = new int[n+1][n+1];
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                map[i][j] = read();
            }
        }

        int[][][] dp = new int[n+1][n+1][3]; //0 가로, 1 대각선, 2세로
        dp[1][2][0] = 1;

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(map[i][j] == 1) continue;
                if(j > 1) {
                    dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][1];
                }

                if(i>1) {
                    dp[i][j][2] += dp[i-1][j][2] + dp[i-1][j][1];
                }

                if(i>1 && j>1) {
                    if(map[i-1][j] == 0 && map[i][j-1] == 0) {
                        dp[i][j][1] += dp[i-1][j-1][1] + dp[i-1][j-1][0] + dp[i-1][j-1][2];
                    }
                }
            }
        }
        System.out.println(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
    }
}