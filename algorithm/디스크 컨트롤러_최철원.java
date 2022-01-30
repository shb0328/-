import java.util.*;
import java.io.*;

class Solution {
    static class Info implements Comparable<Info>{
        int a,b;
        public Info(int a, int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Info o){
            if(this.b==o.b){
                return Integer.compare(this.a,o.a);
            }
            return Integer.compare(this.b,o.b);
        }
    };
    
    public int solution(int[][] jobs) {
        int answer = 0;
        int num = jobs.length;
        PriorityQueue<Info> waitingQueue = new PriorityQueue<>();   //요청시간,소요시간
        PriorityQueue<Info> jobQueue = new PriorityQueue<>();   //소요시간,요청시간
        
        for(int i=0;i<num;i++)
            jobQueue.offer(new Info(jobs[i][1],jobs[i][0]));
        Info ii = jobQueue.poll();
        int curTime = ii.a+ii.b;
        answer +=(ii.a);
        
        while(!jobQueue.isEmpty()){
            //하나의 작업이 끝나기 전에 다른 작업들이 요청되었다면 추가
            while(!jobQueue.isEmpty()){
                ii = jobQueue.peek();
                int startTime = ii.b;
                if(startTime<=curTime){
                    jobQueue.poll();
                    waitingQueue.offer(new Info(ii.b,ii.a));
                }
                else break;
            }
            if(!waitingQueue.isEmpty()){
                ii = waitingQueue.poll();
                curTime += ii.b;        //소요시간 추가
                answer+=(curTime-ii.a);
            }
            else{       //하드디스크가 작업을 수행하고 있지 않을 때
                if(!jobQueue.isEmpty())
                    curTime = jobQueue.peek().b;
            }
        }
        while(!waitingQueue.isEmpty()){
            ii = waitingQueue.poll();
            curTime += ii.b;        //소요시간 추가
            answer+=(curTime-ii.a);
        }
        return answer/num;
    }
}
