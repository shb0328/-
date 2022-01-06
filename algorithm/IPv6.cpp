#include <iostream>
#include <string>
#include <vector>
#include <sstream>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	int part = 0;
	string str, s;
	vector<string> v, temp;
	cin >> str;

	istringstream ss(str);
	while (getline(ss, s, ':')) {
		int len = s.size();
		if (len) {
			part++;
			if (len < 4) {		//축약
				while (len < 4) {
					s = "0" + s;
					len++;
				}
			}
		}
		else if (len == 0)	s = "-1";
		v.push_back(s);
	}

	bool add = false;
	for (int i = 0; i < v.size(); i++) {
		s = v[i];
		if (s == "-1") {
			if (!add) {
				int repeat = 8 - part;
				while (repeat--) {
					temp.push_back("0000");
				}
				add = true;
			}
		}
		else temp.push_back(s);
	}
	for (int i = 0; i < 8; i++) {
		cout << temp[i];
		if (i < 7)
			cout << ":";
	}
	return 0;
}