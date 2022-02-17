class Solution {
    public String convert(String s, int num) {
        if(num==1) return s;
        
        String answer = "";
        String[] str= new String[num];
        for(int i=0;i<num;i++)
            str[i]="";
        int mod = 2*num-2;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            int idx = i%mod;
            if(idx>=num) idx = 2*(num-1)-idx;
            str[idx]+=c;
        }
        for(int i=0;i<num;i++)
            answer += str[i];
        return answer;
    }
}
