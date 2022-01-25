import java.util.*;
class Solution {
    public static class Node{
        int num;
        int cnt; //오류 횟수
        int time;
        
        public Node(){}
        public Node(int a,int b, int t){
            num = a;
            cnt = b;
            time = t;
        }
    }
    public static boolean visited[];
    public static ArrayList<Integer> edge[];
    public static int dp[][];
    public static int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = -1;
        edge = new ArrayList[n+1];
        visited = new boolean[n+1];
        dp = new int[n+1][k+1];
        for(int i=1;i<=n;i++){
            edge[i] = new ArrayList<Integer>();
            visited[i] = false;
            for(int j=0;j<k;j++){
                dp[i][j] = 987654321;
            }
        }

        for(int i=0;i<edge_list.length;i++){
            int s = edge_list[i][0];
            int e=  edge_list[i][1];
            edge[s].add(e);
            edge[e].add(s);
        }
        int destination = gps_log[gps_log.length-1];
        //최단거리를 탐색 후 t-1 시간 안에 목적지까지 갈 수 있는지 확인 
        // 갈수 없으면 아래 과정 생략 -1 리턴
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(gps_log[0]);
        visited[gps_log[0]] = true;
        int dist = 0;
        exit:while(!q.isEmpty()){
            int qSize = q.size();
            for(int i=0;i<qSize;i++){
                int curNode = q.poll();
                if(curNode==destination){ //성공
                    break exit;
                }
                for(int j=0;j<edge[curNode].size();j++){
                    int nextNode = edge[curNode].get(j);
                    if(visited[nextNode]) continue;
                    visited[nextNode] = true;
                    q.add(nextNode);
                }
            }
            dist++;
        }
        boolean isCutOff = false;
        for(int i=1;i<=n;i++){
            if(visited[destination]==false){
                isCutOff = true;
                break;
            }
        }
        if((dist > (gps_log.length-1)) || isCutOff){ //최단거리상 불가능
            return -1;
        }

        
        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> o1.cnt - o2.cnt);
        pq.add(new Node(gps_log[0],0,1));

        //Node.cnt가 낮은 것부터 탐색을 진행시킨다(아직 오류가 없는 노드, 도착점에 도착하면 그것이 정답)
        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            if(dp[curNode.num][curNode.time-1] <= curNode.cnt){
                continue;
            }
            dp[curNode.num][curNode.time-1] = curNode.cnt;
            if(curNode.time == gps_log.length){
                if(curNode.num == destination){
                    return curNode.cnt;
                }
                continue;
            }

            for(int i=0;i<=edge[curNode.num].size();i++){
                int nextNum = (i!=edge[curNode.num].size())?edge[curNode.num].get(i):curNode.num;
                int curTime = curNode.time;
                int nextGPSPosition = gps_log[curTime];

                if(nextNum == nextGPSPosition){
                    pq.add(new Node(nextNum,curNode.cnt,curTime+1));
                }else{
                    pq.add(new Node(nextNum,curNode.cnt+1,curTime+1));
                }
            }
        }

        return answer;
    }
}
