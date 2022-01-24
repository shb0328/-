import java.util.*;
import java.io.*;

class Solution {
    static int needChange[][];
    static int num;
    static boolean check[];
    
    static class Info{
        String str;
        int idx, val;
        public Info(String str, int idx, int val){
            this.str=str;
            this.idx=idx;
            this.val=val;
        }
    };
    
    static void cal(String str, String[] words){
        for(int i=0;i<num-1;i++){
            int cnt = 0;
            for(int k=0;k<words[i].length();k++){
                if(words[i].charAt(k)!=str.charAt(k))
                    cnt++;
            }
            needChange[0][i+1]=cnt;
            needChange[i+1][0]=cnt;
        }
        
        for(int i=0;i<num-2;i++){
            for(int j=i+1;j<num-1;j++){
                int cnt = 0;
                for(int k=0;k<words[i].length();k++){
                    if(words[i].charAt(k)!=words[j].charAt(k))
                        cnt++;
                }
                needChange[i+1][j+1]=cnt;
                needChange[j+1][i+1]=cnt;
            }
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        //초기화
        num = words.length+1;
        check = new boolean[num];
        needChange = new int[num][num];
        
        cal(begin,words);
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(begin,0,0));
        check[0]=true;
        boolean finish=false;
        
        while(!q.isEmpty()){
            Info ii = q.poll();
            String str = ii.str;
            int idx = ii.idx;
            int cv = ii.val;
            if(str.equals(target)){
                finish = true;
                answer = cv;
                break;
            }
            for(int i=0;i<num-1;i++){
                if(idx==i+1) continue;      //자기 자신
                int diff = needChange[idx][i+1];
                if(diff==1 && !check[i+1]){
                    check[i+1]=true;
                    q.offer(new Info(words[i],i+1,cv+1));
                }
            }
        }
        return answer;
    }
}
