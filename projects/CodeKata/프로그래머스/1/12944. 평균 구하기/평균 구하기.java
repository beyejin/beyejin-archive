class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        int sum = 0;
        if(1 <= arr.length & arr.length<= 100){
            for(int i=0; i < arr.length; i++){
                sum += arr[i];
            }
            answer = (double)sum/arr.length;
        }
        else{
            System.out.println("잘못된 입력값");
        }
        return answer;
    }
}