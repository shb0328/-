package baekjoon;
import java.util.*;
import java.io.*;
public class baekjoon_2116_주사위쌓기 {
    public static int N;
    public static int dice[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dice = new int[N][6];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<6;j++){
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int matching[] = new int[6];
        matching[0] = 5; matching[5]=0;
        matching[1] = 3; matching[3] = 1;
        matching[2] = 4; matching[4] = 2;
        int answer = 0;
        for(int i=0;i<6;i++){
            int candiAns = 0;
            int bottom = i;
            int top = matching[bottom];
            int bottomValue = dice[0][i];
            int topValue = dice[0][top];
            int maxValue = 0;
            for(int j=0;j<6;j++){
                if(dice[0][j] == bottomValue || dice[0][j] == topValue) continue;
                maxValue = Math.max(dice[0][j],maxValue);
            }
            candiAns += maxValue;
            for(int j=1;j<N;j++){
                for(int k=0;k<6;k++){
                    if(dice[j][k]==topValue){
                        bottom = k;
                        bottomValue = dice[j][k];
                        top = matching[k];
                        topValue = dice[j][top];
                        int temp = 0;
                        for(int l=0;l<6;l++){
                            if(dice[j][l] == bottomValue || dice[j][l] == topValue) continue;
                            temp = Math.max(temp,dice[j][l]);
                        }
                        candiAns += temp;
                        break;
                    }
                }
            }
            answer = Math.max(answer,candiAns);
        }
        System.out.println(answer);
    }
}
