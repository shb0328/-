class Solution {
    public static boolean visited[] = new boolean[51];
    //s1에서 한 문자만 바꿔서 s2가 가능한지
    public static boolean availTrans(String s1, String s2){
        int cnt = 0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i))cnt++;
        }
        if(cnt==1)return true;
        return false;
    }

    public static int dfs(String begin, String target, String[] words){
        if(begin.equals(target)){
            return 0;
        }
        System.out.println("현재 단어 : " + begin);
        int answer = 987654321;
        for(int i=0;i<words.length;i++){
            if(visited[i]) continue;
            if(!availTrans(begin,words[i])) continue;
            visited[i] = true;
            answer = Math.min(answer,dfs(words[i],target,words)+1);
            visited[i] = false;
        }
        return answer;
    }

    public static int solution(String begin, String target, String[] words) {
        int answer = dfs(begin,target,words);
        if(answer==987654321){
            return 0;
        }
        return answer;
    }
}
