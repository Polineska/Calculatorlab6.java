public class Token { //создаем свой тип с операциями и числами
    private final TokenType Type;
    private final Drobi Number;
    private final Operation Operation;

    public Token(Operation operation) { //конструктор операция
        Type = TokenType.Operator;
        Operation = operation;
        Number = null;
    }
    public Token(Drobi number) { //конструктор число
        Type = TokenType.Number;
        Number = number;
        Operation = null;
    }
    public TokenType GetTokenType() {
        return Type;
    }
    public Drobi GetNumber() {
        if (Number == null) {
            throw new RuntimeException("This token is an operator");
        }
        return Number;
    }
    public Operation GetOperation() {
        if (Operation == null) {
            throw new RuntimeException("This token is a number");
        }
        return Operation;
    }
}

enum TokenType {
    Number,
    Operator
}