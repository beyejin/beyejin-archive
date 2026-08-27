class Solution {
    public int[] solution(long n) {
        String str = String.valueOf(n); 
        int[] answer = new int[str.length()];
        
        for(int i = 0; i < str.length(); i++){
            answer[i]=(int)(n % 10);
            n/=10;
        }
        return answer;
    }
}