package animal;

public class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String getTypeName() {
        return "강아지";
    }
}