import java.io.*;
import java.util.*;
public class Main {
    public static int N,M;
    public static int board[][];
    public static boolean visited[][];
    public static int dir[][] = {{-1,0},{0,1},{1,0},{0,-1}};
    public static class Node{
        int y;
        int x;
        int virus;
        public Node(){}
        public Node(int a,int b,int c){
            y = a;
            x = b;
            virus = c;
        }
    }
    public static boolean isBoundry(int y,int x){
        if(y<0 || x<0 || y>=N || x >=M)return false;
        return true;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        Queue<Node> q = new LinkedList<Node>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]==1 || board[i][j] == 2){
                    q.add(new Node(i,j,board[i][j]));
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()){
            int qSize = q.size();
            HashSet<Integer> hs = new HashSet<Integer>();
            for(int i=0;i<qSize;i++){
                Node cur = q.poll();
                for(int j=0;j<4;j++){
                    int ny = cur.y + dir[j][0];
                    int nx = cur.x + dir[j][1];
                    if(!isBoundry(ny,nx) || visited[ny][nx] || board[ny][nx]==-1) continue;
                    if(board[ny][nx]!=0 || board[ny][nx]==3) continue;
                    int num = (ny*M + nx) + N*M*cur.virus;
                    hs.add(num);
                }
            }
            Iterator<Integer> iter = hs.iterator();
            while(iter.hasNext()){
                int num = iter.next();
                num -= N*M;
                if((num)<N*M){ //virus == 1일 때
                    visited[num/M][num%M] = true;
                    if(hs.contains(num+2*N*M)){
                        board[num/M][num%M] = 3;
                    }else{
                        board[num/M][num%M] = 1;
                        q.add(new Node(num/M,num%M,1));
                    }
                }else{
                    num -= N*M;
                    visited[num/M][num%M] = true;
                    if(hs.contains(num+N*M)){
                        board[num/M][num%M] = 3;
                    }else{
                        board[num/M][num%M] = 2;
                        q.add(new Node(num/M,num%M,2));
                    }
                }
            }
        }
        int first,second,third;
        first=second=third=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(board[i][j]==1){
                    first++;
                }else if(board[i][j]==2){
                    second++;
                }else if(board[i][j]==3){
                    third++;
                }
            }
        }
        System.out.println(first + " " + second + " " + third);
    }
}
