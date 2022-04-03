import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Customer> q = new LinkedList<>();
        PriorityQueue<Customer> customer_list;
 
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
 
        for(int i = 0; i < n; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            int num = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            q.offer(new Customer(num, time, 0));
        }
 
        int m = Integer.parseInt(br.readLine());
        customer_list = new PriorityQueue<>((o1, o2) -> o1.input_time - o2.input_time);
        for(int i = 0; i < m; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            int num = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int input_time = Integer.parseInt(st.nextToken());
            customer_list.offer(new Customer(num, time, input_time));
        }
        
        StringBuilder sb = new StringBuilder();
        int current_time = 0;
        boolean flag = true;
        while(current_time < w) {
            Customer current = q.poll();
 
            if(current.time > t) {
                for(int i = 0; i < t; i++) {
                    if(current_time >= w) {
                    	flag = false;
                    	break;
                    }
                    sb.append(current.num + "\n");
                    current_time++;
                }
            } else {
                for(int i = 0; i < current.time; i++) {
                    if(current_time >= w) {
                    	flag = false;
                    	break;
                    }
                    sb.append(current.num + "\n");
                    current_time++;
                }
            }
            if(!flag) break;
            while(!customer_list.isEmpty() && customer_list.peek().input_time <= current_time) {
                q.offer(customer_list.poll());
            }
 
            if(current.time > t) {
                current.time -= t;
                q.offer(current);
            }
        }
        
        System.out.print(sb.toString());
    }
 
    public static class Customer {
        int num, time, input_time;
 
        public Customer(int num, int time, int input_time) {
            this.num = num;
            this.time = time;
            this.input_time = input_time;
        }
    }
}
