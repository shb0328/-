import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static class Node{
        int m;
        int p;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N,W;
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Node node[] = new Node[N];

        int answer = 0;
        for(int i=0;i<N;i++){
            node[i] = new Node();
            st = new StringTokenizer(br.readLine());
            node[i].m = Integer.parseInt(st.nextToken());
            node[i].p = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(node, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.p-o1.p;
            }
        });
        int idx = 0;
        while(W>0){
            if(node[idx].m<=W){
                answer += node[idx].p * node[idx].m;
                W-= node[idx].m;
            }else{
                answer += node[idx].p * W;
                W = 0;
            }
            idx++;
        }
        System.out.println(answer);
    }
}
