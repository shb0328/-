import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Star {
	int idx;
	double x;
	double y;

	Star(int idx, double x, double y) {
		this.idx = idx;
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> {
	int start;
	int end;
	double weight;

	Edge(int start, int end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		if (weight < o.weight) {
			return -1;
		}
		return 1;
	}
}

public class Main {

	public static int[] parent;
	public static ArrayList<Edge> edgeList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Star[] stars = new Star[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(i, x, y);
		}

		edgeList = new ArrayList<Edge>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				edgeList.add(new Edge(stars[i].idx, stars[j].idx,
						Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2))));
			}
		}
		Collections.sort(edgeList);

		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		
		double answer = 0;
		
		for(int i=0;i<edgeList.size();i++) {
			Edge edge = edgeList.get(i);
			
			if(find_root(edge.start) != find_root(edge.end)) {
				answer += edge.weight;
				union_root(edge.start, edge.end);
			}
		}
		System.out.println(answer);
		br.close();
	}

	public static int find_root(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find_root(parent[x]);
	}

	public static void union_root(int x, int y) {
		x = find_root(x);
		y = find_root(y);

		if (x != y)
			parent[y] = x;
	}

}
