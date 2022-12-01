import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zad2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пароль: ");
        String parol = in.nextLine();
        System.out.println(parol);
        int k = parol.length();
        if (k < 8){
            System.out.println("Пароль ненадежен. Повторите ввод");
        }
        else {
            Pattern pattern = Pattern.compile("([[0-9]|[a-zA-Z]|\\_]+)");
            Matcher matcher = pattern.matcher(parol);
            if (matcher.find()) {
                Pattern pattern1 = Pattern.compile("([A-Z]+)");
                Matcher matcher1 = pattern1.matcher(parol);
                if (matcher1.find()) {
                    Pattern pattern2 = Pattern.compile("([0-9]+)");
                    Matcher matcher2 = pattern2.matcher(parol);
                    if (matcher2.find()) {
                        Pattern pattern3 = Pattern.compile("([a-z]+)");
                        Matcher matcher3 = pattern3.matcher(parol);
                        if (matcher3.find()) {
                            System.out.println("Пароль надежен");
                        }
                        else{
                            System.out.println("Пароль ненадежен. Повторите ввод");
                        }
                    }
                    else{
                        System.out.println("Пароль ненадежен. Повторите ввод");
                    }
                }
                else{
                    System.out.println("Пароль ненадежен. Повторите ввод");
                }
            } else {
                System.out.println("Пароль ненадежен. Повторите ввод");
            }
        }
    }
}