#include <iostream>
#include <string>
#include <sstream>
#include <map>
#include <algorithm>
#include <vector>
#include <queue>
struct info {
	int idx, score;
};
struct cmp {
	bool operator()(info &a, info &b) {
		return a.score < b.score;
	}
};
using namespace std;
double percent[4];
double winRate[6][3];
vector<int> v, matches[6];
int totalMatch = 0;

void dfs(int cnt,double win) {
	if(cnt == 6) {
		totalMatch++;
		int points[4] = { 0, };
		for (int i = 0; i < 6; i++) {
			int val = v[i];
			if (val == 0) points[matches[i][0]] += 3;
			else if(val==2) points[matches[i][1]] += 3;
			else {
				points[matches[i][0]]++;
				points[matches[i][1]]++;
			}
		}
		priority_queue<info, vector<info>, cmp> pq;
		vector<info> ranking;
		for (int i = 0; i < 4; i++) 
			pq.push({ i,points[i] });
		while(!pq.empty()){
		    int idx = pq.top().idx;
		    int score = pq.top().score;
		    pq.pop();
		    ranking.push_back({idx,score});
		}
		if(ranking[1].score>ranking[2].score){      //앞 2팀 확정
		    percent[ranking[0].idx]+=win;
		    percent[ranking[1].idx]+=win;
		}
		else if(ranking[0].score==ranking[1].score && ranking[2].score==ranking[3].score){      //1,1,1,1   ->4팀중 2
		    percent[ranking[0].idx]+=win/2;
		    percent[ranking[1].idx]+=win/2;
		    percent[ranking[2].idx]+=win/2;
		    percent[ranking[3].idx]+=win/2;
		}
		else if(ranking[2].score==ranking[3].score){        //1,2,2,2   ->3팀중 1
		    percent[ranking[0].idx]+=win;
		    percent[ranking[1].idx]+=win/3;
		    percent[ranking[2].idx]+=win/3;
		    percent[ranking[3].idx]+=win/3;
		}
		else if(ranking[0].score==ranking[1].score){        //1,1,1,3       ->3팀중 2
		    percent[ranking[0].idx]+=(win*2)/3;
		    percent[ranking[1].idx]+=(win*2)/3;
		    percent[ranking[2].idx]+=(win*2)/3;
		}
		else{       //1,2,2,4           ->2팀중 1
		    percent[ranking[0].idx]+=win;    
		    percent[ranking[1].idx]+=win/2;
		    percent[ranking[2].idx]+=win/2;
		}
		return;
	}
	for (int i = 0; i < 3; i++) {
		double val = winRate[cnt][i];
		if (val == 0) continue;
		v.push_back(i);
		dfs(cnt + 1, win*val);
		v.pop_back();
	}
}

int main() {
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	string str,s;
	map<string, int> m;
	for (int i = 0; i < 4; i++) {
		cin >> str;
		m[str] = i;
	}
	for (int i = 0; i < 6; i++) {
		for(int j=0;j<5;j++) {
			cin >> str;
			if (j < 2)	matches[i].push_back(m[str]);
			else	winRate[i][j - 2] = stod(str);
		}
	}
	dfs(0, 1.0);
	cout << fixed;
	cout.precision(6);
	for (int i = 0; i < 4; i++)
		cout << percent[i] << " ";
	return 0;
}
