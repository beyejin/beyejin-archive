class Solution {
    public int solution(int[] number) {
        int answer = 0;
        for(int i=0; i<number.length;i++){
            for(int j=i+1; j<number.length;j++){
                int sum = number[i]+number[j];
                for(int k = j+1; k<number.length;k++){
                    if(number[k]== -sum){ 
                    answer++;
                    }
                }
                
            }  
    
        }
        
        return answer;
    }
}