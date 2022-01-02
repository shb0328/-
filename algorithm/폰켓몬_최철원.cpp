#include <vector>
#include <map>
#include <cmath>
using namespace std;

int solution(vector<int> nums)
{
    int answer = 0;
    map<int,int> m;
    for(int val : nums){
        if(m.find(val)==m.end())    m[val]=1;
        else    m[val]++;
    }
    return min(nums.size()/2,m.size());
}
