import java.util.*;
import java.io.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        int len = times.length;
        long l = 0,r = (long)n*(long)times[len-1],mid;
        while(l<=r){
            mid = l + (r-l)/2;
            long sum=0;
            for(int i=0;i<len;i++)
                sum+=(mid/times[i]);
            if(sum>=n){
                answer = mid;
                r = mid-1;
            }
            else    l = mid+1;
        }
        return answer;
    }
}
