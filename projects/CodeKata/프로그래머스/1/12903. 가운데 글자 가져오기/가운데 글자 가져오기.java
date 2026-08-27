class Solution {
    public String solution(String s) {
        String answer = "";
        int i=0;
        if(s.length()%2 == 0){
            i = s.length()/2-1;
            answer = s.substring(i,i+2);
        }
        else{
            i = (int) s.length()/2;
            answer = String.valueOf(s.charAt(i));
        }
        return answer;
    }
}