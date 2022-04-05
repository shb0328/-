import java.io.*;
import java.util.StringTokenizer;

public class Main {
	final static int MAX = 987654321;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int node = Integer.parseInt(br.readLine().trim());
		int edge = Integer.parseInt(br.readLine().trim());
		
		int cost[][] = new int[node+1][node+1];
		for(int i=1;i<=node;i++) {
			for(int j=i;j<=node;j++) {
				if(i==j) cost[i][j]=0;
				else {
					cost[i][j]=MAX;
					cost[j][i]=MAX;
				}
			}
		}
		for(int i=0;i<edge;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			cost[a][b] = Math.min(cost[a][b], c);
		}
		
		//Floyd
		for(int k=1;k<=node;k++) 
			for(int i=1;i<=node;i++) 
				for(int j=1;j<=node;j++) 
					cost[i][j] = Math.min(cost[i][j], cost[i][k]+cost[k][j]);
				
		for(int i=1;i<=node;i++) {
			for(int j=1;j<=node;j++) {
				int val = cost[i][j]==MAX ? 0 : cost[i][j];
				System.out.print(val+" ");
			}
			System.out.println();
		}
	}
}
