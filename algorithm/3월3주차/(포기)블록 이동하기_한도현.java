import java.util.*;
class Solution {
    public static int N,M;
    public static int dir[][] = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};

    public static class Node{
        int num;
        boolean isVertical;

        public Node(){}
        public Node(int a,boolean b){
            num = a;
            isVertical = b;
        }
    }

    public static int getNum(int y1, int x1, int y2,int x2){
        return y1*M + x1 + y2*M + x2;
    }

    public static boolean isBoundry(int y1,int x1, int y2,int x2){
        if(y1<0 || x1<0 || y2<0 || x2 <0 || y1>=N || y2 >=N || x1>=M || x2 >= M) return false;
        return true;
    }
    public static boolean isBoundry2(int y,int x){
        if(y<0 || x<0 || y>=N || x>=M) return false;
        return true;
    }

    public static int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length;
        boolean []visited = new boolean[N*M*2];
        visited[1] = true;
        Node node = new Node(1,false);
        Queue<Node> q = new LinkedList<Node>();

        q.add(node);
        exit:while(!q.isEmpty()){
            int qSize = q.size();
            for(int i=0;i<qSize;i++){
                Node curNode = q.poll();

                int y1,x1,y2,x2; //로봇 두 칸
                if(curNode.isVertical){
                    int first = (curNode.num-M)/2;
                    int second = first + M;
                    y1 = first / M;
                    x1 = first % M;
                    y2 = second / M;
                    x2 = second % M;
                    if((y1==N && x1==M) || (y2==N && x2==M))
                        break exit;
                }else{
                    int row = curNode.num / (2*M);
                    y1 = y2 = row;
                    x1 = ((curNode.num - (curNode.num / (2*M))) - 1 ) / 2;
                    x2 = x1 + 1;
                    if((y1==N && x1==M) || (y2==N && x2==M))
                        break exit;
                }
                //1. 네 방향
                for(int j=0;j<4;j++){
                    int ny1 = y1 + dir[j*2][0];
                    int nx1 = x1 + dir[j*2][1];
                    int ny2 = y2 + dir[j*2][0];
                    int nx2 = x2 + dir[j*2][1];
                    int nextNum = getNum(ny1,nx1,ny2,nx2);
                    if(!isBoundry(ny1,nx1,ny2,nx2)) continue;
                    if(visited[nextNum])continue;
                    if(board[ny1][nx1]==1 || board[ny2][nx2]==1) continue;
                    visited[nextNum] = true;
                    q.add(new Node(nextNum,curNode.isVertical));
                }

                //2. 회전
                //2-1가로 일 때, first가 기준이면 second는 first의 위 아래로 이동 가능
                // second가 기준일 때도 fisrt는 second의 위 아래로 이동 가능

                int rotateY1 = curNode.isVertical?(y1 + dir[6][0]):(y1+dir[0][0]);
                int rotateX1 = curNode.isVertical?(x1 + dir[6][1]):(x1+dir[0][1]);
                int rotateY2 = curNode.isVertical?(y1 + dir[2][0]):(y1+dir[4][0]);
                int rotateX2 = curNode.isVertical?(x1 + dir[2][1]):(x1+dir[4][1]);
                int rotateY3 = curNode.isVertical?(y2 + dir[6][0]):(y2+dir[0][0]);
                int rotateX3 = curNode.isVertical?(x2 + dir[6][1]):(x2+dir[0][1]);
                int rotateY4 = curNode.isVertical?(y2 + dir[2][0]):(y2+dir[4][0]);
                int rotateX4 = curNode.isVertical?(x2 + dir[2][1]):(x2+dir[4][1]);

                if(isBoundry(rotateY1,rotateX1,y1,x1) && board[rotateY1][rotateX1]!=1){
                    int num = getNum(y1,x1,rotateY1,rotateX1);
                    if(board[rotateY3][rotateX3]!=1 && !visited[num]){
                        visited[num] = true;
                        q.add(new Node(num,curNode.isVertical^true));
                    }
                }
                if(isBoundry(rotateY2,rotateX2,y1,x1) && board[rotateY2][rotateX2]!=1){
                    int num = getNum(y1,x1,rotateY2,rotateX2);
                    if(board[rotateY4][rotateX4]!=1 && !visited[num]){
                        visited[num] = true;
                        q.add(new Node(num,curNode.isVertical^true));
                    }
                }
                if(isBoundry(rotateY3,rotateX3,y2,x2)&& board[rotateY3][rotateX3]!=1){
                    int num = getNum(y2,x2,rotateY3,rotateX3);
                    if(board[rotateY1][rotateX1]!=1 && !visited[num]){
                        visited[num] = true;
                        q.add(new Node(num,curNode.isVertical^true));
                    }
                }
                if(isBoundry(rotateY4,rotateX4,y2,x2) && board[rotateY4][rotateX4]!=1){
                    int num = getNum(y2,x2,rotateY4,rotateX4);
                    if(board[rotateY2][rotateX2]!=1 && !visited[num]){
                        visited[num] = true;
                        q.add(new Node(num,curNode.isVertical^true));
                    }
                }
            }

            answer++;
        }

        return answer;
    }
              
}
