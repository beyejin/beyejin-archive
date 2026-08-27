package chapter2.clazz;

public class Main {
    public static void main(String[] args) {
        Person personA= new Person("yejin",23);
        Person personB= new Person("steve",20);


        String name =personB.getName();
        System.out.println("이름이 뭐야 "+name);

        int result1= personA.sum(3,4);
        System.out.println("합 결과는 "+result1);

        System.out.println("personA의 주소 "+personA.adress);
        personA.setAdress(personA.adress);
        personA.setAdress("서울");
        System.out.println("personA의 주소는 "+personA.adress);
    }


}
