class Solution {
    static class Info{
        int y,x;
        public Info(int y, int x){
            this.x=x;
            this.y=y;
        }
    };
    
    final static int dx[] = {0,1,0,-1};
    final static int dy[] = {-1,0,1,0};
    
    public int numIslands(char[][] grid) {
        int answer = 0;
        int row = grid.length;
        int col = grid[0].length;
        boolean check[][] = new boolean[row][col];
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(!check[i][j] && grid[i][j]=='1'){
                    check[i][j]=true;
                    answer++;
                    Queue<Info> q = new LinkedList<>();
                    q.offer(new Info(i,j));
                    while(!q.isEmpty()){
                        Info ii = q.poll();
                        int cx = ii.x;
                        int cy = ii.y;
                        for(int k=0;k<4;k++){
                            int nx = cx+dx[k];
                            int ny = cy+dy[k];
                            if(nx>=0 && ny>=0 && nx<col && ny<row && !check[ny][nx] && grid[ny][nx]=='1'){
                                check[ny][nx]=true;
                                q.offer(new Info(ny,nx));
                            }
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}
