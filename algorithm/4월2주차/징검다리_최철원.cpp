#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int solution(int distance, vector<int> rocks, int n) {
    int answer = 0;
    vector<int> v;
    sort(rocks.begin(),rocks.end());
    int cur=0;
    for(int i=0;i<rocks.size();i++){
        int loc = rocks[i];
        v.push_back(loc-cur);
        cur = loc;
    }
    v.push_back(distance-cur);
    int len = v.size();

    int l=0,r=1000000000,mid;
    while(l<=r){
        mid = l + (r-l)/2;
        int idx=0,cnt=0;
        while(idx<len){
            int val = v[idx];
            if(val<mid){        //합쳐야 하는 경우
                while(1){
                    if(cnt==n){     //더 이상 합치지 못하는 경우
                        cnt++;
                        break;
                    }
                    if(idx!=len-1){     //뒤로 합칠 수 있는 경우
                        int next = v[++idx];
                        val+=next;
                        cnt++;
                        if(val>=mid)    break;                        
                        else{       //뒤로 더 합쳐야 하는 경우
                            //계속 진행
                        }
                    }
                    else{       //앞으로 합쳐야 하는 경우(앞은 이미 기준 넘어섬)
                        cnt++;
                        break;
                    }
                }
            }
            else{       //기준 충족하는 경우
                
            }
            idx++;
        }
        if(cnt<=n){     
            l = mid+1;
            answer = mid;
        }
        else{       //합칠 갯수가 부족한 경우
            r = mid-1;
        }

    }
    return answer;
}
