#include <iostream>
#include <string>
#include <algorithm>
#include <stack>
using namespace std;
struct info{
    char c;
    int idx;
};

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int num,answer=0;
    string str;
    cin>>num>>str;
    stack<info> s;
    
    for(int i=0;i<num;i++){
        char c = str[i];
        if(s.empty()){
            s.push({c,i});
            continue;
        }
        char topChar = s.top().c;
        if(topChar=='(' && c==')') s.pop();
        else s.push({c,i});
    }
    int temp = num;
    while(!s.empty()){
        int idx = s.top().idx;
        int diff = temp-idx-1;
        if(diff>1) answer = max(answer, diff);
        temp=idx;
        s.pop();
    }
    answer = max(answer,temp);
    cout<<answer;
    return 0;
}
