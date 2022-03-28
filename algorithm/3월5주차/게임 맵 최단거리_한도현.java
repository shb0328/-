import java.util.*;
class Solution {
    public static int dir[][] ={{-1,0},{0,1},{1,0},{0,-1}};
    public static class Node {
        int y;
        int x;
        public Node(){}
        public Node(int a,int b){
            y = a;
            x = b;
        }
    }
    public static int solution(int[][] maps) {
        int answer = 0;
        int N = maps.length;
        int M = maps[0].length;
        boolean visited[][] = new boolean[N][M];
        Queue<Node> q = new LinkedList<Node>();
        q.add(new Node(0,0));
        visited[0][0] = true;
        boolean isSuc = false;
        exit:while(!q.isEmpty()){
            int qSize = q.size();
            answer++;
            for(int i=0;i<qSize;i++){
                Node curNode = q.poll();
                if(curNode.y == (N-1)&&curNode.x ==(M-1)){
                    isSuc = true;
                    break exit;
                }
                for(int j=0;j<4;j++){
                    int ny = curNode.y + dir[j][0];
                    int nx = curNode.x + dir[j][1];
                    if(ny<0 || nx <0 || ny>=N || nx>=M)continue;
                    if(visited[ny][nx] || maps[ny][nx]==0) continue;
                    visited[ny][nx] = true;
                    q.add(new Node(ny,nx));
                }
            }
        }
        if(isSuc){
            return answer;
        }else{
            return -1;
        }
    }
}
