import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int val: nums){
            if(map.containsKey(val))    map.put(val,map.get(val)+1);
            else    map.put(val,1);
        }
        return Math.min(nums.length/2,map.size());
    }
}
