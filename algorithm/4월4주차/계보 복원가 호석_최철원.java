import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    
    static int node;
    static List<String> people;
    static List<Integer> li[];
    static List<Integer> directChild[];
    static int conn[];
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    node = Integer.parseInt(br.readLine());
	    //초기화
	    people = new ArrayList<>();
	    li = new List[node];
	    directChild = new List[node];
	    for(int i=0;i<node;i++){
	        li[i] = new ArrayList<>();
	        directChild[i] = new ArrayList<>();
	    }
	    conn = new int[node];
	    HashMap<String, Integer> map = new HashMap<>();
	    HashMap<Integer, String> revMap = new HashMap<>();
	    
	    String str = br.readLine();
	    StringTokenizer st = new StringTokenizer(str);
	    for(int i=0;i<node;i++){
	        String ss = st.nextToken();
	        people.add(ss);
	    }
	    Collections.sort(people);
	    int idx=0;
	    for(String ss : people){
	        map.put(ss,idx);
	        revMap.put(idx++,ss);
	    }
	    
	    int edge = Integer.parseInt(br.readLine());
	    for(int i=0;i<edge;i++){
	        str = br.readLine();
	        st = new StringTokenizer(str);
	        String child = st.nextToken();
	        String par = st.nextToken();
	        int cidx = map.get(child);
	        int pidx = map.get(par);
	        
	        conn[cidx]++;
	        li[pidx].add(cidx);
	    }
	    List<String> ancestor = new ArrayList<>();
	    Queue<String> q = new LinkedList<>();
	    for(int i=0;i<node;i++)
	        if(conn[i]==0){
	            ancestor.add(revMap.get(i));
	            q.offer(revMap.get(i));
	        }
	    System.out.println(ancestor.size());
	    for(String ss: ancestor)
	        System.out.print(ss+" ");
	   System.out.println();
	   while(!q.isEmpty()){
	       String ss = q.poll();
	       int sidx = map.get(ss);
	       for(int i=0;i<li[sidx].size();i++){
	           int next = li[sidx].get(i);
	           if(--conn[next]==0){
	                q.offer(revMap.get(next));
	                directChild[sidx].add(next);
	           }
	       }
	   }
	   
	   for(int i=0;i<node;i++){
	       String ss = people.get(i);
	       int sidx = map.get(ss);
	       System.out.print(ss+" "+directChild[sidx].size()+" ");
	       Collections.sort(directChild[sidx]);
	       for(int k=0;k<directChild[sidx].size();k++)
	            System.out.print(revMap.get(directChild[sidx].get(k))+" ");
	       System.out.println();
	   }
	}
}
