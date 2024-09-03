package main.java.org.BackJoon.Problem2061;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		BigInteger K = new BigInteger(st.nextToken());
		
		
		int L = Integer.parseInt(st.nextToken());
		boolean[] primes = new boolean[L + 1];
        primes[1] = true;
        
		for(int i=2;i<L;i++) {
			if(primes[i])continue;
			BigInteger now = new BigInteger(Integer.toString(i));
			
			if( K.mod(now).compareTo(BigInteger.ZERO) ==0 ){
				  System.out.println("BAD "+i);
				  System.exit(0);
			}
			for(int j=i+i; j<=L; j+=i){
	                primes[j] = true;
	        }
		}
		System.out.println("GOOD");	System.exit(0);
	}

}
