public enum OperatorType {
    ADD('+'),
    SUB('-'),
    MUL('*'),
    DIV('/');

    private final char symbol;

    OperatorType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static OperatorType fromChar(char ch) {
        for (OperatorType op : OperatorType.values()) {
            if (op.symbol == ch) {
                return op;
            }
        }
        throw new IllegalArgumentException("올바른 연산자를 입력해주세요.");
    }
}