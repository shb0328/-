#define MAX 101
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <map>
#include <iostream>
using namespace std;

struct info {
	int y, x, val;
};
struct cmp {
	bool operator()(info &a, info &b) {
		if (a.y == b.y) return a.x > b.x;
		return a.y > b.y;
	}
};

map<long long, int> m;
int arr[100][100];
int row, col;
int dx[8] = { 0,1,1,1,0,-1,-1,-1 };
int dy[8] = { -1,-1,0,1,1,1,0,-1 };
int canGo[8];
queue<info> q;

bool canMove(int y, int x) {
	if (x >= 0 && y >= 0 && x < col && y < row && arr[y][x] == 0) return true;
	return false;
}

long long calValue(int y1, int x1, int y2, int x2) {
	priority_queue<info, vector<info>, cmp> pq;
	pq.push({ y1,x1,0 });
	pq.push({ y2,x2,0 });
	long long fx = pq.top().x;
	long long fy = pq.top().y;
	long long rightVal = fx * MAX + fy;
	rightVal *= (MAX*MAX);
	pq.pop();
	long long sx = pq.top().x;
	long long sy = pq.top().y;
	pq.pop();
	long long leftVal = sx * MAX + sy;
	rightVal += leftVal;
	return rightVal;
}

bool checkInMap(int y1, int x1, int y2, int x2, int val) {
	long long calVal = calValue(y1, x1, y2, x2);
	if (m.find(calVal) == m.end()) {
		m[calVal] = val;
		return true;
	}
	else {
		if (m[calVal] > val) {
			m[calVal] = val;
			return true;
		}
		return false;
	}
}

void calRotate(int y, int x, int ty, int tx) {
	for (int i = 0; i < 8; i++)
		canGo[i] = 10;
	int ny, nx, cd=0, cnt = 0,sd;
	for (int i = 0; i < 8; i += 2) {
		ny = y + dy[i];
		nx = x + dx[i];
		if (nx == tx && ny == ty) {
			sd = i;
			break;
		}
	}
	cd = (sd + 1) % 8;

	//시계방향
	while (cnt++ < 4) {
		ny = y + dy[cd];
		nx = x + dx[cd];
		if (canMove(ny, nx)) {
			cd = (cd + 1) % 8;
			ny = y + dy[cd];
			nx = x + dx[cd];
			if (canMove(ny, nx)) {
				canGo[cd] = min(canGo[cd], cnt);
				cd = (cd + 1) % 8;
			}
			else break;
		}
		else break;
	}

	//반시계 방향
	cd = (sd + 7) % 8;
	cnt = 0;
	while (cnt++ < 4) {
		ny = y + dy[cd];
		nx = x + dx[cd];
		if (canMove(ny, nx)) {
			cd = (cd + 7) % 8;
			ny = y + dy[cd];
			nx = x + dx[cd];
			if (canMove(ny, nx)) {
				canGo[cd] = min(canGo[cd], cnt);
				cd = (cd + 7) % 8;
			}
			else break;
		}
		else break;
	}
}

void move4Dir(int y1, int x1,int y2, int x2, int val) {
	for (int i = 0; i < 8; i += 2) {
		int nay = y1 + dy[i];
		int nax = x1 + dx[i];
		int nby = y2 + dy[i];
		int nbx = x2 + dx[i];
		int cnt = 1;
		while (1) {
			if (canMove(nay, nax) && canMove(nby, nbx)) {     //이동은 가능
				//이미 존재하는지 확인
				int nv = val + cnt;
				if (checkInMap(nay, nax, nby, nbx, nv)) {
					q.push({ nay,nax,nv });
					q.push({ nby,nbx,nv });
					nay += dy[i];
					nax += dx[i];
					nby += dy[i];
					nbx += dx[i];
					cnt++;
				}
				else break;
			}
			else break;
		}
	}
}

int solution(vector<vector<int>> board) {
	int answer = 0;
	int ny, nx, nv;
	row = board.size();
	col = board[0].size();
	cout << "Row,col: " << row << " " << col << endl;
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			arr[i][j] = board[i][j];
			cout << arr[i][j];
		}
		cout << endl;
	}
	q.push({ 0,0,0 });
	q.push({ 0,1,0 });

	checkInMap(0, 0, 0, 1, 0);

	while (!q.empty()) {
		int fx = q.front().x;
		int fy = q.front().y;
		int cv = q.front().val;
		q.pop();
		int sx = q.front().x;
		int sy = q.front().y;
		q.pop();
		long long calVal = calValue(fy, fx, sy, sx);
		cout << "fy,fx,sy,sx,cv,calVal: " << fy << " " << fx << " " << sy << " " << sx << " " << cv << " " << calVal << endl;
		
		if (m[calVal] != cv) continue;
		if ((fx == col - 1 && fy == row - 1) || (sx == col - 1 && sy == row - 1)) {
			answer = cv;
			break;
		}
		//fy,fx 기준
		calRotate(fy, fx, sy, sx);
		for (int i = 0; i < 8; i += 2) {
			if (canGo[i] != 10) {
				ny = fy + dy[i];
				nx = fx + dx[i];
				nv = cv + canGo[i];
				if (checkInMap(fy, fx, ny, nx, nv)) {
					q.push({ fy,fx,nv });
					q.push({ ny,nx,nv });
				}
			}
		}

		//sy,sx 기준
		calRotate(sy, sx, fy, fx);
		for (int i = 0; i < 8; i += 2) {
			if (canGo[i] != 10) {
				ny = sy + dy[i];
				nx = sx + dx[i];
				nv = cv + canGo[i];
				if (checkInMap(sy, sx, ny, nx, nv)) {
					q.push({ sy,sx,nv });
					q.push({ ny,nx,nv });
				}
			}
		}

		//상하좌우로 이동
		move4Dir(fy, fx, sy, sx, cv);
	}
	return answer;
}
