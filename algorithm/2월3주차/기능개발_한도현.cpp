#include <string>
#include <vector>
#include <iostream>
using namespace std;

vector<int> pro;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
	vector<int> answer;
	pro = progresses;
	int size = pro.size();
	int target = 0;
	while (target != size) {
		if (pro[target] >= 100) {
			int cnt = 0;
			int to = target;
			while (to != size) {
				if (pro[to] >= 100) {
					cnt++;
					target++;
				}
				else {
					break;
				}
				to++;
			}
			answer.push_back(cnt);
		}
		for (int i = target; i < size; i++) {
			pro[i] += speeds[i];
		}
	}

	return answer;
}
