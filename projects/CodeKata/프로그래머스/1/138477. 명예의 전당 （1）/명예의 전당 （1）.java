import java.util.Arrays;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        int[] owner = new int[k];
        int count = 0;

        for (int i = 0; i < score.length; i++) {
            if (count < k) {
                owner[count] = score[i];
                count++;

                Arrays.sort(owner, 0, count);
                answer[i] = owner[0];
            } else {
                Arrays.sort(owner);

                if (score[i] > owner[0]) {
                    owner[0] = score[i];
                    Arrays.sort(owner);
                }

                answer[i] = owner[0];
            }
        }

        return answer;
    }
}