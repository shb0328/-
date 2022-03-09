static int row,col;
    static int arr[][];

    public static void rotate(int r, int c, int rot,int times){
        for(int t=0;t<times;t++){
            int cnt = rot%(2*(r+c-4*t-2));
            while(cnt>0){
                int val = arr[t][t];
                for(int j=t;j<col-1-t;j++)  arr[t][j] = arr[t][j+1];
                for(int i=t;i<row-t-1;i++)  arr[i][col-1-t]=arr[i+1][col-1-t];
                for(int j=col-1-t;j>t;j--)  arr[row-t-1][j]=arr[row-t-1][j-1];
                for(int i=row-1-t;i>t;i--)  arr[i][t] = arr[i-1][t];
                arr[t+1][t]=val;
                cnt--;
            }
        }
    }

    public static void matrixRotation(List<List<Integer>> matrix, int r) {
    // Write your code here
        row = matrix.size();
        col = matrix.get(0).size();
        arr = new int[row][col];
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                arr[i][j] = matrix.get(i).get(j);

        int times = Math.min(row,col)/2;

        rotate(row,col,r,times);

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
                System.out.print(arr[i][j]+" ");
            System.out.println();
        }
    }
