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
        this.grid = new int[mapSize][mapSize];  // 배열 크기를 0 기반으로 맞춥니다.
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
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력

        for (int tc = 1; tc <= T; tc++) {
            Map map = new Map();
            FlyCatcher flyCatcher = new FlyCatcher();
            init(map, flyCatcher, br);  // 초기화 메서드 호출
            int flies = flyCatcher.catchFly(map);
            System.out.println("#" + tc + " " + flies);
        }
    }

    private static void init(Map map, FlyCatcher flyCatcher, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int mapSize = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());

        map.setMapSize(mapSize);  // 맵 크기 설정
        flyCatcher.size = size;  // 파리채 크기 설정

        for (int i = 0; i < map.mapSize; i++) {  // 0 기반으로 인덱스 변경
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map.mapSize; j++) {
                map.grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
