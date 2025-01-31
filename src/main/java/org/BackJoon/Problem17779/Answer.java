package org.BackJoon.Problem17779;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Answer {
    static int n;
    static int[][] map;
    static int sum = 0;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum += map[i][j];
            }
        }

        // d1: 왼쪽 아래 방향(↙)으로 내려가는 길이.
        // d2: 오른쪽 아래 방향(↘)으로 내려가는 길이.
        for (int topY = 0; topY < n; topY++) {
            for (int topX = 0; topX < n; topX++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d2 < n; d2++) {
                        // 최하단 꼭짓점(bottom)의 Y 좌표는 topY + d1 + d2
                        if (topY + d1 + d2 >= n) continue;
                        // 좌우 방향(X축)으로 경계를 벗어나는 경우를 방지\
                        if (topX - d1 < 0 || topX + d2 >= n) continue;

                        divideArea(topY, topX, d1, d2);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static void divideArea(int topY, int topX, int d1, int d2) {
        boolean[][] borders = new boolean[n][n];

        // 경계선 설정
        for (int i = 0; i <= d1; i++) {
            borders[topY + i][topX - i] = true;
            borders[topY + d2 + i][topX + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            borders[topY + i][topX + i] = true;
            borders[topY + d1 + i][topX - d1 + i] = true;
        }

        int[] areaSum = new int[6];

        // 1구역
        for (int i = 0; i < topY + d1; i++) {
            for (int j = 0; j <= topX; j++) {
                if (borders[i][j]) break;
                areaSum[1] += map[i][j];
            }
        }

        // 2구역
        for (int i = 0; i <= topY + d2; i++) {
            for (int j = n - 1; j > topX; j--) {
                if (borders[i][j]) break;
                areaSum[2] += map[i][j];
            }
        }

        // 3구역
        for (int i = topY + d1; i < n; i++) {
            for (int j = 0; j < topX - d1 + d2; j++) {
                if (borders[i][j]) break;
                areaSum[3] += map[i][j];
            }
        }

        // 4구역
        for (int i = topY + d2 + 1; i < n; i++) {
            for (int j = n - 1; j >= topX - d1 + d2; j--) {
                if (borders[i][j]) break;
                areaSum[4] += map[i][j];
            }
        }

        // 5구역 (전체 인구에서 다른 지역 합을 빼서 계산)
        areaSum[5] = sum;
        for (int i = 1; i < 5; i++) {
            areaSum[5] -= areaSum[i];
        }

        Arrays.sort(areaSum);
        ans = Math.min(ans, areaSum[5] - areaSum[1]);
    }
}
