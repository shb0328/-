//[TreeSet과 List를 이용]

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

//[TreeSet,  2차 배열을 이용]
import java.util.*;
import java.io.*;

class Solution {
    static Set<String> set;
    static Set<Character> allMenu;
    static List<String> temp;
    static List<Character> li;
    static boolean arr[][];
    static boolean check[];
    static int orderNum,maxCnt;
    
    static void addOrRemove(String str, int full){
        int cnt=0;
        for(int i=0;i<orderNum;i++){
            int idx=0;
            while(idx<full){
                char c = str.charAt(idx);
                if(!arr[i][c-'A']) break;
                idx++;
            }
            if(idx==full) cnt++;
        }
        if(cnt>1){
            if(cnt>maxCnt){
                maxCnt = cnt;
                temp.clear();
                temp.add(str);
            }
            else if(cnt==maxCnt)
                temp.add(str);
        }
    }
    
    static void selNmenu(int lastPick, int cnt, int full, String str){
        if(cnt==full){
            addOrRemove(str,full);
            return;
        }
        for(int i=lastPick;i<li.size();i++){
            if(check[i]) continue;
            char c = li.get(i);
            check[i]=true;
            selNmenu(i,cnt+1,full,str+c);
            check[i]=false;
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        //초기화
        set = new TreeSet<>();
        allMenu = new TreeSet<>();
        orderNum = orders.length;
        arr = new boolean[orderNum][26];
        li = new ArrayList<>();
        
        for(int i=0;i<orderNum;i++){
            for(int j=0;j<orders[i].length();j++){
                char c = orders[i].charAt(j);
                int idx = c-'A';
                arr[i][idx]=true;
                allMenu.add(c);
            }
        }
        Iterator<Character> it1 = allMenu.iterator();
        while(it1.hasNext())
            li.add(it1.next());
        check = new boolean[li.size()];
        
        for(int N=0;N<course.length;N++){
            temp = new ArrayList<>();
            maxCnt = 0;
            selNmenu(0,0,course[N],"");
            for(String s: temp)
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
