
import java.util.Scanner;

public class TrafficLightSwitch{
    public static void main(String[] args){

        Scanner scanner =new Scanner(System.in);

        System.out.println("신호등 프로그램 \n입력하시오");
        String lights=scanner.nextLine();

        switch(lights){
            case"초록불":
                System.out.print("건너시오");
                break;
            case "주황불":
                System.out.print("주의하세요");
                break;
            case "빨간불":
                System.out.print("멈추세요");
                break;
            default:
                System.out.print("잘못 입력했습니다");
        }

    }
}