#include <string>
#include <vector>
#include <queue>
#include <iostream>
using namespace std;

vector<string> solution(vector<string> s) {
    vector<string> answer;
    for(string str: s){
        deque<char> dq;
        int cnt=0,idx=0,len = str.size();
        //110개수 세기, 제거한 값 dq에 넣기
        for(int i=0;i<len;i++){
            char c = str[i];
            dq.push_back(c);
            idx++;
            if(idx>2){
                if(dq[idx-3]=='1' && dq[idx-2]=='1' && dq[idx-1]=='0'){
                    dq.pop_back();
                    dq.pop_back();
                    dq.pop_back();
                    cnt++;
                    idx-=3;
                }
            }
        }
        string result="";
        if(idx<3){      //1, 01, 11을 잡기 위해
            bool addFirst = false;
            if(idx==1 && dq[0]=='1') addFirst=true;
            else if(idx==2 && dq[1]=='1') addFirst=true;
            
            for(int i=0;i<idx;i++)
                result+=dq[i];
            
            if(addFirst){
                while(cnt-->0)
                    result="110"+result;
            }
            else{
                while(cnt-->0)
                    result+="110";
            }
        }
        else{
            for(int i=0;i<idx;i++){
                if(i<idx-2 && dq[i]=='1' && dq[i+1]=='1' && dq[i+2]=='1'){
                    while(cnt--)
                        result+="110";
                    while(i<idx)
                        result+=dq[i++];
                }
                else result+=dq[i];
            }
            while(cnt-->0)
                result+="110";
            
        }
        answer.push_back(result);
    }
    return answer;
}
