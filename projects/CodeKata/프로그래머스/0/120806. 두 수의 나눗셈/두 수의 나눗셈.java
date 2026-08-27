class Solution {
    public int solution(int num1, int num2) {
        
        int answer = 0;
        double doublenum1 = num1;
        double doublenum2 = num2;  
        
        answer = (int) (doublenum1 / doublenum2 * 1000);   
            
        return answer;
    }
}