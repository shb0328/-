//3,6번 TC 오답
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

    static class Info2 implements Comparable<Info2>{
        int y,x;
        public Info2(int y, int x){
            this.x=x;
            this.y=y;
        }
        @Override
        public int compareTo(Info2 o){
            if(this.y==o.y) return Integer.compare(this.x,o.x);
            return Integer.compare(this.y,o.y);
        }
    };
    
	final static int dx[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	final static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	final static int MAX = 101;
    static int arr[][];
	static HashMap<Long,Integer> map;
	static int bigX, smallX, bigY, smallY, row, col;
	static int canRot[];
    static PriorityQueue<Info> pq;
    
    static long getVal(int x1, int y1, int x2, int y2){
        PriorityQueue<Info2> pq = new PriorityQueue<>();
        pq.offer(new Info2(y1,x1));
        pq.offer(new Info2(y2,x2));
        long first,sec;
        Info2 ii = pq.poll();
        first = ii.x*MAX+ii.y;
        ii = pq.poll();
        sec = ii.x*MAX+ii.y;
		long val = first * MAX * MAX + sec;
        return val;
    }
    
	static boolean cal(int y1, int x1, int y2, int x2, int sumVal) {
        long val = getVal(x1,y1,x2,y2);
		if (map.containsKey(val)){
            int originVal = map.get(val);
			if(originVal>sumVal) {
				map.put(val, sumVal);
				return true;
			}
			else return false;
		}
		else {
			map.put(val, sumVal);
			return true;
		}
	}
	
    static void calRot(int y, int x, int dir) {
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
			if(checkCanGo(ny,nx)) {
				nd = (nd+1)%8;
				ny = y+ dy[nd];
				nx = x+ dx[nd];
				if(checkCanGo(ny,nx)) {
					canRot[nd]=Math.min(canRot[nd],val);
				}
				else break;
			}
			else break;
		}
		cnt=3;
		nd=dir;
        val=0;
		//반시계
		while(cnt-->0) {
            val++;
			nd = (nd+7)%8;
			ny = y+ dy[nd];
			nx = x+ dx[nd];
			if(checkCanGo(ny,nx)) {
				nd = (nd+7)%8;
				ny = y+ dy[nd];
				nx = x+ dx[nd];
				if(checkCanGo(ny,nx)) {
					canRot[nd]=Math.min(canRot[nd],val);
				}
				else break;
			}
			else break;
		}
		
	}

	static boolean checkCanGo(int y, int x) {
		if(x >= 0 && y >= 0 && x < col && y < row && arr[y][x] == 0) return true;
		return false;
	}
	
    static void move4Dir(int y1, int x1, int y2, int x2, int val, int dir){
        for (int i = 0; i < 8; i += 2) {
            int nay = y1 + dy[i];
            int nax = x1 + dx[i];
            int nby = y2 + dy[i];
            int nbx = x2 + dx[i];
            int cnt = 1;
            while (true) {
                if (checkCanGo(nay, nax) && checkCanGo(nby, nbx)) {     //이동은 가능
                    //이미 존재하는지 확인
                    int nv = val + cnt;
                    if (cal(nay, nax, nby, nbx, nv)) {
                        pq.offer(new Info(nay,nax,dir,val+cnt));
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
    
	public static int solution(int[][] board) {
		int answer = 0;
		row = board.length;
		col = board[0].length;
        //초기화
        arr = new int[row][col];
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                arr[i][j]=board[i][j];
		map = new HashMap<>();
		pq = new PriorityQueue<>();
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
            //System.out.println("cy,cx,sy,sx,cv: " + cy + " " + cx + " " + sy + " " + sx + " " + cv);
			if ((cx == col - 1 && cy == row - 1) || (sx == col - 1 && sy == row - 1)) {
				answer = cv;
				break;
			}
			calRot(cy, cx, cd);
			for (int i = 0; i < 8; i += 2) {
				if (i == cd || canRot[i]==10)	continue;
				int nsx = cx + dx[i];
				int nsy = cy + dy[i];
				if (checkCanGo(nsy,nsx)) {
					if (cal(nsy, nsx, cy, cx,cv+canRot[i]))
						pq.offer(new Info(cy, cx, i, cv + canRot[i]));
				}
			}
			calRot(sy, sx, (cd+4)%8);
			for (int i = 0; i < 8; i += 2) {
				int nd = (i + 4) % 8;
				if (nd == cd || canRot[i]==10) continue;
				int nx = sx + dx[i];
				int ny = sy + dy[i];
				if (checkCanGo(ny,nx)) {
					if (cal(ny, nx, sy, sx,cv+canRot[i]))
						pq.offer(new Info(sy, sx, i, cv + canRot[i]));
				}
			}
			
			//그대로 이동
			/*for(int i=0;i<8;i+=2) {
                if(i%4!=cd%4) continue;
				int nx = cx+dx[i];
				int ny = cy+dy[i];
				int nsx = sx+dx[i];
				int nsy = sy+dy[i];
				if(checkCanGo(ny,nx,board) && checkCanGo(nsy,nsx,board))
					if(cal(nx,ny,nsx,nsy,cv+1))
						pq.offer(new Info(ny,nx,i,cv+1));
			}*/
			move4Dir(cy,cx,sy,sx,cv,cd);
		}
		return answer;
	}
}
