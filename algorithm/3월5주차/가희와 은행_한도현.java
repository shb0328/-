import java.util.*;
import java.io.*;

public class Main {
    public static class Person{
        int px;
        int tx;
        int depTime;
        public Person(){}
        public Person(int a,int b){
            px = a;
            tx = b;
        }
        public Person(int a,int b,int c){
            px = a;
            tx = b;
            depTime = c;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //시작 대기자 수, 직원이 한번에 처리 가능한 시간, 처리자 저장할 시간, 나중에 들어올 사람 수
        int N,T,W,M;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Queue<Person> q = new LinkedList<Person>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int customerNumber = Integer.parseInt(st.nextToken());
            int tx = Integer.parseInt(st.nextToken());
            q.add(new Person(customerNumber,tx));
        }
        M = Integer.parseInt(br.readLine());
        Person customer[] = new Person[M];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int customerNumber =  Integer.parseInt(st.nextToken());
            int tx =  Integer.parseInt(st.nextToken());
            int depTime =  Integer.parseInt(st.nextToken());
            customer[i] = new Person(customerNumber,tx,depTime);
        }
        Arrays.sort(customer, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.depTime-o2.depTime;
            }
        });
        int idx = 0;
        int time = 0;
        StringBuilder ans = new StringBuilder();
        while(time<W){
            Person frontPerson = q.poll();
            if(frontPerson.tx<=T){ //카운터에서 고객의 모든 일을 시간 안에 처리 가능한 경우
                while(time<W && frontPerson.tx > 0){
                    ans.append(frontPerson.px+"\n");
                    time++;
                    frontPerson.tx--;
                    if(idx<M && customer[idx].depTime==time){
                        q.add(customer[idx++]);
                    }
                }
            }else{
                int cnt = T;
                while(cnt>0 && time < W){
                    time++;
                    cnt--;
                    ans.append(frontPerson.px+"\n");
                    if(idx<M && customer[idx].depTime==time){
                        q.add(customer[idx++]);
                    }
                }
                frontPerson.tx -= T;
                q.add(frontPerson);
            }

        }
        System.out.println(ans.toString());
    }
}
