package main.java.org.swea.Problem2382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class MicroOrganism {
    int y, x;
    int number;
    int direction; // 상:1, 하:2, 좌:3, 우:4

    public MicroOrganism(int y, int x, int number, int direction) {
        this.y = y;
        this.x = x;
        this.number = number;
        this.direction = direction;
    }

    public void move(int N) {
        switch (this.direction) {
            case 1:
                this.y -= 1;
                break;
            case 2:
                this.y += 1;
                break;
            case 3:
                this.x -= 1;
                break;
            case 4:
                this.x += 1;
                break;
        }
        // 가장자리인지 확인
        if (this.y == 0) {
            this.direction = 2;
            this.number /= 2;
        } else if (this.y == N - 1) {
            this.direction = 1;
            this.number /= 2;
        } else if (this.x == 0) {
            this.direction = 4;
            this.number /= 2;
        } else if (this.x == N - 1) {
            this.direction = 3;
            this.number /= 2;
        }
    }
}

public class Solution {
    private static int T, N, M, K;
    private static List<MicroOrganism> microOrganism ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int TC = 1; TC <= T; TC++) {
            init(br);

            for (int minute = 0; minute < M; minute++) {
                for (MicroOrganism mo : microOrganism) {
                    mo.move(N);
                }

                Map<String, List<MicroOrganism>> map = new HashMap<>();
                
                for (MicroOrganism mo : microOrganism) {
                    if (mo.number == 0) continue;
                    String key = mo.y + "," + mo.x;
                    map.computeIfAbsent(key, k -> new ArrayList<>()).add(mo);
                }

                List<MicroOrganism> newMicroOrganism = new ArrayList<>();
                for (Map.Entry<String, List<MicroOrganism>> entry : map.entrySet()) {
                    List<MicroOrganism> list = entry.getValue();
                    if (list.size() == 1) {
                        newMicroOrganism.add(list.get(0));
                    } else {
                        // 병합 처리
                        int sum = 0;
                        int maxNumber = -1;
                        int direction = 0;
                        for (MicroOrganism mo : list) {
                            sum += mo.number;
                            if (mo.number > maxNumber) {
                                maxNumber = mo.number;
                                direction = mo.direction;
                            }
                        }
                        // y, x는 동일
                        String[] pos = entry.getKey().split(",");
                        int y = Integer.parseInt(pos[0]);
                        int x = Integer.parseInt(pos[1]);
                        newMicroOrganism.add(new MicroOrganism(y, x, sum, direction));
                    }
                }

                microOrganism = newMicroOrganism;
            }

            // 남아있는 미생물 수 합산
            int ans = 0;
            for (MicroOrganism mo : microOrganism) {
                ans += mo.number;
            }

            // 출력 형식에 맞게 출력
            System.out.println("#" + TC + " " + ans);
        }
    }

	private static void init(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

       microOrganism = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            microOrganism.add(new MicroOrganism(y, x, number, direction));
        }
	}
}
