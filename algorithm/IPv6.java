import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine().trim();
		String list[] = str.split(":");
		List<String> li = new ArrayList<>();
		List<String> temp = new ArrayList<>();
		int part = 0;
		
		for(String s : list) {
			int len = s.length();
			if(len>0) {
				part++;
				if(len<4) {
					while(len<4) {
						s = "0"+s;
						len++;
					}
				}
			}
			else s = "-1";
			li.add(s);
		}
		
		boolean add = false;
		for(String ss: li) {
			if(ss.equals("-1")) {
				if(add) continue;
				int repeat = 8-part;
				while(repeat>0) {
					temp.add("0000");
					repeat--;
				}
				add=true;
			}
			else temp.add(ss);
		}
        //마지막이 ::일때 못잡기 때문
		while(part<8) {
			temp.add("0000");
			part++;
		}
		for(int i=0;i<8;i++) {
			System.out.print(temp.get(i));
			if(i<7) System.out.print(":");
		}
	}
}