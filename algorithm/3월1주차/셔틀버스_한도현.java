import java.util.*;
class Solution {
    public static int stringToIntTime(String time){
        int hour = Integer.parseInt(time.substring(0,2))*60;
        int minute = Integer.parseInt(time.substring(3,5));
        return hour+minute;
    }

    public static String intToStringTime(int time){
        String hour = String.valueOf(time/60);
        String minute = String.valueOf(time-(time/60) * 60);
        if(hour.length()==1){
            hour = "0" + hour;
        }
        if(minute.length()==1){
            minute = "0" + minute;
        }
        return hour + ":" + minute;
    }
    public static String solution(int n, int t, int m, String[] timetable) {
        int N = timetable.length;
        int [] timeTable = new int[N];
        for(int i=0;i<N;i++){
            timeTable[i] = stringToIntTime(timetable[i]);
        }

        Arrays.sort(timeTable);

        int idx=0;
        int candi = 0;
        int startTime = 540-t;
        int nn = n;
        exit:while(nn>0){
            startTime += t;
            int busSeat = m;
            for(int i=idx;i<N;i++){
                if(timeTable[i]<=startTime){
                    busSeat--;
                    idx++;
                    if(busSeat==0){
                        candi=timeTable[i]-1;
                        nn--;
                        break;
                    }
                }
            }
            if(busSeat!=0){
                candi = startTime;
                nn--;
            }
        }

        return intToStringTime(candi);
    }
}
