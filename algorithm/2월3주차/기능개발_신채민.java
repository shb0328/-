import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = new int[progresses.length];
        
        for(int i=0; i < progresses.length;i++){ 
        	int progress = progresses[i];
        	int workDay = 0;
        	while(progress < 100){
        		progress += speeds[i];
        		workDay++; 
        	}
        	answer[i] = workDay;
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0 ; i < answer.length ; i++){ 
            int curr = answer[i];
            int cnt = 1;
            if(curr < 0)
                continue;
            for(int j=i+1 ; j < answer.length ; j++){  
            	int compare = answer[j];
            	if(curr >= compare){
            		answer[j] = -1; 
            		cnt++;
            	} else {
            		break;
            	}
            }
            list.add(cnt);
        }        
        
        answer = list.stream().mapToInt(i ->i).toArray();
        return answer;
    }
}
