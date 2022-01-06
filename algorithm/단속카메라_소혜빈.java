import java.util.Arrays;

public class 단속카메라_소혜빈 {
    public int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes,(o1,o2)->o1[1]-o2[1]);
        int[] starts = Arrays.stream(routes).mapToInt(v -> v[0]).toArray();
        int[] ends = Arrays.stream(routes).mapToInt(v -> v[1]).toArray();

        int x = ends[0];
        for (int i = 1; i < starts.length; i++) {
            if(starts[i] <= x) continue;
            x = ends[i];
            answer++;
        }
        return answer;
    }
}
