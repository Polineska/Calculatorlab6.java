import java.util.HashMap;
import java.util.HashSet;

public class Drobi {
    private int Chislitel;// не должны давать пользоваетлям заменять числа, они приватны
    private int Znamenatel;

    public Drobi(int chislitel, int znamenatel) { //задаем класс конструкторами
        if (znamenatel== 0) throw new ArithmeticException("Знаменатель не может быть равен нулю");// проверка на ноль
        Chislitel = chislitel;
        Znamenatel = znamenatel;
        ToLowestTerms();//сокращение дроби*/
    }
    public Drobi() {
        this.Chislitel = 1;
        this.Znamenatel = 1;
    }

    public int getChislitel() { return Chislitel; } //позволяет получить значение, записать туда значений
    public int getZnamenatel() { return Znamenatel; }

    public Drobi Sum(Drobi other) {
        Drobi result = Sum(this,other);
        return result;
    }

    public static Drobi Sum(Drobi first, Drobi second) {
        var newZnamenatel = Mathh.nok(first.Znamenatel, second.Znamenatel);// создание нового экземпляра класса// функция НОК
        var newChislitel = first.Chislitel * (newZnamenatel / first.Znamenatel) + second.Chislitel * (newZnamenatel / second.Znamenatel);
        Drobi r = new Drobi(newChislitel, newZnamenatel);
        return r;
    }

    public Drobi Vychitanie(Drobi other) {
        return Drobi.Vychitanie(this, other);// сама себя вызывает дробь
    }

    public static Drobi Vychitanie(Drobi first, Drobi second) {
        var newZnamenatel = Mathh.nok(first.Znamenatel, second.Znamenatel);// создание нового экземпляра класса// функция НОК
        var newChislitel = first.Chislitel * (newZnamenatel / first.Znamenatel) - second.Chislitel * (newZnamenatel / second.Znamenatel);
        Drobi r = new Drobi(newChislitel, newZnamenatel);
        return r;
    }

    public Drobi Umnozhenie(Drobi other) {
        return Drobi.Umnozhenie(this, other);
    }
    public static Drobi Umnozhenie(Drobi first, Drobi second) {
        return new Drobi(first.Chislitel * second.Chislitel, first.Znamenatel * second.Znamenatel);
    }

    public Drobi Delenie(Drobi other) {
        return Drobi.Delenie(this, other);
    }
    public static Drobi Delenie(Drobi first, Drobi second) {
        if (second.Chislitel == 0) throw new ArithmeticException("На ноль делить нельзя");
        return new Drobi(first.Chislitel * second.Znamenatel, second.Chislitel * first.Znamenatel);
    }


    private HashMap<Integer, Integer> GetPrimeDrobi(int ch) {// целое число ,получение из числа его простые множители 120=2*2*2*3*5
        var res  = new HashMap<Integer, Integer>();
        if (ch < 0) { //число меньше 0=> один из множителей -1
            res.put(-1, 1);
            ch *= -1;
        }
        int i = 2;
        while (i <= ch) {
            while (ch % i == 0) {
                res.put(i, res.getOrDefault(i, 0) + 1);//получить если по такому числу, нет числа, то возращается дефолтное значение,в моем случае 0
                ch /= i;
            }
            i++;
        }
        return res;
    }

    private void ToLowestTerms() { //сокращение дроби
        var chislitelTerms = GetPrimeDrobi(Chislitel);//коллекция простых чисел для числителя,таблица для числителя
        var znamenatelTerms = GetPrimeDrobi(Znamenatel);//коллекция простых чисел для знаменателя,таблица для знаменателя
        var commonKeys = new HashSet<>(chislitelTerms.keySet());// пересечения множеств ключей //таблица для числителей и знаменталей
        commonKeys.retainAll(znamenatelTerms.keySet());//поиск пересечений ключей для знаменателя/
        for (var key: commonKeys) {
            int minCount = Math.min(chislitelTerms.get(key), znamenatelTerms.get(key));//для каждого из пересечений ищем минимум напрмер, 8/16=2*2*2/2*2*2*2 сокращаем на 3 двойки
            for (int i = 0; i < minCount; i++) {
                Chislitel /= key;
                Znamenatel /= key;
            }
        }
    }


    @Override// переопределить метода to string для вывода в консоль
    public String toString() {
        return "Drobi = " + Chislitel + "/" + Znamenatel;

    }
}

class Mathh {
    static int nok(int a, int b) {
        return a * b / nod(a, b);// формула Эвклида поиск нок
    }

    static int nod(int a, int b) {
        while (a > 0 && b > 0)
            if (a > b) a %= b;
            else b %= a;
        return a + b;
    }
}
