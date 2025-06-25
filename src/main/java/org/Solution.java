package org;

import java.util.*;

class Solution {
    /* 4-방향 이동 벡터 */
    static final int[][] DIR = {
            {-1,0}, {1,0}, {0,-1}, {0,1}
    };

    public int solution(int[][] land) {
        int n = land.length;      // 세로 길이
        int m = land[0].length;   // 가로 길이

        /* ------------------------------ 1단계: 덩어리(connected component) 라벨링 ------------------------------ */
        int[][] comp = new int[n][m];          // 셀 → 덩어리 ID
        List<Integer> size = new ArrayList<>(); size.add(0);      // id=0 dummy, 1번부터 사용
        List<Set<Integer>> touchedCols = new ArrayList<>(); touchedCols.add(Set.of());

        int id = 0;                            // 현재까지 부여한 덩어리 ID
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                /* 아직 라벨링되지 않은 석유 셀 발견 */
                if (land[y][x] == 1 && comp[y][x] == 0) {
                    id++;                      // 새 덩어리 번호
                    int cnt = 0;               // 덩어리 크기(셀 수)
                    Set<Integer> cols = new HashSet<>();   // 이 덩어리가 닿은 열 모음

                    Deque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{y, x});
                    comp[y][x] = id;

                    /* BFS로 덩어리 전체 탐색 */
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int cy = cur[0], cx = cur[1];
                        cnt++;                 // 셀 수 증가
                        cols.add(cx);          // 열 번호 기록

                        for (int[] d : DIR) {
                            int ny = cy + d[0], nx = cx + d[1];
                            if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                            if (land[ny][nx] == 1 && comp[ny][nx] == 0) {
                                comp[ny][nx] = id;
                                q.offer(new int[]{ny, nx});
                            }
                        }
                    }
                    /* 덩어리 정보 저장 */
                    size.add(cnt);          // id → 덩어리 크기
                    touchedCols.add(cols); // id → 닿은 열 집합
                }
            }
        }

        /* ------------------------------ 2단계: 열별 획득 가능 석유량 계산 ------------------------------ */
        int[] colOil = new int[m];          // 각 열에 시추관을 놓았을 때 얻는 석유량
        for (int i = 1; i <= id; i++) {
            int vol = size.get(i);          // 덩어리 크기
            for (int c : touchedCols.get(i)) {
                colOil[c] += vol;           // 해당 열마다 한 번씩만 더하기
            }
        }

        /* ------------------------------ 3단계: 최대값 반환 ------------------------------ */
        return Arrays.stream(colOil).max().orElse(0);
    }

    public static void main(String[] args) {
        /* 예시 1 */
        int[][] land1 = {
                {0,0,0,1,1,1,0,0},
                {0,0,0,0,1,1,0,0},
                {1,1,0,0,0,1,1,0},
                {1,1,1,0,0,0,0,0},
                {1,1,1,0,0,0,1,1}
        };

        /* 예시 2 */
        int[][] land2 = {
                {1,0,1,0,1,1},
                {1,0,1,0,0,0},
                {1,0,1,0,0,1},
                {1,0,0,1,0,0},
                {1,0,0,1,0,1},
                {1,0,0,0,0,0},
                {1,1,1,1,1,1}
        };

        Solution sol = new Solution();   // 방금 작성하신 클래스

        System.out.println(sol.solution(land1)); // → 9
        System.out.println(sol.solution(land2)); // → 16

    }

}
