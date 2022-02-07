import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int row,col;
        int sum = brown/2 + 2;
        int sub = (int)Math.sqrt(sum*sum-4*(brown + yellow));
        row = (sum+sub)/2;
        col = (sum-sub)/2;
        answer[0] = row;
        answer[1] = col;
        return answer;
    }
}
