import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static final int MAX = 100*100000;
    static int num,maxWeight,answer;
    static int weight[],val[];
    static int dp[][];      //dp[해당 index까지][가치의 총 합] = 총 무게 W
    
    static void cal(int idx, int weightSum, int valSum){      //현재 index, 무게 총합, 가치 총합
        if(idx==num){
            answer = Math.max(answer,valSum);
            return;
        }
        if(dp[idx][valSum]<=weightSum) return;
        dp[idx][valSum] = weightSum;
        int totWeight = weight[idx]+weightSum;
        int totVal = val[idx]+valSum;
        if(totWeight>maxWeight) cal(idx+1,weightSum,valSum);
        else{   //들 수 있다면 
            //든다
            cal(idx+1,totWeight,totVal);
            //안든다
           cal(idx+1,weightSum,valSum);
        }
    }
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str = br.readLine();
	    StringTokenizer st = new StringTokenizer(str);
	    num = Integer.parseInt(st.nextToken());
	    maxWeight = Integer.parseInt(st.nextToken());
	    //초기화
	    weight = new int[num];
	    val = new int[num];
	    answer=0;
	    dp = new int[num+1][100*1000+1];
	    
	    for(int i=0;i<num;i++){
	        str = br.readLine();
	        st = new StringTokenizer(str);
	        weight[i] = Integer.parseInt(st.nextToken());
	        val[i] = Integer.parseInt(st.nextToken());
	        for(int j=0;j<100*1000+1;j++)
	            dp[i][j]=MAX;
	        
	    }
	    cal(0,0,0);
	    System.out.println(answer);
	}
}
