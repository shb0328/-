import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int idx = 0;
        int N = progresses.length;
        ArrayList<Integer> tempAns = new ArrayList<Integer>();
        while(idx<N){
            for(int i=0;i<N;i++){
                progresses[i] += speeds[i];
            }
            if(progresses[idx] >= 100){
                int cnt = 0;
                for(int i=idx;i<N;i++){
                    if(progresses[i]<100) break;
                    idx++;
                    cnt++;
                }
                tempAns.add(cnt);
            }
        }
        int[] answer = new int[tempAns.size()];
        for(int i=0;i<tempAns.size();i++){
            answer[i] = tempAns.get(i);
        }
        return answer;
    }
}
