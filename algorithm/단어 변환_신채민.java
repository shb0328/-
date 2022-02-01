import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public static int answer;
    public static boolean[] visit;
    public static Queue<Integer> queue = new LinkedList<Integer>();
    
    public int solution(String begin, String target, String[] words) {
        if(Arrays.asList(words).contains(target)){
            bfs(begin, target, words);
        }
        return answer;
    }
    
    public static void bfs(String begin, String target, String[] words){
        
        visit = new boolean[words.length];
        queue.offer(-1);
        String word = begin;
        
        while(!queue.isEmpty()){
            int curr = queue.poll();
            int[] arr = getArr(word, words);
            
            if(curr == -1 || !visit[curr]){
                if(curr != -1) visit[curr] = true;
                for(int next = 0; next < words.length; next++){
                    if(!visit[next] && arr[next] == 1){
                        word = words[next];
                        queue.offer(next);
                    }
                }
            }
            
            answer++;
            if(word.equals(target)) return;
        }
    }
    
    public static int[] getArr(String str, String[] words){
        int[] res = new int[words.length];
        int cnt = str.length();
        for(int i=0;i<words.length;i++){
            String word = words[i];
            for(int j=0;j<str.length();j++){
                if(str.charAt(j) == word.charAt(j)){
                    cnt--;
                }
            }
            res[i] = cnt;
            cnt = str.length();
        }
        return res;
    }
}
