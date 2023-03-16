import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String s;
        System.out.println("Введите строку символы через пробелы");
        //считывание из консоли строку для подачи в метод Drobi Evaluate
        while (scann.hasNext()) {
            try {
                s = scann.nextLine();
                if (s.equals("quit")) { //стоп слово quit, после которого программа прекращает работу
                    System.out.println("Program is finished");
                    break;}
                var total = Calculator.Evaluate(s);
                System.out.println(total);
            } catch (ArithmeticException e) { //Неккоректно введены данные пользователем
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка. Некорректное выражение");
                break;
            }

        }scann.close();
    }
}