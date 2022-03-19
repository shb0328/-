import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_bj_주사위쌓기 {
    private static int[] acrossIdx;
    private static int[][] dices;

    static {
        acrossIdx = new int[6];
        acrossIdx[0] = 5;        acrossIdx[5] = 0;
        acrossIdx[1] = 3;        acrossIdx[3] = 1;
        acrossIdx[2] = 4;        acrossIdx[4] = 2;
    }

    public static void main(String[] args) throws IOException {

        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dices = new int[n][6];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int diceIdx = 0; diceIdx < 6; diceIdx++){
                dices[i][diceIdx] = Integer.parseInt(st.nextToken());
            }
        }

        //solution
        int result = 0;
        for (int diceIdxOfFirst = 0; diceIdxOfFirst < 6; diceIdxOfFirst++) {
            int groundIdx = diceIdxOfFirst;
            int diceInterfaceValue = dices[0][acrossIdx[groundIdx]];
            int sum = findMaxValueFromDice(0, groundIdx);

            for (int diceNum = 1; diceNum < n; diceNum++) {
                for (int curDiceIdx = 0; curDiceIdx < 6; curDiceIdx++) {
                    if(dices[diceNum][curDiceIdx] == diceInterfaceValue) {
                        groundIdx = curDiceIdx;
                        diceInterfaceValue = dices[diceNum][acrossIdx[curDiceIdx]];
                        sum += findMaxValueFromDice(diceNum, groundIdx);
                        break;
                    }
                }
            }
            result = Integer.max(result, sum);
        }

        //output
        System.out.println(result);

        br.close();
    }

    private static int findMaxValueFromDice(int diceNum, int groundIdx) {
        ArrayList<Integer> candidateNumList = new ArrayList<>();
        for (int diceIdx = 0; diceIdx < 6; diceIdx++) {
            if(diceIdx != groundIdx && diceIdx != acrossIdx[groundIdx]) {
                candidateNumList.add(dices[diceNum][diceIdx]);
            }
        }
        return Collections.max(candidateNumList);
    }
}
