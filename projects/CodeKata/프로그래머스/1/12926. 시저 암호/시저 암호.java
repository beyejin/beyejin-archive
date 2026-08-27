class Solution {
    public String solution(String s, int n) {
        String answer = "";
        
        for (int i=0; i<s.length();i++){
            char ch =s.charAt(i);
            if(ch == ' '){
                answer += " ";
            }
            // 대문자인 경우
            else if (ch >= 'A' && ch <= 'Z') {
                ch += n;
                if (ch > 'Z') {
                    ch -= 26;
                }
                answer += ch;
            }
            // 소문자인 경우
            else if (ch >= 'a' && ch <= 'z') {
                ch += n;
                if (ch > 'z') {
                    ch -= 26;
                }
                answer += ch;
            }
        }
        return answer;
    }
}