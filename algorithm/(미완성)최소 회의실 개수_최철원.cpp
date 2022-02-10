#include <iostream>
#include <stack>
#include <queue>
#include <stack>
#include <algorithm>
using namespace std;
struct info {
	int left, right;
};
struct cmp {
	bool operator()(info &a, info &b) {
		if (a.left == b.left)		return a.right > b.right;
		return a.left > b.left;
	}
};

int main() {
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	int num, a, b, result = 1;
	cin >> num;
	priority_queue<info, vector<info>, cmp> pq;

	for (int i = 0; i < num; i++) {
		cin >> a >> b;
		pq.push({ a,b });
	}
	stack<info> s;
	info ii;
	ii = pq.top();
	pq.pop();
	int sl = ii.left, sr = ii.right;
	s.push({ sl,sr });
	int len;

	while (!pq.empty()) {
		int cl = pq.top().left;
		int cr = pq.top().right;
		pq.pop();
		if (cl < sr)	s.push({ cl,cr });
		else {
			s.pop();
			while (!s.empty()) {
				sl = s.top().left;
				sr = s.top().right;
				if (cl < sr) break;
				s.pop();
			}
			s.push({ cl,cr });
		}
		len = s.size();
		result = max(result, len);
	}
	cout << result;
	return 0;
}
