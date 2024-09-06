package main.java.org.swea.Problem2477;

import java.io.*;
import java.util.*;

class Person{
    int number;
    int startTime;
    int receptionDaskNumber;
    int repairDaskNumber;
    public Person(int number, int startTime){
        this.number=number;
        this.startTime=startTime;
    }
}

class Line{
	int number=0;
	int endTime;
	public Line(int number, int endTime) {
		this.number=number;
		this.endTime=endTime;
	}
}
class Solution{
	static int n,m,k,a,b,t;
   	static int[] receptionDask, repairDask;
    static Person[] person;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	t = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=t;tc++){
        	init(br);
        	run();
        	
        	int ans=0;
            for(int i=1 ;i<=k ;i++){
                if(person[i].receptionDaskNumber==a && person[i].repairDaskNumber==b) {
                	ans+=i;
                }
            }
            sb.append("#").append(tc).append(" ");
            if(ans==0) sb.append(-1).append("\n");
            else sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    
    public static void run() {
    	ArrayDeque<Integer> beforeReceptionLine = new ArrayDeque<>();
    	Line receptionLine[] = new Line[n+1];
    	ArrayDeque<Integer> beforeRepairLine = new ArrayDeque<>();
    	Line repairLine[] = new Line[m+1];
    	for(int i=1;i<=n;i++)
    		receptionLine[i] = new Line(0,0);
    	for(int i=1;i<=m;i++) {
    		repairLine[i]=new Line(0,0);
    	}
    	
    	int currentTime=0;
    	while(true) {
    		//repairDask
    		for(int i=1;i<=m;i++) {
    			if(repairLine[i]!=null && repairLine[i].number!=0 && repairLine[i].endTime == currentTime) {
    				
    				repairLine[i].number=0;
    				repairLine[i].endTime=0;
    			}
    		}
    		//receptionLine -> beforeRepairLine
    		for(int i=1;i<=n;i++) {
    			if(receptionLine[i]!=null && receptionLine[i].number!=0 && receptionLine[i].endTime==currentTime) {
    				beforeRepairLine.addLast(receptionLine[i].number);
    				receptionLine[i].number=0;
    				receptionLine[i].endTime=0;
    			}
    		}
    		
    		// beforeRepairLine -> repairDask
			for(int i=1;i<=m;i++) {
				if(beforeRepairLine.isEmpty()) break;
				if(repairLine[i].number==0) {
					int currentPerson = beforeRepairLine.pollFirst();
					person[currentPerson].repairDaskNumber=i;
					
					repairLine[i].number=currentPerson;
					repairLine[i].endTime=currentTime+repairDask[i];
				}
			}
    		
    		
    		// beforeReceptionLine -> receptionLine
    		for(int i=1;i<=k;i++) {
        		if(person[i].startTime==currentTime) {
        			beforeReceptionLine.addLast(i);
        		}
        	}
        	while(!beforeReceptionLine.isEmpty()) {
        		boolean visited=false;
        		for(int j=1;j<=n;j++) {
        			if(receptionLine[j].number==0) {
        				int currentPerson = beforeReceptionLine.pollFirst();
        				person[currentPerson].receptionDaskNumber=j;
        				
        				receptionLine[j].number = currentPerson;
        				receptionLine[j].endTime = currentTime+receptionDask[j];
        				
        				visited=true;
        				if(beforeReceptionLine.isEmpty()) break;
        			}
        		}
        		if(!visited)break;
        	}
    		currentTime++;
    			
    		boolean shoudContinue=false;
    		for(int i=1;i<=k;i++) {
    			if(person[i].repairDaskNumber==0) {
    				shoudContinue=true;
    				break;
    			}
    		}
    		if(!shoudContinue) break;
    	}
    	
    }
    
    public static void init(BufferedReader br) throws IOException{
     	StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k =  Integer.parseInt(st.nextToken());
        a  = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        receptionDask = new int[n+1];
        for(int i=1;i<=n;i++){
            receptionDask[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        repairDask = new int[m+1];
        for(int i=1;i<=m;i++){
            repairDask[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        person = new Person[k+1];
        for(int i=1 ;i<=k ;i++){
            person[i] = new Person(i, Integer.parseInt(st.nextToken()));
        }
    }
    
}