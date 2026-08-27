package animal;

public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String getTypeName() {
        return "고양이";
    }
}