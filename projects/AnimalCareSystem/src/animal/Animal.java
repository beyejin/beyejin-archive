package animal;

public class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    // 자식이 오버라이딩해서 "강아지/고양이" 반환
    public String getTypeName() {
        return "동물";
    }
}