package chapter2.clazz;

public class Person {
    String name;
    int age;
    String adress;

    Person(String name,int age){
        this.name=name;
        this.age=age;
    }

    int sum(int value1,int value2){
        int result=value1+value2;
        return result;

    }

//게터 연습
    String getName(){
        return this.name;
    }
    //세터 연습

    void setAdress(String adress){
        this.adress=adress;
    }
}
