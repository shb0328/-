import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int num,result;
    static int arr[][];
    static int opposite[] = {5,3,4,1,2,0};
    
    static void simulate(int idx, int height, int sum){
        if(height==num){
            result = Math.max(result,sum);
            return;
        }
        int topVal = arr[height][idx];
        int maxVal = 0, nextTopIdx=0;
        for(int i=0;i<6;i++){
            if(height<num-1 && arr[height+1][i]==topVal) nextTopIdx = opposite[i];
            if(i==idx || i==opposite[idx]) continue;
            maxVal = Math.max(maxVal,arr[height][i]);
        }
        simulate(nextTopIdx,height+1,sum+maxVal);
    }
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    num = Integer.parseInt(br.readLine());
	    
	    //초기화
	    arr = new int[num][6];
	    result =0;
	    
	    for(int i=0;i<num;i++){
	        String str = br.readLine();
	        StringTokenizer st = new StringTokenizer(str);
	        for(int j=0;j<6;j++)
	            arr[i][j] = Integer.parseInt(st.nextToken());
	    }
	    
	    for(int i=0;i<6;i++)
	        simulate(i,0,0);
		System.out.print(result);
	}
}
