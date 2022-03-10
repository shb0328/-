#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

class Solution {
public:
    int sum=0;
    int num,selectAll;
    vector<int> v, canMakeIdxList,v2;
    bool check[15]={false,};
    bool result = false;
    
    void dfs(int idx, int addResult, int idxSum){
        if(addResult==sum/4){
            canMakeIdxList.push_back(idxSum);
            return;
        }
        if(idx==num)    return;
        if(addResult+v[idx]<=sum/4) dfs(idx+1,addResult+v[idx], idxSum+(1<<idx));
        dfs(idx+1,addResult, idxSum);
    }
    
    void getComb(int idx, int cnt, int addAll){
        if(cnt==4){
            if(addAll==selectAll){
                result = true;
                for(int a: v2)
                    cout<<a<<" ";
                cout<<endl;
            }
            return;
        }
        for(int i=idx;i<canMakeIdxList.size();i++){
            int val = canMakeIdxList[i];
            if(check[i]) continue;
            if(addAll+val<=selectAll){
                check[i]=true;
                v2.push_back(i);
                getComb(idx+1,cnt+1,addAll+val);
                check[i]=false;
                v2.pop_back();
            }
        }
    }
    
    bool makesquare(vector<int>& matchsticks) {
        num = matchsticks.size();
        sort(matchsticks.begin(),matchsticks.end());
        v = matchsticks;
        for(int a: matchsticks)
            sum+=a;
        if(sum%4!=0) return false;
        dfs(0,0,0);
        if(canMakeIdxList.size()<4) return false;        //만들 수 있는 경우의 수가 4가지도 안되는 경우
        sort(canMakeIdxList.begin(),canMakeIdxList.end());
        selectAll = (1<<num)-1;
        for(int a: canMakeIdxList)
            cout << a<<" ";
        cout<<endl;
        cout << "SelectAll: "<<selectAll<<endl;
        getComb(0,0,0);
        return result;
    }
};
