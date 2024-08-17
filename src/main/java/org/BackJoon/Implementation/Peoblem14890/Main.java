package org.BackJoon.Implementation.Peoblem14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Arr{
    int count;
    int number;
    boolean alreadyPutted = false;
    public Arr(int count, int number){
        this.count=count;
        this.number=number;
    }
}
public class Main {
    static int[][] map;
    static int N,L,ans=0;
    public static void main(String[] args) throws IOException {
        init();
        judgeIsLoad();
        System.out.println(ans);
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0;i<N;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    public static void judgeIsLoad(){
        for(int i=0; i<N; i++){
            ArrayList<Arr> arr = new ArrayList<>();
            int count=1, number=map[i][0];

            for(int j=1; j<N; j++){
                if(map[i][j] != map[i][j-1]){
                    arr.add(new Arr(count, number));
                    count=1;
                    number = map[i][j];
                } else if(map[i][j] == map[i][j-1])
                    count++;
            }
            arr.add(new Arr(count,number));

            boolean isAns=true;
            for(int j=0; j<arr.size()-1;j++){
                int currentNumber= arr.get(j).number;
                int nextNumber = arr.get(j+1).number;
                if(Math.abs(currentNumber - nextNumber) > 1){
                    isAns=false;
                    break;
                } else if( currentNumber > nextNumber && arr.get(j+1).count < L){
                    isAns=false;
                    break;
                } else if( currentNumber < nextNumber && arr.get(j).count < L){
                    isAns = false;
                    break;
                } else if( currentNumber > nextNumber && arr.get(j+1).alreadyPutted == true && arr.get(j+1).count <2*L){
                    isAns=false;
                    break;
                } else if( currentNumber < nextNumber && arr.get(j).alreadyPutted == true && arr.get(j).count < 2*L){
                    isAns=false;
                    break;
                }

                if( currentNumber > nextNumber ) arr.get(j+1).alreadyPutted=true;
                if( currentNumber < nextNumber ) arr.get(j).alreadyPutted=true;
            }
            if(isAns)  ans++;
        }

        for(int j=0; j<N; j++){
            ArrayList<Arr> arr = new ArrayList<>();
            int count=1, number=map[0][j];

            for(int i=1; i<N; i++){
                if(map[i][j] != map[i-1][j]){
                    arr.add(new Arr(count, number));
                    count=1;
                    number = map[i][j];
                } else if(map[i][j] == map[i-1][j])
                    count++;
            }
            arr.add(new Arr(count,number));

            boolean isAns=true;
            for(int i=0; i<arr.size()-1;i++){
                int currentNumber= arr.get(i).number;
                int nextNumber = arr.get(i+1).number;
                if(Math.abs(currentNumber - nextNumber) > 1){
                    isAns=false;
                    break;
                } else if( currentNumber > nextNumber && arr.get(i+1).count < L ){
                    isAns=false;
                    break;
                } else if( currentNumber < nextNumber && arr.get(i).count < L){
                    isAns = false;
                    break;
                } else if( currentNumber > nextNumber && arr.get(i+1).alreadyPutted == true && arr.get(i+1).count <2*L){
                    isAns=false;
                    break;
                } else if( currentNumber < nextNumber && arr.get(i).alreadyPutted == true && arr.get(i).count < 2*L){
                    isAns=false;
                    break;
                }

                if( currentNumber > nextNumber ) arr.get(i+1).alreadyPutted=true;
                if( currentNumber < nextNumber ) arr.get(i).alreadyPutted=true;
            }
            if(isAns)  ans++;
        }
    }
}
