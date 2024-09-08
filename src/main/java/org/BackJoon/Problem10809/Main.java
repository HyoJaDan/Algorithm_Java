package main.java.org.BackJoon.Problem10809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int from=97;
	private static final int to=97+26;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] arr = new int[to+1];
        for(int i=from;i<to;i++) {
        	arr[i]=-1;
        }
        for(int i=0;i<s.length();i++) {
        	if(arr[s.charAt(i)] ==-1) {
        		arr[s.charAt(i)]=i;
        	}
        }
        for(int i=from;i<to;i++)
        	System.out.printf(arr[i]+" ");
    }
}
