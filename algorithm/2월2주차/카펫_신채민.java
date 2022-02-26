class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0,0};
        for(int i=1;i<=yellow;i++){
            for(int j=1;j<=yellow;j++){
                if(i*j==yellow){
                    if((i+2)*(j+2)==(brown+yellow)){
                        if(i>j){
                            answer[0] = i+2;
                            answer[1] = j+2;
                        }else{
                            answer[0] = j+2;
                            answer[1] = i+2;
                        }
                        return answer;
                    }
                }
            }
        }
        return answer;
    }
}
