package main.java.org.BackJoon.Problem14891;

import java.io.*;
import java.util.*;

public class Main {
    /*
    8媛� 4以� 媛� �넲�땲諛뷀�� �긽�깭  [3]�뿉 留욌떎�쓬
    0 = N,
    1 = S -> true

    �쉶�쟾 �슏�닔 numOfRotate
    �넲�땲諛뷀��, 1 : �떆怨� -1 : 諛섏떆怨�
     */
    static boolean arr[][] = new boolean[5][8];
    static int rotate[][];
    static int whichWheel;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int numOfRotate = init(br);

        for(int tc=0;tc<numOfRotate;tc++){
            st= new StringTokenizer(br.readLine());
            whichWheel = Integer.parseInt(st.nextToken());
            int temp = Integer.parseInt(st.nextToken());
            boolean isDirectionClock = temp == 1 ? true : false;

            boolean currentDirection = isDirectionClock;

//            System.out.println("===============짹rotate===============짹");
            int i;
            for(i= whichWheel; i<4; i++){
                if(arr[i][2] == arr[i+1][6]) {
                    break;
                }
                currentDirection = !currentDirection;
            }
            while(i > whichWheel){
                rotate(currentDirection, i);
                currentDirection = !currentDirection;
                i--;
            }

            currentDirection = isDirectionClock;
            for(i = whichWheel; i>1; i--){
                if(arr[i][6] == arr[i-1][2]) {
                    break;
                }
                currentDirection = !currentDirection;
            }
            while(i < whichWheel){
                rotate(currentDirection, i);
                currentDirection = !currentDirection;
                i++;
            }

            
            rotate(isDirectionClock, whichWheel);
        }
        int ans=0;
        int num=1;
        for(int i=1;i<=4;i++){
            if(arr[i][0])
                ans+=num;
            num*=2;
        }
        System.out.println(ans);
    }
    static void rotate(boolean isDirectionClock, int whichWheel){
        if(isDirectionClock){
            rotateClock(arr[whichWheel]);
        } else {
            rotateReverseClock(arr[whichWheel]);
        }

//        for(int i=1;i<=4;i++){
//            for(int j=0;j<8;j++){
//                System.out.print(arr[i][j] == true ? 1 : 0);
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
    static void rotateClock(boolean[] wheel){
        boolean temp = wheel[7];
        for(int i=6;i>=0;i--){
            wheel[i+1] = wheel[i];
        }
        wheel[0] = temp;
    }
    static void rotateReverseClock(boolean[] wheel){
        boolean temp = wheel[0];
        for(int i=0;i<7;i++){
            wheel[i] = wheel[i+1];
        }
        wheel[7] = temp;
    }
    static int init(BufferedReader br) throws IOException{
        for(int i=1;i<=4;i++) {
            String s = br.readLine();
            for(int j=0;j<s.length();j++){
                if('1' == s.charAt(j))
                    arr[i][j]=true;
                else arr[i][j]=false;
            }
        }

        return Integer.parseInt(br.readLine());
    }
}
