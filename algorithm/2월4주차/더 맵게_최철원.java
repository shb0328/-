import java.util.*;
import java.io.*;

class Solution {
    
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int a: scoville)
            pq.add((long)a);
        boolean avail=true;
        while(pq.size()>1){
            long lowest = pq.poll();
            if(lowest>=(long)K) break;
            long secondLow = pq.poll();
            long val = lowest + secondLow*2;
            pq.add(val);
            answer++;
        }
        if(pq.poll()<(long)K) avail = false;
        return avail ? answer : -1;
    }
}
