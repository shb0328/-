import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class 폰켓몬_소혜빈 {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        set.addAll(Arrays.stream(nums).boxed().collect(toList()));
        int answer = Integer.min(set.size(), nums.length/2);
        return answer;
    }
}
