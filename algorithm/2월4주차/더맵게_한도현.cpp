#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <functional>
using namespace std;

int solution(vector<int> scoville, int K) {
	if (scoville.size() == 1) return -1;
	priority_queue<int, vector<int>, greater<int>> pq;
	for (int i = 0; i < scoville.size(); i++) {
		pq.push(scoville[i]);
	}
	int first, second;
	int cnt = 0;
	while (pq.top() < K) {
		if (pq.size() < 2) return -1;
		first = pq.top(); pq.pop();
		second = pq.top(); pq.pop();
		pq.push(first + (second * 2));
		cnt++;
	}
	return cnt;
}
