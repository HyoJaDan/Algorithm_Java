package main.java.org.BackJoon.Problem16236;

import java.io.*;
import java.util.*;

class Point {
    int y, x;
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class PPoint extends Point {
    int level;
    public PPoint(int y, int x, int level) {
        super(y, x);
        this.level = level;
    }
}

public class Main {
    static int direct[][] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int n, currentSize = 2, ans = 0, currentEat = 0;
    static int map[][];
    static Point currentPosition;

    public static void main(String[] args) throws IOException {
        init();
        
        while (true) {
            PPoint target = bfs();  // 먹을 수 있는 물고기 탐색
            if (target == null) break;  // 더 이상 먹을 물고기가 없으면 종료

            // 물고기를 먹고 크기 및 위치 업데이트
            currentEat++;
            if (currentEat == currentSize) {
                currentSize++;
                currentEat = 0;
            }

            // 시간 업데이트 및 상어 위치 갱신
            ans += target.level;
            currentPosition = new Point(target.y, target.x);
            map[target.y][target.x] = 0;  // 먹은 물고기 자리는 빈 칸으로 변경
        }

        System.out.println(ans);  // 최종 시간 출력
    }

    // BFS를 사용하여 먹을 수 있는 물고기 탐색
    public static PPoint bfs() {
        boolean visited[][] = new boolean[n][n];
        visited[currentPosition.y][currentPosition.x] = true;

        Queue<PPoint> q = new LinkedList<>();
        q.add(new PPoint(currentPosition.y, currentPosition.x, 0));

        List<PPoint> fishes = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            PPoint now = q.poll();

            if (now.level > minDistance) break;  // 최소 거리보다 멀리 있는 물고기는 무시

            for (int i = 0; i < 4; i++) {
                int dy = now.y + direct[i][0];
                int dx = now.x + direct[i][1];

                if (dy < 0 || dx < 0 || dy >= n || dx >= n) continue;  // 범위를 벗어나면 패스
                if (visited[dy][dx] || map[dy][dx] > currentSize) continue;  // 이미 방문했거나 큰 물고기면 패스

                visited[dy][dx] = true;
                q.add(new PPoint(dy, dx, now.level + 1));

                if (map[dy][dx] != 0 && map[dy][dx] < currentSize) {
                    fishes.add(new PPoint(dy, dx, now.level + 1));  // 먹을 수 있는 물고기 추가
                    minDistance = now.level + 1;  // 최소 거리 갱신
                }
            }
        }

        if (fishes.isEmpty()) return null;  // 먹을 물고기가 없으면 null 반환

        // 가장 위, 왼쪽에 있는 물고기 선택
        Collections.sort(fishes, (a, b) -> {
            if (a.level == b.level) {
                if (a.y == b.y) return a.x - b.x;
                return a.y - b.y;
            }
            return a.level - b.level;
        });

        return fishes.get(0);  // 가장 조건에 맞는 물고기 반환
    }

    // 초기화 함수
    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    currentPosition = new Point(i, j);  // 아기 상어의 위치 저장
                    map[i][j] = 0;  // 아기 상어가 있던 자리는 빈 칸으로 설정
                }
            }
        }
    }
}
