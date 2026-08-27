import java.util.Arrays;

class Solution {
    public String[] solution(String[] strings, int n) {
        int a = strings.length;

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a - i - 1; j++) {

                // n번째 문자가 더 크면 자리 교체
                if (strings[j].charAt(n) > strings[j + 1].charAt(n)) {
                    String change = strings[j];
                    strings[j] = strings[j + 1];
                    strings[j + 1] = change;
                }

                // n번째 문자가 같으면 사전순 비교
                else if (strings[j].charAt(n) == strings[j + 1].charAt(n)) {
                    if (strings[j].compareTo(strings[j + 1]) > 0) {
                        String change = strings[j];
                        strings[j] = strings[j + 1];
                        strings[j + 1] = change;
                    }
                }
            }
        }

        return strings;
    }
}