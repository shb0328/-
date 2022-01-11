import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int num = commands.length;
        int[] answer = new int[num];
        for(int i=0;i<num;i++){
            List<Integer> li = new ArrayList<>();
            for(int j=commands[i][0];j<=commands[i][1];j++)
                li.add(array[j-1]);
            Collections.sort(li);
            answer[i]=li.get(commands[i][2]-1);
        }
        return answer;
    }
}
