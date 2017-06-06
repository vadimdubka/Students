package Testing;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

//Вы можете определить набор тестовых данных, которые будут подаваться на вход тестирующего класса.

//@RunWith — аннотация говорит о том, что мы будем запускать наш тест с помощью «запускателя» Parametrized.
@RunWith(Parameterized.class)
public class TestCalc2 {

// Обратите внимание на данные в скобках - первый два - числа, которые складываем/вычитаем. Вторые - это их сумма и разность

    // @Parameters — эта аннотация объявляет метод, который будет вызываться для получения данных.
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {5, 3, 8, 2},
                {15, 10, 25, 5},
                {5, 10, 15, -5}
        });
    }

    int x1, x2, sum, sub;

    public TestCalc2(int x1, int x2, int sum, int sub) {
        this.x1 = x1;
        this.x2 = x2;
        this.sum = sum;
        this.sub = sub;
    }

    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new CalcListener());
        core.run(TestCalc2.class);
    }

//    I solved it by renaming one of the test methods to start with "test..." (JUnit3 style) and then all tests are found. I renamed it back to what it was previously, and it still works.
    @Test
    public void testget2SumTest() {
        Calc c = new Calc();
        assertEquals(sum, c.getSum(x1, x2));
    }

    @Test
    public void testget2SubtractionTest() {
        Calc c = new Calc();
        assertEquals(sub, c.getSubtraction(x1, x2));
    }


}

class CalcListener extends RunListener {

    @Override
    public void testStarted(Description desc) {
        System.out.println("Started:" + desc.getDisplayName());
    }

    @Override
    public void testFinished(Description desc) {
        System.out.println("Finished:" + desc.getDisplayName());
    }

    @Override
    public void testFailure(Failure fail) {
        System.out.println("Failed:" + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
    }
}
