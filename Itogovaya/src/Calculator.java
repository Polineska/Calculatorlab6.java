import java.util.*;

public class Calculator {
    public static Drobi Evaluate(String equation) {
        //Зависит от того, если ли пробел ДО и ПОСЛЕ оператора
        /*var parts = equation заменяем пробел на тильду
                .replace(" + ", "~+~")
                .replace(" - ", "~-~")
                .replace(" * ", "~*~")
                .replace(" / ", "~/~")
                .replace(" ( ", "~(~")
                .replace(" ) ", "~)~")
                .split("~"); сплит по тильде*/
        var tokens = ParseTokens(equation); //фарсит токены из строки
        return EvaluateTokens(tokens); //считает токены
    }

    private static ArrayList<Token> ParseTokens(String equation) { //задаем массив типа токен(токен включает в себя и число и знак)
        var parts = equation.split(" "); //сплит по пробелам
        var tokens = new ArrayList<Token>(parts.length);
        for (var part: parts) {
            switch (part) {
                /*case "+":
                    tokens.add(new Token(Operation.Add));
                    break;
                case "-":
                    tokens.add(new Token(Operation.Subtract));
                    break;
                case "*":
                    tokens.add(new Token(Operation.Multiply));
                    break;
                case "/":
                    tokens.add(new Token(Operation.Divide));
                    break;
                case "(":
                    tokens.add(new Token(Operation.OpenBracket));
                    break;
                case ")":
                    tokens.add(new Token(Operation.CloseBracket));
                default:*/
                //создание нового токена типа операция
                case "+" -> tokens.add(new Token(Operation.Add));
                case "-" -> tokens.add(new Token(Operation.Subtract));
                case "*" -> tokens.add(new Token(Operation.Multiply));
                case "/" -> tokens.add(new Token(Operation.Divide));
                case "(" -> tokens.add(new Token(Operation.OpenBracket));
                case ")" -> tokens.add(new Token(Operation.CloseBracket));
                default -> {
                    var fractionParts = part.split("/"); //дробь делим отдельно на знаменатель и числитель
                    var chislitel = Integer.parseInt(fractionParts[0]);
                    var znamenatel = Integer.parseInt(fractionParts[1]);
                    tokens.add(new Token(new Drobi(chislitel, znamenatel))); //создаем новую дробь и кладем в токен, где получается number из нашей дроби
                }
            }
        }
        return tokens; //коллекция токенов
    }

    private static Drobi EvaluateTokens(ArrayList<Token> tokens) { //Обратная польская нотация
        var operators = new Stack<Operation>(); //создаем стэк
        var reversePolishNotation = new ArrayDeque<Token>(); // создаем двойную очередь из токенов, так как модем работать с  двумя концами
        for (var token: tokens) {
            if (token.GetTokenType() == TokenType.Number) {
                reversePolishNotation.addLast(token); //если токен число, то добавляем в конец очереди
                continue;
            }
            switch (token.GetOperation()) { //если токен операция
                case Add, Subtract -> {
                    while (!operators.empty() && operators.peek() != Operation.OpenBracket) //если операторы не пусты, верхняя точка не равняется открытой скобки, так как у скобки открытой больше приоретет, то ставим его в очередь
                        reversePolishNotation.addLast(new Token(operators.pop()));
                    operators.push(token.GetOperation());
                }
                case OpenBracket -> operators.push(token.GetOperation()); //если открытая скобка, то кладем в операторы
                case CloseBracket -> {
                    while (operators.peek() != Operation.OpenBracket)
                        reversePolishNotation.addLast(new Token(operators.pop()));
                    operators.pop();
                }
                case Divide, Multiply -> {//если / или * достаем все у чего приоретет равняется или больше
                    while (!operators.empty() && operators.peek() != Operation.OpenBracket && operators.peek().GetPriority().equals(token.GetOperation().GetPriority()))
                        reversePolishNotation.addLast(new Token(operators.pop()));
                    operators.push(token.GetOperation()); //кладем в операторы
                }
            }
        }
        while (!operators.empty()) reversePolishNotation.addLast(new Token(operators.pop()));//оставшиеся операции кладем в очередь
        var solution = new Stack<Token>();//создаем стэк из токенов
        while (!reversePolishNotation.isEmpty()) { //пока есть токены в опн достаем их
            var cur = reversePolishNotation.pop(); //если число, то достаем
            if (cur.GetTokenType() == TokenType.Number) {
                solution.push(cur);
                continue;
            }
            var first = solution.pop();
            var second = solution.pop();//если не число
            Drobi res = switch (cur.GetOperation()) {
                case Add -> first.GetNumber().Sum(second.GetNumber());
                case Subtract -> second.GetNumber().Vychitanie(first.GetNumber());
                case Multiply -> first.GetNumber().Umnozhenie(second.GetNumber());
                case Divide -> second.GetNumber().Delenie(first.GetNumber());
                default -> null;
            };
            solution.push(new Token(res));//обратно кладем в стек
        }
        return solution.pop().GetNumber();//единственное число, и есть наш ответ
    }
}
