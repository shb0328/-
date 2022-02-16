class Solution {
   public static String convert(String s, int numRows) {
		if(numRows==1) return s;
        char [][] board = new char[1001][1001];
        for(int i=0;i<1000;i++){
        	for(int j=0;j<1000;j++){
        		board[i][j] = '-';
        	}
        }
        int idx = 0;
        int y = 0;
        int x = 0;
        int dir[][] = {{1,0},{-1,1}};
        int direction = 0;
        while(idx<s.length()){
            board[y][x] = s.charAt(idx++);
            if(y==0){
            	direction=0;
            }else if(y==numRows-1){
            	direction = 1;
            }
            y += dir[direction][0];
            x += dir[direction][1];
        }
        
        
        StringBuilder answer = new StringBuilder();

        int loopSize;
        if(numRows!=1){
        	loopSize = (s.length()/(numRows+(numRows-2)) + 1) * (numRows-1);
        }else{
        	loopSize = 1000;
        }
        loopSize = loopSize > 1000 ? 1000: loopSize;
        for(int i=0;i<numRows;i++){
        	for(int j=0;j<loopSize;j++){
        		if(board[i][j]!='-'){
        			answer.append(board[i][j]);
        		}
        	}
        }
        return answer.toString();
    }
}
