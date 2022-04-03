import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		int cnt[] = new int[1000001];
		int ans[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
			cnt[arr[i]]++;
		}
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(arr[N-1]);
		
		for(int i=N-1;i>=0;i--){
			while(!stack.isEmpty()){
				if(cnt[arr[i]]<cnt[stack.peek()]){
					ans[i] = stack.peek();
					break;
				}else{
					stack.pop();
				}
			}
			if(stack.isEmpty()){
				ans[i] = -1;
			}
			stack.push(arr[i]);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++){
			sb.append(ans[i] + " ");
		}
		System.out.println(sb.toString());
	}

}
