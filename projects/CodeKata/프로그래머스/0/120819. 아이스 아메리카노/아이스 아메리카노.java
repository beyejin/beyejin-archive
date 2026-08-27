class Solution {
    public int[] solution(int money) {
        int[] answer = new int [2];
        answer[0] = (int) (money/5500);
        int num = answer[0];
        answer[1] = money-(num*5500);
        
        return answer;
    }
}