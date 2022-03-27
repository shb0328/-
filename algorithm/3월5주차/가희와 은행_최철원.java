import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static class Info implements Comparable<Info> {
        int idx,needTime,arriveTime;
        public Info(int idx, int needTime, int arriveTime){
            this.idx=idx;
            this.needTime=needTime;
            this.arriveTime=arriveTime;
        }
        @Override
        public int compareTo(Info o){
            return Integer.compare(this.arriveTime,o.arriveTime);
        }
    };
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int num = Integer.parseInt(st.nextToken());
	    int t = Integer.parseInt(st.nextToken());
	    int finTime = Integer.parseInt(st.nextToken());
	    Queue<Info> q = new LinkedList<>();
	    
	    for(int i=0;i<num;i++){
	        st = new StringTokenizer(br.readLine());
	        int idx = Integer.parseInt(st.nextToken());
	        int timeLeft = Integer.parseInt(st.nextToken());
	        q.offer(new Info(idx,timeLeft,0));
	    }
	    int afterOneMin = Integer.parseInt(br.readLine());
	    PriorityQueue<Info> pq = new PriorityQueue<>();
	    
	    for(int i=0;i<afterOneMin;i++){
	        st = new StringTokenizer(br.readLine());
	        int idx = Integer.parseInt(st.nextToken());
	        int timeLeft = Integer.parseInt(st.nextToken());
	        int arriveTime = Integer.parseInt(st.nextToken());
	        pq.offer(new Info(idx,timeLeft,arriveTime));
	    }
	    int curTime = 0;
	    List<String> li = new ArrayList<>();
	    
	    while(!q.isEmpty()){
	        //q에서 원소 하나 빼고 실행
	        Info ii = q.poll();
	        if(ii.needTime<=t){
	            for(int i=curTime;i<Math.min(curTime+ii.needTime,finTime);i++)
	                li.add(String.valueOf(ii.idx));
	            curTime+=ii.needTime;
	        }
	        else{
	            for(int i=curTime;i<Math.min(curTime+t,finTime);i++)
	                li.add(String.valueOf(ii.idx));
	            curTime+=t;
	        }
	        if(curTime>finTime) break;
	        //pq에서 추가될 요소 있는지 확인
	        while(!pq.isEmpty()){
	            int arriveTime = pq.peek().arriveTime;
	            if(arriveTime<=curTime){
	                Info ii2 = pq.poll();
	                q.offer(new Info(ii2.idx, ii2.needTime, ii2.arriveTime));
	            }
	            else break;
	        }
	        //q에서 뺐던 원소가 다시 추가된다면 추가
	        if(ii.needTime>t) q.offer(new Info(ii.idx,ii.needTime-t,ii.arriveTime));
	    }
	    final String answer = String.join("\n",li);
	    System.out.println(answer);
	}
}
