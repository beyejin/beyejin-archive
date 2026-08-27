import java.util.Random;

public class Nickname {

    private String[] firstList = {"기초충", "먹인", "재미있는"};
    private String[] secondList = {"도전적인", "노란색의", "바람같은"};
    private String[] thirdList = {"돌고래", "개발자", "호랑이"};

    public String createRandomNickname() {
        Random random = new Random();

        String first = firstList[random.nextInt(firstList.length)];
        String second = secondList[random.nextInt(secondList.length)];
        String third = thirdList[random.nextInt(thirdList.length)];

        return first + " " + second + " " + third;
    }

    public static void main(String[] args) {
        Nickname nickname = new Nickname();
        String myNickname = nickname.createRandomNickname();
        System.out.println(myNickname);
    }
}