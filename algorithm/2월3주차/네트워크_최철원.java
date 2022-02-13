import java.util.*;
import java.io.*;

class Solution {
    
    static List<Integer> li[];
    static int check[];
    
    public int solution(int n, int[][] computers) {
        int cnt = 0;
        //초기화
        li = new List[n];
        check = new int[n];
        for(int i=0;i<n;i++)
            li[i] = new ArrayList<>();
        
        for(int i=0;i<n-1;i++)
            for(int j=i+1;j<n;j++)
                if(computers[i][j]==1){
                    li[i].add(j);
                    li[j].add(i);
                }
        for(int i=0;i<n;i++){
            if(check[i]==0){
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                check[i] = ++cnt;
                
                while(!q.isEmpty()){
                    int cidx = q.poll();
                    for(int k=0;k<li[cidx].size();k++){
                        int next = li[cidx].get(k);
                        if(check[next]==0){
                            check[next] = cnt;
                            q.offer(next);
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
