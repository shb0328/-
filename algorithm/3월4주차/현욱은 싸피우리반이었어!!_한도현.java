import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        Stack<Integer> st = new Stack<Integer>();
        int plusPoint[] = new int[N];
        int tempPoint = 0;
        int answer = 0;
        for(int i=0;i<str.length();i++){
            char cur = str.charAt(i);
            if(cur=='('){
                st.add(i);
                if(tempPoint!=0){
                    plusPoint[i] = tempPoint;
                    tempPoint = 0;
                }
            }else{
                if(!st.empty()){
                    tempPoint = (i-st.peek()+1) + plusPoint[st.peek()];
                    answer = Math.max(answer,tempPoint);
                    st.pop();
                }else{
                    tempPoint = 0;
                }
            }
        }
        System.out.println(answer);
    }
}
