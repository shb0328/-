import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class 나는친구가적다_소혜빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        https://www.baeldung.com/java-string-to-stream
//        String str = br.readLine().chars().filter(v -> !(v >= '0' && v <= '9')).mapToObj(c -> (char) c).map(c -> c.toString()).collect(Collectors.joining());
        String str = br.readLine().replaceAll("[0-9]", "");
//        System.out.println(str);
        int answer = kmp(str, br.readLine());
        System.out.println(answer);
        br.close();
    }

    public static int[] getPi(String pattern) {
        char[] p = pattern.toCharArray();
        int pl = pattern.length();
        int j = 0;
        int[] pi = new int[pl];
        for (int i = 1; i < pl; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = pi[j - 1];
            }
            if (p[i] == p[j]) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    public static int kmp(String str, String pattern) {
        List<Integer> answer = new LinkedList<>();
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int sl = str.length();
        int pl = pattern.length();
        int j = 0;
        int[] pi = getPi(pattern);
        for (int i = 0; i < sl; i++) {
            while (j > 0 && s[i] != p[j]) {
                j = pi[j - 1];
            }
            if(s[i] == p[j]) {
                if (j == pl - 1) {
                    return 1;
                } else {
                    j++;
                }
            }
        }
        return 0;
    }
}
