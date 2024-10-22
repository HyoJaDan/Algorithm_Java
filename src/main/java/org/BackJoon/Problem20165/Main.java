package main.java.org.BackJoon.Problem20165;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, R, result;
    static int[][] map, originalMap;
    static boolean[][] fallen;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        StringBuilder sb = new StringBuilder();

        for (int Round=0; Round <R; Round++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            char dir = st.nextToken().charAt(0);
            attack(x, y, dir);

            st = new StringTokenizer(br.readLine());
            int defX = Integer.parseInt(st.nextToken()) - 1;
            int defY = Integer.parseInt(st.nextToken()) - 1;
            map[defX][defY] = originalMap[defX][defY];
            fallen[defX][defY] = false;
        }

        sb.append(result).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (fallen[i][j]) sb.append("F ");
                else sb.append("S ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void attack(int x, int y, char direction) {
        int dir = 0;
        switch (direction) {
            case 'E': dir = 0; break;
            case 'W': dir = 1; break;
            case 'S': dir = 2; break;
            case 'N': dir = 3; break;
        }
        run(x, y, dir);
    }

    private static void run(int x, int y, int dir) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, map[x][y]});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int xx = current[0];
            int yy = current[1];
            int height = current[2];

            if (fallen[xx][yy]) continue;

            fallen[xx][yy] = true;
            result++;

            int nowX = xx;
            int nowY = yy;
            for (int i = 1; i < height; i++) {
                nowX += dx[dir];
                nowY += dy[dir];

                if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= M) break;
                if (!fallen[nowX][nowY]) {
                    queue.offer(new int[]{nowX, nowY, map[nowX][nowY]});
                }
            }
        }
    }
    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        originalMap = new int[N][M];
        fallen = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                originalMap[i][j] = map[i][j];
            }
        }
    }
}
