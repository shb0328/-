import java.util.*;
import java.io.*;

class Solution {
    static class Info {
		int y, x, dir, val;

		public Info(int y, int x, int dir, int val) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.val = val;
		}
	};

	final static int dx[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	final static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	final static int MAX = 101;
	static HashSet<Long> set;
	static int bigX, smallX, bigY, smallY, row, col;
	static boolean canRot[];

	static boolean cal(int x1, int y1, int x2, int y2) {
		long first = x1 * MAX + y1;
		long sec = x2 * MAX + y2;
		long val = first * MAX * MAX + sec;
		long val2 = sec * MAX * MAX + first;
		if (set.contains(val) || set.contains(val2))
			return false;
		set.add(val);
		set.add(val2);
		return true;
	}

	static void calRot(int y, int x, int dir,int[][] board) {
		canRot = new boolean[8];
		int cnt = 3;
		int nd = dir;
		int ny,nx;
		//시계방향
		while(cnt-->0) {
			nd = (nd+1)%8;
			ny = y+ dy[nd];
			nx = x+ dx[nd];
			if(nx>=0 && ny>=0 && nx<col && ny<row && board[ny][nx]==0) {
				nd = (nd+1)%8;
				ny = y+ dy[nd];
				nx = x+ dx[nd];
				if(nx>=0 && ny>=0 && nx<col && ny<row && board[ny][nx]==0) {
					canRot[nd]=true;
				}
				else break;
			}
			else break;
		}
		cnt=3;
		nd=dir;
		ny=y;
		nx=x;
		//반시계
		while(cnt-->0) {
			nd = (nd+7)%8;
			ny = y+ dy[nd];
			nx = x+ dx[nd];
			if(nx>=0 && ny>=0 && nx<col && ny<row && board[ny][nx]==0) {
				nd = (nd+7)%8;
				ny = y+ dy[nd];
				nx = x+ dx[nd];
				if(nx>=0 && ny>=0 && nx<col && ny<row && board[ny][nx]==0) {
					canRot[nd]=true;
				}
				else break;
			}
			else break;
		}
		
	}

	static boolean checkCanGo(int y, int x, int[][] board) {
		if(x >= 0 && y >= 0 && x < col && y < row && board[y][x] == 0) return true;
		return false;
	}
	
	public static int solution(int[][] board) {
		int answer = 0;
		row = board.length;
		col = board[0].length;
		set = new HashSet<>();
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(0, 0, 2, 0));
		cal(0, 0, 1, 0);

		while (!q.isEmpty()) {
			Info ii = q.poll();
			int cx = ii.x;
			int cy = ii.y;
			int cv = ii.val;
			int cd = ii.dir;
			int sx = ii.x + dx[cd];
			int sy = ii.y + dy[cd];
			if ((cx == col - 1 && cy == row - 1) || (sx == col - 1 && sy == row - 1)) {
				answer = cv;
				break;
			}
			calRot(cy, cx, cd, board);
			for (int i = 0; i < 8; i += 2) {
				if (i == cd || !canRot[i])	continue;
				int nsx = cx + dx[i];
				int nsy = cy + dy[i];
				if (checkCanGo(nsy,nsx,board)) {
					if (cal(nsx, nsy, cx, cy))
						q.offer(new Info(cy, cx, i, cv + 1));
				}
			}
			calRot(sy, sx, (cd+4)%8, board);
			for (int i = 0; i < 8; i += 2) {
				int nd = (i + 4) % 8;
				if (nd == cd || !canRot[i]) continue;
				int nx = sx + dx[i];
				int ny = sy + dy[i];
				if (checkCanGo(ny,nx,board)) {
					if (cal(nx, ny, sx, sy))
						q.offer(new Info(sy, sx, i, cv + 1));
				}
			}
			
			//그대로 이동
			for(int i=0;i<8;i+=2) {
				int nx = cx+dx[i];
				int ny = cy+dy[i];
				int nsx = sx+dx[i];
				int nsy = sy+dy[i];
				if(checkCanGo(ny,nx,board) && checkCanGo(nsy,nsx,board))
					if(cal(nx,ny,nsx,nsy))
						q.offer(new Info(ny,nx,i,cv+1));
			}
			
		}
		return answer;
	}
}
