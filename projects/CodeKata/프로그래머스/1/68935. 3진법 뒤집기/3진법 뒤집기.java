import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        List<Integer> nforthree = new ArrayList<>();
      
        while(n>0){
            nforthree.add(n % 3);
            n/=3;
        }
        
        for(int i = 0; i < nforthree.size(); i++){
           answer += nforthree.get(i)*Math.pow(3,nforthree.size() - 1-i);
        }
        return answer;
    }
}