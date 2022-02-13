#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    int N =3;
    int M = 3;
    bool suc = false;
   
        for(int i=N;;i++){
            for(int j=M;j<=i;j++){
                if(i*2+2*j == brown+4){
                    if((i-2)*(j-2)==yellow){
                        answer.push_back(i);
                        answer.push_back(j);
                        return answer;
                    }
                }
            }
        }
}
