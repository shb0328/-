package baekjoon.twentytwo_second_quater;

import java.io.*;
import java.util.*;

public class baekjoon_2110_공유기설치 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N,K;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int arr[] = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int left = 0;
        int right = arr[arr.length-1];
        int answer = 0;
        while(left<=right){
            int mid = (left+right)/2;
            int previous = arr[0];
            int cnt = 1; // cnt가 K면 공유기 설치 끝(성공)
            boolean suc = false;
            for(int i=1;i<N;i++){
                if(arr[i]-previous>=mid){ //설치 가능
                    previous = arr[i];
                    cnt++;
                    if(cnt==K){
                        suc = true;
                        break;
                    }
                }
            }
            if(suc){
                left = mid+1;
                answer=  mid;
            }else{
                right = mid-1;
            }
        }
        System.out.println(answer);
    }
}
