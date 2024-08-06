package main.java.org.BackJoon.Graph.Problem14220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class From {
    private final int to;
    private final int distance;

    public From(int to, int distance) {
        this.to = to;
        this.distance = distance;
    }

    public int getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }
}

class CityMap {
    private final int size;
    private final List<From>[] map;

    @SuppressWarnings("unchecked")
    public CityMap(int size) {
        this.size = size;
        map = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            map[i] = new ArrayList<>();
        }
    }

    public void addRoute(int from, int to, int distance) {
        if (distance > 0) {
            map[from].add(new From(to, distance));
        }
    }

    public List<From> getRoutesFrom(int city) {
        return map[city];
    }

    public int getSize() {
        return size;
    }
}

class PostalService {
    private final CityMap cityMap;
    private final int[][] dp;

    public PostalService(CityMap cityMap) {
        this.cityMap = cityMap;
        this.dp = new int[cityMap.getSize()][cityMap.getSize()];
    }

    public int findMinimumDistance() {
        int n = cityMap.getSize();

        // DP 초기화 및 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = 0;
                } else {
                    int minValue = Integer.MAX_VALUE;
                    for (From route : cityMap.getRoutesFrom(j)) {
                        int currentDistance = dp[i - 1][route.getTo()];
                        if (currentDistance == 0 && i != 1) {
                            continue;
                        }
                        int temp = currentDistance + route.getDistance();
                        if (temp < minValue) {
                            minValue = temp;
                        }
                    }
                    dp[i][j] = (minValue == Integer.MAX_VALUE) ? 0 : minValue;
                }
            }
        }

        // 최소 거리 찾기
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[n - 1][i] < ans && dp[n - 1][i] != 0) {
                ans = dp[n - 1][i];
            }
        }

        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}

public class Refector {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println("0");
            return;
        }

        CityMap cityMap = new CityMap(n);

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(st.nextToken());
                cityMap.addRoute(i, j, value);
            }
        }

        PostalService postalService = new PostalService(cityMap);
        int result = postalService.findMinimumDistance();
        System.out.println(result);
    }
}
