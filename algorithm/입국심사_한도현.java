import java.util.*;
import java.io.*;
class Solution {
    public long solution(int n, int[] times) {
        long left = 0;
        long right = 0;
        long ans = 0;
        for(int i=0;i<times.length;i++){
            if(right < times[i]){
                right = times[i];
            }
        }
        right *= n;
        while(left<=right){
            long mid = (left+right)/2;
            long availNum = 0;
            for(int i=0;i<times.length;i++){
                availNum += mid/times[i];
                if(availNum>=n) break;
            }   
            if(availNum>=n){
                ans = mid;
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }
        return ans;
    }
}
