package baekjoon;
import java.util.*;

public class baekjoon_1644_소수의연속합 {
    public static int N;
    public static int noSosu[];
    public static int sosuCnt;
    //소수 저장
    public static void init(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        noSosu = new int[N+1];
        noSosu[0] = noSosu[1] = 1;
        sosuCnt = 0;
        for(int i = 2;i<=N;i++){
            if(noSosu[i]==1){
                continue;
            }
            sosuCnt++;
            for(int j=i*2;j<=N;j+=i){
                noSosu[j] = 1;
            }
        }
    }

    public static void main(String[] args) {
        init();
        int answer = 0;
        long S[] = new long[sosuCnt+1];
        int idx = 1;
        long tmpSum = 0;
        for(int i=0;i<=N;i++){
            if(noSosu[i]==0){
                tmpSum += i;
                S[idx++] += tmpSum;
            }
        }

        int left = 0;
        int right = 1;
        while(right<sosuCnt+1){
            long sum = S[right]-S[left];
            if((int)sum == N){
                answer++;
                right++;
            }
            else if((int)sum > N){
                left++;
            }else{
                right++;
            }
        }
        System.out.println(answer);
    }
}
