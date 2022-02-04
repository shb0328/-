class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        for(int i=0;i<puddles.length;i++){
            map[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
        }
        map[0][0] = 1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(map[j][i] == -1){
                    map[j][i] = 0;
                }
                else{
                    if(i != 0) map[j][i] += map[j][i-1] % 1000000007;
                    if(j != 0) map[j][i] += map[j-1][i] % 1000000007;
                }
            }
        }
        return map[n-1][m-1] % 1000000007;
    }
}
