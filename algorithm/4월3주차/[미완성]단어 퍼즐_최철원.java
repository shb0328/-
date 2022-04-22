import java.util.*;
import java.io.*;

class Solution {
    
    static class Info implements Comparable<Info>{
        String str;
        int cnt;
        
        public Info(String str, int cnt){
            this.str=str;
            this.cnt=cnt;
        }
        
        @Override
        public int compareTo(Info o){
            return Integer.compare(this.cnt,o.cnt);
        }
    };
    
    static List<String> li[];
    
    public int solution(String[] strs, String t) {
        int answer = -1;
        //초기화
        li = new List[26];
        int tlen = t.length();
        
        for(int i=0;i<26;i++)
            li[i] = new ArrayList<>();
        
        for(String ss: strs){
            char c = ss.charAt(0);
            li[c-'a'].add(ss);
        }
        /*for(int i=0;i<26;i++)
            Collections.sort(li[i]);*/
        
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info("",0));
        
        while(!q.isEmpty()){
            Info ii = q.poll();
            String cs = ii.str;
            int cc = ii.cnt;
            int idx = cs.length();
            if(idx==tlen){
                answer = cc;
                break;
            }
            char c = t.charAt(idx);
            for(int i=0;i<li[c-'a'].size();i++){
                String lis = li[c-'a'].get(i);
                int clen = lis.length();
                if(idx+clen>tlen) continue;
                String subs = t.substring(idx,idx+clen);
                if(subs.equals(lis)){
                    q.offer(new Info(cs+lis,cc+1));
                }
            }
        }
        
        return answer;
    }
}
