package main.java.org.BackJoon.Problem1152;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();

        if(s.isEmpty()){
            System.out.println(0);
        } else {
            String[] words = s.split("\\s+");
            System.out.println(words.length);
        }
    }
}
