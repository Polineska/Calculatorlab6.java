public enum Operation {
    Add(1),
    Subtract(1),
    Multiply(2),
    Divide(2),
    OpenBracket(3),
    CloseBracket(3);
    private final Integer Priority;

    Operation(int priority) {
        Priority = priority;
    }

    public Integer GetPriority() { return Priority; }
}