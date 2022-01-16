import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int car = routes.length;
        
        Arrays.sort(routes, new Comparator<int[]>(){
            @Override
            public int compare(int[] arr1, int[] arr2){
                return arr1[0] - arr2[0];
            }
        });
        
        int tmp = routes[0][1];
        for(int i=0;i<car;i++){
            
            if(routes[i][0]>tmp){
                answer++;
                tmp = routes[i][1];
            }
            if(tmp >= routes[i][1]){
                tmp = routes[i][1];
            }
            
        }
        return answer+1;
    }
}
