package main.java.org.BackJoon.Problem5052;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=T; tc++) {
            int n = Integer.parseInt(br.readLine());

            String[] arr = new String[n];
            Set<String> set = new TreeSet<>();
            boolean flag = true;

            for (int i=0; i<n; i++) {
                String s = br.readLine();
                arr[i] = s;
                set.add(s);
            }

            for (int i=0; i<n; i++) {
                for (int j=0; j<arr[i].length(); j++) {
                    if (set.contains(arr[i].substring(0, j))) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag == true) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}