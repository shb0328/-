import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Prgms_폰켓몬 {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        set.addAll(Arrays.stream(nums).boxed().collect(toList()));
        int answer = Integer.min(set.size(), nums.length/2);
        return answer;
    }
}
