import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int node;
    static List<Integer> li[];
    static int arriveNum[];
    static boolean isArticultaionPoint[];
    static boolean isArticultaionEdge[];
    static int cur;
    
    static int findArticPoint(int idx, boolean isRoot){
        int len = li[idx].size();
        arriveNum[idx] = cur++; //도착 순번
        int temp = arriveNum[idx];
        
        for(int i=0;i<len;i++){
            int next = li[idx].get(i);
            if(arriveNum[next]!=0){     //이미 도달한 적 있는 경우
                temp = Math.min(temp,arriveNum[next]);
            }
            else{
                int result = findArticPoint(next,false);
                if(!isRoot && result>=arriveNum[idx])
                    isArticultaionPoint[idx]=true;
                temp = Math.min(temp,result);
            }
        }
        if(isRoot && len>1) isArticultaionPoint[idx]=true;
        return temp;
    }
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    node = Integer.parseInt(br.readLine());
	    
	    //초기화
	    li = new List[node+1];
	    for(int i=1;i<=node;i++)
	        li[i] = new ArrayList<>();
	    arriveNum = new int[node+1];
	    cur=1;
	    isArticultaionPoint = new boolean[node+1];
	    isArticultaionEdge = new boolean[node+1];
	    
	    for(int i=0;i<node-1;i++){
	        String str = br.readLine();
	        StringTokenizer st = new StringTokenizer(str);
	        int a = Integer.parseInt(st.nextToken());
	        int b = Integer.parseInt(st.nextToken());
	        li[a].add(b);
	        li[b].add(a);
	    }
	    findArticPoint(1,true);
	    
	    /*for(int i=1;i<=node;i++){
	        System.out.println("i,isArticultaionPoint[i]: "+i+" "+isArticultaionPoint[i]);
	    }*/
	    int query = Integer.parseInt(br.readLin());
	    for(int q = 0;q<query;i++){
	        String str = br.readLine();
	        StringTokenizer st = new StringTokenizer(str);
	        int a = Integer.parseInt(st.nextToken());
	        int b = Integer.parseInt(st.nextToken());
	        if(a==1) System.out.println(isArticultaionPoint[b] ? "yes" : "no");
	        else System.out.println(isArticultaionEdge[b] ? "yes" : "no");
	    }
	    
	}
}
