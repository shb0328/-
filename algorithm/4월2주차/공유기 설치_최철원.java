import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str = br.readLine();
	    StringTokenizer st = new StringTokenizer(str);
	    
	    int num = Integer.parseInt(st.nextToken());
	    int install = Integer.parseInt(st.nextToken());
	    List<Integer> li = new ArrayList<>();
	    
	    for(int i=0;i<num;i++){
	        int val = Integer.parseInt(br.readLine());
	        li.add(val);
	    }
	    Collections.sort(li);
	    int l=0,r=1000000001,mid,answer=0;
	    while(l<=r){
	        mid = l +(r-l)/2;
	        int cnt=1,loc=li.get(0);
	        for(int i=1;i<num;i++){
	            int cur = li.get(i);
	            int diff = cur-loc;
	            if(diff>=mid){
	                loc = cur;
	                cnt++;
	            }
	            if(cnt>install) break;
	        }
	        if(cnt<install) r = mid-1;
	        else{
	            answer = mid;
	            l = mid+1;
	        }
	    }
	    System.out.println(answer);
	}
}
