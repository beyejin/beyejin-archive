class Solution {
    public int solution(String[] babbling) {
        String[] hasmal = {"aya", "ye", "woo", "ma"};
        int answer = 0;

        for (String word : babbling) {

            String prev = "";
            boolean possible = true;

            while (word.length() > 0) {
                boolean matched = false;

                for (int i = 0; i < hasmal.length; i++) {

                    if (word.startsWith(hasmal[i]) && !prev.equals(hasmal[i])) {
                        prev = hasmal[i];
                        word = word.substring(hasmal[i].length());
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                answer++;
            }
        }

        return answer;
    }
}