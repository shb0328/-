class Solution {
    public static String go(String str){
        //1. 110 찾기
        //2. 마지막 0 찾기
        int cnt = 0;
        StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            sb.append(str.charAt(i));
            if(sb.length()>=3){
                if(sb.charAt(sb.length()-1) == '0' && sb.charAt(sb.length()-2) == '1' && sb.charAt(sb.length()-3) == '1'){
                    sb.delete(sb.length()-3,sb.length());
                    cnt++;
                }
            }
        }
        int insertPosition = -1;
        for(int i=0;i<sb.length();i++){
            if(sb.charAt(i) == '0'){
                insertPosition = i+1;
            }
        }
        if(insertPosition==-1){
            for(int i=0;i<cnt;i++){
                answer.append("110");
            }
            answer.append(sb.toString());
        }else{
            answer.append(sb.substring(0,insertPosition));
            for(int i=0;i<cnt;i++){
                answer.append("110");
            }
            answer.append(sb.substring(insertPosition));
        }
        return answer.toString();

    }
    public static String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for(int i=0;i<s.length;i++){
            String prev = s[i];
            String ans = go(prev);
            answer[i] = ans;
        }

48
}
실행 결과    
        return answer;
    }
}
