import java.io.*;
import java.util.*;

public class Main {
    public static boolean visited[];
    public static int sosuDp[];
    public static int isSosu(int num){
        if(sosuDp[num]!=-1) return sosuDp[num];
        for(int i=2;i*i<=num;i++){
            if(num%i==0) return sosuDp[num] = 0;
        }
        return sosuDp[num] = 1;
    }

    public static int bfs(int s, int e){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        int ans = 0;
        while(!q.isEmpty()){
            int qSzie = q.size();
            for(int i=0;i<qSzie;i++){
                int curNum = q.poll();
                if(curNum == e){
                    return ans;
                }
                String sNum = Integer.toString(curNum);
                for(int j=0;j<4;j++){
                    for(int k=0;k<=9;k++){
                        String nextSNum = sNum.substring(0,j) + k + sNum.substring(j+1);
                        int nextNum = Integer.parseInt(nextSNum);
                        if(nextNum<1000) continue;
                        if(visited[nextNum]) continue;
                        if(isSosu(nextNum)==0)continue;
                        visited[nextNum] = true;
                        q.add(nextNum);
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        sosuDp = new int[10000];
        for(int i=1000;i<10000;i++){
            sosuDp[i] = -1;
        }
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];
            visited[s] = true;
            int tempAns = bfs(s,e);
            if(tempAns==-1){
                ans.append("Impossible\n");
            }else{
                ans.append(tempAns + "\n");
            }
        }
        System.out.println(ans.toString());
    }
}
