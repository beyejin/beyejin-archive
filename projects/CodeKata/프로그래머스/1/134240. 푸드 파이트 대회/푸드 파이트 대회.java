class Solution {
    public String solution(int[] food) {
        
        StringBuilder left = new StringBuilder();
        
        for (int i = 1; i < food.length; i++){
            for(int j = 0; j < food[i]/2; j++){
                left.append(i);
            }
        }
        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString()+"0"+right.toString();
    }
}