#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;
int N, M;
char board[51][51];
bool visited[51][51][64];
int dy[4] = { -1,0,1,0 };
int dx[4] = { 0,1,0,-1 };
int ans;

struct Node {
	int y;
	int x;
	int key; //0~ 63
};

void bfs(int y, int x) {
	queue<Node> q;
	q.push({ y,x,0 });
	int ret = 0;
	while (!q.empty()) {
		ret++;
		int q_size = q.size();
		for (int i = 0; i < q_size; i++) {
			Node cur = q.front(); q.pop();
			/*cout << "위치 : " << cur.y << "," << cur.x << "  열쇠";
			int A = 1;
			char B = 'a';
			for (int j = 0; j < 6; j++) {
				if ((A << j) & cur.key) {
					cout << B << " ";
				}
				else {
					cout << "0" << " ";
				}
				B++;
			}
			cout << endl;*/
			for (int j = 0; j < 4; j++) {
				int ny = cur.y + dy[j];
				int nx = cur.x + dx[j];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				if (visited[ny][nx][cur.key] || board[ny][nx] == '#') continue;
				if (board[ny][nx] == '1')
				{
					ans = ret;
					return;
				}
				if (board[ny][nx] - 'a' >= 0 && board[ny][nx] - 'a' <= 5)
				{ //열쇠
					int key = (1 << (board[ny][nx] - 'a'));
					visited[ny][nx][key | cur.key] = true;
					q.push({ ny,nx,key | cur.key });
				}
				else if (board[ny][nx] == '.') 
				{
					visited[ny][nx][cur.key] = true;
					q.push({ ny,nx,cur.key });
				}
				else 
				{//문
					int need = 1 << (board[ny][nx] - 'A');
					if (cur.key&need)
					{
						visited[ny][nx][cur.key] = true;
						q.push({ ny,nx,cur.key });
					}
				}

			}
		}
	}
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> board[i][j];
		}
	}
	ans = 0x7fffffff;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (board[i][j] == '0') {
				board[i][j] = '.';
				visited[i][j][0] = true;
				bfs(i, j);
			}
		}
	}
	ans = (ans == 0x7fffffff) ? -1 : ans;
	cout << ans;


}
