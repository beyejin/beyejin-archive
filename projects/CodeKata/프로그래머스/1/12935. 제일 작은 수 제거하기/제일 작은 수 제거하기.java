import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        
        if(arr.length == 1){
            return new int[]{-1};
        }
        
        int[] answer = new int [arr.length-1];
        int[] arrsort = arr.clone();
        Arrays.sort(arrsort);
        int j =0;
        

        for(int num : arr){
            if(num != arrsort[0]){
                answer[j++] = num;
            }
        }
        
        return answer;
    }
}