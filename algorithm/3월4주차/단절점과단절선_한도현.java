import java.util.*;
import java.io.*;
public class Main{

    public static void main(String[] args) throws Exception {
        StringBuilder sb= new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N+1];
        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a]++;
            arr[b]++;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a==2){
                sb.append("yes\n");
            }else{
                if(arr[b]>=2){
                    sb.append("yes\n");
                }else{
                    sb.append("no\n");
                }
            }
        }
        System.out.println(sb.toString());
    }
}
