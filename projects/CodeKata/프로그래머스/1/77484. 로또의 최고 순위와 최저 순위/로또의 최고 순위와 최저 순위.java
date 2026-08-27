class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zero = 0;   // 0 개수
        int match = 0;  // 실제 맞은 개수

        for (int lotto : lottos) {
            if (lotto == 0) {
                zero++;
            } else {
                for (int win : win_nums) {
                    if (lotto == win) {
                        match++;
                    }
                }
            }
        }

        int best = match + zero; // 최고로 맞을 수 있는 개수
        int worst = match;       // 최소로 맞은 개수

        int[] answer = new int[2];
        answer[0] = getRank(best);
        answer[1] = getRank(worst);

        return answer;
    }

    public int getRank(int count) {
        switch (count) {
            case 6:
                return 1;
            case 5:
                return 2;
            case 4:
                return 3;
            case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 6;
        }
    }
}