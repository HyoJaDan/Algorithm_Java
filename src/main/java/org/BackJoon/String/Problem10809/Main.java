package org.BackJoon.String.Problem10809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        for(int i=0;i<26;i++){
            if(int n = s.contains("a"+i)) {
                System.out.printf(n+" ");
            } else {
                System.out.printf("-1 ");
            }
        }
    }
}
