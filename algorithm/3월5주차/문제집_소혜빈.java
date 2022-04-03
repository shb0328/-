import java.io.*;
import java.util.*;

public class Main_bj_1766_문제집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //init
        List<List<Integer>> adj = new ArrayList<>(); //인접리스트
        for (int i = 0; i < n + 1; i++) adj.add(new ArrayList<>());

        int[] indegree = new int[n+1]; //진입차수

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            //a -> b
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);

            indegree[b]++;
        }

        //solve
        Queue<Integer> pq = new PriorityQueue<>(); // 작은 수 = 쉬운 문제 = 먼저 풀어야함
        List<String> list = new ArrayList<>();

        for (int i = 1; i < indegree.length; ++i) {
            if (indegree[i] == 0) {
                pq.offer(i);
                indegree[i]--; //방문체크 -1
            }
        }

        while (!pq.isEmpty()) {
            int a = pq.poll();
            list.add(String.valueOf(a));

            for(int b : adj.get(a)) {
                indegree[b]--; //진입차수 감소
            }

            for(int i = 1; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    pq.offer(i);
                    indegree[i]--; //방문체크 -1
                }
            }
        }

        //result
        String result = String.join(" ", list);
        System.out.println(result);

        br.close();
    }
}
