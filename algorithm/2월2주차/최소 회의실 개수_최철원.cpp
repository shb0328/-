#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;
struct info {
	int left, right;
};
struct cmp {
	bool operator()(info &a, info &b) {
		if (a.left == b.left)		return a.right < b.right;
		return a.left > b.left;
	}
};

int main() {
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	int num, a, b, result = 1;
	cin >> num;
	priority_queue<info, vector<info>, cmp> pq;
    priority_queue<int,vector<int>,greater<int>> haveRight;
    
	for (int i = 0; i < num; i++) {
		cin >> a >> b;
		pq.push({ a,b });
	}
	info ii;
	ii = pq.top();
	pq.pop();
	haveRight.push(ii.right);
	int len;

	while (!pq.empty()) {
		int cl = pq.top().left;
		int cr = pq.top().right;
		int hr = haveRight.top();
		pq.pop();
		if (cl < hr)	haveRight.push(cr);
		else {
			haveRight.pop();
			while (!haveRight.empty()) {
				hr = haveRight.top();
				if (cl < hr) break;
				haveRight.pop();
			}
			haveRight.push(cr);
		}
		len = haveRight.size();
		result = max(result, len);
	}
	cout << result;
	return 0;
}
