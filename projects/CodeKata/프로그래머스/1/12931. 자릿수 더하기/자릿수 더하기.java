import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;

        while (n > 0) {
            answer += n % 10;  // 1의 자리 더하기
            n /= 10;           // 다음 자리로 이동
        }

        return answer;
    }
}