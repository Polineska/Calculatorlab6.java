import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input data: ");
        String dan = in.nextLine();
        System.out.println(dan);
        Pattern pattern = Pattern.compile("[0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9][0-9][0-9]");
        Matcher matcher = pattern.matcher(dan);
        if (matcher.find()) {
            Pattern pattern22 = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(01|0[3-9]|1[0-2])\\/(19[0-9][0-9]|[2-9][0-9][0-9][0-9])|(0[1-9]|1[0-9]|2[0-9])\\/(02)\\/(19[0-9][0-9]|[2-9]\\d{3})");
            Matcher matcher22 = pattern22.matcher(dan);
            if (matcher22.find()) {
                System.out.println("Введенное выражение является датой");
            }
            else {
                System.out.println("Введенное выражение не является датой");
            }
        }
        else {
            System.out.println("Введенное выражение не соответствует заданному формату даты dd/mm/yyyy");
        }
    }
}