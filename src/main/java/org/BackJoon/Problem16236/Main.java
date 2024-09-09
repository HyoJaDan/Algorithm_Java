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
            PPoint target = bfs();  // ���� �� �ִ� ����� Ž��
            if (target == null) break;  // �� �̻� ���� ����Ⱑ ������ ����

            // ����⸦ �԰� ũ�� �� ��ġ ������Ʈ
            currentEat++;
            if (currentEat == currentSize) {
                currentSize++;
                currentEat = 0;
            }

            // �ð� ������Ʈ �� ��� ��ġ ����
            ans += target.level;
            currentPosition = new Point(target.y, target.x);
            map[target.y][target.x] = 0;  // ���� ����� �ڸ��� �� ĭ���� ����
        }

        System.out.println(ans);  // ���� �ð� ���
    }

    // BFS�� ����Ͽ� ���� �� �ִ� ����� Ž��
    public static PPoint bfs() {
        boolean visited[][] = new boolean[n][n];
        visited[currentPosition.y][currentPosition.x] = true;

        Queue<PPoint> q = new LinkedList<>();
        q.add(new PPoint(currentPosition.y, currentPosition.x, 0));

        List<PPoint> fishes = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            PPoint now = q.poll();

            if (now.level > minDistance) break;  // �ּ� �Ÿ����� �ָ� �ִ� ������ ����

            for (int i = 0; i < 4; i++) {
                int dy = now.y + direct[i][0];
                int dx = now.x + direct[i][1];

                if (dy < 0 || dx < 0 || dy >= n || dx >= n) continue;  // ������ ����� �н�
                if (visited[dy][dx] || map[dy][dx] > currentSize) continue;  // �̹� �湮�߰ų� ū ������ �н�

                visited[dy][dx] = true;
                q.add(new PPoint(dy, dx, now.level + 1));

                if (map[dy][dx] != 0 && map[dy][dx] < currentSize) {
                    fishes.add(new PPoint(dy, dx, now.level + 1));  // ���� �� �ִ� ����� �߰�
                    minDistance = now.level + 1;  // �ּ� �Ÿ� ����
                }
            }
        }

        if (fishes.isEmpty()) return null;  // ���� ����Ⱑ ������ null ��ȯ

        // ���� ��, ���ʿ� �ִ� ����� ����
        Collections.sort(fishes, (a, b) -> {
            if (a.level == b.level) {
                if (a.y == b.y) return a.x - b.x;
                return a.y - b.y;
            }
            return a.level - b.level;
        });

        return fishes.get(0);  // ���� ���ǿ� �´� ����� ��ȯ
    }

    // �ʱ�ȭ �Լ�
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
                    currentPosition = new Point(i, j);  // �Ʊ� ����� ��ġ ����
                    map[i][j] = 0;  // �Ʊ� �� �ִ� �ڸ��� �� ĭ���� ����
                }
            }
        }
    }
}
