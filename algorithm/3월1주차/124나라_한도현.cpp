#include <string>
#include <iostream>
#include <sstream>
#include <memory.h>
using namespace std;
string int2str(int n)
{
	stringstream ss; // create a stringstream
	ss << n; // add n to the stream
	return ss.str();
}


string solution(int n) {
	int number = n;
	string answer = "";
	
	do {
		if (n % 3 == 0) {
			answer = "4" +answer;
			n = n - 3;
		}
		else if (n % 3 == 1)
			answer = "1" + answer;
		else if (n % 3 == 2)
			answer = "2" + answer;
		if (n / 3 == 0)break;
	} while (n = n / 3);

	return answer;
}
