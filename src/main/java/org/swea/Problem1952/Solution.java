package main.java.org.swea.Problem1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int[] days;
    private static int T, dayCost, monthCost, threeMonthCost, yearCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int TC = 1; TC <= T; TC++) {
            init(br);
            int result = Math.min(findMinCost(1), yearCost);
            System.out.println("#" + TC + " " + result);
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        dayCost = Integer.parseInt(st.nextToken());
        monthCost = Integer.parseInt(st.nextToken());
        threeMonthCost = Integer.parseInt(st.nextToken());
        yearCost = Integer.parseInt(st.nextToken());

        days = new int[13];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 12; i++) {
            days[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int findMinCost(int month) {
        if (month > 12) return 0;

        // 1일권 혹은 1달권 이용
        int cost = Math.min(days[month] * dayCost, monthCost) + findMinCost(month + 1);

        // 3개월권 이용
        if (month <= 10) {
            cost = Math.min(cost, threeMonthCost + findMinCost(month + 3));
        }

        return cost;
    }
}
