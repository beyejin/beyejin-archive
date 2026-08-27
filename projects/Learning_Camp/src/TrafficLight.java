import java.util.Scanner;

public class TrafficLight{
    public static void main(String[] args){
        Scanner scanner =new Scanner(System.in);
        System.out.println("신호등 프로그램 \n입력하시오");
        String lights=scanner.nextLine();
        if(lights.equals("초록불")){
            System.out.println("건너시오");
        }
        else if(lights.equals("노란불")){
            System.out.println("주의하시오");
        }
        else if(lights.equals("빨간불")){
            System.out.println("멈추시오");
        }
        else{
            System.out.println("잘못된 입력값");
        }
    }
}