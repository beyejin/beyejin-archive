class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int index =0;
        
        for(int s :section){
            if(s>index){
                answer++;
                index= m+s-1;
            }
        }
        return answer;
    }
}