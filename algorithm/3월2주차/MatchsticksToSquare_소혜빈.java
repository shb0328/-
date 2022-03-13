import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(makesquare(new int[]{13, 11, 1, 8, 6, 7, 8, 8, 6, 7, 8, 9, 8}));
    }

    private static boolean[] ck;
    private static int one;

    public static boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length; //1~15
        if (n < 4) return false;
        ck = new boolean[n];

        int sum = Arrays.stream(matchsticks).sum(); //10^8 * 15 = 15억 < int range = 21억
        if (sum % 4 != 0) return false;

        one = sum / 4;
        Arrays.sort(matchsticks);
        if (one < matchsticks[n - 1]) return false;

        return recursive(matchsticks, n - 1, one, 0);
    }

    private static boolean recursive(int[] matchsticks, int idx, int need, int cnt) {
        if (cnt == 4) return true;
        if (idx < 0) return false;
        if (ck[idx]) return recursive(matchsticks, idx - 1, need, cnt);

        int cur = matchsticks[idx];

        if (cur > need) return recursive(matchsticks, idx - 1, need, cnt);

        ck[idx] = true;
        if (cur == need) {
            if (recursive(matchsticks, matchsticks.length - 1, one, cnt + 1)) return true;

        } else { // if(cur < need)
            if (recursive(matchsticks, idx - 1, need - cur, cnt)) return true;

        }
        ck[idx] = false;
        return recursive(matchsticks, idx - 1, need, cnt);
    }
}
