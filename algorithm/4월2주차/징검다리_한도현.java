import java.util.*;

class Solution {
    public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int arr[] = new int[rocks.length+1];
        for(int i=0;i<arr.length-1;i++){
            arr[i] = rocks[i];
        }
        arr[arr.length-1] = distance;
        Arrays.sort(arr);
        int N = arr.length;
        int left = 0;
        int right = distance;
        while(left<=right){
            int mid = (left+right)/2;
            boolean suc = true;
            int previous = 0;
            int cnt = 0; // n개 까지 가능
            for(int i=0;i<N;i++){
                if(arr[i]-previous < mid){
                    if(cnt==n){
                        suc = false;
                        break;
                    }else{
                        cnt++;
                    }
                }else{
                    previous = arr[i];
                }
            }
            if(suc){
                left = mid+1;
                answer = mid;
            }else{
                right = mid-1;
            }
        }
        return answer;
    }
}
