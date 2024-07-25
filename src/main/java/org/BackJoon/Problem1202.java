package org.BackJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Jewelry {
    int weight;
    int price;
    Jewelry(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
}
public class Problem1202 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewelry[] jewelries = new Jewelry[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            jewelries[i] = new Jewelry(weight, price);
        }
        //    if (a.start < b.start) return true; // 제일 먼저 f를 기준으로 오름차순 정렬
        Arrays.sort(jewelries, new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                if (o1.weight == o2.weight) {
                    return o2.price - o1.price;
                }
                return o1.weight - o2.weight;
            }
        });
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0;
        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && jewelries[j].weight <= bags[i]) {
                pq.offer(jewelries[j++].price);
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
