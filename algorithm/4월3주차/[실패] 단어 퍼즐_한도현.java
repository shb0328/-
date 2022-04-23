import java.util.*;

class Solution {
    public static HashSet<String> hs;
    public static boolean checkOk(int idx, String str, String fullStr){
        if(idx+str.length()>fullStr.length()){
            return false;
        }
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=fullStr.charAt(i+idx)){
                return false;
            }
        }
        return true;
    }
    public static int solution(String[] strs, String t) {
        hs = new HashSet<String>();
        int answer = 1;
        Queue<String> q = new LinkedList<String>();
        for(int i=0;i<strs.length;i++){
            String str = strs[i];
            if(checkOk(0,str,t)){
                q.add(str);
                hs.add(str);
                if(str.length()==t.length()) return 1;
            }
        }
        while(!q.isEmpty()){
            answer++;
            int qSize = q.size();
            for(int j=0;j<qSize;j++){
                String cur = q.poll();
                for(int i=0;i<strs.length;i++){
                    String str = strs[i];
                    if(checkOk(cur.length(),str,t)){
                        String next = cur + str;
                        if(hs.contains(next)) continue;
                        if(t.equals(next))
                            return answer;
                        hs.add(next);
                        q.add(next);
                    }
                }
            }
        }

        return -1;
    }
}
