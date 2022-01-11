import java.util.*;
import java.io.*;

class Solution {
    static Set<String> set;
    static Set<Character> menu,singleMenu[];
    static List<Character> allSingleMenu;
    static List<String> menuList;
    static char li[];
    static boolean flag[];
    static int num,menuNum,maxCnt;
    
    static void check(String str){
        int cnt=0;
        for(int i=0;i<num;i++){
            boolean hasAll = true;
            for(int j=0;j<str.length();j++){
                char c = str.charAt(j);
                if(!singleMenu[i].contains(c)){
                    hasAll=false;
                    break;
                }
            }
            if(hasAll) cnt++;
        }
        if(cnt>1){
            if(cnt>maxCnt){
                menuList.clear();
                menuList.add(str);
                maxCnt = cnt;
            }
            else if(cnt==maxCnt){
                menuList.add(str);
            }
        }
    }
    
    static void dfs(int selected, int cnt, int maxi){
        if(cnt==maxi){
            String str = new String(li);
            check(str);
            return;
        }
        for(int i=selected;i<menuNum;i++){
            if(flag[i]) continue;
            flag[i] = true;
            li[cnt] = allSingleMenu.get(i);
            dfs(i,cnt+1,maxi);
            flag[i]=false;
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        //초기화
        set = new TreeSet<>();
        menu = new TreeSet<>();
        num = orders.length;
        singleMenu = new HashSet[num];
        for(int i=0;i<num;i++)
            singleMenu[i] = new HashSet<>();
        allSingleMenu = new ArrayList<>();
        
        //모든 메뉴 종류, 단일 메뉴 추가
        for(int i=0;i<num;i++){
            for(int j=0;j<orders[i].length();j++){
                char c = orders[i].charAt(j);
                singleMenu[i].add(c);
                menu.add(c);
            }
        }
        menuNum = menu.size();
        flag = new boolean[menuNum];
        
        Iterator<Character> it1 = menu.iterator();
        while(it1.hasNext())
            allSingleMenu.add(it1.next());
        
        for(int i=0;i<course.length;i++){
            li = new char[course[i]];
            menuList = new ArrayList<>();
            maxCnt = 0;
            dfs(0,0,course[i]);
            for(String s: menuList)
                set.add(s);
        }
        
        Iterator<String> it = set.iterator();
        String[] answer = new String[set.size()];
        int idx=0;
        while(it.hasNext())
            answer[idx++]=it.next();
        return answer;
    }
}
