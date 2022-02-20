public class Prgms_네트워크 {
    public static void main(String[] args) {
        Prgms_네트워크 instance = new Prgms_네트워크();
        Solution solution = instance.getSolution();
        int answer = solution.solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        System.out.println("answer = " + answer);
    }

    public Solution getSolution() {
        return new Solution();
    }

    class Solution {
        private boolean[] ck;

        public int solution(int n, int[][] computers) {
            int answer = 0;

            ck = new boolean[n];
            for(int i = 0; i < n; ++i) {
                if(ck[i]) continue;
                dfs(n, computers, i);
                answer++;
            }

            return answer;
        }

        private void dfs(int n, int[][] computers, int idx) {
            ck[idx] = true;
            for(int i = 0; i < n; ++i){
                if(computers[idx][i] == 1 && !ck[i]) dfs(n, computers, i);
            }
        }
    }
}
