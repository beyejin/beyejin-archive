class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        int j = 0;

        for (long i = x; j < n; i += x) {
            answer[j] = i;
            j++;
        }
        
        return answer;
    }
}