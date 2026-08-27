import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        
        int[] supo1 = {1, 2, 3, 4, 5};
        int[] supo2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] supo3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int supo1t = 0;
        int supo2t = 0;
        int supo3t = 0;
        
        for (int i=0; i<answers.length; i++){
            if(supo1[i%supo1.length] == answers[i]){supo1t++;}
            if(supo2[i%supo2.length] == answers[i]){supo2t++;}
            if(supo3[i%supo3.length] == answers[i]){supo3t++;}
        }
        int winer = Math.max(supo1t,Math.max(supo2t,supo3t));
        
        ArrayList<Integer> result = new ArrayList<>();
        
        if(supo1t==winer){result.add(1);}
        if(supo2t==winer){result.add(2);}
        if(supo3t==winer){result.add(3);}
        
        int[] answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}