package main.java.org.BackJoon.DP.LCS;

import java.io.*;
import java.util.*;

public class Main {
    static String s1, s2;
    static int map[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s2 = br.readLine();
        s1 = br.readLine();
        map = new int[s1.length()+1][s2.length()+1];

        for(int i=1;i<s1.length()+1;i++){
            for(int j=1;j<s2.length()+1;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    map[i][j] = map[i-1][j-1]+1;
                } else {
                    map[i][j] = Integer.max(map[i-1][j], map[i][j-1]);
                }
            }
        }
        System.out.println(map[s1.length()][s2.length()]);

        int x = s1.length();
        int y = s2.length();
        StringBuilder ans = new StringBuilder();

        while (x > 0 && y > 0) {
            if (s1.charAt(x - 1) == s2.charAt(y - 1)) {
                ans.append(s1.charAt(x - 1));
                x--;
                y--;
            } else if (map[x - 1][y] > map[x][y - 1]) {
                x--;
            } else {
                y--;
            }
        }

        System.out.println(ans.reverse().toString()); // LCS 臾몄옄�뿴 異쒕젰
    }
}
