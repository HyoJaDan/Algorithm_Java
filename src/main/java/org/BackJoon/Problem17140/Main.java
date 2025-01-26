package org.BackJoon.Problem17140;
import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int num;
    int times;
    public Node(int num,int times){
        this.num=num;
        this.times=times;
    }
    @Override
    public int compareTo(Node other){
        if(this.times != other.times)
            return Integer.compare(this.times, other.times);
        return Integer.compare(this.num, other.num);
    }
}
public class Main {
    static int map[][] = new int[101][101];
    static int r,c,k, maxY=3,maxX=3, time = 0;
    static List<Node> arr;
    static boolean flag = false;
    public static void main(String[] args) throws IOException{
        init();

        if(isAns()){
            System.out.println(time);
        }
        while(++time <= 100 && !flag){
            if(maxY >= maxX) sortYLines();
            else sortXLines();

//            System.out.println(time);
//            for(int i=1;i<=maxY;i++){
//                for(int j=1;j<=maxX;j++){
//                    System.out.print(map[i][j]+" ");
//                }
//                System.out.println();
//            }
            if(isAns()){
                System.out.println(time);
            }
        }
        if(!flag){
            System.out.println(-1);
        }
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    public static void sortYLines(){
        int tempMaxX=maxX;
        for(int i=1; i<=maxY; i++){
            Map<Integer, Integer> treeMap = new TreeMap<>();
            for(int j=1; j<=maxX; j++){
                if(map[i][j]==0) continue;
                treeMap.put(map[i][j], treeMap.getOrDefault(map[i][j], 0)+1);
            }
            arr = new ArrayList<>();
            for(Map.Entry<Integer,Integer> entry : treeMap.entrySet()){
                arr.add(new Node(entry.getKey(), entry.getValue()));
            }
            Collections.sort(arr);

            int j=1;
            for(Node now : arr){
                map[i][j] = now.num;
                map[i][j+1] = now.times;

                if(j+1 > tempMaxX) tempMaxX = j+1;
                if(j+1 == 100) break;

                j+=2;
            }
            for(j = arr.size()*2+1; j<=tempMaxX;j++){
                map[i][j]=0;
            }
        }
        maxX = tempMaxX;
    }
    public static void sortXLines(){
        int tempMaxY = maxY;
        for(int j=1; j<=maxX; j++){
            Map<Integer, Integer> treeMap = new TreeMap<>();
            for(int i=1; i<=maxY; i++){
                if(map[i][j]==0) continue;
                treeMap.put(map[i][j], treeMap.getOrDefault(map[i][j], 0)+1);
            }
            arr = new ArrayList<>();
            for(Map.Entry<Integer,Integer> entry : treeMap.entrySet()){
                arr.add(new Node(entry.getKey(), entry.getValue()));
            }
            Collections.sort(arr);

            int i=1;
            for(Node now : arr){
                map[i][j] = now.num;
                map[i+1][j] = now.times;

                if(i+1 > tempMaxY) tempMaxY = i+1;
                if(i+1 == 100) break;
                i+=2;
            }
            for(i = arr.size()*2+1; i<=tempMaxY;i++){
                map[i][j]=0;
            }
        }
        maxY = tempMaxY;
    }
    public static boolean isAns(){
        if(map[r][c]==k){
            flag = true;
            return true;
        }
        else return false;
    }
}
