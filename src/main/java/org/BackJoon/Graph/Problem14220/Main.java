package main.java.org.BackJoon.Graph.Problem14220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class From {
    int to;
    int value;
    
    public From(int to, int value) {
        this.to = to;
        this.value = value;
    }
}

public class Main {
    private static int[][] DP;
    private static int N;
    private static ArrayList<From>[] map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[N][N];
        map = new ArrayList[N];
        
        for (int i = 0; i < N; i++) {
            map[i] = new ArrayList<From>();
        }

        int value;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                value = Integer.parseInt(st.nextToken());
                if (value == 0) continue;
                map[i].add(new From(j, value));
            }
        }

        Integer ans = Integer.MAX_VALUE;

        for (int start = 0; start < N; start++) {
            // DP ÃÊ±âÈ­
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    DP[i][j] = Integer.MAX_VALUE;
                }
            }
            DP[0][start] = 0;

            for (int i = 1; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (From edge : map[j]) {
                        if (DP[i-1][edge.to] != Integer.MAX_VALUE) {
                            DP[i][j] = Math.min(DP[i][j], DP[i-1][edge.to] + edge.value);
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                ans = Math.min(ans, DP[N-1][i]);
            }
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(ans);
        }
    }
}
