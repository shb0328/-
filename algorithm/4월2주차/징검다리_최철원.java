import java.util.*;
import java.io.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        //초기화
        int answer = 0;
        int l = 0,r=1000000001,mid;
        List<Integer> li = new ArrayList<>();
        
        Arrays.sort(rocks);
        li.add(rocks[0]);
        for(int i=1;i<rocks.length;i++)
            li.add(rocks[i]-rocks[i-1]);
        li.add(distance - rocks[rocks.length-1]);
        int len = li.size();
        
        //바위간의 거리가 mid 이상의 거리가 아니라면 바위 제거
        while(l<=r){
            mid = l + (r-l)/2;
            int cnt = 0;
            int idx = 0;
            while(idx<len && cnt<=n){
                int a = li.get(idx++);
                if(a>=mid)  continue;
                else{
                    int sum = a;
                    while(idx<len){
                        sum+=li.get(idx++);
                        cnt++;
                        if(sum>=mid)    break;
                    }
                    if(sum<mid) cnt++;      //우측으로 끝까지 합해도 mid에 못미치는 경우, 앞이랑 합한다
                }
            }
            if(cnt>n)   r = mid-1;
            else if(cnt<=n){
                l= mid+1;
                answer = mid;         //해당 줄은 cnt==n일때만 해야 하는게 아닌가.. 문제 오류 아닌가?
            }
        }
        return answer;
    }
}
