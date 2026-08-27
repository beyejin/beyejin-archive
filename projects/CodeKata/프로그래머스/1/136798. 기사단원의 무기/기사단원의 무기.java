class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;

        for (int knight = 1; knight <= number; knight++) {
            int count = 0;

            for (int i = 1; i * i <= knight; i++) {
                if (knight % i == 0) {
                    if (i * i == knight) {
                        count += 1;
                    } else {
                        count += 2;
                    }
                }
            }

            if (count > limit) {
                answer += power;
            } else {
                answer += count;
            }
        }

        return answer;
    }
}
