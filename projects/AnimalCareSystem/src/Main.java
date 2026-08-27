import zoo.Zoo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Zoo zoo=new Zoo();
        int menu=0;

        while(menu!=7){
            System.out.println("=== 동물원 관리 시스템 ===");
            System.out.println("1. 동물 등록");
            System.out.println("2. 동물 목록 보기");
            System.out.println("3. 동물과 놀기");
            System.out.println("4. 먹이주기");
            System.out.println("5. 동물 상태 확인");
            System.out.println("6. 울음소리 듣기");
            System.out.println("7. 종료");
            System.out.print("메뉴를 선택하세요: ");
            menu=scanner.nextInt();
            scanner.nextLine();

            if(menu==7){
                System.out.println("프로그램 종료");
                break;
            }

            switch(menu){
                case 1: {
                    System.out.print("동물 이름을 입력하세요: ");
                    String name = scanner.nextLine();

                    System.out.print("동물 나이를 입력하세요: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("동물 종류를 입력하세요 (1.강아지 2.고양이): ");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    zoo.registerAnimal(type, name, age);
                    break;
                }
                case 2:{
                    zoo.printAnimals();
                    break;

                }
                case 3:{
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    break;
                }
                case 6:{
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }
}