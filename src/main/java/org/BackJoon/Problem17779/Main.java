package org.BackJoon.Problem17779;

import java.io.*;
import java.util.*;

class Point{
    int y,x;
    public Point(int y,int x){
        this.y=y;this.x=x;
    }
}
public class Main {
    static int map[][];
    static int n, sum=0, ans = Integer.MAX_VALUE;
    static Point top,left,bottom,right;
    public static void main(String[] args) throws IOException{
        init();

        // getTop
        for(int i=0;i<n-2;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0) continue;
                if(i==0 && j==n-1) continue;
                if(i==n-1 && j==0) continue;
                if(i==n-1 && j==n-1) continue;

                top = new Point(i,j);
                getLeft();
            }
        }
        System.out.println(ans);
    }
    public static void getLeft(){
        Point goLeft = new Point(1,-1);
        while(true){
            int dy = top.y+goLeft.y;
            int dx = top.x+goLeft.x;

            if(dy <0 || dx<0 || dy>=n || dx>=n) break;

            left = new Point(dy,dx);
            getBottom();
            goLeft.y+=1;
            goLeft.x-=1;
        }
    }
    public static void getBottom(){
        Point goRight = new Point(1, 1);
        while(true){
            int dy = left.y+goRight.y;
            int dx = left.x+goRight.y;

            if(dy <0 || dx<0 || dy>=n || dx>=n) break;

            bottom = new Point(dy,dx);
            getRight();
            goRight.y+=1;
            goRight.x+=1;
        }
    }
    public static void getRight(){
        Point goRight = new Point(-1,1);
        while(true){
            int dy = bottom.y+goRight.y;
            int dx = bottom.x+goRight.x;

            if(dy<0 || dx<0 || dy>=n || dx>=n) break;
            if(dy-top.y == dx-top.x){
                right = new Point(dy,dx);
//                System.out.println("------------------");
//                System.out.println(top.y+" "+top.x);
//                System.out.println(left.y+" "+left.x);
//                System.out.println(bottom.y+" "+bottom.x);
//                System.out.println(right.y+" "+right.x);
                calculateArea();
                break;
            }
            goRight.y-=1;
            goRight.x+=1;
        }
    }
    public static void calculateArea(){
        ArrayList<Integer> arr = new ArrayList<>();

        //getArea 1
        int areaA = 0;
        for(int i=0; i<left.y; i++){
            int temp = top.x;
            if(i-top.y >= 0){
                temp -= i-top.y+1;
            }
//            System.out.print("A : ");
            for(int j=0; j<=temp; j++){
                areaA += map[i][j];
//                System.out.println(i+" "+j+" "+areaA );
            }
        }
        arr.add(areaA);

        int areaB = 0;
        for(int i=0; i<= right.y; i++){
            int temp = top.x+1;
            if(i-top.y >=0){
                temp += i-top.y;
            }
//            System.out.print("B : ");
            for(int j=temp; j<n; j++){
                areaB += map[i][j];
//                System.out.println(i+" "+j+" "+areaB );
            }
        }
        arr.add(areaB);

        int areaC = 0;
        for(int i=left.y; i<n;i++){
            int temp = bottom.x;
            if(i <= bottom.y){
                temp -= bottom.y-i;
            }
//            System.out.print("C : ");
            for(int j=0; j< temp; j++){
                areaC += map[i][j];
//                System.out.println(i+" "+j +" "+areaC);
            }
        }
        arr.add(areaC);

        int areaD =0;
        for(int i=right.y+1; i<n;i++){
            int temp =bottom.x;
            if(i <= bottom.y){
                temp += bottom.y-i+1;
            }
//            System.out.print("D : ");
            for(int j=temp; j<n; j++){
                areaD += map[i][j];
//                System.out.println(i+" "+j+" "+areaD );
            }
        }
        arr.add(areaD);
        int areaE = sum-(areaA+areaB+areaC+areaD);
        arr.add(areaE);
        Collections.sort(arr);

        int now = arr.get(arr.size()-1)-arr.get(0);
//        System.out.println(now+" "+areaA+" "+areaB+" "+areaC+" "+areaD+" " +areaE);
        ans = Integer.min(now,ans);
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[n+1][n+1] ;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                sum+=map[i][j];
            }
        }
    }
}
