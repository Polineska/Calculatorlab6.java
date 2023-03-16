import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class Testunit {
    @Test
    public void SumIsIncorrectWhenSimpleExampleIsGiven() {
        assertEquals("Drobi = 5/6", Calculator.Evaluate("1/2 + 1/3").toString(), "Sum is done incorrectly");
    }

    @Test
    public void VychitanieIsIncorrectWhenSimpleExampleIsGiven() {
        assertEquals("Drobi = 1/6", Calculator.Evaluate("1/2 - 1/3").toString(), "Vychitanie is done incorrectly");
    }

    @Test
    public void UmnozhenieIsIncorrectWhenSimpleExampleIsGiven() {
        assertEquals("Drobi = 1/15", Calculator.Evaluate("1/5 * 1/3").toString(), "Umnozhenie is done incorrectly");
    }

    @Test
    public void DelenieIsIncorrectWhenSimpleExampleIsGiven() {
        assertEquals("Drobi = 3/5", Calculator.Evaluate("1/5 / 1/3").toString(), "Delenie is done incorrectly");
    }

    @Test
    public void DifferentIsIncorrectWhenDifficultExampleIsGiven() {
        assertEquals("Drobi = 15/224", Calculator.Evaluate("5/8 / 7/6 * 1/8").toString(), "Different is done incorrectly");
    }

    @Test
    public void DifferenttwoIsIncorrectWhenDifficultExampleIsGiven() {
        assertEquals("Drobi = -1/4", Calculator.Evaluate("-1/-3 * 3/4 - 1/2").toString(), "Differenttwo is done incorrectly");
    }

    @Test
    public void DifferentthreeIsIncorrectWhenDifficultExampleIsGiven() {
        assertEquals("Drobi = 1/4", Calculator.Evaluate("-1/-3 * 3/4 - 1/2 + 2/4").toString(), "Differenthree is done incorrectly");
    }

    @Test
    public void DifferentfourIsIncorrectWhenDifficultExampleIsGiven() {
        assertEquals("Drobi = -3/2", Calculator.Evaluate("( -1/3 * 3/4 - 1/2 ) * 2/1").toString(), "Differentfour is done incorrectly");
    }

    @Test
    public void IsInCorrectWhenNedopystimyiSimvol() {
        assertThrows(NumberFormatException.class, () -> {
            Calculator.Evaluate("1/2 % 1/6");
        }, " NedopystimyiSimvol error was expected");
    }

    @Test
    public void IsInCorrectWhenDrobi() {
        assertThrows(NumberFormatException.class, () -> {
            Calculator.Evaluate("a - 1/2");
        }, "IncorrectDrobi error was expected");
    }

    @Test

    @DisplayName("Delenie by Zero")
    public void whenDivisionByZeroShouldThrowArithmeticException() {
        assertThrows(ArithmeticException.class, () -> {
            Calculator.Evaluate("1/5 - 1/0");
        }, "ZeroShouldThrowArithmeticException error was expected");

    }
}