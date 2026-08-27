import java.util.ArrayList;
import java.util.stream.Collectors;

public class ArithmeticCalculator<T extends Number> {

    private ArrayList<Double> results = new ArrayList<>();

    public void setResults(double result) {
        results.add(result);
    }
    public ArrayList<Double> getResults() {
        return this.results;
    }

    public void removeResults(){
        if(!results.isEmpty()){
            results.remove(0);
        }
        else {
            System.out.println("삭제할 결과가 없습니다.");
        }
    }
    public ArrayList<Double> findGreaterResults(double value) {
        return results.stream()
                .filter(r -> r > value)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public double calculate(OperatorType type, T first, T second) {
        double a = first.doubleValue();
        double b = second.doubleValue();

        switch (type) {
            case ADD:
                return a+b;

            case SUB:
                return a-b;

            case MUL:
                return a*b;

            case DIV:
                if (b == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                return a/b;

            default:
                throw new IllegalArgumentException("올바른 연산자를 입력해주세요.");
        }
    }

}