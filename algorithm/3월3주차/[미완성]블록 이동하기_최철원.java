import java.util.*;
import java.io.*;

class Solution {
    static class Info implements Comparable<Info>{
		int y, x, dir, val;

		public Info(int y, int x, int dir, int val) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.val = val;
		}
        
        @Override
        public int compareTo(Info o){
            return Integer.compare(this.val,o.val);
        }
	};

	final static int dx[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	final static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	final static int MAX = 101;
	static HashMap<Long,Integer> map;
	static int bigX, smallX, bigY, smallY, row, col;
	static int canRot[];

	static boolean cal(int x1, int y1, int x2, int y2, int sumVal) {
		long first = x1 * MAX + y1;
		long sec = x2 * MAX + y2;
		long val = first * MAX * MAX + sec;
		long val2 = sec * MAX * MAX + first;
		System.out.println("Cal val, val2: "+val+" "+val2);
		System.out.println("y1,x1,y2,x2,sumVal: "+y1+" "+x1+" "+y2+" "+x2+" "+sumVal);
		System.out.println("val이 map에 있는가? :"+map.containsKey(val));
		System.out.println("val2가 map에 있는가? :"+map.containsKey(val2));
		System.out.println();
		if(map.containsKey(val) || map.containsKey(val2)) {
			int originVal = map.get(val);
			if(originVal>sumVal) {
				map.put(val, sumVal);
				map.put(val2, sumVal);
				return true;
			}
			else return false;
		}
		else {
			map.put(val, sumVal);
			map.put(val2, sumVal);
			return true;
		}
	}

	static void calRot(int y, int x, int dir,int[][] board) {
		canRot = new int[8];
        for(int i=0;i<8;i++)
            canRot[i]=10;
		int cnt = 3;
		int nd = dir;
		int ny,nx,val=0;
		//시계방향
		while(cnt-->0) {
            val++;
			nd = (nd+1)%8;
			ny = y+ dy[nd];
			nx = x+ dx[nd];
			if(nx>=0 && ny>=0 && nx<col && ny<row && board[ny][nx]==0) {
				nd = (nd+1)%8;
				ny = y+ dy[nd];
				nx = x+ dx[nd];
				if(nx>=0 && ny>=0 && nx<col && ny<row && board[ny][nx]==0) {
					canRot[nd]=Math.min(canRot[nd],val);
				}
				else break;
			}
			else break;
		}
		cnt=3;
		nd=dir;
		ny=y;
		nx=x;
        val=0;
		//반시계
		while(cnt-->0) {
            val++;
			nd = (nd+7)%8;
			ny = y+ dy[nd];
			nx = x+ dx[nd];
			if(nx>=0 && ny>=0 && nx<col && ny<row && board[ny][nx]==0) {
				nd = (nd+7)%8;
				ny = y+ dy[nd];
				nx = x+ dx[nd];
				if(nx>=0 && ny>=0 && nx<col && ny<row && board[ny][nx]==0) {
					canRot[nd]=Math.min(canRot[nd],val);
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
		map = new HashMap<>();
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(0, 0, 2, 0));
		cal(0, 0, 1, 0, 0);

		while (!pq.isEmpty()) {
			Info ii = pq.poll();
			int cx = ii.x;
			int cy = ii.y;
			int cv = ii.val;
			int cd = ii.dir;
			int sx = ii.x + dx[cd];
			int sy = ii.y + dy[cd];
            System.out.println("cy,cx,sy,sx,cv: " + cy + " " + cx + " " + sy + " " + sx + " " + cv);
			if ((cx == col - 1 && cy == row - 1) || (sx == col - 1 && sy == row - 1)) {
				answer = cv;
				break;
			}
			calRot(cy, cx, cd, board);
			for (int i = 0; i < 8; i += 2) {
				if (i == cd || canRot[i]==10)	continue;
				int nsx = cx + dx[i];
				int nsy = cy + dy[i];
				if (checkCanGo(nsy,nsx,board)) {
					if (cal(nsx, nsy, cx, cy,cv+canRot[i]))
						pq.offer(new Info(cy, cx, i, cv + canRot[i]));
				}
			}
			calRot(sy, sx, (cd+4)%8, board);
			for (int i = 0; i < 8; i += 2) {
				int nd = (i + 4) % 8;
				if (nd == cd || canRot[i]==10) continue;
				int nx = sx + dx[i];
				int ny = sy + dy[i];
				if (checkCanGo(ny,nx,board)) {
					if (cal(nx, ny, sx, sy,cv+canRot[i]))
						pq.offer(new Info(sy, sx, i, cv + canRot[i]));
				}
			}
			
			//그대로 이동
			for(int i=0;i<8;i+=2) {
                if(i%4!=cd%4) continue;
				int nx = cx+dx[i];
				int ny = cy+dy[i];
				int nsx = sx+dx[i];
				int nsy = sy+dy[i];
				if(checkCanGo(ny,nx,board) && checkCanGo(nsy,nsx,board))
					if(cal(nx,ny,nsx,nsy,cv+1))
						pq.offer(new Info(ny,nx,i,cv+1));
			}
			
		}
		return answer;
	}
}
