#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#include <iostream>
using namespace std;
struct info{
    int start,end;
};
struct cmp{
    bool operator()(info &a, info &b){
        if(a.end==b.end){
            return a.start > b.start;
        }
        else return a.end > b.end;
    }
};

int solution(vector<vector<int>> routes) {
    int answer = 0;
    priority_queue<info,vector<info>,cmp> pq;
    for(int i=0;i<routes.size();i++){
        pq.push({routes[i][0],routes[i][1]});
    }
    answer++;
    int left = pq.top().start;
    int right = pq.top().end;
    pq.pop();
    while(!pq.empty()){
        int cl = pq.top().start;
        int cr = pq.top().end;
        pq.pop();
        if(right<cl){       //새로운 단속
            answer++;
            right = cr;
        }
        /* 뒤쪽 오름차순 정렬 + left는 비교대상이 아니기 때문에 필요없음
        else{       //겹칠 때
            left = max(left,cl);
            right = min(right,cr);
        }*/
    }
    return answer;
}
