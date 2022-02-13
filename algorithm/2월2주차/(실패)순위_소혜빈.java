import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Prgms_순위 {

    public static void main(String[] args) {
        Prgms_순위 instance = new Prgms_순위();
        Solution sol = instance.getSolution();
        int answer = sol.solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}});
        System.out.println(answer);
    }

    public Solution getSolution() {
        return new Solution();
    }

    class Solution {
        public int solution(int n, int[][] results) {
            boolean[] ck = new boolean[n + 1];
            Person[] players = new Person[n + 1];
            for (int i = 1; i <= n; i++) players[i] = new Person(i);
            players[0] = new Person(n); // 정보 전달 목적

            for (int i = 0; i < results.length; i++) {
                int winner = results[i][0];
                int loser = results[i][1];
                players[winner].Iwin.add(loser);
                players[loser].Ilose.add(winner);
            }

            Queue<Person[]> q = new ArrayDeque<>();
            q.offer(players);
            while (!q.isEmpty()) {
                Person[] p = q.poll();
                int m = p[0].num;
                for (int i = 1; i < p.length; i++) {
                    if (ck[p[i].num]) continue;
                    int sizeOfIwin = p[i].Iwin.size();
                    int sizeOfIlose = p[i].Ilose.size();
                    if (sizeOfIwin + sizeOfIlose >= m - 1) {
                        p[i].grade = sizeOfIlose + 1;
//                        p[i].Iwin.toArray(players);
                        Person[] losers = new Person[sizeOfIwin + 1];
                        Person[] winners = new Person[sizeOfIlose + 1];
                        for (int idx = 1; idx < losers.length; idx++) losers[idx] = players[p[i].Iwin.get(idx-1)];
                        for (int idx = 1; idx < winners.length; idx++) winners[idx] = players[p[i].Ilose.get(idx-1)];
                        losers[0] = new Person(m - p[i].grade);
                        winners[0] = new Person(p[i].grade);
                        q.offer(losers);
                        q.offer(winners);
                        ck[i] = true;
                    }
                }
            }


            int answer = 0;
            for (int i = 1; i < ck.length; i++) {
                if(ck[i]) answer++;
            }
            return answer;
        }
    }

    class Person {
        int num;
        int grade;
        List<Integer> Iwin;
        List<Integer> Ilose;

        public Person(int num) {
            this.num = num;
            this.grade = 0;
            Iwin = new ArrayList<>();
            Ilose = new ArrayList<>();
        }
    }
}
