class Result {
    public static class Info{
        int x,y;
        public Info(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    final static int dx[] = {0,1,0,-1};
    final static int dy[] = {-1,0,1,0};

    public static List<String> bomberMan(int n, List<String> grid) {
        List<String> ans = new ArrayList<>();
        if(n>2) n = (n-2)%4+2;      //중복되는 패턴 존재
        int row = grid.size();
        int col = grid.get(0).length();
        char arr[][] = new char[row][col];
        int check[][] = new int[row][col];
        
        int curTime = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                char c = grid.get(i).charAt(j);
                arr[i][j]=c;
                if(c=='O'){
                    check[i][j]=curTime+3;
                }
            }
        }
        curTime++;
        while(curTime++<n){
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    char c = arr[i][j];
                    if(c=='.'){
                        arr[i][j] = 'O';
                        check[i][j]=curTime+3;
                    }
                }
            }
            if(curTime==n) break;
            curTime++;
            for(int i=0;i<row;i++)
                for(int j=0;j<col;j++)
                    if(check[i][j]==curTime){
                        arr[i][j]='.';
                        for(int k=0;k<4;k++){
                            int nx = j+dx[k];
                            int ny = i+dy[k];
                            if(nx>=0 && ny>=0 && nx<col && ny<row)
                                arr[ny][nx]='.';
                        }
                    }
        }
        for(int i=0;i<row;i++)
            ans.add(new String(arr[i]));
        return ans;
    }

}
