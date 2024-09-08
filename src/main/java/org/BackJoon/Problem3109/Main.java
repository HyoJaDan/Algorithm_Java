package main.java.org.BackJoon.Problem3109;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int R, C, cnt;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static boolean check;

    public static void dfs(int x, int y) {
        visit[x][y] = true;
        if (y == C - 1) {
            cnt++;
            check = true;
            return;
        }
        for (int i = 0; i < 3; i++) {
            int next_x = x + dx[i];
            int next_y = y + dy[i];
            if (next_x >= 0 && next_x < R && next_y >= 0 && next_y < C) {
                if (map[next_x][next_y] == 1 && !visit[next_x][next_y]) {
                    dfs(next_x, next_y);
                    if (check) return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rc = br.readLine().split(" ");
        R = Integer.parseInt(rc[0]);
        C = Integer.parseInt(rc[1]);

        map = new int[R][C];
        visit = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                if (line.charAt(j) == '.') map[i][j] = 1;
                else map[i][j] = 0;
            }
        }

        for (int i = 0; i < R; i++) {
            check = false;
            dfs(i, 0);
        }

        System.out.println(cnt);
    }
}
