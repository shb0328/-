import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int num;
    static List<Integer> li[];
    static int height[];
    static int par[];
    
    static void makeTree(int idx, int level){
        height[idx] = level;
        for(int i=0;i<li[idx].size();i++){
            int next = li[idx].get(i);
            if(height[next]==-1){
                par[next]=idx;
                makeTree(next,level+1);
            }
        }
    }
    
    static int lca(int a, int b){
        int ah = height[a];
        int bh = height[b];
        while(ah<bh){
            b = par[b];
            bh--;
        }
        while(ah>bh){
            a = par[a];
            ah--;
        }
        while(b!=a){
            a=par[a];
            b=par[b];
        }
        return a;
    }
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		num = Integer.parseInt(br.readLine());
		//초기화
		li = new List[num+1];
		height = new int[num+1];
		par = new int[num+1];
		for(int i=1;i<=num;i++){
		    li[i] = new ArrayList<>();
		    height[i] = -1;
		}
		
		for(int i=0;i<num-1;i++){
		    str = br.readLine();
		    st = new StringTokenizer(str);
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    li[a].add(b);
		    li[b].add(a);
		}
		par[0]=-1;
		makeTree(1,0);
        
		int query = Integer.parseInt(br.readLine());
		for(int i=0;i<query;i++){
		    str = br.readLine();
		    st = new StringTokenizer(str);
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    System.out.println(lca(a,b));
		}
	}
}
