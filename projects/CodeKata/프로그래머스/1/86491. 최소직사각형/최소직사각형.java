class Solution {
    public int solution(int[][] sizes) {
        
        int bigw =0;
        int bigh =0;
        
        for(int i=0; i<sizes.length; i++){
            int w = Math.max(sizes[i][0], sizes[i][1]);
            int h = Math.min(sizes[i][0], sizes[i][1]);
            
            bigw = Math.max(bigw, w);
            bigh = Math.max(bigh, h);
        }
    
        return bigw*bigh;
    }
}