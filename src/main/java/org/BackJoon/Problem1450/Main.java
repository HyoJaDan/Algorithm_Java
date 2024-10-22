package org.BackJoon.Problem1450;

import java.io.*;
import java.util.*;
class Main{
    static Long n,c,arr[],arr2[],arrSize,arr2Size, ans=0L;
    static List<Long> list, list2;
    public static void main(String[] args) throws IOException{
        init();
        dfs(0,0L, arrSize, arr, list);
        dfs(0,0L, arr2Size, arr2, list2);
        Collections.sort(list);
        Collections.sort(list2);

        for(int i=0;i<list.size();i++){
            ans += binarySearch(c - list.get(i));
        }
        System.out.println(ans);
    }
    public static int binarySearch(Long target){
        int left = 0;
        int right = list2.size()-1;

//        while(left < right){
//            int mid = (left + right) /2;
//            if(target < list2.get(mid)){
//                right = mid+1;
//            } else {
//                left = mid+1;
//            }
//        }
        while(left <= right){
            int mid = (left + right) /2;
            if(target < list2.get(mid)){
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return left;
    }
    public static void dfs(int level, Long sum, Long maxLevel, Long arr[], List<Long> list){
        if(sum > c) return;
        if(level == maxLevel){
            list.add(sum);
            return;
        }
        dfs(level+1, sum, maxLevel, arr, list);
        dfs(level+1, sum+arr[level], maxLevel, arr, list);
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        arrSize = n/2;
        arr2Size = n-arrSize;
        list = new ArrayList<>();
        list2 = new ArrayList<>();

        arr = new Long[Math.toIntExact(arrSize)];
        arr2 = new Long[Math.toIntExact(arr2Size)];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<arrSize;i++) arr[i] = Long.parseLong(st.nextToken());
        for(int i=0;i<arr2Size;i++) arr2[i] = Long.parseLong(st.nextToken());
    }
}
//https://jackpot53.tistory.com/33