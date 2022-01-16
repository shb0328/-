import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int len = commands.length;
        int[] answer = new int[len];
        for(int i=0;i<len;i++){
            int start = commands[i][0]-1;
            int end = commands[i][1]-1;
            int[] tmp = new int[end-start+1];
            for(int j=start;j<=end;j++){
                tmp[j-start] = array[j];
            }
            Arrays.sort(tmp);
            answer[i] = tmp[commands[i][2]-1];
        }
        return answer;
    }
}
