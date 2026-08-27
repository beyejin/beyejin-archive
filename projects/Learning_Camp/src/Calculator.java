import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        double result = 0;

        System.out.println("=== Java 계산기 ===");
        String ans="y";

        while(ans.equals("y")){
            System.out.print("첫 번째 숫자: ");
            double first = scanner.nextInt();

            System.out.print("연산자를 입력하세요(+, -, *, /): ");
            String symbol= scanner.next();

            System.out.print("두 번째 숫자: ");
            double second = scanner.nextInt();


            if(symbol.equals("+")){
                calculator.sum(first, second);
            }
            else if(symbol.equals("-")){
                calculator.subtract(first, second);
            }
            else if(symbol.equals("*")){
                calculator.multiply(first, second);
            }
            else if(symbol.equals("/")){
                calculator.divide(first, second);
            }
            else{
                System.out.println("잘못된 연산자입니다.");
            }
            System.out.println("계속 계산하시겠습니까? (y/n):");
            ans= scanner.next();


        }
        System.out.println("계산기를 종료합니다.");
        scanner.close();

    }
    void sum(double value1, double value2) {
        double result = value1 + value2;
        System.out.println("결과: "+value1+"+"+value2+"="+result);
    }
    void subtract(double value1, double value2) {
        double result = value1 - value2;
        System.out.println("결과: "+value1+"-"+value2+"="+result);
    }
    void multiply(double value1, double value2){
        double result= value1 * value2;
        System.out.println("결과: "+value1+"*"+value2+"="+result);
    }

    void divide(double value1, double value2){
        if(value2==0){
            System.out.println("0으로 나눌 수 없습니다.");
            return;
        }

        double result = value1 / value2;
        System.out.println("결과: "+value1+"/"+value2+"="+result);
    }
}