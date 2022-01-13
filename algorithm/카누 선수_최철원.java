import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int test = Integer.parseInt(br.readLine());
	    while(test-->0){
    	    String str = br.readLine();
    	    StringTokenizer st = new StringTokenizer(str);
    	    int weight = Integer.parseInt(st.nextToken());
    	    int num = Integer.parseInt(st.nextToken());
    	    List<Integer> li[] = new List[4];
    	    for(int i=0;i<4;i++){
    	        li[i] = new ArrayList<>();
    	        str = br.readLine();
    	        st = new StringTokenizer(str);
    	        for(int j=0;j<num;j++)
    	            li[i].add(Integer.parseInt(st.nextToken()));
    	    }
    	    
    	    List<Integer> twoGroupSum[] = new List[2];
    	    for(int i=0;i<2;i++){
    	        twoGroupSum[i] = new ArrayList<>();
    	        for(int j=0;j<num;j++)
    	            for(int k=0;k<num;k++)
    	                twoGroupSum[i].add(li[i*2].get(j)+li[i*2+1].get(k));
    	    }
    	    Collections.sort(twoGroupSum[1]);
    	    int result = twoGroupSum[0].get(0)+twoGroupSum[1].get(0);
    	    boolean finish = false;
    	    for(int a : twoGroupSum[0]){
    	        int l=0,r=num*num-1,mid,sum;
    	        while(l<=r){
    	            mid = l + (r-l)/2;
    	            sum = a+twoGroupSum[1].get(mid);
    	            if(Math.abs(result-weight)>Math.abs(sum-weight))    result=sum;
    	            else if(Math.abs(result-weight)==Math.abs(sum-weight))  result = Math.min(result,sum);
    	            if(sum>weight) r = mid-1;           //sum을 줄인다
    	            else if(sum<weight) l = mid+1;      //sum을 늘린다
    	            else{
    	                finish=true;
    	                break;
    	            }
    	        }
    	        if(finish) break;
    	    }
    	    System.out.println(result);
	    }
	}
}
