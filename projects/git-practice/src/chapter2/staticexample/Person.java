package chapter2.staticexample;

public class Person {
    //static 변수
    static int population=0;

    //static 메서드
    static void printPopulation(){
        System.out.println("현제 인구 수: "+population);
    }
}
