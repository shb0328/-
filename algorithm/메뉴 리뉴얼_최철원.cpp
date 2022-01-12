#include <string>
#include <vector>
#include <algorithm>
#include <set>
#include <iostream>
using namespace std;
set<char> menu[20];
vector<char> v,allMenu;
vector<string> temp;
set<char> s;
int maxCnt,num;
bool check[26]={false,};

void canBcourse(){
    int cnt=0;
    string str="";
    for(int i=0;i<v.size();i++)
        str+=v[i];
    
    for(int i=0;i<num;i++){
        bool haveAll=true;
        for(int j=0;j<v.size();j++){
            char c = v[j];
            if(menu[i].find(c)==menu[i].end()){
                haveAll=false;
                break;
            }
        }
        if(haveAll) cnt++;
    }
    if(cnt>1){
        if(maxCnt<cnt){
            maxCnt=cnt;
            temp.clear();
            temp.push_back(str);
        }
        else if(maxCnt==cnt)
            temp.push_back(str);
    }
}

void selNmenu(int lastIdx, int cnt, int full){
    if(cnt==full){
        canBcourse();
        return;
    }
    for(int i=lastIdx;i<allMenu.size();i++){
        if(check[i]) continue;
        check[i]=true;
        v.push_back(allMenu[i]);
        selNmenu(i+1,cnt+1,full);
        v.pop_back();
        check[i]=false;
    }
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;
    set<string> totalAvailCourse;
    num = orders.size();
    //모든 메뉴 넣기
    for(int k=0;k<num;k++){
        string str = orders[k];
        for(int i=0;i<str.size();i++){
            char c = str[i];
            s.insert(c);
            menu[k].insert(c);
        }
    }
    for(auto it = s.begin();it!=s.end();it++)
        allMenu.push_back(*it);
    
    for(int i=0;i<course.size();i++){
        maxCnt=0;
        v.clear();
        selNmenu(0,0,course[i]);
        for(string ss : temp)
            totalAvailCourse.insert(ss);
    }
    for(auto it = totalAvailCourse.begin();it!=totalAvailCourse.end();it++)
        answer.push_back(*it);
    return answer;
}
