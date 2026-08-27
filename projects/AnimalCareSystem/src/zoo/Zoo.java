package zoo;
import animal.Animal;
import animal.Dog;
import animal.Cat;

import java.util.ArrayList;

public class Zoo {
    private ArrayList<Animal> animals = new ArrayList<>();

    // ✅ Main의 case 1에서 호출하는 메서드
    public void registerAnimal(int type, String name, int age) {
        Animal a;

        if (type == 1) {
            a = new Dog(name, age);
        } else if (type == 2) {
            a = new Cat(name, age);
        } else {
            System.out.println("잘못된 종류입니다. (1: 강아지, 2: 고양이)");
            return;
        }

        animals.add(a);
        System.out.println(a.getName() + "(" + a.getTypeName() + ", " + a.getAge() + "살)가 등록되었습니다.");
    }

    // (메뉴 2번에서 쓰면 좋음)
    public void printAnimals() {
        if (animals.size() == 0) {
            System.out.println("등록된 동물이 없습니다.");
            return;
        }

        System.out.println("=== 동물 목록 ===");
        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            System.out.println((i + 1) + ". " + a.getName() + " (" + a.getTypeName() + ", " + a.getAge() + "살)");
        }
    }

    public int size() {
        return animals.size();
    }

    // 다음 기능들(놀기/먹이/상태/울음)은 다음 단계에서 채울 거라,
    // Main에서 미리 호출해도 컴파일되게 "빈 메서드"만 만들어둘 수도 있어.
}