import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for(int i=0;i<nums.length;i++){
            if(!list.contains(nums[i])){
                list.add(nums[i]);
            }
        }
        
        if(nums.length/2 > list.size()){
            answer = list.size();
        }else{
            answer = nums.length/2;
        }
        return answer;
    }
}
