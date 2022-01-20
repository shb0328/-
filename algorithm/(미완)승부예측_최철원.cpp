#include <iostream>
#include <string>
#include <sstream>
#include <map>
#include <algorithm>
#include <vector>
#include <queue>
#include <set>
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
		cout << "\nval: ";
		for (int i = 0; i < 6; i++) {
			int val = v[i];
			cout << val << " ";
			if (val == 0) points[matches[i][0]] += 3;
			else if(val==2) points[matches[i][1]] += 3;
			else {
				points[matches[i][0]]++;
				points[matches[i][1]]++;
			}
		}
		set<int> set;
		priority_queue<info, vector<info>, cmp> pq;
		cout << "\npoints: ";
		for (int i = 0; i < 4; i++) {
			set.insert(points[i]);
			cout << points[i] << " ";
			pq.push({ i,points[i] });
		}
		int times, len = set.size();
		if (len == 4)	times = 2;		//1,2,3,4등
		else if (len == 3)	times = 3;
		else times = 4;
		cout << "\nidx: ";
		while (times--) {
			int idx = pq.top().idx;
			cout << idx << " ";
			percent[idx] += win;
			pq.pop();
		}
		cout <<"\n"<<len << " " << win << endl;
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
	int cnt = 0;
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
	for (int i = 0; i < 4; i++)
		cout << percent[i] << " ";
	return 0;
}
