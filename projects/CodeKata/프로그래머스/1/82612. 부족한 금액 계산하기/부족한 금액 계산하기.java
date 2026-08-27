class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;
        long totalprice = 0;
      
        
        for(int i =1; i<=count; i++){
            totalprice += (long) price * i;
        }
        
        if(money >= totalprice){
            answer = 0;
        }
        else{
            answer = totalprice - money;
        }

        return answer;
    }
}