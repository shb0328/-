import java.io.*;
import java.util.*;
public class Main {
    public static class Line{
        int point_1;
        int point_2;
        float distance;
    }
    public static int N;
    public static Line line[];
    public static Float PointY[],PointX[];
    public static int par[];

    public static float getDist(int i, int j){
        float answer = 0;
        float y1,y2,x1,x2;
        y1 = PointY[i];
        x1 = PointX[i];
        y2 = PointY[j];
        x2 = PointX[j];

        return (float)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
    public static int find(int x){
        if(par[x]==x) return x;
        return par[x] = find(par[x]);
    }

    public static void merge(int a,int b){
        int x = find(a);
        int y = find(b);
        if(x!=y){
            par[x] = y;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        line = new Line[N*(N-1)/2];
        par = new int[N];
        PointY = new Float[N];
        PointX = new Float[N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            PointY[i] = Float.parseFloat(st.nextToken());
            PointX[i] = Float.parseFloat(st.nextToken());
            par[i] = i;
        }
        for(int i=0;i<N*(N-1)/2;i++){
            line[i] = new Line();
        }
        int idx = 0;
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                line[idx].point_1 = i;
                line[idx].point_2 = j;
                line[idx++].distance = getDist(i,j);
            }
        }
        Arrays.sort(line, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return Float.compare(o1.distance,o2.distance);
            }
        });
//        System.out.println("모든 라인 출력");
//        for(int i=0;i<idx;i++){
//            System.out.print(line[i].point_1 +" ~ " + line[i].point_2);
//            System.out.println(" = " + line[i].distance);
//        }
        float answer = 0;
        for(int i=0;i<N*(N-1)/2;i++){
            int s = line[i].point_1;
            int e = line[i].point_2;
            if(find(s)!=find(e)){
                merge(s,e);
                answer += line[i].distance;
            }
        }
        System.out.println(String.format("%.2f",answer));
    }

}
