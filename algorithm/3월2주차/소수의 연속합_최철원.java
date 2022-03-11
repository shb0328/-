import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static boolean isPrime[];
	final static int MAX = 4000001;
	static int num;
	
	static void prime() {
		for(int i=2;i<=num;i++)
			isPrime[i]=true;
		for(int i=2;i<=Math.sqrt((double)num);i++) {
			if(isPrime[i]) {
				for(int j=i+i;j<=num;j+=i)
					isPrime[j]=false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine().trim());
		isPrime = new boolean[MAX];
		int result = 0;
		
		//에라토스테네스의 체를 통해 소수 판별
		prime();
		
		//num이하 소수 담기
		List<Integer> li = new ArrayList<>();
		for(int i=2;i<=num;i++)
			if(isPrime[i] && i<=num) 
				li.add(i);

		int len = li.size();
		int l = 0, r = 0, sum=0;
		if(len>0) sum=li.get(0);
		
		while(l<=r && r<len) {
			if(sum<=num) {
				if(sum==num) result++;
				r++;
				if(r<len)	sum+=li.get(r);
			}
			else {
				if(l==r) {
					r++;
					if(r<len)	sum+=li.get(r);
				}
				else {
					sum-=li.get(l);
					l++;
				}
			}
		}
		System.out.println(result);
	}
}
