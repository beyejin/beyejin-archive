import java.util.Arrays;
import java.util.Collections;
class Solution {
    public int solution(int k, int m, int[] score) {
        int result = 0;
        
        Arrays.sort(score);
       
        for(int i =score.length-m; i>=0; i -= m){
            result += score[i]*m;
        }
        return result;
    }
}