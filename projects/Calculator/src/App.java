import java.util.ArrayList;
import java.util.Scanner;

public class App {

    // 정수이면 소수점 없이, 실수이면 그대로 출력
    public static String formatNumber(double num) {
        return (num % 1 == 0) ? String.valueOf((int) num) : String.valueOf(num);
    }

    // 숫자 리스트를 출력용 문자열 리스트로 변환
    public static ArrayList<String> formatList(ArrayList<Double> list) {
        ArrayList<String> formatted = new ArrayList<>();
        for (double num : list) {
            formatted.add(formatNumber(num));
        }
        return formatted;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArithmeticCalculator<Double> cal = new ArithmeticCalculator<>();

        System.out.println("---계산기 프로그램---");
        System.out.println("계산 후 exit 입력 시 종료되고, remove 입력 시 가장 오래된 값이 삭제됩니다.");

        while (true) {
            double first;
            double second;

            // 첫 번째 수 입력
            while (true) {
                System.out.print("첫번째 수를 입력하시오: ");
                String input1 = sc.next();

                try {
                    first = Double.parseDouble(input1);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("올바르지 않은 입력값입니다.");
                }
            }

            // 두 번째 수 입력
            while (true) {
                System.out.print("두번째 수를 입력하시오: ");
                String input2 = sc.next();

                try {
                    second = Double.parseDouble(input2);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("올바르지 않은 입력값입니다.");
                }
            }

            // 연산자 입력
            OperatorType operator;
            while (true) {
                System.out.print("연산자를 입력해주세요(+, -, *, /): ");
                String opInput = sc.next();

                if (opInput.length() != 1) {
                    System.out.println("올바른 연산자를 입력해주세요.");
                    continue;
                }

                try {
                    operator = OperatorType.fromChar(opInput.charAt(0));
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            // 계산 및 결과 저장
            try {
                double result = cal.calculate(operator, first, second);
                cal.setResults(result);

                String f = formatNumber(first);
                String s = formatNumber(second);
                String r = formatNumber(result);

                System.out.println(f + " " + operator.getSymbol() + " " + s + " = " + r);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
                continue;
            }

            ArrayList<Double> results = cal.getResults();
            System.out.println("목록: " + formatList(results));

            // 결과가 2개 이상일 때만 기준값 조회
            if (results.size() >= 2) {
                while (true) {
                    System.out.print("기준값을 입력하세요: ");
                    String valueInput = sc.next();

                    try {
                        double value = Double.parseDouble(valueInput);
                        ArrayList<Double> filtered = cal.findGreaterResults(value);
                        System.out.println(formatNumber(value) + "보다 큰 결과들: " + formatList(filtered));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("올바른 숫자를 입력해주세요.");
                    }
                }
            }

            // 다음 동작 선택
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료, remove 입력 시 가장 오래된 값 삭제, 아무거나 누르면 계속): ");
            String endInput = sc.next();

            if (endInput.equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                sc.close();
                return;
            } else if (endInput.equalsIgnoreCase("remove")) {
                cal.removeResults();
                System.out.println("삭제 완료 " + formatList(cal.getResults()));
            }
        }
    }
}