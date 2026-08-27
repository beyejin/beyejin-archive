
class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        String substring ="";
        long ip = Long.parseLong(p);
        
        for(int i=0; i<=t.length()-p.length();i++){
            
            String s =t.substring(i,i+p.length());
            long is = Long.parseLong(s);
            
            if(is<=ip){
                answer++;
            }
        }
        return answer;
    }
}