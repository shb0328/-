import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int num = Integer.parseInt(br.readLine());
	    String str = br.readLine();
	    StringTokenizer st = new StringTokenizer(str);
	    
	    //초기화
	    Map<Integer,Integer> map = new HashMap<>();
	    int arr[] = new int[num];
	    int cnt[] = new int[1000001];
	    int ans[] = new int[num];
	    int maxi = -1;
	    Stack<Integer> s = new Stack<>();       //index 넣기
	    
	    for(int i=0;i<num;i++){
	        arr[i] = Integer.parseInt(st.nextToken());
	        cnt[arr[i]]++;
	    }
	    
	    for(int i=num-1;i>=0;i--){
	        int nowCnt = cnt[arr[i]];
	        if(!s.isEmpty() && nowCnt>=maxi) s.clear();
	        while(!s.isEmpty()){
	            int idx = s.peek();
	            if(cnt[arr[idx]]>nowCnt){
	                ans[i] = arr[idx];
	                break;
	            }
	            else s.pop();
	        }
	        maxi = Math.max(maxi,nowCnt);
	        if(s.isEmpty()) ans[i]=-1;
	        s.add(i);
	    }
	    StringBuilder sb = new StringBuilder();
		for(int i=0;i<num;i++)
			sb.append(ans[i] + " ");
		System.out.println(sb.toString());
	}
}
