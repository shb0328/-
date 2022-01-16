#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
using namespace std;
bool arr[901][901] = { false, };
int num, n, f, a, b;
vector<int> avail, v, ans;
bool fin = false;

void dfs(int idx, int cnt) {
	if (fin) return;
	if (cnt == n) {
		bool canGo = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) continue;
				if (!arr[v[i]][v[j]]) {
					canGo = false;
					break;
				}
			}
			if (!canGo) break;
		}
		if (canGo) {
			fin = true;
			ans = v;
		}
		return;
	}
	for (int i = idx; i <avail.size(); i++) {
		v.push_back(avail[i]);
		dfs(i + 1, cnt + 1);
		v.pop_back();
	}
}

int main() {
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	cin >> n >> num >> f;
	for (int i = 0; i < f; i++) {
		cin >> a >> b;
		arr[a][b] = true;
		arr[b][a] = true;
	}
	for (int i = 1; i <= num; i++) {
		int cnt = 0;
		for (int j = 1; j <= num; j++)
			if (arr[i][j]) cnt++;
		if (cnt >= n - 1) avail.push_back(i);
	}
	dfs(0, 0);
	if (!fin) cout << -1;
	else {
		for (int a : ans)
			cout << a << '\n';
	}
	return 0;
}
