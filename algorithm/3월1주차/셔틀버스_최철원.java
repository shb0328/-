import java.util.*;
import java.io.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int len = timetable.length;
        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<len;i++){
            st = new StringTokenizer(timetable[i],":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            pq.add(hour*60+min);
        }
        int leaveMin = 9*60;
        int cnt=0, idx=0, latest = 0;
        Stack<Integer> s = new Stack<>();
        
        while(!pq.isEmpty() && idx<n){
            int arrive = pq.peek();
            if(arrive<=leaveMin){       //출발시각 이전에 도착한 경우
                pq.poll();
                cnt++;
                if(idx==n-1) s.add(arrive);
                if(cnt==m){
                    cnt=0;
                    leaveMin+=t;
                    idx++;
                }
            }
            else{
                cnt=0;
                leaveMin+=t;
                idx++;
            }
        }
        latest = s.size()<m ? 540+(n-1)*t : s.peek()-1;
        
        String hour = String.valueOf(latest/60);
        hour = hour.length()==1 ? "0"+hour : hour;
        String min = String.valueOf(latest%60);
        min = min.length()==1 ? "0"+min : min;
        
        answer = hour+":"+min;
        return answer;
    }
}
