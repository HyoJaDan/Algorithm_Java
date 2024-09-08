package main.java.org.swea.Problem1949;

import java.io.*;
import java.util.*;

class Point {
    int y, x;
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int t, n, k, ans;
    static ArrayList<Point> startPoint;
    static int[][] map, direct = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            init(br);

            ans = 0;
            for (Point start : startPoint) {
                visited = new boolean[n][n];
                dfs(start.y, start.x, map[start.y][start.x], false, 1);
            }

            System.out.println("#" + tc + " " + ans);
        }
    }

    public static void dfs(int y, int x, int height, boolean isUsedK, int length) {
        ans = Math.max(ans, length);
        visited[y][x] = true;

        for(int i=0;i<4;i++) {
            int ny = y + direct[i][0];
            int nx = x + direct[i][1];

            if (ny >= 0 && nx >= 0 && ny < n && nx < n && !visited[ny][nx]) {
                if (map[ny][nx] < height) {
                    dfs(ny, nx, map[ny][nx], isUsedK, length + 1);
                } else if (!isUsedK && map[ny][nx] - k < height) {
                    int originalHeight = map[ny][nx];
                    map[ny][nx] = height - 1;
                    dfs(ny, nx, map[ny][nx], true, length + 1);
                    map[ny][nx] = originalHeight;
                }
            }
        }

        visited[y][x] = false; 
    }

    public static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        int maxLevel = 0;
        startPoint = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > maxLevel) {
                    maxLevel = map[i][j];
                    startPoint.clear(); // 더 높은 봉우리가 나오면 초기화
                }
                if (map[i][j] == maxLevel) {
                    startPoint.add(new Point(i, j)); // 최대 봉우리 저장
                }
            }
        }
    }
}
