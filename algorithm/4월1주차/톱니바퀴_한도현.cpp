#include <iostream>
#include <memory.h>
#include <algorithm>

using namespace std;

int K; //회전 횟수
//(톱니바퀴번호, 톱니바퀴 정보(1이면 S ,0이면 N극   ))
int clock[4][8];
bool visited[4];
void show() {
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 8; j++) {
			cout << clock[i][j] << " ";
		}
		cout << endl;
	}
}
void simul(int num, int dir) {
	visited[num] = true;
	bool left, right;  //왼쪽과 오른쪽 톱니바퀴를 돌릴지 여부
	left = right = false;
	if (num-1>=0 && clock[num - 1][2] != clock[num][6]) left = true;
	if (num + 1 < 4 && clock[num + 1][6] != clock[num][2]) right = true;
	int temp = clock[num][7];
	int temp2 = clock[num][0];
	if (dir == 1) { //시계방향회전
		for (int i = 7; i > 0; i--) {
			clock[num][i] = clock[num][i - 1];
		}
		clock[num][0] = temp;
	}
	else { //반시계 회전
		for (int i = 0; i < 7; i++) {
			clock[num][i] = clock[num][i + 1];
		}
		clock[num][7] = temp2;
	}
	if (left&& !visited[num-1]) simul(num - 1, dir*(-1));
	if (right && !visited[num+1]) simul(num + 1, dir*(-1));
}

int cal() {
	int answer = 0;
	if (clock[0][0] == 1) answer += 1;
	if (clock[1][0] == 1) answer += 2;
	if (clock[2][0] == 1) answer += 4;
	if (clock[3][0] == 1) answer += 8;
	return answer;
}
int main() {
	ios_base::sync_with_stdio(false);
	char input[9];
	for (int i = 0; i < 4; i++) {
		cin >> input;
		for (int j = 0; j < 8; j++) {
			clock[i][j] = input[j] - '0';
		}
	}
	cin >> K;
	int number, dir;
	for (int i = 0; i < K; i++) {
		memset(visited, false, sizeof(visited));
		cin >> number >> dir;
		simul(number-1, dir);
		//show();
	}
	cout << cal() << endl;
}
