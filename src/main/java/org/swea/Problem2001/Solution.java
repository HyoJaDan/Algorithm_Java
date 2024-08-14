package main.java.org.swea.Problem2001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Map {
    int[][] grid;
    int mapSize;

    public void setMapSize(int size) {
        this.mapSize = size;
        this.grid = new int[mapSize][mapSize];  // �迭 ũ�⸦ 0 ������� ����ϴ�.
    }
}

class FlyCatcher {
    int size;

    public int catchFly(Map map) {
        int maxFlies = Integer.MIN_VALUE;

        for (int i = 0; i <= map.mapSize - this.size; i++) {
            for (int j = 0; j <= map.mapSize - this.size; j++) {
                int now = 0;
                for (int y = i; y < i + size; y++) {
                    for (int x = j; x < j + size; x++) {
                        now += map.grid[y][x];
                    }
                }
                maxFlies = Math.max(maxFlies, now);
            }
        }
        return maxFlies;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // �׽�Ʈ ���̽� �� �Է�

        for (int tc = 1; tc <= T; tc++) {
            Map map = new Map();
            FlyCatcher flyCatcher = new FlyCatcher();
            init(map, flyCatcher, br);  // �ʱ�ȭ �޼��� ȣ��
            int flies = flyCatcher.catchFly(map);
            System.out.println("#" + tc + " " + flies);
        }
    }

    private static void init(Map map, FlyCatcher flyCatcher, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int mapSize = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());

        map.setMapSize(mapSize);  // �� ũ�� ����
        flyCatcher.size = size;  // �ĸ�ä ũ�� ����

        for (int i = 0; i < map.mapSize; i++) {  // 0 ������� �ε��� ����
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map.mapSize; j++) {
                map.grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
