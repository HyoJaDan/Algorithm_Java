package main.java.org.BackJoon.Problem11720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int ans=0;
        for(int i=0;i<s.length();i++){
            ans += s.charAt(i)-'0';
        }
        System.out.println(ans);
    }
}
