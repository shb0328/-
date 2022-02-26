class Solution {
    public static int maxArea(int[] height) {
        int answer = 0;
        int left = 0;
        int right = height.length-1;
        answer = (right-left)*Math.min(height[right],height[left]);
        while(left<right){
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
            if(right>left) {
                int h = Math.min(height[left], height[right]);
                answer = Math.max(answer, h * (right - left));
            }
        }
        return answer;
    }
}
