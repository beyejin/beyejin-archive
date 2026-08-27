

public class RepetitiveSentence {
    public static void main(String[] args) {
        part1();
        part2();
        part3();
    }

    public static void part1() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
        }
    }

    public static void part2() {
        for (int i = 2; i <= 100; i += 2) {
            System.out.println(i);
        }
    }

    public static void part3() {
        for (int i = 2; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.println(i + "*" + j + "=" + (i * j));
            }
            System.out.println(); // 줄 바꿈
        }
    }   }
