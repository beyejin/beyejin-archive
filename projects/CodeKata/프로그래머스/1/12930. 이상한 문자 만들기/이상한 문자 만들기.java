class Solution {
    public String solution(String s) {
        String answer = "";
        
        int index =0;
        
        for(int i=0; i<s.length(); i++){
            char a = s.charAt(i);
            if(a == ' '){
                answer += " ";
                index = 0;
            }
            else{
                if(index % 2 == 0){
                    answer += Character.toUpperCase(a);
                }
                else{
                    answer += Character.toLowerCase(a);
                }
                index++;
            }
            
        }
        
        return answer;
    }
}