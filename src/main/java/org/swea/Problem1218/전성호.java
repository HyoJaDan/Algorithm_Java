package main.java.org.swea.Problem1218;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Àü¼ºÈ£ {

	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int n = Integer.parseInt(br.readLine());
			String s = br.readLine();

			Deque<Character> dq = new ArrayDeque<>();
			for(int i=0;i<s.length();i++) {
				if(dq.isEmpty())
					dq.offerFirst(s.charAt(i));
				else if(dq.peekFirst() == '(' && s.charAt(i) ==')') 
					dq.pollFirst();
				else if(dq.peekFirst() == '{' && s.charAt(i) =='}')  
					dq.pollFirst();
				else if(dq.peekFirst() == '<' && s.charAt(i) =='>') 
					dq.pollFirst();
				else if(dq.peekFirst() == '[' && s.charAt(i) ==']') 
					dq.pollFirst();
				else
					dq.offerFirst(s.charAt(i));
			}
			if(dq.isEmpty())
				System.out.println("#"+test_case+" "+1);
			else System.out.println("#"+test_case+" "+0);
		}
	}
}
