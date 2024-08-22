package main.java.org.swea.Problem6782;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
    public static void main(String[] args) throws Exception {
        int T, cnt;
        long N, temp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; ++t) {
            cnt = 0;
            N = Long.parseLong(br.readLine());
            while (true) {
                if (N == 2)
                    break;
                else if (N == 1) // N�씠 1�씠硫� 1 利앷� �썑 醫낅즺
                {
                    cnt += 1;
                    break;
                } else {
                    temp = (long) (Math.sqrt(N));
                    if (temp * temp == N) {
                        ++cnt;
                        N = temp;
                    } else {
//                        cnt += ((temp + 1) * (temp + 1) - N);
//                        ++cnt;
//                        N = temp + 1;
                        cnt++;
                        N++;
                    }
                }
            }
            System.out.println("#" + t + " " + cnt);
        }
    }
}