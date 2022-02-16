import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int num = progresses.length;
        for(int i=0;i<num;i++){
            progresses[i] = (int)Math.ceil((double)(100-progresses[i])/speeds[i]);
        }
        
        int idx=0,cnt=0,day=0;
        List<Integer> li = new ArrayList<>();
        while(idx<num){
            if(day<progresses[idx]){
                if(cnt>0)   li.add(cnt);
                day=progresses[idx];
                cnt=1;
                idx++;
            }
            else{
                while(idx<num && day>=progresses[idx]){
                    idx++;
                    cnt++;
                }
            }
            if(idx==num) li.add(cnt);
        }
        
        int len = li.size();
        int[] answer = new int[len];
        for(int i=0;i<len;i++)
            answer[i]=li.get(i);
        
        return answer;
    }
}
