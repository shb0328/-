import java.util.*;
import java.io.*;

class Solution {
    public class Info implements Comparable<Info>{
        int start,end;
        public Info(int start, int end){
            this.start=start;
            this.end=end;
        }
        @Override
        public int compareTo(Info o){
            return Integer.compare(this.end,o.end);
        }
    }
    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for(int i=0;i<routes.length;i++){
            pq.offer(new Info(routes[i][0],routes[i][1]));
        }
        Info ii = pq.poll();
        int right = ii.end;
        answer++;
        while(!pq.isEmpty()){
            ii = pq.poll();
            int cl = ii.start;
            int cr = ii.end;
            if(right<cl){
                right = cr;
                answer++;
            }
        }
        return answer;
    }
}
