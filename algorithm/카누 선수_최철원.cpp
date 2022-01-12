#include <iostream>
#include <set>
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int test,weight,num,val;
    cin >> test;
    while(test--){
        cin>>weight>>num;
        vector<int> v[4];
        for(int i=0;i<4;i++){
            for(int j=0;j<num;j++){
                cin>>val;
                v[i].push_back(val);
            }
        }
        vector<int> twoSum[2];
        for(int i=0;i<2;i++)
            for(int j=0;j<num;j++)
                for(int k=0;k<num;k++)
                    twoSum[i].push_back(v[i*2][j]+v[i*2+1][k]);
        sort(twoSum[1].begin(),twoSum[1].end());
        
        int result = twoSum[0][0]+twoSum[1][0];
        bool finish = false;
        for(int i=0;i<num*num;i++){
            int l=0,r=num*num-1,mid,firVal = twoSum[0][i];
            while(l<=r){
                mid = l +(r-l)/2;
                int sum = firVal+twoSum[1][mid];
                if(abs(result-weight)>abs(weight-sum))   result = sum;
                else if(abs(result-weight)==abs(weight-sum)) result = min(result,sum);
                if(sum<weight)  l = mid+1;    //sum을 늘린다
                else if(sum>weight) r = mid-1;        //sum을 줄인다
                else{       //정확히 sum
                    finish=true;
                    break;
                }
            }
            if(finish) break;
        }
        cout<<result<<'\n';
    }
    return 0;
}
